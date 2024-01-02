package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import event.WakeUpEvent;
import place.Room;
import smarthome.Simulation;
import utils.Constants;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.ResourceNotAvailableException;
import utils.exceptions.WrongDeviceStatusException;

import java.time.LocalDate;
import java.time.LocalTime;

public class AlarmClock extends Device implements ElectricityConsumer {

    private LocalTime ringTime;
    private boolean rangToday;
    private LocalDate lastRingDay;

    public AlarmClock(int id, Room startRoom) {
        super(DeviceType.ALARM_CLOCK, id, startRoom);
        ringTime = LocalTime.of(8,0);   // 8:00 by default
        rangToday = ringTime.isAfter(Simulation.getInstance().getCurrentTime().toLocalTime());
        lastRingDay = Simulation.getInstance().getCurrentTime().toLocalDate();
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.ALARM_CLOCK);
    }

    @Override
    public boolean routine() {
        if (!super.routine())    return false;

        updateStatus();
        if (shouldRing())   ring();

        return true;
    }

    //---------- API for human -----------//
    public void turnOn() throws DeviceIsBrokenException, ResourceNotAvailableException {
        setStandby();
    }

    public void setAlarm(LocalTime ringTime) throws WrongDeviceStatusException {
        if (ringTime == null)   return;
        checkDeviceStandby();

        this.ringTime = ringTime;
        rangToday = ringTime.isAfter(Simulation.getInstance().getCurrentTime().toLocalTime());
    }

    public void stopAlarm() {
        setOff();
    }

    //------------- Help functions -------------//

    private void updateStatus() {
        LocalDate currentDate = Simulation.getInstance().getCurrentTime().toLocalDate();
        if (currentDate.isEqual(lastRingDay)) {
            rangToday = false;
            lastRingDay = currentDate;
        }
    }

    private boolean shouldRing() {
        return !rangToday && status == DeviceStatus.STANDBY && ringTime.isAfter(Simulation.getInstance().getCurrentTime().toLocalTime());
    }

    private void ring() {
        status = DeviceStatus.ON;
        new WakeUpEvent(this, this.room).throwEvent();
        rangToday = true;
    }

    //---------- Getters and Setters ----------//

    public LocalTime getRingTime() {
        return ringTime;
    }
}

