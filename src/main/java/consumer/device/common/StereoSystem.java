package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.common.entertainment.Song;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;

import java.time.LocalTime;
import java.util.List;
import java.util.Queue;

public class StereoSystem extends Device implements ElectricityConsumer {

    private int volume; // percent
    private Queue<Song> queue;
    private Song currentSong;
    private LocalTime timeToReady;   // from song in queue

    public StereoSystem(int id, Room startRoom) {
        super(DeviceType.STEREO_SYSTEM, id, startRoom);
        currentSong = null; // TODO - null?
        // TODO - set other? (volume, queue, timeToReady)
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.STEREO_SYSTEM * volume / 100);
    }

    @Override
    public boolean routine() {
        super.routine();
        // TODO - doAction(): timeToReady--, check timeToReady, pop next song or stop playing
        return true;
    }

    public void play(List<Song> playlist) {
        // TODO - implement
    }

    public void play(Song song) {
        currentSong = song;
        status = DeviceStatus.ON;
        // TODO - something more
    }

    public void stop() {
        // TODO - check this function
        currentSong = null;
        status = DeviceStatus.OFF;
    }

    // TODO - maybe delete some getters or setters

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = HelpFunctions.adjustPercent(volume);
    }

    public Queue<Song> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Song> queue) {
        this.queue = queue;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    public LocalTime getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(LocalTime timeToReady) {
        this.timeToReady = timeToReady;
    }
}
