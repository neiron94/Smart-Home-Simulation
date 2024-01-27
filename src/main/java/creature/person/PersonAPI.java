/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package creature.person;

import consumer.device.Device;
import consumer.device.DeviceType;
import consumer.device.common.*;
import consumer.device.common.entertainment.*;
import creature.Action;
import creature.Creature;
import place.Room;
import place.RoomConfiguration;
import place.RoomType;
import utils.HelpFunctions;
import utils.Priority;
import utils.RankedQueue;
import utils.exceptions.*;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import static utils.Constants.Creature.*;
import static utils.HelpFunctions.findDevice;
import static utils.HelpFunctions.makeRecord;

/**
 * Class contains possible person's functions. Complex functions are called
 * from {@link Creature#routine()} and consists of simple functions.
 */
public final class PersonAPI {

    //*********************************************** Simple functions ***********************************************//

    //################################# Room functions ##################################//

    private static final Function<Action<Person, Room>, Boolean> goToRoom = action -> {
        if (action.getExecutor().getRoom() != action.getSubject()) {
            action.getExecutor().setRoom(action.getSubject());
            makeRecord(action.getExecutor(), String.format("Go to %s", action.getSubject()));
        }
        return true;
    };

    private static final Function<Action<Person, Room>, Boolean> changeHumidityRoom = action -> {
        action.getSubject().getControlPanel().changeHumidity(new Random().nextDouble(-5, 5));

        makeRecord(action.getExecutor(), String.format("Change preferred humidity in %s to %.1f%%", action.getSubject(), action.getSubject().getControlPanel().getHumidity()));
        return true;
    };

    private static final Function<Action<Person, Room>, Boolean> changeTemperatureRoom = action -> {
        action.getSubject().getControlPanel().changeTemperature(new Random().nextDouble(-3, 3));

        makeRecord(action.getExecutor(), String.format("Change preferred temperature in %s to %.1f°C", action.getSubject(), action.getSubject().getControlPanel().getTemperature()));
        return true;
    };

    private static final Function<Action<Person, Room>, Boolean> changeBrightnessRoom = action -> {
        action.getSubject().getControlPanel().changeBrightness(new Random().nextDouble(-10, 10));

        makeRecord(action.getExecutor(), String.format("Change preferred brightness in %s to %.1f%%", action.getSubject(), action.getSubject().getControlPanel().getBrightness()));
        return true;
    };

    private static final Function<Action<Person, Room>, Boolean> saveConfigRoom = action -> {
        action.getSubject().getControlPanel().saveConfiguration(action.getExecutor().getName());

        makeRecord(action.getExecutor(), "Save new room configuration");
        return true;
    };

    private static final Function<Action<Person, Room>, Boolean> downloadConfigRoom = action -> {
        RoomConfiguration configuration = action.getSubject().getControlPanel().getRandomConfiguration().orElse(null);
        if (configuration == null) return false;
        action.getSubject().getControlPanel().loadConfiguration(configuration);
        makeRecord(action.getExecutor(), String.format("Change configuration of %s to %s", action.getSubject(), configuration.getName()));
        return true;
    };

    //################################# Void functions ##################################//

    //------------ Common home functions ------------//

    public static final Function<Action<Person, Void>, Boolean> cleanAfterPet = action -> {
        makeRecord(action.getExecutor(), "Clean floor after pet");
        return true;
    };

    public static final Function<Action<Person, Void>, Boolean> wakeUp = action -> {
        makeRecord(action.getExecutor(), "Wake up");
        return true;
    };

    private static final Function<Action<Person, Void>, Boolean> goSleep = action -> {
        makeRecord(action.getExecutor(), "Go to bed");
        return true;
    };

    private static final Function<Action<Person, Void>, Boolean> goNap = action -> {
        makeRecord(action.getExecutor(), "Take a nap");
        return true;
    };

    private static final Function<Action<Person, Void>, Boolean> sweepFloor = action -> {
        makeRecord(action.getExecutor(), "Swept the floor");
        return true;
    };

    private static final Function<Action<Person, Void>, Boolean> washFloor = action -> {
        makeRecord(action.getExecutor(), "Washed the floor");
        return true;
    };

    private static final Function<Action<Person, Void>, Boolean> cleanFurniture = action -> {
        makeRecord(action.getExecutor(), "Cleaned the furniture");
        return true;
    };


    //---------------- Street functions ----------------//

    private static final Function<Action<Person, Void>, Boolean> goForWalk = action -> {
        action.getExecutor().setAtHome(false);
        makeRecord(action.getExecutor(), "Go for a walk");
        return true;
    };

    private static final Function<Action<Person, Void>, Boolean> doSport = action -> {
        action.getExecutor().setAtHome(false);
        String sportDescription = HelpFunctions.getRandomObject(List.of("Go to gym", "Go jogging", "Ride bike", "Go skiing")).orElse(null);
        if (sportDescription == null) return false;
        makeRecord(action.getExecutor(), sportDescription);
        return true;
    };

    private static final Function<Action<Person, Void>, Boolean> leaveHome = action -> {
        action.getExecutor().setAtHome(false);
        makeRecord(action.getExecutor(), "Leave home for a while");
        return true;
    };

    private static final Function<Action<Person, Void>, Boolean> returnHome = action -> {
        action.getExecutor().setAtHome(true);
        makeRecord(action.getExecutor(), "Return home");
        return true;
    };


    //------------------ Food functions ------------------//

    private static final Function<Action<Person, Void>, Boolean> eatFood = action -> {
        action.getExecutor().setHunger(action.getExecutor().getHunger() - EAT_HUNGER_DECREASE * new Random().nextDouble(1, 3));
        action.getExecutor().setFullness(action.getExecutor().getFullness() + EAT_FULLNESS_INCREASE * new Random().nextDouble(1, 2));
        makeRecord(action.getExecutor(), "Eat");
        return true;
    };

    private static final Function<Action<Person, Void>, Boolean> drinkCoffee = action -> {
        action.getExecutor().setHunger(action.getExecutor().getHunger() - EAT_HUNGER_DECREASE * new Random().nextDouble(0.5, 1));
        action.getExecutor().setFullness(action.getExecutor().getFullness() + EAT_FULLNESS_INCREASE * new Random().nextDouble(2, 3));
        makeRecord(action.getExecutor(), "Drink coffee");
        return true;
    };


    //################################ Device functions #################################//

    //--------------- Common device functions ---------------//

    private static final Function<Action<Person, Device>, Boolean> turnOnDevice = action -> {
        try {
            action.getSubject().turnOn();
        } catch (DeviceIsBrokenException | ResourceNotAvailableException e) {
            return false;
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Turn on %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, Device>, Boolean> turnOffDevice = action -> {
        action.getSubject().turnOff();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Turn off %s", action.getSubject()));
        return true;
    };


    //------------ Common cleaning device functions ------------//

    private static final Function<Action<Person, CleaningDevice>, Boolean> cleanFilter = action -> {
        action.getSubject().cleanFilter();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Clean filter of %s", action.getSubject()));
        return true;
    };

    //----------------------- Alarm clock -----------------------//

    private static final Function<Action<Person, AlarmClock>, Boolean> setAlarmClock = action -> {
        Random random = new Random();
        LocalTime ringTime = LocalTime.of(random.nextInt(7, 9), 0);
        while (true) {
            try {
                action.getSubject().setAlarm(ringTime);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Set %s to %s", action.getSubject(), action.getSubject().getRingTime().format(DateTimeFormatter.ofPattern("HH:mm"))));
        return true;
    };

    //----------------------- Coffee machine -----------------------//

    private static final Function<Action<Person, CoffeeMachine>, Boolean> fillCoffeeMachine = action -> {
        action.getSubject().fillAll();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Fill %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, CoffeeMachine>, Boolean> makeCoffee = action -> {
        CoffeeType coffeeType = HelpFunctions.getRandomObject(List.of(CoffeeType.values())).orElse(null);
        if (coffeeType == null) return false;

        while (true) {
            try {
                action.getSubject().makeCoffee(coffeeType);
                break;
            } catch (EntryProblemException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), fillCoffeeMachine).perform();
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Make %s in %s", coffeeType, action.getSubject()));
        return true;
    };


    //----------------------- Dishwasher -----------------------//

    private static final Function<Action<Person, Dishwasher>, Boolean> putDishesDishwasher = action -> {
        int amount = new Random().nextInt(40, 70);
        action.getSubject().putDishes(amount);

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Put dishes to %s, fullness: %d%%", action.getSubject(), action.getSubject().getFullness()));
        return true;
    };

    private static final Function<Action<Person, Dishwasher>, Boolean> takeDishesDishwasher = action -> {
        action.getSubject().takeDishes();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Take dishes from %s, fullness: %d%%", action.getSubject(), action.getSubject().getFullness()));
        return true;
    };

    private static final Function<Action<Person, Dishwasher>, Boolean> startDishwasher = action -> {
        DishwasherProgram program = HelpFunctions.getRandomObject(List.of(DishwasherProgram.values())).orElse(null);
        if (program == null) return false;

        while (true) {
            try {
                action.getSubject().startWash(program);
                break;
            } catch (DirtyFilterException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), cleanFilter).perform();
            } catch (EntryProblemException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), putDishesDishwasher).perform();
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Start %s on '%s'", action.getSubject(), program));
        return true;
    };


    //----------------------- Dryer -----------------------//

    private static final Function<Action<Person, Dryer>, Boolean> putClothesDryer = action -> {
        action.getSubject().putClothes();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Put clothes to %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, Dryer>, Boolean> takeClothesDryer = action -> {
        action.getSubject().takeClothes();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Take clothes from %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, Dryer>, Boolean> startDryer = action -> {
        DryerProgram program = HelpFunctions.getRandomObject(List.of(DryerProgram.values())).orElse(null);
        if (program == null) return false;

        while (true) {
            try {
                action.getSubject().startDry(program);
                break;
            } catch (DirtyFilterException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), cleanFilter).perform();
            } catch (EntryProblemException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), putClothesDryer).perform();
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Start %s on '%s'", action.getSubject(), program));
        return true;
    };

    //----------------------- Fridge -----------------------//

    private static final Function<Action<Person, Fridge>, Boolean> takeFoodFridge = action -> {
        int amount = new Random().nextInt(5, 25);
        try {
            action.getSubject().takeFood(amount);
            action.getExecutor().setHunger(action.getExecutor().getHunger() - EAT_HUNGER_DECREASE / 2);
        } catch (WrongDeviceStatusException e) {
            new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform();
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Take food from %s, fullness: %d%%", action.getSubject(), action.getSubject().getFullness()));
        return true;
    };

    private static final Function<Action<Person, Fridge>, Boolean> putFoodFridge = action -> {
        int amount = new Random().nextInt(0, 10);
        action.getSubject().putFood(amount);

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Put food to %s, fullness: %d%%", action.getSubject(), action.getSubject().getFullness()));
        return true;
    };

    private static final Function<Action<Person, Fridge>, Boolean> increaseTemperatureFridge = action -> {
        action.getSubject().increaseTemperature();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Increase temperature in %s, current temperature: %.1f°C", action.getSubject(), action.getSubject().getTemperature()));
        return true;
    };

    private static final Function<Action<Person, Fridge>, Boolean> decreaseTemperatureFridge = action -> {
        action.getSubject().decreaseTemperature();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Decrease temperature in %s, current temperature: %.1f°C", action.getSubject(), action.getSubject().getTemperature()));
        return true;
    };


    //----------------------- Gaming console -----------------------//

    private static final Function<Action<Person, GamingConsole>, Boolean> startConsole = action -> {
        Game game = EntertainmentService.GameService.getRandomGame().orElse(null);
        if (game == null) return false;

        while (true) {
            try {
                action.getSubject().play(game);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Play '%s' on %s", action.getSubject().getCurrentGame(), action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, GamingConsole>, Boolean> stopConsole = action -> {
        action.getSubject().stop();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Stop %s", action.getSubject()));
        return true;
    };


    //----------------------- Microwave -----------------------//

    private static final Function<Action<Person, Microwave>, Boolean> putFoodMicrowave = action -> {
        action.getSubject().putFood();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Put food to %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, Microwave>, Boolean> takeFoodMicrowave = action -> {
        action.getSubject().takeFood();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Take food from %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, Microwave>, Boolean> heatFoodMicrowave = action -> {
        Random random = new Random();
        Duration duration = Duration.ofMinutes(random.nextInt(0, 10));
        int power = random.nextInt(10, 100);
        while (true) {
            try {
                action.getSubject().heatFood(duration, power);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            } catch (EntryProblemException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), putFoodMicrowave).perform();
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Heat food in %s, power: %d%%, duration: %d:%02d", action.getSubject(), action.getSubject().getPower(), duration.toHours(), duration.toMinutes()%60));
        return true;
    };


    //----------------------- Oven -----------------------//

    private static final Function<Action<Person, Oven>, Boolean> putFoodOven = action -> {
        action.getSubject().putFood();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Put food to %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, Oven>, Boolean> takeFoodOven = action -> {
        action.getSubject().takeFood();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Take food from %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, Oven>, Boolean> cookFoodOven = action -> {
        Random random = new Random();
        Duration duration = Duration.ofMinutes(random.nextInt(20, 120));
        int temperature = random.nextInt(50, 200);
        while (true) {
            try {
                action.getSubject().cookFood(duration, temperature);
                break;
            } catch (EntryProblemException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), putFoodOven).perform();
            } catch (DeviceIsOccupiedException e) {
                return false;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Cook food in %s, temperature: %.1f°C, duration: %d:%02d", action.getSubject(), action.getSubject().getTemperature(), duration.toHours(), duration.toMinutes()%60));
        return true;
    };


    //----------------------- Stereo system -----------------------//

    private static final Function<Action<Person, StereoSystem>, Boolean> setVolumeStereoSystem = action -> {
        action.getSubject().setVolume(new Random().nextInt(10, 100));

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Set volume of %s to %d%%", action.getSubject(), action.getSubject().getVolume()));
        return true;
    };

    private static final Function<Action<Person, StereoSystem>, Boolean> stopStereoSystem = action -> {
        action.getSubject().stop();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Stop %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, StereoSystem>, Boolean> playSong = action -> {
        Song song = EntertainmentService.AudioService.getRandomSong().orElse(null);
        if (song == null) return false;

        while (true) {
            try {
                action.getSubject().play(song);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Play song '%s' on %s", action.getSubject().getCurrentSong().name(), action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, StereoSystem>, Boolean> playPlaylist = action -> {
        List<Song> playlist = EntertainmentService.AudioService.getRandomPlaylist().orElse(null);
        if (playlist == null) return false;

        while (true) {
            try {
                action.getSubject().play(playlist);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Play playlist on %s", action.getSubject()));
        return true;
    };


    //----------------------- Toaster -----------------------//

    private static final Function<Action<Person, Toaster>, Boolean> putToast = action -> {
        action.getSubject().putToast();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Put toast to %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, Toaster>, Boolean> takeToast = action -> {
        action.getSubject().takeToast();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Take toast from %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, Toaster>, Boolean> makeToast = action -> {
        ToastType toastType = HelpFunctions.getRandomObject(List.of(ToastType.values())).orElse(null);
        if (toastType == null) return false;

        while (true) {
            try {
                action.getSubject().makeToast(toastType);
                break;
            } catch (EntryProblemException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), putToast).perform();
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Make %s in %s", toastType, action.getSubject()));
        return true;
    };


    //----------------------- TV -----------------------//

    private static final Function<Action<Person, TV>, Boolean> stopTV = action -> {
        action.getSubject().stop();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Stop %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, TV>, Boolean> setBrightnessTV = action -> {
        int brightness = new Random().nextInt(10, 100);
        action.getSubject().setBrightness(brightness);

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Set brightness of %s to %d%%", action.getSubject(), action.getSubject().getBrightness()));
        return true;
    };

    private static final Function<Action<Person, TV>, Boolean> setVolumeTV = action -> {
        int volume = new Random().nextInt(5, 100);
        action.getSubject().setVolume(volume);

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Set volume of %s to %d%%", action.getSubject(), action.getSubject().getVolume()));
        return true;
    };

    private static final Function<Action<Person, TV>, Boolean> startTV = action -> {
        Video video = EntertainmentService.VideoService.getRandomVideo().orElse(null);
        if (video == null) return false;

        while (true) {
            try {
                action.getSubject().show(video);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Watch '%s' on %s", action.getSubject().getCurrentVideo().name(), action.getSubject()));
        return true;
    };


    //----------------------- Vent -----------------------//

    private static final Function<Action<Person, Vent>, Boolean> cleanFilterVent = action -> {
        action.getSubject().cleanFilter();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Clean filter of %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, Vent>, Boolean> stopVent = action -> {
        action.getSubject().stop();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Stop %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, Vent>, Boolean> startVent = action -> {
        VentProgram program = HelpFunctions.getRandomObject(List.of(VentProgram.values())).orElse(null);
        if (program == null) return false;

        while (true) {
            try {
                action.getSubject().startVent(program);
                break;
            } catch (DirtyFilterException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), cleanFilterVent).perform();
            } catch (DeviceIsOccupiedException e) {
                return false;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Start %s with '%s'", action.getSubject(), program));
        return true;
    };


    //----------------------- Washer -----------------------//

    private static final Function<Action<Person, Washer>, Boolean> putClothesWasher = action -> {
        action.getSubject().putClothes();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Put clothes to %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, Washer>, Boolean> takeClothesWasher = action -> {
        action.getSubject().takeClothes();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Take clothes from %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, Washer>, Boolean> startWasher = action -> {
        WasherProgram program = HelpFunctions.getRandomObject(List.of(WasherProgram.values())).orElse(null);
        if (program == null) return false;

        while (true) {
            try {
                action.getSubject().startWash(program);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            } catch (DirtyFilterException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), cleanFilter).perform();
            } catch (EntryProblemException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), putClothesWasher).perform();
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Start %s with '%s'", action.getSubject(), program));
        return true;
    };


    //----------------------- Water tap -----------------------//

    private static final Function<Action<Person, WaterTap>, Boolean> openWaterTap = action -> {
        Random random = new Random();
        int temperature = random.nextInt(10, 60);
        int openness = random.nextInt(10, 100);
        while (true) {
            try {
                action.getSubject().open(temperature, openness);
                break;
            } catch (DeviceIsOccupiedException e) {
                return false;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform())
                    return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Open %s, openness:%d%%, temperature: %.1f°C", action.getSubject(), action.getSubject().getOpenness(), action.getSubject().getTemperature()));
        return true;
    };

    private static final Function<Action<Person, WaterTap>, Boolean> closeWaterTap = action -> {
        action.getSubject().close();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Close %s", action.getSubject()));
        return true;
    };


    //----------------------- WC -----------------------//

    private static final Function<Action<Person, WC>, Boolean> doPee = action -> {
        action.getExecutor().setFullness(action.getExecutor().getFullness() - FULLNESS_THRESHOLD);
        action.getSubject().makeThings();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Use %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, WC>, Boolean> flushAfterPee = action -> {
        try {
            action.getSubject().flush(FlushType.SMALL);
        } catch (DeviceIsBrokenException | ResourceNotAvailableException e) {
            return false;
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Flush %s after pee", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, WC>, Boolean> doPoo = action -> {
        action.getExecutor().setFullness(action.getExecutor().getFullness() - FULLNESS_THRESHOLD * 2);
        action.getSubject().makeThings();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Use %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, WC>, Boolean> flushAfterPoo = action -> {
        try {
            action.getSubject().flush(FlushType.BIG);
        } catch (DeviceIsBrokenException | ResourceNotAvailableException e) {
            return false;
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Flush %s after poo", action.getSubject()));
        return true;
    };


    //*********************************************** Complex functions ***********************************************//

    //################################# Relax functions ##################################//

    static final Function<Person, RankedQueue<Action<Person, ?>>> playConsole = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.COMMON);

        try {
            GamingConsole console = (GamingConsole) findDevice(DeviceType.GAMING_CONSOLE);
            queue.add(new Action<>(1, true, person, console.getRoom(), goToRoom));
            queue.add(new Action<>(1, true, person, console, startConsole));
            queue.add(new Action<>(new Random().nextInt(40, 180), true, person, console, stopConsole));
        } catch (DeviceNotFoundException ignored) {
        }

        return queue;
    };

    static final Function<Person, RankedQueue<Action<Person, ?>>> watchTV = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.COMMON);

        try {
            TV tv = (TV) findDevice(DeviceType.TV);
            queue.add(new Action<>(1, true, person, tv.getRoom(), goToRoom));
            queue.add(new Action<>(1, true, person, tv, setBrightnessTV));
            queue.add(new Action<>(1, true, person, tv, setVolumeTV));
            queue.add(new Action<>(1, true, person, tv, startTV));
            queue.add(new Action<>(new Random().nextInt(40, 180), true, person, tv, stopTV));
        } catch (DeviceNotFoundException ignored) {
        }

        return queue;
    };

    static final Function<Person, RankedQueue<Action<Person, ?>>> listenMusic = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.COMMON);

        try {
            StereoSystem stereoSystem = (StereoSystem) findDevice(DeviceType.STEREO_SYSTEM);
            queue.add(new Action<>(1, true, person, stereoSystem.getRoom(), goToRoom));
            queue.add(new Action<>(1, true, person, stereoSystem, setVolumeStereoSystem));
            queue.add(new Action<>(1, true, person, stereoSystem, playPlaylist));
            queue.add(new Action<>(new Random().nextInt(20, 60), false, person, stereoSystem, stopStereoSystem));
        } catch (DeviceNotFoundException ignored) {
        }

        return queue;
    };

    static final Function<Person, RankedQueue<Action<Person, ?>>> takeShower = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.COMMON);

        try {
            WaterTap shower = (WaterTap) findDevice(DeviceType.WATER_TAP, RoomType.SHOWER);
            queue.add(new Action<>(1, true, person, shower.getRoom(), goToRoom));
            queue.add(new Action<>(1, true, person, shower, openWaterTap));
            queue.add(new Action<>(new Random().nextInt(15, 80), true, person, shower, closeWaterTap));
        } catch (DeviceNotFoundException ignored) {
        }

        return queue;
    };


    //################################# Clean functions ##################################//

    static final Function<Person, RankedQueue<Action<Person, ?>>> washClothes = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.COMMON);

        try {
            Washer washer = (Washer) findDevice(DeviceType.WASHER);
            queue.add(new Action<>(1, true, person, washer.getRoom(), goToRoom));
            queue.add(new Action<>(1, true, person, washer, startWasher));
            queue.add(new Action<>(new Random().nextInt(90, 180), false, person, washer, takeClothesWasher));

            Dryer dryer = (Dryer) findDevice(DeviceType.DRYER);
            queue.add(new Action<>(1, true, person, dryer.getRoom(), goToRoom));
            queue.add(new Action<>(1, true, person, dryer, startDryer));
            queue.add(new Action<>(new Random().nextInt(90, 180), false, person, dryer, takeClothesDryer));
        } catch (DeviceNotFoundException ignored) {
        }

        return queue;
    };

    static final Function<Person, RankedQueue<Action<Person, ?>>> washDishes = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.COMMON);

        try {
            Dishwasher dishwasher = (Dishwasher) findDevice(DeviceType.DISHWASHER);
            queue.add(new Action<>(1, true, person, dishwasher.getRoom(), goToRoom));
            queue.add(new Action<>(1, true, person, dishwasher, startDishwasher));
            queue.add(new Action<>(new Random().nextInt(120, 180), false, person, dishwasher, takeDishesDishwasher));
        } catch (DeviceNotFoundException ignored) {
        }

        return queue;
    };

    static final Function<Person, RankedQueue<Action<Person, ?>>> cleanHome = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.COMMON);

        queue.add(new Action<>(new Random().nextInt(30, 50), true, person, null, sweepFloor));
        queue.add(new Action<>(new Random().nextInt(40, 90), true, person, null, washFloor));
        queue.add(new Action<>(new Random().nextInt(50, 100), true, person, null, cleanFurniture));

        return queue;
    };


    //################################# Food functions ##################################//

    static final Function<Person, RankedQueue<Action<Person, ?>>> takeLunch = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.EAT);

        Oven oven;
        Fridge fridge;
        try {
            oven = (Oven) findDevice(DeviceType.GAS_OVEN, DeviceType.ELECTRIC_OVEN);
            fridge = (Fridge) findDevice(DeviceType.FRIDGE);
        } catch (DeviceNotFoundException e) {
            return queue;
        }

        Vent vent = null;
        WaterTap waterTap = null;
        try {
            vent = (Vent) findDevice(DeviceType.VENT, oven.getRoom());
            waterTap = (WaterTap) findDevice(DeviceType.WATER_TAP);
        } catch (DeviceNotFoundException ignored) {
        }

        queue.add(new Action<>(1, true, person, fridge.getRoom(), goToRoom));
        queue.add(new Action<>(1, true, person, fridge, takeFoodFridge));

        // Wash food
        if (waterTap != null) {
            queue.add(new Action<>(1, true, person, waterTap.getRoom(), goToRoom));
            queue.add(new Action<>(1, true, person, waterTap, openWaterTap));
            queue.add(new Action<>(new Random().nextInt(5, 15), true, person, waterTap, closeWaterTap));
        }

        queue.add(new Action<>(1, true, person, oven.getRoom(), goToRoom));
        if (vent != null) queue.add(new Action<>(1, true, person, vent, startVent));
        queue.add(new Action<>(1, true, person, oven, cookFoodOven));
        queue.add(new Action<>(new Random().nextInt(60, 120), false, person, oven, takeFoodOven));
        if (vent != null) queue.add(new Action<>(1, true, person, vent, stopVent));
        queue.add(new Action<>(new Random().nextInt(20, 40), true, person, null, eatFood));

        // Put remains food
        queue.add(new Action<>(1, true, person, fridge.getRoom(), goToRoom));
        queue.add(new Action<>(1, true, person, fridge, putFoodFridge));

        return queue;
    };

    static final Function<Person, RankedQueue<Action<Person, ?>>> takeBreakfast = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.EAT);

        Fridge fridge;
        try {
            fridge = (Fridge) findDevice(DeviceType.FRIDGE);
        } catch (DeviceNotFoundException e) {
            return queue;
        }

        queue.add(new Action<>(1, true, person, fridge.getRoom(), goToRoom));
        queue.add(new Action<>(1, true, person, fridge, takeFoodFridge));

        try {
            Toaster toaster = (Toaster) findDevice(DeviceType.TOASTER);
            queue.add(new Action<>(1, true, person, toaster.getRoom(), goToRoom));
            queue.add(new Action<>(1, true, person, toaster, makeToast));
            queue.add(new Action<>(new Random().nextInt(5, 10), false, person, toaster, takeToast));

            CoffeeMachine coffeeMachine = (CoffeeMachine) findDevice(DeviceType.COFFEE_MACHINE);
            queue.add(new Action<>(1, true, person, coffeeMachine.getRoom(), goToRoom));
            queue.add(new Action<>(1, true, person, coffeeMachine, makeCoffee));
            queue.add(new Action<>(new Random().nextInt(5, 10), true, person, null, drinkCoffee));
        } catch (DeviceNotFoundException ignored) {
        }

        queue.add(new Action<>(new Random().nextInt(15, 30), true, person, null, eatFood));
        queue.add(new Action<>(1, true, person, fridge.getRoom(), goToRoom));
        queue.add(new Action<>(1, true, person, fridge, putFoodFridge));

        return queue;
    };

    static final Function<Person, RankedQueue<Action<Person, ?>>> takeDinner = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.EAT);

        Fridge fridge;
        try {
            fridge = (Fridge) findDevice(DeviceType.FRIDGE);
        } catch (DeviceNotFoundException e) {
            return queue;
        }

        queue.add(new Action<>(1, true, person, fridge.getRoom(), goToRoom));
        queue.add(new Action<>(1, true, person, fridge, takeFoodFridge));

        try {
            Microwave microwave = (Microwave) findDevice(DeviceType.MICROWAVE);
            queue.add(new Action<>(1, true, person, microwave.getRoom(), goToRoom));
            queue.add(new Action<>(1, true, person, microwave, heatFoodMicrowave));
            queue.add(new Action<>(new Random().nextInt(10, 15), false, person, microwave, takeFoodMicrowave));
        } catch (DeviceNotFoundException ignored) {
        }

        queue.add(new Action<>(new Random().nextInt(20, 40), true, person, null, eatFood));

        queue.add(new Action<>(1, true, person, fridge.getRoom(), goToRoom));
        queue.add(new Action<>(1, true, person, fridge, putFoodFridge));

        return queue;
    };


    //################################# Toilet functions ##################################//

    static final Function<Person, RankedQueue<Action<Person, ?>>> pee = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.EMPTY);

        try {
            WC wc = (WC) findDevice(DeviceType.WC);
            queue.add(new Action<>(1, true, person, wc.getRoom(), goToRoom));
            queue.add(new Action<>(1, true, person, wc, doPee));
            queue.add(new Action<>(new Random().nextInt(3, 5), true, person, wc, flushAfterPee));
        } catch (DeviceNotFoundException ignored) {
        }

        return queue;
    };

    static final Function<Person, RankedQueue<Action<Person, ?>>> poo = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.EMPTY);

        WC wc;
        try {
            wc = (WC) findDevice(DeviceType.WC);
        } catch (DeviceNotFoundException e) {
            return queue;
        }

        Vent vent = null;
        try {
            vent = (Vent) findDevice(DeviceType.VENT, wc.getRoom());
        }
        catch (DeviceNotFoundException ignored) {
        }

        queue.add(new Action<>(1, true, person, wc.getRoom(), goToRoom));
        if (vent != null) queue.add(new Action<>(1, true, person, vent, startVent));
        queue.add(new Action<>(1, true, person, wc, doPoo));
        queue.add(new Action<>(new Random().nextInt(5, 15), true, person, wc, flushAfterPoo));
        if (vent != null) queue.add(new Action<>(1, true, person, vent, stopVent));

        return queue;
    };


    //################################# Other functions ##################################//

    static final Function<Person, RankedQueue<Action<Person, ?>>> nap = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.SLEEP);

        queue.add(new Action<>(1, true, person, null, goNap));
        queue.add(new Action<>(new Random().nextInt(20, 100), true, person, null, wakeUp));

        return queue;
    };

    static final Function<Person, RankedQueue<Action<Person, ?>>> sleep = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.SLEEP);

        try {
            AlarmClock alarmClock = (AlarmClock) findDevice(DeviceType.ALARM_CLOCK);
            queue.add(new Action<>(1, true, person, alarmClock.getRoom(), goToRoom));
            queue.add(new Action<>(1, true, person, alarmClock, setAlarmClock));
        } catch (DeviceNotFoundException ignored) {
        }

        queue.add(new Action<>(1, true, person, null, goSleep));
        queue.add(new Action<>(new Random().nextInt(400, 600), true, person, null, wakeUp));

        return queue;
    };


    //################################# Street functions ##################################//

    static final Function<Person, RankedQueue<Action<Person, ?>>> goWalk = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.COMMON);

        queue.add(new Action<>(1, true, person, null, goForWalk));
        queue.add(new Action<>(new Random().nextInt(120, 360), true, person, null, returnHome));

        return queue;
    };

    static final Function<Person, RankedQueue<Action<Person, ?>>> goSport = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.COMMON);

        queue.add(new Action<>(1, true, person, null, doSport));
        queue.add(new Action<>(new Random().nextInt(60, 180), true, person, null, returnHome));

        return queue;
    };

    static final Function<Person, RankedQueue<Action<Person, ?>>> goAway = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.COMMON);

        queue.add(new Action<>(1, true, person, null, leaveHome));
        queue.add(new Action<>(new Random().nextInt(1440, 4320), true, person, null, returnHome));

        return queue;
    };


    //################################# Control panel functions ##################################//

    static final Function<Person, RankedQueue<Action<Person, ?>>> changeRoomParameters = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.COMMON);

        queue.add(new Action<>(1, true, person, person.getRoom(), changeTemperatureRoom));
        queue.add(new Action<>(1, true, person, person.getRoom(), changeHumidityRoom));
        queue.add(new Action<>(1, true, person, person.getRoom(), changeBrightnessRoom));

        return queue;
    };

    static final Function<Person, RankedQueue<Action<Person, ?>>> saveRoomConfiguration = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.COMMON);

        queue.add(new Action<>(1, true, person, person.getRoom(), saveConfigRoom));

        return queue;
    };

    static final Function<Person, RankedQueue<Action<Person, ?>>> changeRoomConfiguration = person -> {
        RankedQueue<Action<Person, ?>> queue = new RankedQueue<>(Priority.COMMON);

        queue.add(new Action<>(1, true, person, person.getRoom(), downloadConfigRoom));

        return queue;
    };

    //******************************************** Functions collections ********************************************//

    static final List<Function<Person, RankedQueue<Action<Person, ?>>>> streetFunctions = List.of(goWalk, goSport, goAway);

    static final List<Function<Person, RankedQueue<Action<Person, ?>>>> otherFunctions = List.of(playConsole, watchTV, listenMusic,
            takeShower, washClothes, washDishes, cleanHome, changeRoomParameters, saveRoomConfiguration, changeRoomConfiguration);
}
