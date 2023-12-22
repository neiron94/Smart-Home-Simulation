package consumer.device;

import consumer.Consumer;
import place.Room;
import utils.Percent;

public abstract class Device implements Consumer {
    private static int id = 1;

    protected final int deviceID;
    protected final DeviceStatus START_STATUS;
    protected final Manual MANUAL;
    protected DeviceStatus status;
    protected Percent durability;
    protected Percent maxDurability;
    protected Room room;    // TODO - what if null?

    public Device(DeviceStatus startStatus, Manual manual, Room startRoom) {
        deviceID = id++;
        status = START_STATUS = startStatus;
        MANUAL = manual;
        durability = maxDurability = new Percent(100);   // TODO - means 100%
        room = startRoom;
        this.add(); // Consumer's method
    }

    public void routine() { // Is called every tick
        this.consume(); // TODO - something can be added
    }

    public void repair() {
        // TODO - implement
    }

    public void brake() {
        // TODO - implement. Throws event
    }

    // TODO - maybe remove some getters or setters

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public Percent getDurability() {
        return durability;
    }

    public void setDurability(Percent durability) {
        this.durability = durability;
    }

    public Percent getMaxDurability() {
        return maxDurability;
    }

    public void setMaxDurability(Percent maxDurability) {
        this.maxDurability = maxDurability;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    // TODO - test if getClass returns child class
    @Override
    public String toString() {
        return getClass().getSimpleName() + "_" + deviceID;
    }
}
