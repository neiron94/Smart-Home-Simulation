package consumer;

public interface GasConsumer extends Consumer {
    int GAS_COST = 0;   // cost for unit of gas TODO - move to Constants and give value

    void consumeGas();

    default void deleteGas()   // deletes itself from proper places
    {
        // TODO - implement
    }

    default void addGas()  // adds itself to proper places
    {
        // TODO - implement
    }

    void leak();    // Throws event
}
