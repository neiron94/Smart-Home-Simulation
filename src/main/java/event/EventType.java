package event;

public enum EventType {
    FLOOD("Flood"),
    LEAK("Leak"),
    FIRE("Fire"),
    BREAK("Brake"),
    WAKEUP("Wakeup"),
    ALERT("Alert"),
    FILL("Fill");

    private final String description;

    EventType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s Event", description);
    }
}
