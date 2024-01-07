package consumer.device;

import consumer.*;
import event.BreakEvent;
import place.Room;
import utils.Constants;
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.WrongDeviceStatusException;
import utils.exceptions.NotRepairableDeviceException;
import utils.exceptions.ResourceNotAvailableException;


public abstract class Device implements Consumer, Comparable<Device> {
    protected final int deviceID;
    protected  final DeviceType type;
    protected Room room;    // TODO - what if null?
    protected final Manual manual;
    protected DeviceStatus status;
    protected long durability;  // number of ticks before break
    private long maxDurability;

    public Device(DeviceType type, int id, Room startRoom) {
        this.type = type;
        deviceID = id;
        this.room = startRoom;

        manual = new Manual(type);
        status = type.getStartStatus();
        durability = maxDurability = countDurability(type);

        accept(new AddVisitor());   // add to consumption map in supply system
    }

    //--------- Main public functions ----------//

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public boolean routine() { // Is called every tick  // TODO - implement random brake, fire, flood, leak
        if (status == DeviceStatus.ON) decreaseDurability(Constants.USE_DEGRADATION);
        else decreaseDurability(Constants.TIME_DEGRADATION);

        if (durability <= 0 || status == DeviceStatus.OFF)    return false;
        accept(new ConsumeVisitor());
        return true;
    }

    //---------- API for human -----------//

    public void repair() throws NotRepairableDeviceException {
        status = type.getStartStatus();
        maxDurability -= maxDurability * 0.05;
        durability = maxDurability;
        if (maxDurability <= 0)
            throw new NotRepairableDeviceException("Device " + this + " can't be repaired.");
    }

    //------------- Help functions -------------//

    protected void checkDeviceOn() throws WrongDeviceStatusException {  // TODO - move to help functions?
        if (status != DeviceStatus.ON)
            throw new WrongDeviceStatusException(this + " should be on.");
    }

    protected void checkDeviceStandby() throws WrongDeviceStatusException {
        if (status != DeviceStatus.STANDBY)
            throw new WrongDeviceStatusException(this + " should be standby.");
    }

    private void brake() {
        durability = 0;
        new BreakEvent(this, this.room).throwEvent();
    }

    private long countDurability(DeviceType type) {
        long hours = type.getGuarantee().getDays() * 24L * 4 / 3;
        return (long)(hours / Constants.TICK_DURATION);
    }

    protected void checkBeforeStatusSet() throws DeviceIsBrokenException, ResourceNotAvailableException {
        if (durability <= 0)
            throw new DeviceIsBrokenException(this + " is broken.");
        if (room != null) {
            if (this instanceof ElectricityConsumer && !room.isActiveElectricity())    // TODO - remove room != null?
                throw new ResourceNotAvailableException("Electricity in " + room + " is not available.");
            if (this instanceof WaterConsumer && !room.isActiveWater())    // TODO - remove room != null?
                throw new ResourceNotAvailableException("Water in " + room + " is not available.");
            if (this instanceof GasConsumer && !room.isActiveGas())    // TODO - remove room != null?
                throw new ResourceNotAvailableException("Gas in " + room + " is not available.");
        }
    }

    public void decreaseDurability(long degradation) {
        this.durability -= degradation;
        if (durability <= 0) {
            brake();
        }
    }

    @Override
    public String toString() {
        return String.format("%s_%d", type.getName(), deviceID);
    }

    @Override
    public int compareTo(Device device) {
        return Integer.compare(this.deviceID, device.deviceID);
    }

    //---------- Getters and Setters ----------//

    public DeviceStatus getStatus() {
        return status;
    }

    protected void setOn() throws DeviceIsBrokenException, ResourceNotAvailableException {
        if (status == DeviceStatus.ON)  return;
        checkBeforeStatusSet();
        this.status = DeviceStatus.ON;
    }

    protected void setStandby() throws DeviceIsBrokenException, ResourceNotAvailableException {
        if (status == DeviceStatus.STANDBY) return;
        checkBeforeStatusSet();
        this.status = DeviceStatus.STANDBY;
    }

    protected void setOff() {
        this.status = DeviceStatus.OFF;
    }

    public long getDurability() {
        return durability;
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
}
