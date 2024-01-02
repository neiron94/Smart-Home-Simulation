package consumer.device.common.entertainment;

public enum GameGenre {
    RPG("RPG"),
    STRATEGY("Strategy"),
    ACTION("Action"),
    QUEST("Quest"),
    HORROR("Horror"),
    SPORT("Sport"),
    RACING("Racing");

    private final String name;

    GameGenre(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
