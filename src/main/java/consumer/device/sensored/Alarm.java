package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.sensored.sensor.EventSensor;
import event.AlertEvent;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;

public abstract class Alarm<T extends EventSensor> extends EventDevice<T> {
    protected boolean isAlerting;

    public Alarm(DeviceType alarmType, int id, Room startRoom) {
        super(alarmType, id, startRoom);
        isAlerting = false;
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.ALARM);
    }

    @Override
    public void react() {
        alert();
    }

    private void alert() {
        // Throws event if not alerting
        if (!isAlerting) {
            isAlerting = true;
            status = DeviceStatus.ON;
            new AlertEvent(this, this.room).throwEvent();
        }
    }

    public void stop() {
        isAlerting = false;
        restoreStatus();
    }

    public boolean isAlerting() {
        return isAlerting;
    }
}