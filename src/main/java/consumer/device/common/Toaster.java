package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;

public class Toaster extends Device implements ElectricityConsumer {
    private boolean isToastInside;
    private ToastType program;
    private Time timeToReady;   // TODO -Time?

    public Toaster(int id, Room startRoom) {
        super(DeviceType.TOASTER, id, startRoom);
    }

    @Override
    public void consumeElectricity() {
        // TODO - implement, depends on ToastType
    }

    @Override
    public void fire() {
        // TODO - implement
    }

    @Override
    public void routine() {
        super.routine();
        // TODO - doAction(): timeToReady--, if == 0 -> set STANDBY
    }

    public void makeToast(ToastType program) {
        // TODO - check durability
        this.program = program;
        // TODO - smth else?
    }

    public void putToast() {
        isToastInside = true;
    }

    public void takeToast() {
        isToastInside = false;
    }

    // TODO - maybe delete some getters or setters

    public boolean isToastInside() {
        return isToastInside;
    }

    public void setToastInside(boolean toastInside) {
        isToastInside = toastInside;
    }

    public ToastType getProgram() {
        return program;
    }

    public void setProgram(ToastType program) {
        this.program = program;
    }

    public Time getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(Time timeToReady) {
        this.timeToReady = timeToReady;
    }
}
