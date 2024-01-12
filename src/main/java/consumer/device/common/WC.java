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

/**
 * Common stupid WC, which can be flushed.
 */
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

    @Override
    public WC copy() {
        return new WC(id, room);
    }

    //---------- API for human -----------//

    /**
     * Seat on the toilet (or stand near).
     */
    public void makeThings() {
        shouldBeFlushed = true;
        isOccupied = true;
    }

    /**
     * Flush WC after use.
     * @param flushType type of flushing
     * @throws DeviceIsBrokenException if WC is broken
     * @throws ResourceNotAvailableException if no water available in this room
     */
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
