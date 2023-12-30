package consumer;

public interface ElectricityConsumer extends Consumer {
    int ELECTRICITY_COST = 0;   // cost for unit of electricity TODO - move to Constants and give value

    int consumeElectricity();
    void fire();    // Throws event
}
