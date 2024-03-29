/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common.entertainment;

/**
 * Platforms for videos.
 */
public enum VideoPlatform {
    NETFLIX("Netflix"),
    YOUTUBE("YouTube"),
    DISNEY_PLUS("Disney+"),
    DISCOVERY_CHANNEL("Discovery Channel"),
    SPORT_CHANNEL("Sport Channel"),
    MOVIE("Blue-Ray Movie"),
    PORNHUB("PornHub");

    private final String name;

    VideoPlatform(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
