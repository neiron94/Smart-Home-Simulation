package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceType;
import consumer.device.common.entertainment.Song;
import event.WakeUpEvent;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import java.time.LocalDateTime;

public class AlarmClock extends Device implements ElectricityConsumer {

    private LocalDateTime ringTime;
    private Song song;

    public AlarmClock(int id, Room startRoom) {
        super(DeviceType.ALARM_CLOCK, id, startRoom);
        // TODO - set ringTime, song?
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.ALARM_CLOCK);
    }

    @Override
    public void routine() {
        super.routine();
        // TODO - doAction(): compare ringTime with Simulation.getDate()
    }

    public void ring() {
        new WakeUpEvent(this, this.room).throwEvent();
    }

    // TODO - maybe delete some getters or setters

    public LocalDateTime getRingTime() {
        return ringTime;
    }

    public void setRingTime(LocalDateTime ringTime) {
        this.ringTime = ringTime;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}

