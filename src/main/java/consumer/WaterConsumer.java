package consumer;

public interface WaterConsumer extends Consumer {
    int WATER_COST = 0;   // cost for unit of water TODO - move to Constants and give value

    void consumeWater();

    default void deleteWater()   // deletes itself from proper places
    {
        // TODO - implement
    }

    default void addWater()  // adds itself to proper places
    {
        // TODO - implement
    }

    void flood();   // Throws event
}
