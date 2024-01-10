package consumer;

/**
 * Interface for Visitor pattern for going through all the consumers.
 */
public interface Visitor {

    /**
     * Decides, which visit functions should be applied to this consumer.
     * Because of the fact, that Consumer and ConcreteConsumers are
     * interfaces (not classes) one device can implement multiple ConcreteConsumers,
     * that's why this function exists.
     * @param consumer Consumer to visit.
     */
    default void visit(Consumer consumer) {
        if (consumer instanceof ElectricityConsumer)
            this.visit((ElectricityConsumer) consumer);
        if (consumer instanceof WaterConsumer)
            this.visit((WaterConsumer) consumer);
        if (consumer instanceof GasConsumer)
            this.visit((GasConsumer) consumer);
    }

    /**
     * Visit method for ElectricityConsumer.
     * @param consumer ElectricityConsumer to visit.
     */
    void visit(ElectricityConsumer consumer);

    /**
     * Visit method for GasConsumer.
     * @param consumer GasConsumer to visit.
     */
    void visit(GasConsumer consumer);

    /**
     * Visit method for WaterConsumer.
     * @param consumer WaterConsumer to visit.
     */
    void visit(WaterConsumer consumer);
}
