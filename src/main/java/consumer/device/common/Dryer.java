package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.HelpFunctions;
import utils.exceptions.*;


public class Dryer extends CleaningDevice implements ElectricityConsumer {

    private DryerProgram program;
    private boolean areClothesInside;

    public Dryer(int id, Room startRoom) {
        super(DeviceType.DRYER, id, startRoom);
        program = DryerProgram.COLD;
        areClothesInside = false;
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return program != null ? HelpFunctions.countElectricityConsumption(status, program.getElectricityConsumption()) : 0;
    }

    @Override
    public Dryer copy() {
        return new Dryer(id, room);
    }

    //---------- API for human -----------//

    public void startDry(DryerProgram program) throws WrongDeviceStatusException, DirtyFilterException, EntryProblemException, DeviceIsOccupiedException {
        if (program == null) return;
        checkBeforeStart();

        setReadyTime(program.getDuration());
        this.program = program;
        status = DeviceStatus.ON;
        isOccupied = true;
    }

    public void putClothes() {
        areClothesInside = true;
    }

    public void takeClothes() {
        restoreStatus();
        areClothesInside = false;
    }

    //------------- Help functions -------------//

    @Override
    protected void checkBeforeStart() throws DirtyFilterException, WrongDeviceStatusException, DeviceIsOccupiedException, EntryProblemException {
        super.checkBeforeStart();
        if (!areClothesInside)
            throw new EntryProblemException("No clothes inside.");
    }

    //---------- Getters and Setters ----------//

    public DryerProgram getProgram() {
        return program;
    }

    public boolean isAreClothesInside() {
        return areClothesInside;
    }
}
