package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import consumer.device.sensored.sensor.TemperatureSensor;
import place.Room;
import utils.Percent;

    public class AC extends ParameterDevice<TemperatureSensor> {
        public AC(int id, Room startRoom, TemperatureSensor sensor) {
            super(DeviceType.AC, id, startRoom, sensor);
        }

        @Override
        public void consumeElectricity() {
            // TODO - implement, depends on power
        }

        @Override
        public void fire() {
            // TODO - implement
        }

        @Override
        public void react(Number parameter) {
            // TODO - increment and decrement by 5?
            if (room.getTemperature() > room.getControlPanel().getTemperature())
                power.increment();
            else if (room.getTemperature() < room.getControlPanel().getTemperature())
                power.decrement();
        }

        @Override
        public void setStatus(DeviceStatus status) {
            if (status != DeviceStatus.STANDBY)
                this.status = status;
        }
    }

