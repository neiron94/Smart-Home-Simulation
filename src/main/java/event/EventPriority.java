package event;

public enum EventPriority {
    LOW("Low", 1),
    MEDIUM("Medium", 2),
    HIGH("High", 3);

    private final String description;
    private final int value;

    EventPriority(String description, int value) {
        this.description = description;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return String.format("%s priority", description);
    }
}
