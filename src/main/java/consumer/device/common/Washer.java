package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.WaterConsumer;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.HelpFunctions;
import utils.exceptions.*;


public class Washer extends CleaningDevice implements WaterConsumer, ElectricityConsumer {

    private WasherProgram program;
    private boolean areClothesInside;

    public Washer(int id, Room startRoom) {
        super(DeviceType.WASHER, id, startRoom);
        program = WasherProgram.DELICATE;
        areClothesInside = false;
    }

    //--------- Main public functions ----------//

    @Override
    public double consumeElectricity() {
        return program != null ? HelpFunctions.countElectricityConsumption(status, program.getPower()) : 0;
    }

    @Override
    public double consumeWater() {
        return program != null ? HelpFunctions.countWaterConsumption(status, program.getWaterConsumption()) : 0;
    }

    @Override
    public Washer copy() {
        return new Washer(id, room);
    }

    //---------- API for human -----------//

    public void startWash(WasherProgram program) throws WrongDeviceStatusException, DirtyFilterException, EntryProblemException, DeviceIsOccupiedException {
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
    protected void checkBeforeStart() throws DirtyFilterException, EntryProblemException, WrongDeviceStatusException, DeviceIsOccupiedException {
        super.checkBeforeStart();
        if (!areClothesInside)
            throw new EntryProblemException("No clothes inside.");
    }

    //---------- Getters and Setters ----------//

    public WasherProgram getProgram() {
        return program;
    }

    public boolean AreClothesInside() {
        return areClothesInside;
    }
}
