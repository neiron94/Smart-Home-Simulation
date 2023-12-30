package consumer;

public interface WaterConsumer extends Consumer {
    int WATER_COST = 0;   // cost for unit of water TODO - move to Constants and give value

    int consumeWater();
    void flood();   // Throws event
}
