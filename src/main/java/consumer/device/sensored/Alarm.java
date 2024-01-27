/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.sensored;

import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.sensored.sensor.EventSensor;
import event.AlertEvent;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;

/**
 * Alarm for alerting about disaster events.
 * @param <T> Type of sensor, which works with events.
 */
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

    /**
     * React on event by alerting (throw AlertEvent).
     */
    @Override
    public void react() {
        alert();
    }

    /**
     * Stop alerting.
     */
    public void stop() {
        isAlerting = false;
        restoreStatus();
    }

    private void alert() {
        // Throws event if not alerting
        if (!isAlerting) {
            isAlerting = true;
            status = DeviceStatus.ON;
            new AlertEvent(this, this.room).throwEvent();
        }
    }

    public boolean isAlerting() {
        return isAlerting;
    }
}