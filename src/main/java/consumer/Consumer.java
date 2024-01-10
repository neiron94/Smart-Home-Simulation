package consumer;

/**
 * Is implemented only by device. Allows to add and delete consumer to/from proper places,
 * call proper consume functions and throw proper disaster events by accept(Visitor) function.
 */
public interface Consumer {
    /**
     * Implementation of Visitor design pattern.
     * @param visitor Visitor which will perform some actions.
     */
    default void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
