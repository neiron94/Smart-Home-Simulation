package consumer.device.sensored;

import consumer.device.sensored.sensor.GasSensor;
import consumer.device.sensored.sensor.SmokeSensor;

public class FireAlarm extends Alarm<SmokeSensor> {
    public FireAlarm(Room startRoom, SmokeSensor sensor) {
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
