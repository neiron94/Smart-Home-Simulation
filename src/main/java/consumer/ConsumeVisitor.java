package consumer;

import smarthome.Simulation;

/**
 * Make consumer consume resources and write it to supply systems' map.
 */
public class ConsumeVisitor implements Visitor {
    /**
     * Make consumer consume electricity.
     * @param consumer ElectricityConsumer to visit.
     */
    @Override
    public void visit(ElectricityConsumer consumer) {
        Simulation.getInstance().getHome().getElectricitySupplySystem().addConsumption(consumer, consumer.consumeElectricity());
    }

    /**
     * Make consumer consume gas.
     * @param consumer GasConsumer to visit.
     */
    @Override
    public void visit(GasConsumer consumer) {
        Simulation.getInstance().getHome().getGasSupplySystem().addConsumption(consumer, consumer.consumeGas());
    }

    /**
     * Make consumer consume water.
     * @param consumer WaterConsumer to visit.
     */
    @Override
    public void visit(WaterConsumer consumer) {
        Simulation.getInstance().getHome().getWaterSupplySystem().addConsumption(consumer, consumer.consumeWater());
    }
}
