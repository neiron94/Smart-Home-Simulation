package consumer.device;

import consumer.Consumer;
import place.Room;
import smarthome.Simulation;
import utils.Percent;

public abstract class Device implements Consumer {
    protected final int deviceID;
    protected  final DeviceType type;
    protected Room room;    // TODO - what if null?
    protected final Manual manual;
    protected DeviceStatus status;
    protected Percent durability;
    protected Percent maxDurability;

    public Device(DeviceType type, int id, Room startRoom) {
        this.type = type;
        deviceID = id;
        this.room = startRoom;

        manual = new Manual(type);
        status = type.getStartStatus();
        durability = maxDurability = new Percent(100);   // TODO - means 100%

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

    public DeviceType getType() {
        return type;
    }

    public Manual getManual() {
        return manual;
    }

    @Override
    public String toString() {
        return String.format("%s_%d", type.getName(), deviceID);
    }
}
