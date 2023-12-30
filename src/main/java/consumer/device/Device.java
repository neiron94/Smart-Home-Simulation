package consumer.device;

import consumer.AddVisitor;
import consumer.ConsumeVisitor;
import consumer.Consumer;
import event.BreakEvent;
import place.Room;
import smarthome.Simulation;


public abstract class Device implements Consumer {
    public static final double TICK_DURATION = 1.0 * 1 / 60; // 1 minute in hours TODO - move to Constants
    protected final int deviceID;
    protected  final DeviceType type;
    protected Room room;    // TODO - what if null?
    protected final Manual manual;
    protected DeviceStatus status;
    protected int durability;       // percent
    protected int maxDurability;    // percent

    public Device(DeviceType type, int id, Room startRoom) {
        this.type = type;
        deviceID = id;
        this.room = startRoom;

        manual = new Manual(type);
        status = type.getStartStatus();
        durability = maxDurability = 100;   // TODO - means 100%

        accept(new AddVisitor());   // add to consumption map in supply system
    }

    public void routine() { // Is called every tick
        accept(new ConsumeVisitor());   // TODO - send one common ConsumeVisitor to routine(), so shouldn't create new one for each device?
    }

    public void repair() {
        // TODO - implement
    }

    public void brake() {
        new BreakEvent(this, this.room).throwEvent();
    }

    // TODO - maybe remove some getters or setters

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public void setMaxDurability(int maxDurability) {
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
