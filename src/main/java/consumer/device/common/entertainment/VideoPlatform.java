package consumer.device.common.entertainment;

public enum VideoPlatform {
    NETFLIX("Netflix"),
    YOUTUBE("YouTube"),
    DISNEY_PLUS("Disney+"),
    SPORT_CHANNEL("Sport Channel"),
    PORNHUB("PornHub");

    private final String name;

    VideoPlatform(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
