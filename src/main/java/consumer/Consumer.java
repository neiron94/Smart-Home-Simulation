package consumer;

public interface Consumer {
    default void consume() {
        if (this instanceof ElectricityConsumer)
            ((ElectricityConsumer) this).consumeElectricity();
        if (this instanceof WaterConsumer)
            ((WaterConsumer) this).consumeWater();
        if (this instanceof GasConsumer)
            ((GasConsumer) this).consumeGas();
    }

    default void delete() {
        if (this instanceof ElectricityConsumer)
            ((ElectricityConsumer) this).deleteElectricity();
        if (this instanceof WaterConsumer)
            ((WaterConsumer) this).deleteWater();
        if (this instanceof GasConsumer)
            ((GasConsumer) this).deleteGas();
    }

    default void add() {
        if (this instanceof ElectricityConsumer)
            ((ElectricityConsumer) this).addElectricity();
        if (this instanceof WaterConsumer)
            ((WaterConsumer) this).addWater();
        if (this instanceof GasConsumer)
            ((GasConsumer) this).addGas();
    }
}
