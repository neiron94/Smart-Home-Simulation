package consumer;

public interface Visitor {  // TODO - rename class?

    default void visit(Consumer consumer) {
        if (consumer instanceof ElectricityConsumer)
            this.visit((ElectricityConsumer) consumer);
        if (consumer instanceof WaterConsumer)
            this.visit((WaterConsumer) consumer);
        if (consumer instanceof GasConsumer)
            this.visit((GasConsumer) consumer);
    }

    void visit(ElectricityConsumer consumer);
    void visit(GasConsumer consumer);
    void visit(WaterConsumer consumer);
}
