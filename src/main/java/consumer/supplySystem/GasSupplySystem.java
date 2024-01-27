/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.supplySystem;

import consumer.GasConsumer;
import place.Room;

/**
 * System which can store information about consumed gas by all device in the house in total
 * and which can switch on/off gas in whole house/concrete room.
 */
public class GasSupplySystem extends SupplySystem<GasConsumer> {

    /**
     * See {@link SupplySystem#switchRoom(Room, boolean)}.
     * Also makes gas unavailable in this room.
     * @param room Concrete room.
     * @param switchOn true -> turn on, false -> turn off.
     */
    @Override
    public void switchRoom(Room room, boolean switchOn) {
        super.switchRoom(room, switchOn);
        room.setActiveGas(switchOn);
    }
}
