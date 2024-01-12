package consumer.device.common.entertainment;

import java.time.Duration;

/**
 * Video for TV.
 * @param name name of the video
 * @param description description of the video
 * @param platform platform of the video
 * @param duration duration of the video
 */
public record Video(String name, String description, VideoPlatform platform, Duration duration) {
    @Override
    public String toString() {
        return name;
    }
}
