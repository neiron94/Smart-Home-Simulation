package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceType;
import place.Room;

import java.util.Date;

public class AlarmClock extends Device implements ElectricityConsumer {

    private Date ringTime;
    private Song song;

    public AlarmClock(int id, Room startRoom) {
        super(DeviceType.ALARM_CLOCK, id, startRoom);
        // TODO - set ringTime, song?
    }

    @Override
    public int consumeElectricity() {
        // TODO - implement
        return 0;
    }

    @Override
    public void fire() {
        // TODO - implement
    }

    @Override
    public void routine() {
        super.routine();
        // TODO - doAction(): compare ringTime with Simulation.getDate()
    }

    public void ring() {
        // TODO - throw event
    }

    // TODO - maybe delete some getters or setters

    public Date getRingTime() {
        return ringTime;
    }

    public void setRingTime(Date ringTime) {
        this.ringTime = ringTime;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}

