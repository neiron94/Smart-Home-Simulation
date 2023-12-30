package consumer.device.common.entertainment;

import java.time.LocalTime;

public record Video(String name, String description, VideoPlatform platform, LocalTime duration) {}
