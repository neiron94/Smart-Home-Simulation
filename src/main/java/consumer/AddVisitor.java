package consumer;

import smarthome.Simulation;

public class AddVisitor implements Visitor {
    @Override
    public void visit(ElectricityConsumer consumer) {
        Simulation.getInstance().getHome().getElectricitySupplySystem().addConsumer(consumer);
        // TODO - also add to simulation device array?
    }

    @Override
    public void visit(GasConsumer consumer) {
        Simulation.getInstance().getHome().getGasSupplySystem().addConsumer(consumer);
        // TODO - also add to simulation device array?
    }

    @Override
    public void visit(WaterConsumer consumer) {
        Simulation.getInstance().getHome().getWaterSupplySystem().addConsumer(consumer);
        // TODO - also add to simulation device array?
    }
}
