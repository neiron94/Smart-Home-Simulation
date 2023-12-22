package creature;

public class Action {
    private int duration;
    private final boolean busy;
    private final String description;

    public Action(ActionType id) { // TODO Control after creating ActionType enum and
        this.duration = id.duration;
        this.busy = id.busy;
        this.description = id.description; // TODO Get from enum/constants
    }

    public void decreaseDuration() {
        this.duration--;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isBusy() {
        return busy;
    }

    public String getDescription() {
        return description;
    }
}
