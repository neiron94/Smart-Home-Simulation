package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;

import java.util.List;

public class TV extends Device implements ElectricityConsumer {

    public static List<Video> shows;    // simulation of database of videos TODO - should be preloaded, maybe in a static block

    private Percent brightness;
    private Percent volume;
    private Video currentVideo;


    public TV(Room startRoom) {
        super(DeviceStatus.STANDBY, null, startRoom);  // TODO - manual should be taken from somewhere
        currentVideo = null; // TODO - null?
    }

    @Override
    public void consumeElectricity() {
        // TODO - implement, depends on brightness and volume
    }

    @Override
    public void fire() {
        // TODO - implement
    }

    public void show(Video video) {
        currentVideo = video;
        status = DeviceStatus.ON;
        // TODO - maybe add something
    }

    public void stop() {
        // TODO- check this function
        currentVideo = null;
        status = DeviceStatus.OFF;
    }

    // TODO - maybe delete some getters or setters

    public static List<Video> getShows() {
        return shows;
    }

    public static void setShows(List<Video> shows) {
        TV.shows = shows;
    }

    public Percent getBrightness() {
        return brightness;
    }

    public void setBrightness(Percent brightness) {
        this.brightness = brightness;
    }

    public Percent getVolume() {
        return volume;
    }

    public void setVolume(Percent volume) {
        this.volume = volume;
    }

    public Video getCurrentVideo() {
        return currentVideo;
    }

    public void setCurrentVideo(Video currentVideo) {
        this.currentVideo = currentVideo;
    }
}
