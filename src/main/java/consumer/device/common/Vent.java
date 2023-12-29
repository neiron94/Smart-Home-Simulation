package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import consumer.device.Manual;
import place.Room;


public class Vent extends Device implements ElectricityConsumer {

    private VentProgram program;
    private int filterStatus;

    public Vent(int id, Room startRoom) {
        super(DeviceType.VENT, id, startRoom);
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
        filterStatus = 100;
    }

    // TODO - maybe delete some getters or setters

    public VentProgram getProgram() {
        return program;
    }

    public void setProgram(VentProgram program) {
        this.program = program;
    }

    public int getFilterStatus() {
        return filterStatus;
    }

    public void setFilterStatus(int filterStatus) {
        this.filterStatus = filterStatus;
    }
}
