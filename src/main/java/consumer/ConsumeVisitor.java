package consumer;

import smarthome.Simulation;

public class ConsumeVisitor implements Visitor {
    @Override
    public void visit(ElectricityConsumer consumer) {
        Simulation.getInstance().getHome().getElectricitySupplySystem().addConsumption(consumer, consumer.consumeElectricity());
    }

    @Override
    public void visit(GasConsumer consumer) {
        Simulation.getInstance().getHome().getGasSupplySystem().addConsumption(consumer, consumer.consumeGas());
    }

    @Override
    public void visit(WaterConsumer consumer) {
        Simulation.getInstance().getHome().getWaterSupplySystem().addConsumption(consumer, consumer.consumeWater());
    }
}
