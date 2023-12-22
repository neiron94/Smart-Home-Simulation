package consumer;

public interface ElectricityConsumer extends Consumer {
    int ELECTRICITY_COST = 0;   // cost for unit of electricity TODO - move to Constants and give value

    void consumeElectricity();

    default void deleteElectricity()   // deletes itself from proper places
    {
        // TODO - implement
    }

    default void addElectricity()  // adds itself to proper places
    {
        // TODO - implement
    }

    void fire();    // Throws event
}
