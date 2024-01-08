package creature.person;

import consumer.device.Device;
import consumer.device.common.*;
import consumer.device.common.entertainment.EntertainmentService;
import consumer.device.common.entertainment.Game;
import consumer.device.common.entertainment.Song;
import consumer.device.common.entertainment.Video;
import consumer.device.sensored.Alarm;
import utils.exceptions.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

public class PersonAPI {

    // TODO - solve "go to room before use" problem
    // TODO - write complex functions
    // TODO - change numbers for constants?
    // TODO - create simple functions which does not use devices

    private static void addActivity(Person person, Device device, String description) {
        person.getActivity().addActivity(description);
        person.getActivity().increaseUsage(device);
    }

    //---------------------------- Complex functions ----------------------------//

    //---------------------------- Simple functions ----------------------------//

    //------------ Common for all devices ------------//

    private static final BiFunction<Person, Device, Boolean> turnOnDevice = ((person, device) -> {
        try {
            device.turnOn();
        } catch (DeviceIsBrokenException | ResourceNotAvailableException e) {
            return false;
        }

        addActivity(person, device, String.format("Turn on %s", device));
        return true;
    });

    private static final BiFunction<Person, Device, Boolean> turnOffDevice = ((person, device) -> {
        device.turnOff();

        addActivity(person, device, String.format("Turn off %s", device));
        return true;
    });

    // TODO - move to event solving?
    private static final BiFunction<Person, Device, Boolean> repairDevice = ((person, device) -> {
        try {
            device.repair();
        } catch (NotRepairableDeviceException e) {

            // TODO
        }

        addActivity(person, device, String.format("Repair %s", device));
        return true;
    });


    //------------ Common for cleaning devices ------------//

    private static final BiFunction<Person, CleaningDevice, Boolean> cleanFilter = ((person, cleaningDevice) -> {
        cleaningDevice.cleanFilter();

        addActivity(person, cleaningDevice, String.format("Clean filter of %s", cleaningDevice));
        return true;
    });

    //------------ Alarm clock ------------//

    private static final BiFunction<Person, AlarmClock, Boolean> setAlarmClock = ((person, alarmClock) -> {
        Random random = new Random();
        LocalTime ringTime = LocalTime.of(random.nextInt(6, 11), random.nextInt(0, 60));
        while (true) {
            try {
                alarmClock.setAlarm(ringTime);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, alarmClock)) return false;
            }
        }

        addActivity(person, alarmClock, String.format("Set %s to %s", alarmClock, alarmClock.getRingTime()));
        return true;
    });

    // TODO - move to event solving?
    private static final BiFunction<Person, AlarmClock, Boolean> stopAlarmClock = ((person, alarmClock) -> {
        alarmClock.stopAlarm();

        addActivity(person, alarmClock, String.format("Stop %s", alarmClock));
        return true;
    });


    //------------ Coffee machine ------------//

    private static final BiFunction<Person, CoffeeMachine, Boolean> fillCoffeeMachine = ((person, coffeeMachine) -> {
        coffeeMachine.fillAll();

        addActivity(person, coffeeMachine, String.format("Fill %s", coffeeMachine));
        return true;
    });

    private static final BiFunction<Person, CoffeeMachine, Boolean> makeCoffee = ((person, coffeeMachine) -> {
        CoffeeType coffeeType = CoffeeType.getRandomType();
        while (true) {
            try {
                coffeeMachine.makeCoffee(coffeeType);
                break;
            } catch (EntryProblemException e) {
                fillCoffeeMachine.apply(person, coffeeMachine);
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, coffeeMachine)) return false;
            }
        }

        addActivity(person, coffeeMachine, String.format("Make %s in %s", coffeeType, coffeeMachine));
        return true;
    });


    //------------ Dishwasher ------------//

    private static final BiFunction<Person, Dishwasher, Boolean> putDishesDishwasher = ((person, dishwasher) -> {
        int amount = new Random().nextInt(40, 70);
        dishwasher.putDishes(amount);

        addActivity(person, dishwasher, String.format("Put dishes to %s, fullness: %d%%", dishwasher, dishwasher.getFullness()));
        return true;
    });

    private static final BiFunction<Person, Dishwasher, Boolean> takeDishesDishwasher = ((person, dishwasher) -> {
        dishwasher.takeDishes();

        addActivity(person, dishwasher, String.format("Take dishes from %s, fullness: %d%%", dishwasher, dishwasher.getFullness()));
        return true;
    });

    private static final BiFunction<Person, Dishwasher, Boolean> startDishwasher = ((person, dishwasher) -> {
        DishwasherProgram program = DishwasherProgram.getRandomProgram();
        while (true) {
            try {
                dishwasher.startWash(program);
                break;
            } catch (DirtyFilterException e) {
                cleanFilter.apply(person, dishwasher);
            } catch (EntryProblemException e) {
                putDishesDishwasher.apply(person, dishwasher);
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, dishwasher)) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        addActivity(person, dishwasher, String.format("Start %s on program %s", dishwasher, program));
        return true;
    });


    //------------ Dryer ------------//

    private static final BiFunction<Person, Dryer, Boolean> putClothesDryer = ((person, dryer) -> {
        dryer.putClothes();

        addActivity(person, dryer, String.format("Put clothes to %s", dryer));
        return true;
    });

    private static final BiFunction<Person, Dryer, Boolean> takeClothesDryer = ((person, dryer) -> {
        dryer.takeClothes();

        addActivity(person, dryer, String.format("Take clothes from %s", dryer));
        return true;
    });

    private static final BiFunction<Person, Dryer, Boolean> startDryer = ((person, dryer) -> {
        DryerProgram program = DryerProgram.getRandomProgram();
        while (true) {
            try {
                dryer.startDry(program);
                break;
            } catch (DirtyFilterException e) {
                cleanFilter.apply(person, dryer);
            } catch (EntryProblemException e) {
                putClothesDryer.apply(person, dryer);
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, dryer)) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        addActivity(person, dryer, String.format("Start %s on program %s", dryer, program));
        return true;
    });


    //------------ Feeder ------------//

    // TODO - move to event solving?
    private static final BiFunction<Person, Feeder, Boolean> addFoodFeeder = ((person, feeder) -> {
        feeder.addFood();

        addActivity(person, feeder, String.format("Add food to %s", feeder));
        return true;
    });

    // TODO - move to event solving?
    private static final BiFunction<Person, Feeder, Boolean> addWaterFeeder = ((person, feeder) -> {
        feeder.addWater();

        addActivity(person, feeder, String.format("Add water to %s", feeder));
        return true;
    });


    //------------ Fridge ------------//

    private static final BiFunction<Person, Fridge, Boolean> takeFoodFridge = ((person, fridge) -> {
        int amount = 0;  // TODO - depends on hunger?
        try {
            fridge.takeFood(amount);
        } catch (WrongDeviceStatusException e) {
            turnOnDevice.apply(person, fridge);
        }

        addActivity(person, fridge, String.format("Take food from %s, fullness: %d%%", fridge, fridge.getFullness()));
        return true;
    });

    private static final BiFunction<Person, Fridge, Boolean> putFoodFridge = ((person, fridge) -> {
        int amount = 0;   // TODO - how much?
        fridge.putFood(amount);

        addActivity(person, fridge, String.format("Put food to %s, fullness: %d%%", fridge, fridge.getFullness()));
        return true;
    });

    private static final BiFunction<Person, Fridge, Boolean> increaseTemperatureFridge = ((person, fridge) -> {
        fridge.increaseTemperature();

        addActivity(person, fridge, String.format("Increase temperature of %s, current temperature: %.1f°C", fridge, fridge.getTemperature()));
        return true;
    });

    private static final BiFunction<Person, Fridge, Boolean> decreaseTemperatureFridge = ((person, fridge) -> {
        fridge.decreaseTemperature();

        addActivity(person, fridge, String.format("Decrease temperature of %s, current temperature: %.1f°C", fridge, fridge.getTemperature()));
        return true;
    });


    //------------ Gaming console ------------//

    private static final BiFunction<Person, GamingConsole, Boolean> playConsole = ((person, console) -> {
        Game game = EntertainmentService.GameService.getRandomGame();
        while (true) {
            try {
                console.play(game);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, console)) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        addActivity(person, console, String.format("Play %s on %s", console.getCurrentGame(), console));
        return true;
    });

    private static final BiFunction<Person, GamingConsole, Boolean> stopConsole = ((person, console) -> {
        console.stop();

        addActivity(person, console, String.format("Stop %s", console));
        return true;
    });


    //------------ Microwave ------------//

    private static final BiFunction<Person, Microwave, Boolean> putFoodMicrowave = ((person, microwave) -> {
        microwave.putFood();

        addActivity(person, microwave, String.format("Put food to %s", microwave));
        return true;
    });

    private static final BiFunction<Person, Microwave, Boolean> takeFoodMicrowave = ((person, microwave) -> {
        microwave.takeFood();

        addActivity(person, microwave, String.format("Take food from %s", microwave));
        return true;
    });

    private static final BiFunction<Person, Microwave, Boolean> heatFoodMicrowave = ((person, microwave) -> {
        Random random = new Random();
        Duration duration = Duration.ofMinutes(random.nextInt(0, 10));
        int power = random.nextInt(10, 100);
        while (true) {
            try {
                microwave.heatFood(duration, power);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, microwave)) return false;
            } catch (EntryProblemException e) {
                putFoodMicrowave.apply(person, microwave);
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        addActivity(person, microwave, String.format("Heat food in %s, power: %d%%, duration: %s", microwave, microwave.getPower(), duration));
        return true;
    });


    //------------ Oven ------------//

    private static final BiFunction<Person, Oven, Boolean> putFoodOven = ((person, oven) -> {
        oven.putFood();

        addActivity(person, oven, String.format("Put food to %s", oven));
        return true;
    });

    private static final BiFunction<Person, Oven, Boolean> takeFoodOven = ((person, oven) -> {
        oven.takeFood();

        addActivity(person, oven, String.format("Take food from %s", oven));
        return true;
    });

    private static final BiFunction<Person, Oven, Boolean> cookFoodOven = ((person, oven) -> {
        Random random = new Random();
        Duration duration = Duration.ofMinutes(random.nextInt(20, 120));
        int temperature = random.nextInt(50, 200);
        while (true) {
            try {
                oven.cookFood(duration, temperature);
                break;
            } catch (EntryProblemException e) {
                putFoodOven.apply(person, oven);
            } catch (DeviceIsOccupiedException e) {
                return false;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, oven)) return false;
            }
        }

        addActivity(person, oven, String.format("Cook food in %s, temperature: %.1f°C, duration: %s", oven, oven.getTemperature(), duration));
        return true;
    });


    //------------ Stereo system ------------//

    private static final BiFunction<Person, StereoSystem, Boolean> setVolumeStereoSystem = ((person, stereoSystem) -> {
        stereoSystem.setVolume(new Random().nextInt(10, 100));

        addActivity(person, stereoSystem, String.format("Set volume of %s to %d%%", stereoSystem, stereoSystem.getVolume()));
        return true;
    });

    private static final BiFunction<Person, StereoSystem, Boolean> stopStereoSystem = ((person, stereoSystem) -> {
        stereoSystem.stop();

        addActivity(person, stereoSystem, String.format("Stop %s", stereoSystem));
        return true;
    });

    private static final BiFunction<Person, StereoSystem, Boolean> playSong = ((person, stereoSystem) -> {
        Song song = EntertainmentService.AudioService.getRandomSong();
        while (true) {
            try {
                stereoSystem.play(song);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, stereoSystem)) return false;
            }
        }

        addActivity(person, stereoSystem, String.format("Play song %s on %s", stereoSystem.getCurrentSong().name(), stereoSystem));
        return true;
    });

    private static final BiFunction<Person, StereoSystem, Boolean> playPlaylist = ((person, stereoSystem) -> {
        List<Song> playlist = EntertainmentService.AudioService.getRandomPlaylist();
        while (true) {
            try {
                stereoSystem.play(playlist);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, stereoSystem)) return false;
            }
        }

        addActivity(person, stereoSystem, String.format("Play playlist on %s", stereoSystem));
        return true;
    });


    //------------ Toaster ------------//

    private static final BiFunction<Person, Toaster, Boolean> putToast = ((person, toaster) -> {
        toaster.putToast();

        addActivity(person, toaster, String.format("Put toast to %s", toaster));
        return true;
    });

    private static final BiFunction<Person, Toaster, Boolean> takeToast = ((person, toaster) -> {
        toaster.takeToast();

        addActivity(person, toaster, String.format("Take toast from %s", toaster));
        return true;
    });

    private static final BiFunction<Person, Toaster, Boolean> makeToast = ((person, toaster) -> {
        ToastType toastType = ToastType.getRandomType();
        while (true) {
            try {
                toaster.makeToast(toastType);
                break;
            } catch (EntryProblemException e) {
                putToast.apply(person, toaster);
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, toaster)) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        addActivity(person, toaster, String.format("Make %s in %s", toastType, toaster));
        return true;
    });


    //------------ TV ------------//

    private static final BiFunction<Person, TV, Boolean> stopTV = ((person, tv) -> {
        tv.stop();

        addActivity(person, tv, String.format("Stop %s", tv));
        return true;
    });

    private static final BiFunction<Person, TV, Boolean> setBrightnessTV = ((person, tv) -> {
        int brightness = new Random().nextInt(10, 100);
        tv.setBrightness(brightness);

        addActivity(person, tv, String.format("Set brightness of %s to %d%%", tv, tv.getBrightness()));
        return true;
    });

    private static final BiFunction<Person, TV, Boolean> setVolumeTV = ((person, tv) -> {
        int volume = new Random().nextInt(5, 100);
        tv.setVolume(volume);

        addActivity(person, tv, String.format("Set volume of %s to %d%%", tv, tv.getVolume()));
        return true;
    });

    private static final BiFunction<Person, TV, Boolean> watchTV = ((person, tv) -> {
        Video video = EntertainmentService.VideoService.getRandomVideo();
        while (true) {
            try {
                tv.show(video);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, tv)) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        addActivity(person, tv, String.format("Watch %s on %s", tv.getCurrentVideo().name(), tv));
        return true;
    });


    //------------ Vent ------------//

    private static final BiFunction<Person, Vent, Boolean> cleanFilterVent = ((person, vent) -> {
        vent.cleanFilter();

        addActivity(person, vent, String.format("Clean filter of %s", vent));
        return true;
    });

    private static final BiFunction<Person, Vent, Boolean> stopVent = ((person, vent) -> {
        vent.stop();

        addActivity(person, vent, String.format("Stop %s", vent));
        return true;
    });

    private static final BiFunction<Person, Vent, Boolean> startVent = ((person, vent) -> {
        VentProgram program = VentProgram.getRandomProgram();
        while (true) {
            try {
                vent.startVent(program);
                break;
            } catch (DirtyFilterException e) {
                cleanFilterVent.apply(person, vent);
            } catch (DeviceIsOccupiedException e) {
                return false;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, vent)) return false;
            }
        }

        addActivity(person, vent, String.format("Start %s with program %s", vent, program));
        return true;
    });


    //------------ Washer ------------//

    private static final BiFunction<Person, Washer, Boolean> putClothesWasher = ((person, washer) -> {
        washer.putClothes();

        addActivity(person, washer, String.format("Put clothes to %s", washer));
        return true;
    });

    private static final BiFunction<Person, Washer, Boolean> takeClothesWasher = ((person, washer) -> {
        washer.takeClothes();

        addActivity(person, washer, String.format("Take clothes from %s", washer));
        return true;
    });

    private static final BiFunction<Person, Washer, Boolean> startWasher = ((person, washer) -> {
        WasherProgram program = WasherProgram.getRandomProgram();
        while (true) {
            try {
                washer.startWash(program);
                break;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, washer)) return false;
            } catch (DirtyFilterException e) {
                cleanFilter.apply(person, washer);
            } catch (EntryProblemException e) {
                putClothesWasher.apply(person, washer);
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }

        addActivity(person, washer, String.format("Start %s with program %s", washer, program));
        return true;
    });


    //------------ Water tap ------------//

    private static final BiFunction<Person, WaterTap, Boolean> openWaterTap = ((person, waterTap) -> {
        Random random = new Random();
        int temperature = random.nextInt(10, 60);
        int openness = random.nextInt(10, 100);
        while (true) {
            try {
                waterTap.open(temperature, openness);
                break;
            } catch (DeviceIsOccupiedException e) {
                return false;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, waterTap)) return false;
            }
        }

        addActivity(person, waterTap, String.format("Open %s, openness:%d%%, temperature: %.1f°C", waterTap, waterTap.getOpenness(), waterTap.getTemperature()));
        return true;
    });

    private static final BiFunction<Person, WaterTap, Boolean> closeWaterTap = ((person, waterTap) -> {
        waterTap.close();

        addActivity(person, waterTap, String.format("Close %s", waterTap));
        return true;
    });


    //------------ WC ------------//

    private static final BiFunction<Person, WC, Boolean> makeToiletThings = ((person, wc) -> {
        wc.makeThings();

        addActivity(person, wc, String.format("Use %s", wc));
        return true;
    });

    private static final BiFunction<Person, WC, Boolean> flushAfterPee = ((person, wc) -> {
        try {
            wc.flush(FlushType.SMALL);
        } catch (DeviceIsBrokenException | ResourceNotAvailableException e) {
            return false;
        }

        addActivity(person, wc, String.format("Flush %s after pee", wc));
        return true;
    });

    private static final BiFunction<Person, WC, Boolean> flushAfterPoop = ((person, wc) -> {
        try {
            wc.flush(FlushType.BIG);
        } catch (DeviceIsBrokenException | ResourceNotAvailableException e) {
            return false;
        }

        addActivity(person, wc, String.format("Flush %s after poop", wc));
        return true;
    });


    //------------ Alarm ------------//

    // TODO - move to event solving?
    private static final BiFunction<Person, Alarm<?>, Boolean> stopAlarm = ((person, alarm) -> {
        alarm.stop();

        addActivity(person, alarm, String.format("Stop %s", alarm));
        return true;
    });
}
