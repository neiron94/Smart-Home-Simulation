package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.FloodSensor;
import consumer.device.sensored.sensor.SmokeSensor;
import place.Room;

public class FloodAlarm extends Alarm<FloodSensor> {
    public FloodAlarm(int id, Room startRoom) {
        super(DeviceType.FLOOD_ALARM, id, startRoom);
        this.sensor = new FloodSensor();
        this.sensor.attachDevice(this);
    }
}
