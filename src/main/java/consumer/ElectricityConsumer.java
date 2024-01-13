package consumer;

/**
 * Devices, which consumes electricity, implements this interface.
 */
public interface ElectricityConsumer extends Consumer {

    /**
     * Counts amount of electricity consumed during this simulation tick by this consumer.
     * @return consumed electricity (in kilowatts).
     */
    double consumeElectricity();
}
