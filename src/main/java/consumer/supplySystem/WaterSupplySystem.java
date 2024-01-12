package consumer.supplySystem;

import consumer.WaterConsumer;
import place.Room;

/**
 * System which can store information about consumed water by all device in the house in total
 * and which can switch on/off water in whole house/concrete room.
 */
public class WaterSupplySystem extends SupplySystem<WaterConsumer> {

    /**
     * See {@link SupplySystem#switchRoom(Room, boolean)}.
     * Also makes water unavailable in this room.
     * @param room Concrete room.
     * @param switchOn true -> turn on, false -> turn off.
     */
    @Override
    public void switchRoom(Room room, boolean switchOn) {
        super.switchRoom(room, switchOn);
        room.setActiveWater(switchOn);
    }
}
