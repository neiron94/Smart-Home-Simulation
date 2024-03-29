/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common.entertainment;

/**
 * Game for playing on gaming console.
 * @param name name of the game
 * @param description description of the game
 * @param genre genre of the game
 */
public record Game(String name, String description, GameGenre genre) {
    @Override
    public String toString() {
        return name;
    }
}
