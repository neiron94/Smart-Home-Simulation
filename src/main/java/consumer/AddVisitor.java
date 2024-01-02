package consumer;

import consumer.device.Device;
import smarthome.Simulation;

public class AddVisitor implements Visitor {
    private final Simulation simulation = Simulation.getInstance();

    @Override
    public void visit(ElectricityConsumer consumer) {
        simulation.getHome().getElectricitySupplySystem().addConsumer(consumer);
        simulation.getDevices().add((Device)consumer);
    }

    @Override
    public void visit(GasConsumer consumer) {
        simulation.getHome().getGasSupplySystem().addConsumer(consumer);
        simulation.getDevices().add((Device)consumer);
    }

    @Override
    public void visit(WaterConsumer consumer) {
        simulation.getHome().getWaterSupplySystem().addConsumer(consumer);
        simulation.getDevices().add((Device)consumer);
    }
}
