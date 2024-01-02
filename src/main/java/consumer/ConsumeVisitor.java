package consumer;

import smarthome.Simulation;

public class ConsumeVisitor implements Visitor {
    private final Simulation simulation = Simulation.getInstance();

    @Override
    public void visit(ElectricityConsumer consumer) {
        simulation.getHome().getElectricitySupplySystem().addConsumption(consumer, consumer.consumeElectricity());
    }

    @Override
    public void visit(GasConsumer consumer) {
        simulation.getHome().getGasSupplySystem().addConsumption(consumer, consumer.consumeGas());
    }

    @Override
    public void visit(WaterConsumer consumer) {
        simulation.getHome().getWaterSupplySystem().addConsumption(consumer, consumer.consumeWater());
    }
}
