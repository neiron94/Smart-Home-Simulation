package consumer;

import consumer.device.Device;
import smarthome.Simulation;

/**
 * Deletes consumer from simulation and supply systems.
 */
public class DeleteVisitor implements Visitor {
    private final Simulation simulation = Simulation.getInstance();

    /**
     * Deletes consumer from simulation and electricity supply systems.
     * @param consumer ElectricityConsumer to visit.
     */
    @Override
    public void visit(ElectricityConsumer consumer) {
        simulation.getHome().getElectricitySupplySystem().deleteConsumer(consumer);
        simulation.getDevices().remove((Device)consumer);
    }

    /**
     * Deletes consumer from simulation and gas supply systems.
     * @param consumer GasConsumer to visit.
     */
    @Override
    public void visit(GasConsumer consumer) {
        simulation.getHome().getGasSupplySystem().deleteConsumer(consumer);
        simulation.getDevices().remove((Device)consumer);
    }

    /**
     * Deletes consumer from simulation and water supply systems.
     * @param consumer WaterConsumer to visit.
     */
    @Override
    public void visit(WaterConsumer consumer) {
        simulation.getHome().getWaterSupplySystem().deleteConsumer(consumer);
        simulation.getDevices().remove((Device)consumer);
    }
}
