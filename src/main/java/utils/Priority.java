package utils;

public enum Priority {
    COMMON("Default", 0),
    EMPTY("Emptying", 1),
    EAT("Eating", 2),
    EVENT_LOW("Low event", 3),
    EVENT_MEDIUM("Medium event", 4),
    EVENT_HIGH("High event", 5);

    private final String description;
    private final int value;

    Priority(String description, int value) {
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
