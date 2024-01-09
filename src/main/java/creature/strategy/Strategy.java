package creature.strategy;

import consumer.device.Device;
import creature.Creature;
import event.*;

public interface Strategy {
    default void react (Event event) {
        switch (event.getEventType()) {
            case WAKEUP -> react((WakeUpEvent) event);
            case FILL -> react((FillEvent) event);
            case BREAK -> react((BreakEvent) event);
            case ALERT -> react((AlertEvent) event);
            case FIRE -> react((FireEvent) event);
            case FLOOD -> react((FloodEvent) event);
            case LEAK -> react((LeakEvent) event);
        }
    }

    void react(WakeUpEvent event);

    void react(FillEvent event);

    void react(BreakEvent event);

    void react(AlertEvent event);

    void react(FireEvent event);

    void react(FloodEvent event);

    void react(LeakEvent event);

    static void makeRecord(Creature creature, String description) {
        creature.getActivity().addActivity(description);
    }

    static void makeRecord(Creature creature, Device device, String description) {
        creature.getActivity().addActivity(description);
        creature.getActivity().increaseUsage(device);
    }
}
