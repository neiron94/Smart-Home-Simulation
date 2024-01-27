/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common.entertainment;

/**
 * Genres for games.
 */
public enum GameGenre {
    RPG("RPG"),
    STRATEGY("Strategy"),
    ACTION("Action"),
    QUEST("Quest"),
    HORROR("Horror"),
    SPORT("Sport"),
    RACING("Racing"),
    PUZZLE("Puzzle");

    private final String name;

    GameGenre(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
