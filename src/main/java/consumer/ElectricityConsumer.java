/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer;

/**
 * Devices, which consumes electricity, implements this interface.
 */
public interface ElectricityConsumer extends Consumer {

    /**
     * Counts amount of electricity consumed during this simulation tick by this consumer.
     * @return consumed electricity (in kilowatts).
     */
    double consumeElectricity();
}
