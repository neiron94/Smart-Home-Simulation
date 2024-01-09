package creature;

import java.time.Duration;
import java.util.function.Function;

public class Action<T1,T2> {
    private Duration duration;
    private final boolean busy;
    private final T1 executor;
    private final T2 subject;
    private final Function<Action<T1,T2>, Boolean> function;

    public Action(int duration, boolean busy, T1 executor, T2 subject, Function<Action<T1,T2>, Boolean> function) {
        this.duration = Duration.ofMinutes(duration);
        this.busy = busy;
        this.executor = executor;
        this.subject = subject;
        this.function = function;
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

    public void decreaseDuration(int value) {
        duration = duration.minus(Duration.ofMinutes(value));
    }

    public boolean perform() {
        return function.apply(this);
    }
}
