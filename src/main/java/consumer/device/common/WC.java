package consumer.device.common;

import consumer.WaterConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;

public class WC extends Device implements WaterConsumer {

    private boolean shouldBeFlushed;
    private FlushType flushType;

    public WC(int id, Room startRoom) {
        super(DeviceType.WC, id, startRoom);
        shouldBeFlushed = false;
    }

    @Override
    public int consumeWater() {
        // TODO - implement, depends on flushType
        return 0;
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
