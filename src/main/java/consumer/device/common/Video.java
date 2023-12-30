package consumer.device.common;

import java.time.LocalTime;

public record Video(String name, String description, VideoPlatform platform, LocalTime duration) {}
