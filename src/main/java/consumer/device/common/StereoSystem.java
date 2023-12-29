package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;

import java.util.List;
import java.util.Queue;

public class StereoSystem extends Device implements ElectricityConsumer {

    private int volume;
    private Queue<Song> queue;
    private Song currentSong;
    private Time timeToReady;   // from song in queue TODO - Time?

    public StereoSystem(int id, Room startRoom) {
        super(DeviceType.STEREO_SYSTEM, id, startRoom);
        currentSong = null; // TODO - null?
        // TODO - set other? (volume, queue, timeToReady)
    }

    @Override
    public void consumeElectricity() {
        // TODO - implement, depends on volume
    }

    @Override
    public void fire() {
        // TODO - implemens
    }

    @Override
    public void routine() {
        super.routine();
        // TODO - doAction(): timeToReady--, check timeToReady, pop next song or stop playing
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
        this.volume = volume;
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

    public int getTimeToReady() {
        return timeToReady;
    }

    public void setTimeToReady(int timeToReady) {
        this.timeToReady = timeToReady;
    }
}
