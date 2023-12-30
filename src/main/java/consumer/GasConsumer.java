package consumer;

public interface GasConsumer extends Consumer {
    int GAS_COST = 0;   // cost for unit of gas TODO - move to Constants and give value

    int consumeGas();
    void leak();    // Throws event
}
