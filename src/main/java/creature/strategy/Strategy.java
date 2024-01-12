package creature.strategy;

import event.*;

/**
 * Strategy for reacting on events.
 */
public interface Strategy {

    /**
     * Calls proper react function to react on this event.
     * @param event event to react
     */
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

    /**
     * React on WakeUpEvent.
     * @param event WakeUpEvent
     */
    void react(WakeUpEvent event);

    /**
     * React on FillEvent.
     * @param event FillEvent
     */
    void react(FillEvent event);

    /**
     * React on BreakEvent.
     * @param event BreakEvent
     */
    void react(BreakEvent event);

    /**
     * React on AlertEvent.
     * @param event AlertEvent
     */
    void react(AlertEvent event);

    /**
     * React on FireEvent.
     * @param event FireEvent
     */
    void react(FireEvent event);

    /**
     * React on FloodEvent.
     * @param event FloodEvent
     */
    void react(FloodEvent event);

    /**
     * React on LeakEvent.
     * @param event LeakEvent
     */
    void react(LeakEvent event);
}
