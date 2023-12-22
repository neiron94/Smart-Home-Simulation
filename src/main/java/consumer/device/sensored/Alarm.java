package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.Manual;
import consumer.device.sensored.sensor.EventSensor;
import place.Room;

public abstract class Alarm<T extends EventSensor> extends EventDevice<T> {
    private boolean isAlerting;

    public Alarm(Manual manual, Room startRoom, T sensor) {
        super(DeviceStatus.STANDBY, manual, startRoom, sensor);
        isAlerting = false;
    }

    public abstract void alert();   // Throws event if not alerting

    public boolean isAlerting() {
        return isAlerting;
    }

    public void setAlerting(boolean alerting) {
        isAlerting = alerting;
    }
}
