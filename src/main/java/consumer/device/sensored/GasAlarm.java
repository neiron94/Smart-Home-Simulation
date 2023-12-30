package consumer.device.sensored;

import consumer.device.DeviceType;
import consumer.device.sensored.sensor.GasSensor;
import place.Room;

public class GasAlarm extends Alarm<GasSensor> {
    public GasAlarm(int id, Room startRoom, GasSensor sensor) {
        super(DeviceType.GAS_ALARM, id, startRoom, sensor);
    }

    @Override
    public int consumeElectricity() {
        // TODO
        return 0;
    }
}
