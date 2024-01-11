package creature.pet;

import consumer.device.Device;
import consumer.device.DeviceType;
import consumer.device.common.Feeder;
import creature.Action;
import place.Room;
import place.RoomType;
import smarthome.Simulation;
import utils.Constants;
import utils.HelpFunctions;
import utils.Priority;
import utils.RankedQueue;
import utils.exceptions.DeviceNotFoundException;
import utils.exceptions.RoomNotFoundException;
import utils.exceptions.WrongDeviceStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Function;
import static utils.HelpFunctions.*;

public final class PetAPI {

    //*********************************************** Simple functions ***********************************************//

    //################################# Room functions ##################################//

    private static final Function<Action<Pet, Room>, Boolean> goToRoom = action -> {
        if (action.getExecutor().getRoom() != action.getSubject()) {
            action.getExecutor().setRoom(action.getSubject());
            makeRecord(action.getExecutor(), String.format("Go to %s", action.getSubject()));
        }
        return true;
    };

    private static final Function<Action<Pet, Room>, Boolean> useTray = action -> {
        action.getExecutor().setRoom(action.getSubject());
        makeRecord(action.getExecutor(), String.format("Use animal tray in %s", action.getSubject()));
        return true;
    };


    //################################# Void functions ##################################//

    private static final Function<Action<Pet, Void>, Boolean> goOutside = action -> {
        action.getExecutor().setAtHome(false);
        makeRecord(action.getExecutor(), "Go to the backyard");
        return true;
    };

    private static final Function<Action<Pet, Void>, Boolean> returnHome = action -> {
        action.getExecutor().setAtHome(true);
        makeRecord(action.getExecutor(), "Return to home");
        return true;
    };

    private static final Function<Action<Pet, Void>, Boolean> playWithToys = action -> {
        makeRecord(action.getExecutor(), "Play with toys");
        return true;
    };

    private static final Function<Action<Pet, Void>, Boolean> damageFurniture = action -> {
        makeRecord(action.getExecutor(), "Damage furniture");
        return true;
    };

    private static final Function<Action<Pet, Void>, Boolean> goSleep = action -> {
        makeRecord(action.getExecutor(), "Go to sleep");
        return true;
    };

    private static final Function<Action<Pet, Void>, Boolean> wakeUp = action -> {
        makeRecord(action.getExecutor(), "Wake up");
        return true;
    };


    //################################ Device functions #################################//

    //--------------- Common device functions ---------------//

    private static final Function<Action<Pet, Device>, Boolean> damageDevice = action -> {
        action.getSubject().decreaseDurability(Constants.Degradation.DAMAGE_DEGRADATION);
        makeRecord(action.getExecutor(), String.format("Damage %s", action.getSubject()));
        return true;
    };

    //----------------------- Feeder ------------------------//

    private static final Function<Action<Pet, Feeder>, Boolean> drinkWaterFeeder = action -> {
        try {
            action.getSubject().drinkWater(0); // TODO - depends on hunger?
            action.getExecutor().setHunger(0);  // TODO - depends on hunger?
        } catch (WrongDeviceStatusException e) {
            return false;
        }

        HelpFunctions.makeRecord(action.getExecutor(), action.getSubject(), String.format("Drink from %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Pet, Feeder>, Boolean> eatFoodFeeder = action -> {
        try {
            action.getSubject().eatFood(0); // TODO - depends on hunger?
            action.getExecutor().setHunger(0);  // TODO - depends on hunger?
        } catch (WrongDeviceStatusException e) {
            return false;
        }

        HelpFunctions.makeRecord(action.getExecutor(), action.getSubject(), String.format("Eat from %s", action.getSubject()));
        return true;
    };


    //*********************************************** Complex functions ***********************************************//

    public static final Function<Pet, RankedQueue<Action<Pet, ?>>> feed = pet -> {
        RankedQueue<Action<Pet, ?>> queue = new RankedQueue<>(Priority.EAT);

        try {
            Feeder feeder = (Feeder) findDevice(DeviceType.FEEDER);
            queue.add(new Action<>(1, true, pet, feeder.getRoom(), goToRoom));
            queue.add(new Action<>(new Random().nextInt(5, 15), true, pet, feeder, eatFoodFeeder));
            queue.add(new Action<>(new Random().nextInt(3, 7), true, pet, feeder, drinkWaterFeeder));
        } catch (DeviceNotFoundException ignored) {
        }

        return queue;
    };


    public static final Function<Pet, RankedQueue<Action<Pet, ?>>> goToToilet = pet -> {
        RankedQueue<Action<Pet, ?>> queue = new RankedQueue<>(Priority.EMPTY);

        try {
            Room room = HelpFunctions.findRoom(RoomType.TOILET);
            queue.add(new Action<>(1, true, pet, room, goToRoom));
            queue.add(new Action<>(new Random().nextInt(1, 3), true, pet, room, useTray));
        } catch (RoomNotFoundException ignored) {
        }

        return queue;
    };

    public static final Function<Pet, RankedQueue<Action<Pet, ?>>> sleep = pet -> {
        RankedQueue<Action<Pet, ?>> queue = new RankedQueue<>(Priority.SLEEP);

        queue.add(new Action<>(1, true, pet, null, goSleep));
        queue.add(new Action<>(new Random().nextInt(100, 200), true, pet, null, wakeUp));

        return queue;
    };

    public static final Function<Pet, RankedQueue<Action<Pet, ?>>> goToBackyard = pet -> {
        RankedQueue<Action<Pet, ?>> queue = new RankedQueue<>(Priority.COMMON);

        queue.add(new Action<>(1, true, pet, null, goOutside));
        queue.add(new Action<>(new Random().nextInt(30, 90), true, pet, null, returnHome));

        return queue;
    };

    public static final Function<Pet, RankedQueue<Action<Pet, ?>>> play = pet -> {
        RankedQueue<Action<Pet, ?>> queue = new RankedQueue<>(Priority.COMMON);

        queue.add(new Action<>(1, true, pet, null, playWithToys));
        queue.add(new Action<>(new Random().nextInt(20, 40), true, pet, null, damageFurniture));

        return queue;
    };

    public static final Function<Pet, RankedQueue<Action<Pet, ?>>> brakeDevice = pet -> {
        RankedQueue<Action<Pet, ?>> queue = new RankedQueue<>(Priority.COMMON);

        List<Device> devices = Simulation.getInstance().getDevices().stream().toList();
        Device device = HelpFunctions.getRandomObject(devices).orElseThrow(NoSuchElementException::new);
        queue.add(new Action<>(1, true, pet, device, damageDevice));

        return queue;
    };
}
