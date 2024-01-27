/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer;

/**
 * Devices, which consumes gas, implements this interface.
 */
public interface GasConsumer extends Consumer {

    /**
     * Counts amount of gas consumed during this simulation tick by this consumer.
     * @return consumed gas (in cubic meters).
     */
    double consumeGas();
}
