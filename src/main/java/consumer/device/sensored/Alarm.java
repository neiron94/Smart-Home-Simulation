package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import consumer.device.sensored.sensor.EventSensor;
import event.AlertEvent;
import place.Room;

public abstract class Alarm<T extends EventSensor> extends EventDevice<T> {
    protected boolean isAlerting;

    public Alarm(DeviceType alarmType, int id, Room startRoom, T sensor) {
        super(alarmType, id, startRoom, sensor);
        isAlerting = false;
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