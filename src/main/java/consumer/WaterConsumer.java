/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer;

/**
 * Devices, which consumes water, implements this interface.
 */
public interface WaterConsumer extends Consumer {

    /**
     * Counts amount of water consumed during this simulation tick by this consumer.
     * @return consumed water (in liters).
     */
    double consumeWater();
}
