package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.common.entertainment.Song;
import place.Room;
import smarthome.Simulation;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import utils.exceptions.WrongDeviceStatusException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class StereoSystem extends Device implements ElectricityConsumer {

    private int volume; // percent
    private final Queue<Song> queue;
    private Song currentSong;
    private LocalDateTime readyTime;   // from song in queue

    public StereoSystem(int id, Room startRoom) {
        super(DeviceType.STEREO_SYSTEM, id, startRoom);
        currentSong = null;
        setVolume(50);
        readyTime = Simulation.getInstance().getCurrentTime();
        queue = new ArrayDeque<>();
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.STEREO_SYSTEM * volume / 100);
    }

    @Override
    public boolean routine() {
        if (!super.routine()) return false;

        if (status == DeviceStatus.ON && Simulation.getInstance().getCurrentTime().isAfter(readyTime)) {
            if (queue == null || queue.isEmpty())
                stop();
            else {
                currentSong = queue.poll();
                setReadyTime(currentSong.duration());
            }

        }

        return true;
    }

    @Override
    public StereoSystem copy() {
        return new StereoSystem(id, room);
    }

    //---------- API for human -----------//

    public void play(List<Song> playlist) throws WrongDeviceStatusException {
        if (playlist == null || playlist.isEmpty()) return;

        queue.clear();
        queue.addAll(playlist);
        play(Objects.requireNonNull(queue.poll()));
    }

    public void play(Song song) throws WrongDeviceStatusException {
        checkDeviceInStartStatus();

        currentSong = song;
        setReadyTime(song.duration());
        status = DeviceStatus.ON;
    }

    public void stop() {
        currentSong = null;
        restoreStatus();
    }

    public void setVolume(int volume) {
        this.volume = HelpFunctions.adjustPercent(volume);
    }

    //---------- Getters and Setters ----------//

    public int getVolume() {
        return volume;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public LocalDateTime getReadyTime() {
        return readyTime;
    }

    private void setReadyTime(Duration duration) {
        readyTime = Simulation.getInstance().getCurrentTime().plus(duration);
    }
}
