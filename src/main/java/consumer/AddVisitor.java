package consumer;

import consumer.device.Device;
import smarthome.Simulation;

/**
 * Adds consumer to simulation and supply systems.
 */
public class AddVisitor implements Visitor {
    /**
     * Adds consumer to simulation and home's electricity supply system.
     * @param consumer ElectricityConsumer to visit.
     */
    @Override
    public void visit(ElectricityConsumer consumer) {
        Simulation.getInstance().getHome().getElectricitySupplySystem().addConsumer(consumer);
        Simulation.getInstance().getDevices().add((Device)consumer);
    }

    /**
     * Adds consumer to simulation and home's gas supply system.
     * @param consumer GasConsumer to visit.
     */
    @Override
    public void visit(GasConsumer consumer) {
        Simulation.getInstance().getHome().getGasSupplySystem().addConsumer(consumer);
        Simulation.getInstance().getDevices().add((Device)consumer);
    }

    /**
     * Adds consumer to simulation and home's water supply system.
     * @param consumer WaterConsumer to visit.
     */
    @Override
    public void visit(WaterConsumer consumer) {
        Simulation.getInstance().getHome().getWaterSupplySystem().addConsumer(consumer);
        Simulation.getInstance().getDevices().add((Device)consumer);
    }
}
