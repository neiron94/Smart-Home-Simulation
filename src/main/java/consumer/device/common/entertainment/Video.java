package consumer.device.common.entertainment;

import java.time.Duration;

/**
 * Video for TV.
 * @param name Name of the video.
 * @param description Description of the video.
 * @param platform Platform of the video.
 * @param duration Duration of the video.
 */
public record Video(String name, String description, VideoPlatform platform, Duration duration) {
    @Override
    public String toString() {
        return name;
    }
}
