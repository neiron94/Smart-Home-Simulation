package consumer.device;

import consumer.AddVisitor;
import consumer.ConsumeVisitor;
import consumer.Consumer;
import event.BreakEvent;
import place.Room;
import utils.Constants;
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.WrongDeviceStatusException;
import utils.exceptions.NotRepairableDeviceException;
import utils.exceptions.ResourceNotAvailableException;


public abstract class Device implements Consumer {
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

    public boolean routine() { // Is called every tick
        decreaseDurability(Constants.TIME_DEGRADATION);
        if (durability <= 0 || status == DeviceStatus.OFF)    return false;
        accept(new ConsumeVisitor());
        return true;
    }

    //---------- API for human -----------//

    public void repair() throws NotRepairableDeviceException {
        maxDurability -= maxDurability * 0.05;
        durability = maxDurability;
        if (maxDurability <= 0)
            throw new NotRepairableDeviceException("Device " + this + " can't be repaired.");
    }

    //------------- Help functions -------------//

    protected void checkDeviceOn() throws WrongDeviceStatusException {
        if (status != DeviceStatus.ON)
            throw new WrongDeviceStatusException(this + " should be on.");
    }

    protected void checkDeviceStandby() throws WrongDeviceStatusException {
        if (status != DeviceStatus.STANDBY)
            throw new WrongDeviceStatusException(this + " should be standby.");
    }

    private void brake() {
        new BreakEvent(this, this.room).throwEvent();
    }

    private long countDurability(DeviceType type) {
        long hours = type.getGuarantee().getDays() * 24L * 4 / 3;
        return (long)(hours / Constants.TICK_DURATION);
    }

    private void checkBeforeStatusSet() throws DeviceIsBrokenException, ResourceNotAvailableException {
        if (durability <= 0)
            throw new DeviceIsBrokenException(this + " is broken.");
        if (room != null && !room.isActiveElectricity())    // TODO - remove room != null?
            throw new ResourceNotAvailableException("Electricity in " + room + " is not available.");
    }

    @Override
    public String toString() {
        return String.format("%s_%d", type.getName(), deviceID);
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

    public void decreaseDurability(long degradation) {
        this.durability -= degradation;
        if (durability <= 0) {
            durability = 0;
            brake();
        }
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
