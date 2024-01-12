package consumer.device;

import consumer.*;
import event.BreakEvent;
import place.Room;
import utils.Constants;
import utils.HelpFunctions;
import utils.Prototype;
import utils.exceptions.*;

/**
 * Represents device that can be interacted with.
 * Each device consumes some resource (electricity, gas or water).
 */
public abstract class Device implements Consumer, Prototype {
    protected final int id;
    protected final DeviceType type;
    protected Room room;
    protected final Manual manual;
    protected DeviceStatus status;
    protected long durability;  // number of ticks before break
    private long maxDurability;
    protected boolean isFunctional; // is not totally broken
    protected boolean isOccupied;

    /**
     * The only one Device constructor. Automatically creates manual for this device,
     * sets status to start status and durability depending on the device type.
     * Also adds this device to Simulation and SupplySystem via Visitor design pattern.
     *
     * @param type Device type, which is connected to class of the concrete device.
     * @param id ID of the device. All IDs are unique for one device type.
     * @param startRoom Room in which device is located at the start of the program.
     */
    public Device(DeviceType type, int id, Room startRoom) {
        this.type = type;
        this.id = id;
        this.room = startRoom;

        manual = new Manual(type);
        status = type.getStartStatus();
        durability = maxDurability = countDurability(type);

        isFunctional = true;
        isOccupied = false;

        accept(new AddVisitor());   // add to simulation and consumption map in supply system
    }

    //--------- Main public functions ----------//

    /**
     * Routine which should be performed by each device every tick
     * of simulation. Is called from Simulation class in simulate() function.
     * Shouldn't be called by anyone else.
     * @return Can be ignored, is used for inner logic.
     */
    public boolean routine() {
        if (status == DeviceStatus.ON) decreaseDurability(Constants.Degradation.USE_DEGRADATION);
        else decreaseDurability(Constants.Degradation.TIME_DEGRADATION);

        if (durability <= 0 || status == DeviceStatus.OFF)    return false;
        accept(new EventVisitor()); // disaster event can happen
        accept(new ConsumeVisitor());
        return true;
    }

    /**
     * Decreases device's durability, can cause device's breakdown.
     * @param degradation Amount of durability decreasing.
     */
    public void decreaseDurability(long degradation) {
        if (durability > 0) {
            setDurability(durability - degradation);
            if (durability == 0) brake();
        }
    }

    @Override
    public abstract Device copy();

    //---------- API for human -----------//

    /**
     * Repair broken device, called as an event solving. Device's maxDurability is
     * decreased after each repairing.
     * @throws NotRepairableDeviceException Device's maxDurability equals 0.
     */
    public void repair() throws NotRepairableDeviceException {
        status = type.getStartStatus();
        maxDurability -= maxDurability * 0.05;
        durability = maxDurability;
        if (maxDurability <= 0) {
            isFunctional = false;
            throw new NotRepairableDeviceException("Device " + this + " can't be repaired.");
        }
    }

    /**
     * Turn on a device (set status to its startStatus).
     * @throws DeviceIsBrokenException Device is broken.
     * @throws ResourceNotAvailableException There is no electricity/gas/water available in the room.
     */
    public void turnOn() throws DeviceIsBrokenException, ResourceNotAvailableException {
        checkBeforeTurnOn();
        status = type.getStartStatus();
    }

    /**
     * Torn off a device (set status to OFF).
     */
    public void turnOff() {
        this.status = DeviceStatus.OFF;
        this.isOccupied = false;
    }

    //------------- Help functions -------------//

    protected void restoreStatus() {
        status = type.getStartStatus();
        isOccupied = false;
    }

    protected void checkDeviceInStartStatus() throws WrongDeviceStatusException {
        if (status != type.getStartStatus()) throw new WrongDeviceStatusException(this + " should be " + type.getStartStatus());
    }

    protected void checkDeviceNotOccupied() throws DeviceIsOccupiedException {
        if (isOccupied) throw new DeviceIsOccupiedException(this + " is occupied.");
    }

    protected void checkBeforeTurnOn() throws DeviceIsBrokenException, ResourceNotAvailableException {
        if (durability <= 0)
            throw new DeviceIsBrokenException(this + " is broken.");
        if (this instanceof ElectricityConsumer && !room.isActiveElectricity())
            throw new ResourceNotAvailableException("Electricity in " + room + " is not available.");
        if (this instanceof WaterConsumer && !room.isActiveWater())
            throw new ResourceNotAvailableException("Water in " + room + " is not available.");
        if (this instanceof GasConsumer && !room.isActiveGas())
            throw new ResourceNotAvailableException("Gas in " + room + " is not available.");
    }

    private void brake() {
        turnOff();
        new BreakEvent(this, this.room).throwEvent();
    }

    private long countDurability(DeviceType type) {
        long hours = type.getGuarantee().getMonths() * 30 * 24L * 4 / 3; // TODO Constants
        return (long)(hours / Constants.Time.TICK_DURATION);
    }

    @Override
    public String toString() {
        return String.format("%s_%d", type.getName(), id);
    }

    //---------- Getters and Setters ----------//

    public DeviceStatus getStatus() {
        return status;
    }

    private void setDurability(long durability) {
        this.durability = HelpFunctions.adjustToRange(durability, 0, maxDurability);
    }

    public long getDurability() {
        return durability;
    }

    public long getMaxDurability() {
        return maxDurability;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public DeviceType getType() {
        return type;
    }

    public Manual getManual() {
        return manual;
    }

    public boolean isFunctional() {
        return isFunctional;
    }

    public void setFunctional(boolean functional) {
        isFunctional = functional;
    }

    public int getId() {
        return id;
    }
}
