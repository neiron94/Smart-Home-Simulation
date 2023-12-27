package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.GasSensor;
import consumer.device.sensored.sensor.SmokeSensor;
import event.AlertEvent;
import place.Room;

public class FireAlarm extends Alarm<SmokeSensor> {
    public FireAlarm(int id, Room startRoom, SmokeSensor sensor) {
        super(DeviceType.FIRE_ALARM, id, startRoom, sensor);
    }

    @Override
    public void consumeElectricity() {
        // TODO
    }

    @Override
    public void fire() {
        // TODO
    }
}