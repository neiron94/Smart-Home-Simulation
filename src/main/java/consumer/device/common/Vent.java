package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.Manual;
import place.Room;
import utils.Percent;

public class Vent extends Device implements ElectricityConsumer {

    private VentProgram program;
    private Percent filterStatus;

    public Vent(Room startRoom) {
        super(DeviceStatus.STANDBY, null, startRoom);  // TODO - manual should be taken from somewhere
    }

    @Override
    public void consumeElectricity() {
        // TODO - implement, depends on program
    }

    @Override
    public void fire() {
        // TODO - implement
    }

    public void cleanFilter() {
        filterStatus.setMax();
    }

    // TODO - maybe delete some getters or setters

    public VentProgram getProgram() {
        return program;
    }

    public void setProgram(VentProgram program) {
        this.program = program;
    }

    public Percent getFilterStatus() {
        return filterStatus;
    }

    public void setFilterStatus(Percent filterStatus) {
        this.filterStatus = filterStatus;
    }
}