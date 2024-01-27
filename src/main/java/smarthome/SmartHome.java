/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package smarthome;

/**
 * Main class, where smart home simulation starts.
 */
public class SmartHome {

    /**
     * Main function, which calls main cycle of simulation ({@link Simulation#simulate()}).
     * @param args arguments of main function
     */
    public static void main(String[] args) {
        Simulation.getInstance().simulate();
    }
}
