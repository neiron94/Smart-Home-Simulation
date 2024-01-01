package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.common.entertainment.Video;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;

public class TV extends Device implements ElectricityConsumer {

    private int brightness; // percent
    private int volume; // percent
    private Video currentVideo;


    public TV(int id, Room startRoom) {
        super(DeviceType.TV, id, startRoom);
        currentVideo = null; // TODO - null?
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.TV / 2 * brightness + Electricity.TV / 2 * volume);
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
        this.brightness = HelpFunctions.adjustPercent(brightness);
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = HelpFunctions.adjustPercent(volume);
    }

    public Video getCurrentVideo() {
        return currentVideo;
    }

    public void setCurrentVideo(Video currentVideo) {
        this.currentVideo = currentVideo;
    }
}
