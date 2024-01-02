package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceType;
import place.Room;
import utils.HelpFunctions;

import java.time.LocalTime;

public class Toaster extends Device implements ElectricityConsumer {
    private boolean isToastInside;
    private ToastType program;
    private LocalTime timeToReady;

    public Toaster(int id, Room startRoom) {
        super(DeviceType.TOASTER, id, startRoom);
    }

    @Override
    public double consumeElectricity() {
        return program != null ? HelpFunctions.countElectricityConsumption(status, program.getPower()) : 0;
    }

    @Override
    public boolean routine() {
        super.routine();
        // TODO - doAction(): timeToReady--, if == 0 -> set STANDBY
        return true;
    }

    public void makeToast(ToastType program) {
        // TODO - check durability
        this.timeToReady = program.getCookTime();
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

    public LocalTime getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(LocalTime timeToReady) {
        this.timeToReady = timeToReady;
    }
}
