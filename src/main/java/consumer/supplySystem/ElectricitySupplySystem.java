package consumer.supplySystem;

import consumer.ElectricityConsumer;
import place.Room;

/**
 * System which can store information about consumed electricity by all device in the house in total
 * and which can switch on/off electricity in whole house/concrete room.
 */
public class ElectricitySupplySystem extends SupplySystem<ElectricityConsumer> {

    /**
     * See {@link SupplySystem#switchRoom(Room, boolean)}.
     * Also makes electricity unavailable in this room.
     * @param room Concrete room.
     * @param switchOn true -> turn on, false -> turn off.
     */
    @Override
    public void switchRoom(Room room, boolean switchOn) {
        super.switchRoom(room, switchOn);
        room.setActiveElectricity(switchOn);
    }
}
