package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import event.WakeUpEvent;
import place.Room;
import smarthome.Simulation;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import utils.exceptions.WrongDeviceStatusException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Alarm clock provides possibility to set it to time, in which it
 * will ring (throw event) and wake up all people in the room.
 */
public class AlarmClock extends Device implements ElectricityConsumer {
    private LocalDateTime ringAt;

    public AlarmClock(int id, Room startRoom) {
        super(DeviceType.ALARM_CLOCK, id, startRoom);
        ringAt = Simulation.getInstance().getCurrentTime();
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.ALARM_CLOCK);
    }

    /**
     * Check if it time to ring every tick.
     * @return can be ignored
     */
    @Override
    public boolean routine() {
        if (!super.routine())    return false;

        if (shouldRing())   ring();

        return true;
    }

    @Override
    public AlarmClock copy() {
        return new AlarmClock(id, room);
    }

    //---------- API for human -----------//

    /**
     * Sets alarm to the given ringTime.
     * @param ringTime time to ring
     * @throws WrongDeviceStatusException if device is not in start status
     */
    public void setAlarm(LocalTime ringTime) throws WrongDeviceStatusException {
        if (ringTime == null)   return;
        checkDeviceInStartStatus();

        LocalDateTime currentTime = Simulation.getInstance().getCurrentTime();
        if (ringTime.isBefore(currentTime.toLocalTime())) {
            ringAt = LocalDateTime.of(currentTime.toLocalDate().minusDays(1), ringTime);
        } else {
            ringAt = LocalDateTime.of(currentTime.toLocalDate(), ringTime);
        }
    }

    /**
     * Stop alarm. Is called when solving WakeUpEvent.
     */
    public void stopAlarm() {
        restoreStatus();
    }

    //------------- Help functions -------------//

    private boolean shouldRing() {
        return status == type.getStartStatus() && ringAt.equals(Simulation.getInstance().getCurrentTime());
    }

    private void ring() {
        status = DeviceStatus.ON;
        new WakeUpEvent(this, this.room).throwEvent();
    }

    //---------- Getters and Setters ----------//

    public LocalDateTime getRingTime() {
        return ringAt;
    }
}

