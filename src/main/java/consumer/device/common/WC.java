package consumer.device.common;

import consumer.ConsumeVisitor;
import consumer.WaterConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.HelpFunctions;
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.ResourceNotAvailableException;

public class WC extends Device implements WaterConsumer {

    private boolean shouldBeFlushed;
    private FlushType flushType;

    public WC(int id, Room startRoom) {
        super(DeviceType.WC, id, startRoom);
        shouldBeFlushed = false;
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeWater() {
        return flushType != null ? HelpFunctions.countWaterConsumption(status, flushType.getWaterConsumption()) : 0;
    }

    //---------- API for human -----------//

    public void makeThings() {
        shouldBeFlushed = true;
        isOccupied = true;
    }

    public void flush(FlushType flushType) throws DeviceIsBrokenException, ResourceNotAvailableException {
        checkBeforeTurnOn();

        status = DeviceStatus.ON;
        this.flushType = flushType;
        accept(new ConsumeVisitor());
        shouldBeFlushed = false;
        restoreStatus();
    }

    //---------- Getters and Setters ----------//

    public boolean isShouldBeFlushed() {
        return shouldBeFlushed;
    }
}
