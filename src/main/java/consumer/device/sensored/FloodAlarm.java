package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.FloodSensor;
import consumer.device.sensored.sensor.GasSensor;
import place.Room;

public class FloodAlarm extends Alarm<FloodSensor> {
    public FloodAlarm(int id, Room startRoom, FloodSensor sensor) {
        super(DeviceType.FLOOD_ALARM, id, startRoom, sensor);
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
