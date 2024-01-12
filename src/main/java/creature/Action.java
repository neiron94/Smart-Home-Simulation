package creature;

import java.time.Duration;
import java.util.function.Function;

/**
 * Creature's action. Can be busy (watch TV) or not busy (wait until dishwasher completes its work).
 * @param <T1> type of executor (usually Creature)
 * @param <T2> type of subject (can be room, device, nothing etc.)
 */
public class Action<T1,T2> {
    private Duration duration;
    private final boolean busy;
    private final T1 executor;
    private final T2 subject;
    private final Function<Action<T1,T2>, Boolean> function;

    /**
     * Creates new action.
     * @param duration duration of action
     * @param busy is this action is busy
     * @param executor executor of this action
     * @param subject subject of this action
     * @param function functionality of this action
     */
    public Action(int duration, boolean busy, T1 executor, T2 subject, Function<Action<T1,T2>, Boolean> function) {
        this.duration = Duration.ofMinutes(duration);
        this.busy = busy;
        this.executor = executor;
        this.subject = subject;
        this.function = function;
    }

    /**
     * Decreases duration of this action.
     * @param value amount of decreasing
     */
    public void decreaseDuration(int value) {
        duration = duration.minus(Duration.ofMinutes(value));
    }

    /**
     * Performs this action.
     * @return true if action performed normally
     */
    public boolean perform() {
        return function.apply(this);
    }

    public Duration getDuration() {
        return duration;
    }

    public boolean isBusy() {
        return busy;
    }

    public T1 getExecutor() {
        return executor;
    }

    public T2 getSubject() {
        return subject;
    }
}
