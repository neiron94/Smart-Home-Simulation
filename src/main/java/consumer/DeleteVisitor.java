package consumer;

import consumer.device.Device;
import smarthome.Simulation;

public class DeleteVisitor implements Visitor {
    private final Simulation simulation = Simulation.getInstance();

    @Override
    public void visit(ElectricityConsumer consumer) {
        simulation.getHome().getElectricitySupplySystem().deleteConsumer(consumer);
        simulation.getDevices().remove((Device)consumer);
    }

    @Override
    public void visit(GasConsumer consumer) {
        simulation.getHome().getGasSupplySystem().deleteConsumer(consumer);
        simulation.getDevices().remove((Device)consumer);
    }

    @Override
    public void visit(WaterConsumer consumer) {
        simulation.getHome().getWaterSupplySystem().deleteConsumer(consumer);
        simulation.getDevices().remove((Device)consumer);
    }
}
