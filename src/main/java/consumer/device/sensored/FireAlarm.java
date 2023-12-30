package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.SmokeSensor;
import place.Room;

public class FireAlarm extends Alarm<SmokeSensor> {
    public FireAlarm(int id, Room startRoom, SmokeSensor sensor) {
        super(DeviceType.FIRE_ALARM, id, startRoom, sensor);
    }

    @Override
    public int consumeElectricity() {
        // TODO
        return 0;
    }
}
