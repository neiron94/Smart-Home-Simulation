package consumer;

import smarthome.Simulation;

public class DeleteVisitor implements Visitor {
    @Override
    public void visit(ElectricityConsumer consumer) {
        Simulation.getInstance().getHome().getElectricitySupplySystem().deleteConsumer(consumer);
        // TODO - also delete from simulation device array?
    }

    @Override
    public void visit(GasConsumer consumer) {
        Simulation.getInstance().getHome().getGasSupplySystem().deleteConsumer(consumer);
        // TODO - also delete from simulation device array?
    }

    @Override
    public void visit(WaterConsumer consumer) {
        Simulation.getInstance().getHome().getWaterSupplySystem().deleteConsumer(consumer);
        // TODO - also delete from simulation device array?
    }
}
