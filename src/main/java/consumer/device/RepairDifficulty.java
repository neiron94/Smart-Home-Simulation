package consumer.device;

public enum RepairDifficulty {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private final String description;

    RepairDifficulty(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
