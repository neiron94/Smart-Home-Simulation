package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.HumiditySensor;
import consumer.device.sensored.sensor.SmokeSensor;
import consumer.device.sensored.sensor.TemperatureSensor;
import place.Room;

public class FireAlarm extends Alarm<SmokeSensor> {
    public FireAlarm(int id, Room startRoom) {
        super(DeviceType.FIRE_ALARM, id, startRoom);
        this.sensor = new SmokeSensor();
        this.sensor.attachDevice(this);
    }
}
