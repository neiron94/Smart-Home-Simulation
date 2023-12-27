package consumer.device.sensored;

import consumer.device.sensored.sensor.GasSensor;
import consumer.device.sensored.sensor.SmokeSensor;
import event.AlertEvent;
import place.Room;

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
}
