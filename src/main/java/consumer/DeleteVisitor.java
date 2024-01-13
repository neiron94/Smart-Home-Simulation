package consumer;

import consumer.device.Device;
import smarthome.Simulation;

/**
 * Deletes consumer from simulation and supply systems.
 */
public class DeleteVisitor implements Visitor {
    /**
     * Deletes consumer from simulation and electricity supply systems.
     * @param consumer ElectricityConsumer to visit.
     */
    @Override
    public void visit(ElectricityConsumer consumer) {
        Simulation.getInstance().getHome().getElectricitySupplySystem().deleteConsumer(consumer);
        Simulation.getInstance().getDevices().remove((Device)consumer);
    }

    /**
     * Deletes consumer from simulation and gas supply systems.
     * @param consumer GasConsumer to visit.
     */
    @Override
    public void visit(GasConsumer consumer) {
        Simulation.getInstance().getHome().getGasSupplySystem().deleteConsumer(consumer);
        Simulation.getInstance().getDevices().remove((Device)consumer);
    }

    /**
     * Deletes consumer from simulation and water supply systems.
     * @param consumer WaterConsumer to visit.
     */
    @Override
    public void visit(WaterConsumer consumer) {
        Simulation.getInstance().getHome().getWaterSupplySystem().deleteConsumer(consumer);
        Simulation.getInstance().getDevices().remove((Device)consumer);
    }
}
