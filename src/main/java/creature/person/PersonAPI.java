package creature.person;

import consumer.device.Device;
import consumer.device.common.*;
import consumer.device.common.entertainment.*;
import consumer.device.sensored.Alarm;
import creature.Action;
import utils.exceptions.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class PersonAPI {

    // TODO - solve "go to room before use" problem
    // TODO - write complex functions
    // TODO - change numbers for constants?
    // TODO - create simple functions which does not use devices

    private static void makeRecord(Person person, String description) {
        person.getActivity().addActivity(description);
    }

    private static void makeRecord(Person person, Device device, String description) {
        person.getActivity().addActivity(description);
        person.getActivity().increaseUsage(device);
    }

    //---------------------------- Complex functions ----------------------------//
    
    

    //---------------------------- Simple functions ----------------------------//

    //------------ Common for all devices ------------//

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

    // TODO - move to event solving?
    private static final Function<Action<Person, Device>, Boolean> repairDevice = action -> {
        try {
            action.getSubject().repair();
        } catch (NotRepairableDeviceException e) {

            // TODO
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Repair %s", action.getSubject()));
        return true;
    };


    //------------ Common for cleaning devices ------------//

    private static final Function<Action<Person, CleaningDevice>, Boolean> cleanFilter = action -> {
        action.getSubject().cleanFilter();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Clean filter of %s", action.getSubject()));
        return true;
    };

    //------------ Alarm clock ------------//

    private static final Function<Action<Person, AlarmClock>, Boolean> setAlarmClock = action -> {
        Random random = new Random();
        LocalTime ringTime = LocalTime.of(random.nextInt(6, 11), random.nextInt(0, 60));
        while (true) {
            try {
                action.getSubject().setAlarm(ringTime);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Set %s to %s", action.getSubject(), action.getSubject().getRingTime()));
        return true;
    };

    // TODO - move to event solving?
    private static final Function<Action<Person, AlarmClock>, Boolean> stopAlarmClock = action -> {
        action.getSubject().stopAlarm();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Stop %s", action.getSubject()));
        return true;
    };


    //------------ Coffee machine ------------//

    private static final Function<Action<Person, CoffeeMachine>, Boolean> fillCoffeeMachine = action -> {
        action.getSubject().fillAll();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Fill %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, CoffeeMachine>, Boolean> makeCoffee = action -> {
        CoffeeType coffeeType = CoffeeType.getRandomType();
        while (true) {
            try {
                action.getSubject().makeCoffee(coffeeType);
                break;
            } catch (EntryProblemException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), fillCoffeeMachine).perform();
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Make %s in %s", coffeeType, action.getSubject()));
        return true;
    };


    //------------ Dishwasher ------------//

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
        DishwasherProgram program = DishwasherProgram.getRandomProgram();
        while (true) {
            try {
                action.getSubject().startWash(program);
                break;
            } catch (DirtyFilterException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), cleanFilter).perform();
            } catch (EntryProblemException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), putDishesDishwasher).perform();
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Start %s on program %s", action.getSubject(), program));
        return true;
    };


    //------------ Dryer ------------//

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
        DryerProgram program = DryerProgram.getRandomProgram();
        while (true) {
            try {
                action.getSubject().startDry(program);
                break;
            } catch (DirtyFilterException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), cleanFilter).perform();
            } catch (EntryProblemException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), putClothesDryer).perform();
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Start %s on program %s", action.getSubject(), program));
        return true;
    };


    //------------ Feeder ------------//

    // TODO - move to event solving?
    private static final Function<Action<Person, Feeder>, Boolean> addFoodFeeder = action -> {
        action.getSubject().addFood();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Add food to %s", action.getSubject()));
        return true;
    };

    // TODO - move to event solving?
    private static final Function<Action<Person, Feeder>, Boolean> addWaterFeeder = action -> {
        action.getSubject().addWater();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Add water to %s", action.getSubject()));
        return true;
    };


    //------------ Fridge ------------//

    private static final Function<Action<Person, Fridge>, Boolean> takeFoodFridge = action -> {
        int amount = 0;  // TODO - depends on hunger?
        try {
            action.getSubject().takeFood(amount);
        } catch (WrongDeviceStatusException e) {
            new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform();
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Take food from %s, fullness: %d%%", action.getSubject(), action.getSubject().getFullness()));
        return true;
    };

    private static final Function<Action<Person, Fridge>, Boolean> putFoodFridge = action -> {
        int amount = 0;   // TODO - how much?
        action.getSubject().putFood(amount);

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Put food to %s, fullness: %d%%", action.getSubject(), action.getSubject().getFullness()));
        return true;
    };

    private static final Function<Action<Person, Fridge>, Boolean> increaseTemperatureFridge = action -> {
        action.getSubject().increaseTemperature();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Increase temperature of %s, current temperature: %.1f°C", action.getSubject(), action.getSubject().getTemperature()));
        return true;
    };

    private static final Function<Action<Person, Fridge>, Boolean> decreaseTemperatureFridge = action -> {
        action.getSubject().decreaseTemperature();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Decrease temperature of %s, current temperature: %.1f°C", action.getSubject(), action.getSubject().getTemperature()));
        return true;
    };


    //------------ Gaming console ------------//

    private static final Function<Action<Person, GamingConsole>, Boolean> playConsole = action -> {
        Game game = EntertainmentService.GameService.getRandomGame();
        while (true) {
            try {
                action.getSubject().play(game);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Play %s on %s", action.getSubject().getCurrentGame(), action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, GamingConsole>, Boolean> stopConsole = action -> {
        action.getSubject().stop();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Stop %s", action.getSubject()));
        return true;
    };


    //------------ Microwave ------------//

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
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
            } catch (EntryProblemException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), putFoodMicrowave).perform();
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Heat food in %s, power: %d%%, duration: %s", action.getSubject(), action.getSubject().getPower(), duration));
        return true;
    };


    //------------ Oven ------------//

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
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Cook food in %s, temperature: %.1f°C, duration: %s", action.getSubject(), action.getSubject().getTemperature(), duration));
        return true;
    };


    //------------ Stereo system ------------//

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
        Song song = EntertainmentService.AudioService.getRandomSong();
        while (true) {
            try {
                action.getSubject().play(song);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Play song %s on %s", action.getSubject().getCurrentSong().name(), action.getSubject()));
        return true;
    };

    private static final Function<Action<Person, StereoSystem>, Boolean> playPlaylist = action -> {
        List<Song> playlist = EntertainmentService.AudioService.getRandomPlaylist();
        while (true) {
            try {
                action.getSubject().play(playlist);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Play playlist on %s", action.getSubject()));
        return true;
    };


    //------------ Toaster ------------//

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
        ToastType toastType = ToastType.getRandomType();
        while (true) {
            try {
                action.getSubject().makeToast(toastType);
                break;
            } catch (EntryProblemException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), putToast).perform();
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Make %s in %s", toastType, action.getSubject()));
        return true;
    };


    //------------ TV ------------//

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

    private static final Function<Action<Person, TV>, Boolean> watchTV = action -> {
        Video video = EntertainmentService.VideoService.getRandomVideo();
        while (true) {
            try {
                action.getSubject().show(video);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Watch %s on %s", action.getSubject().getCurrentVideo().name(), action.getSubject()));
        return true;
    };


    //------------ Vent ------------//

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
        VentProgram program = VentProgram.getRandomProgram();
        while (true) {
            try {
                action.getSubject().startVent(program);
                break;
            } catch (DirtyFilterException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), cleanFilterVent).perform();
            } catch (DeviceIsOccupiedException e) {
                return false;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Start %s with program %s", action.getSubject(), program));
        return true;
    };


    //------------ Washer ------------//

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
        WasherProgram program = WasherProgram.getRandomProgram();
        while (true) {
            try {
                action.getSubject().startWash(program);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
            } catch (DirtyFilterException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), cleanFilter).perform();
            } catch (EntryProblemException e) {
                new Action<>(0, true, action.getExecutor(), action.getSubject(), putClothesWasher).perform();
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Start %s with program %s", action.getSubject(), program));
        return true;
    };


    //------------ Water tap ------------//

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
                if (!new Action<>(0, true, action.getExecutor(), action.getSubject(), turnOnDevice).perform()) return false;
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


    //------------ WC ------------//

    private static final Function<Action<Person, WC>, Boolean> makeToiletThings = action -> {
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

    private static final Function<Action<Person, WC>, Boolean> flushAfterPoop = action -> {
        try {
            action.getSubject().flush(FlushType.BIG);
        } catch (DeviceIsBrokenException | ResourceNotAvailableException e) {
            return false;
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Flush %s after poop", action.getSubject()));
        return true;
    };


    //------------ Alarm ------------//

    // TODO - move to event solving?
    private static final Function<Action<Person, Alarm<?>>, Boolean> stopAlarm = action -> {
        action.getSubject().stop();

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Stop %s", action.getSubject()));
        return true;
    };
}
