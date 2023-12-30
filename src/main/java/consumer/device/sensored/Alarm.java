package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import consumer.device.sensored.sensor.EventSensor;
import event.AlertEvent;
import place.Room;
import utils.HelpFunctions;

public abstract class Alarm<T extends EventSensor> extends EventDevice<T> {
    protected boolean isAlerting;

    public Alarm(DeviceType alarmType, int id, Room startRoom) {
        super(alarmType, id, startRoom);
        isAlerting = false;
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, 1);    // TODO - change 1 for Constant
    }

    @Override
    public void react() {
        alert();
    }

    public void alert() {
        // Throws event if not alerting
        if (!isAlerting) {
            isAlerting = true;
            new AlertEvent(this, this.room).throwEvent();
        }
    }

    public boolean isAlerting() {
        return isAlerting;
    }

    public void setAlerting(boolean alerting) {
        isAlerting = alerting;
    }
}