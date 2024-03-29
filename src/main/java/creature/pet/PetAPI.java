/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package creature.pet;

import consumer.device.Device;
import consumer.device.DeviceType;
import consumer.device.common.Feeder;
import creature.Action;
import creature.Creature;
import place.Room;
import place.RoomType;
import smarthome.Simulation;
import utils.Constants;
import utils.HelpFunctions;
import utils.Priority;
import utils.RankedQueue;
import utils.exceptions.DeviceNotFoundException;
import utils.exceptions.RoomNotFoundException;
import java.util.Random;
import java.util.function.Function;
import static utils.Constants.Creature.FULLNESS_THRESHOLD;
import static utils.Constants.Creature.HUNGER_THRESHOLD;
import static utils.HelpFunctions.*;

/**
 * Class contains possible pet's functions. Complex functions are called
 * from {@link Creature#routine()} and consists of simple functions.
 */
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
        action.getExecutor().setFullness(action.getExecutor().getFullness() - FULLNESS_THRESHOLD * 2);
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
        int amount = new Random().nextInt(5, (int) HUNGER_THRESHOLD * 10);
        action.getSubject().drinkWater(amount * 3);
        action.getExecutor().setHunger(action.getExecutor().getHunger() - amount * 0.1);
        HelpFunctions.makeRecord(action.getExecutor(), action.getSubject(), String.format("Drink from %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Pet, Feeder>, Boolean> eatFoodFeeder = action -> {
        int amount = new Random().nextInt(5, (int) HUNGER_THRESHOLD * 10);
        action.getSubject().eatFood(amount * 3);
        action.getExecutor().setHunger(action.getExecutor().getHunger() - amount);
        HelpFunctions.makeRecord(action.getExecutor(), action.getSubject(), String.format("Eat from %s", action.getSubject()));
        return true;
    };


    //*********************************************** Complex functions ***********************************************//

    static final Function<Pet, RankedQueue<Action<Pet, ?>>> feed = pet -> {
        RankedQueue<Action<Pet, ?>> queue = new RankedQueue<>(Priority.EAT);

        try {
            Feeder feeder = (Feeder) findDevice(DeviceType.FEEDER);
            queue.add(new Action<>(1, true, pet, feeder.getRoom(), goToRoom));
            queue.add(new Action<>(new Random().nextInt(5, 15), true, pet, feeder, eatFoodFeeder));
            queue.add(new Action<>(new Random().nextInt(3, 7), true, pet, feeder, drinkWaterFeeder));
        } catch (DeviceNotFoundException ignored) {}

        return queue;
    };


    static final Function<Pet, RankedQueue<Action<Pet, ?>>> goToToilet = pet -> {
        RankedQueue<Action<Pet, ?>> queue = new RankedQueue<>(Priority.EMPTY);

        try {
            Room room = HelpFunctions.findRoom(RoomType.TOILET);
            queue.add(new Action<>(1, true, pet, room, goToRoom));
            queue.add(new Action<>(new Random().nextInt(1, 3), true, pet, room, useTray));
        } catch (RoomNotFoundException ignored) {}

        return queue;
    };

    static final Function<Pet, RankedQueue<Action<Pet, ?>>> sleep = pet -> {
        RankedQueue<Action<Pet, ?>> queue = new RankedQueue<>(Priority.SLEEP);

        queue.add(new Action<>(1, true, pet, null, goSleep));
        queue.add(new Action<>(new Random().nextInt(100, 200), true, pet, null, wakeUp));

        return queue;
    };

    static final Function<Pet, RankedQueue<Action<Pet, ?>>> goToBackyard = pet -> {
        RankedQueue<Action<Pet, ?>> queue = new RankedQueue<>(Priority.COMMON);

        queue.add(new Action<>(1, true, pet, null, goOutside));
        queue.add(new Action<>(new Random().nextInt(30, 90), true, pet, null, returnHome));

        return queue;
    };

    static final Function<Pet, RankedQueue<Action<Pet, ?>>> play = pet -> {
        RankedQueue<Action<Pet, ?>> queue = new RankedQueue<>(Priority.COMMON);

        queue.add(new Action<>(1, true, pet, null, playWithToys));

        return queue;
    };

    static final Function<Pet, RankedQueue<Action<Pet, ?>>> brakeDevice = pet -> {
        RankedQueue<Action<Pet, ?>> queue = new RankedQueue<>(Priority.COMMON);

        if (new Random().nextInt(0,2) == 0)
            queue.add(new Action<>(new Random().nextInt(20, 40), true, pet, null, damageFurniture));
        else {
            HelpFunctions.getRandomObject(Simulation.getInstance().getDevices().stream().toList())
                    .ifPresent(device -> queue.add(new Action<>(1, true, pet, device, damageDevice)));
        }

        return queue;
    };
}
