package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.Device;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.Constants;
import utils.HelpFunctions;
import utils.exceptions.*;


public class Vent extends Device implements ElectricityConsumer {

    private VentProgram program;
    private double filterStatus;   // percent

    public Vent(int id, Room startRoom) {
        super(DeviceType.VENT, id, startRoom);
        program = VentProgram.SLOW;
        setFilterStatus(100);
    }

    //--------- Main public functions ----------//

    @Override
    public boolean routine() {
        if (!super.routine()) return false;

        if (status == DeviceStatus.ON)
            setFilterStatus(filterStatus - Constants.Degradation.FILTER_DEGRADATION);

        return true;
    }

    @Override
    public Vent copy() {
        return new Vent(id, room);
    }

    @Override
    public double consumeElectricity() {
        return program != null ? HelpFunctions.countElectricityConsumption(status, program.getPower()) : 0;
    }

    //---------- API for human -----------//

    public void startVent(VentProgram program) throws DirtyFilterException, DeviceIsOccupiedException, WrongDeviceStatusException {
        checkDeviceInStartStatus();
        checkDeviceNotOccupied();
        if (filterStatus <= 0)
            throw new DirtyFilterException();

        this.program = program;
        status = DeviceStatus.ON;
        isOccupied = true;
    }

    public void stop() {
        restoreStatus();
    }

    public void cleanFilter() {
        setFilterStatus(100);
    }


    //---------- Getters and Setters ----------//

    public VentProgram getProgram() {
        return program;
    }

    public double getFilterStatus() {
        return filterStatus;
    }

    private void setFilterStatus(double filterStatus) {
        this.filterStatus = HelpFunctions.adjustPercent(filterStatus);
    }
}
