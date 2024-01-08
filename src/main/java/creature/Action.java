package creature;

import consumer.device.Device;

import java.time.Duration;
import java.util.function.BiFunction;

public class Action {
    private Duration duration;
    private final boolean busy;
    private final BiFunction<Creature, Device, Boolean> function;
    private final Device device;
    private final Creature creature;

    public Action(int duration, boolean busy, BiFunction<Creature, Device, Boolean> function, Device device, Creature creature) {
        this.duration = Duration.ofMinutes(duration);
        this.busy = busy;
        this.function = function;
        this.device = device;
        this.creature = creature;
    }

    public Duration getDuration() {
        return duration;
    }

    public boolean isBusy() {
        return busy;
    }

    public BiFunction<Creature, Device, Boolean> getFunction() {
        return function;
    }

    public Device getDevice() {
        return device;
    }

    public Creature getCreature() {
        return creature;
    }

    public void decreaseDuration(int value) {
        duration = duration.minus(Duration.ofMinutes(value));
    }

    public boolean perform() {
        return function.apply(creature, device);
    }
}
