package consumer;

import consumer.device.Device;
import smarthome.Simulation;

/**
 * Adds consumer to simulation and supply systems.
 */
public class AddVisitor implements Visitor {
    private final Simulation simulation = Simulation.getInstance();

    /**
     * Adds consumer to simulation and home's electricity supply system.
     * @param consumer ElectricityConsumer to visit.
     */
    @Override
    public void visit(ElectricityConsumer consumer) {
        simulation.getHome().getElectricitySupplySystem().addConsumer(consumer);
        simulation.getDevices().add((Device)consumer);
    }

    /**
     * Adds consumer to simulation and home's gas supply system.
     * @param consumer GasConsumer to visit.
     */
    @Override
    public void visit(GasConsumer consumer) {
        simulation.getHome().getGasSupplySystem().addConsumer(consumer);
        simulation.getDevices().add((Device)consumer);
    }

    /**
     * Adds consumer to simulation and home's water supply system.
     * @param consumer WaterConsumer to visit.
     */
    @Override
    public void visit(WaterConsumer consumer) {
        simulation.getHome().getWaterSupplySystem().addConsumer(consumer);
        simulation.getDevices().add((Device)consumer);
    }
}
