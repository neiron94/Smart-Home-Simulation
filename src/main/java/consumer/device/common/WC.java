package consumer.device.common;

import consumer.WaterConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.Manual;
import place.Room;

public class WC extends Device implements WaterConsumer {

    private boolean shouldBeFlushed;
    private FlushType flushType;

    public WC(Room startRoom) {
        super(DeviceStatus.OFF, null, startRoom);   // TODO - implement, depends on power
        shouldBeFlushed = false;
    }

    @Override
    public void consumeWater() {
        // TODO - implement, depends on flushType
    }

    @Override
    public void flood() {
        // TODO - implement
    }

    public void flush(FlushType flushType) {
        // TODO - check durability
        shouldBeFlushed = false;
        // TODO - smth else
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }

    // TODO - maybe delete some getters or setters

    public boolean isShouldBeFlushed() {
        return shouldBeFlushed;
    }

    public void setShouldBeFlushed(boolean shouldBeFlushed) {
        this.shouldBeFlushed = shouldBeFlushed;
    }

    public FlushType getFlushType() {
        return flushType;
    }

    public void setFlushType(FlushType flushType) {
        this.flushType = flushType;
    }
}
