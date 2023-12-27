package consumer.device.sensored;

import consumer.GasConsumer;
import consumer.device.DeviceType;
import consumer.device.Manual;
import consumer.device.sensored.sensor.EventSensor;
import consumer.device.sensored.sensor.GasSensor;
import place.Room;

public class GasAlarm extends Alarm<GasSensor> {
    public GasAlarm(int id, Room startRoom, GasSensor sensor) {
        super(DeviceType.GAS_ALARM, id, startRoom, sensor);
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
