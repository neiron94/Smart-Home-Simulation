package consumer.device.sensored;

import consumer.GasConsumer;
import consumer.device.Manual;
import consumer.device.sensored.sensor.EventSensor;
import consumer.device.sensored.sensor.GasSensor;

public class GasAlarm extends Alarm<GasSensor> {
    public GasAlarm(Room startRoom, GasSensor sensor) {
        super(null, startRoom, sensor);  // TODO - manual should be taken from somewhere
    }

    @Override
    public void consumeElectricity() {
        // TODO
    }

    @Override
    public void fire() {
        // TODO
    }

    @Override
    public void alert() {
        // TODO - throws event
    }

    @Override
    public void react() {
        alert();
        // TODO - smth else?
    }
}
