package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;


import java.util.List;

public class TV extends Device implements ElectricityConsumer {

    private int brightness;
    private int volume;
    private Video currentVideo;


    public TV(int id, Room startRoom) {
        super(DeviceType.TV, id, startRoom);
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

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Video getCurrentVideo() {
        return currentVideo;
    }

    public void setCurrentVideo(Video currentVideo) {
        this.currentVideo = currentVideo;
    }
}
