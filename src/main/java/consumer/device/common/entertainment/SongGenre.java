package consumer.device.common.entertainment;

public enum SongGenre {
    ROCK("Rock"),
    CLASSIC("Classic"),
    HIP_HOP("Hip Hop"),
    JAZZ("Jazz"),
    ELECTRONIC("Electronic"),
    REGGAE("Reggae"),
    POP("Pop");

    private final String name;

    SongGenre(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
