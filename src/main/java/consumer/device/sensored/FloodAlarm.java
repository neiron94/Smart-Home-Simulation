package consumer.device.sensored;

import consumer.device.sensored.sensor.FloodSensor;
import consumer.device.sensored.sensor.GasSensor;

public class FloodAlarm extends Alarm<FloodSensor> {
    public FloodAlarm(Room startRoom, FloodSensor sensor) {
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
