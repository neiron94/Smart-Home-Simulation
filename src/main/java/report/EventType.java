package report;

public enum EventType {
    FLOOD("FloodEvent"),
    LEAK("LeakEvent"),
    FIRE("FireEvent"),
    BREAK("BrakeEvent"),
    WAKEUP("WakeupEvent"),
    ALERT("AlertEvent"),
    FILL("FillEvent");

    private final String description;

    EventType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
