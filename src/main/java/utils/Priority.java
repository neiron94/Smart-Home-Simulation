package utils;

/**
 * Represents priority of event or person's queue of actions.
 */
public enum Priority {
    COMMON("Default", 1),
    EMPTY("Emptying", 2),
    EAT("Eating", 3),
    EVENT_LOW("Low event", 4),
    SLEEP("Sleeping", 5),
    EVENT_MEDIUM("Medium event", 6),
    EVENT_HIGH("High event", 7);

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
