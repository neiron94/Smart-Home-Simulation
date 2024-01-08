package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.common.entertainment.Video;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.DeviceIsOccupiedException;
import utils.exceptions.ResourceNotAvailableException;
import utils.exceptions.WrongDeviceStatusException;

public class TV extends Device implements ElectricityConsumer {

    private int brightness; // percent
    private int volume; // percent
    private Video currentVideo;


    public TV(int id, Room startRoom) {
        super(DeviceType.TV, id, startRoom);
        setBrightness(50);
        setVolume(50);
        currentVideo = null;
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.TV / 2 * brightness + Electricity.TV / 2 * volume);
    }

    //---------- API for human -----------//

    public void show(Video video) throws WrongDeviceStatusException, DeviceIsOccupiedException {
        checkDeviceInStartStatus();
        checkDeviceNotOccupied();

        currentVideo = video;
        status = DeviceStatus.ON;
        isOccupied = true;
    }

    public void stop() {
        currentVideo = null;
        restoreStatus();
    }

    public void setBrightness(int brightness) {
        this.brightness = HelpFunctions.adjustPercent(brightness);
    }

    public void setVolume(int volume) {
        this.volume = HelpFunctions.adjustPercent(volume);
    }

    //---------- Getters and Setters ----------//

    public int getBrightness() {
        return brightness;
    }

    public int getVolume() {
        return volume;
    }

    public Video getCurrentVideo() {
        return currentVideo;
    }
}
