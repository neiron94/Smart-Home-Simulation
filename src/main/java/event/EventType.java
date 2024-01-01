package event;

public enum EventType {
    FLOOD("Flood"),
    LEAK("Leak"),
    FIRE("Fire"),
    BREAK("Brake"),
    WAKEUP("Wakeup"),
    ALERT("Alert"),
    FILL("Fill");

    private final String name;

    EventType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s Event", name);
    }
}
