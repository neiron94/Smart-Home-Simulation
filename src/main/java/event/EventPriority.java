package event;

public enum EventPriority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String description;

    EventPriority(String description) {
        this.description = description;
    }

    public String toString() {
        return String.format("%s priority", description);
    }
}
