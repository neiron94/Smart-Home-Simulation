package creature.person;

import consumer.device.Device;
import consumer.device.common.*;
import consumer.device.common.entertainment.EntertainmentService;
import consumer.device.sensored.Alarm;
import utils.exceptions.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;
import java.util.function.BiFunction;

public class PersonAPI {

    // TODO - add description adding for reports
    // TODO - solve "go to room before use" problem
    // TODO - write complex functions
    // TODO - change numbers for constants?

    //---------------------------- Complex functions ----------------------------//

    //---------------------------- Simple functions ----------------------------//

    //------------ Common for all devices ------------//
    private static final BiFunction<Person, Device, Boolean> turnOnDevice = ((person, device) -> {
        try {
            device.turnOn();
        } catch (DeviceIsBrokenException | ResourceNotAvailableException e) {
            return false;
        }
        return true;
    });

    private static final BiFunction<Person, Device, Boolean> turnOffDevice = ((person, device) -> {
        device.turnOff();
        return true;
    });

    // TODO - move to event solving?
    private static final BiFunction<Person, Device, Boolean> repairDevice = ((person, device) -> {
        try {
            device.repair();
        } catch (NotRepairableDeviceException e) {

            // TODO
        }
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
        return true;
    });

    // TODO - move to event solving?
    private static final BiFunction<Person, AlarmClock, Boolean> stopAlarmClock = ((person, alarmClock) -> {
        alarmClock.stopAlarm();
        return true;
    });


    //------------ Coffee machine ------------//

    private static final BiFunction<Person, CoffeeMachine, Boolean> fillCoffeeMachine = ((person, coffeeMachine) -> {
        coffeeMachine.fillAll();
        return true;
    });

    private static final BiFunction<Person, CoffeeMachine, Boolean> makeCoffee = ((person, coffeeMachine) -> {
        while (true) {
            try {
                coffeeMachine.makeCoffee(CoffeeType.getRandomType());
                break;
            } catch (EntryProblemException e) {
                fillCoffeeMachine.apply(person, coffeeMachine);
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, coffeeMachine)) return false;
            }
        }
        return true;
    });


    //------------ Dishwasher ------------//

    private static final BiFunction<Person, Dishwasher, Boolean> putDishesDishwasher = ((person, dishwasher) -> {
        dishwasher.putDishes(new Random().nextInt(40, 70));
        return true;
    });

    private static final BiFunction<Person, Dishwasher, Boolean> takeDishesDishwasher = ((person, dishwasher) -> {
        dishwasher.takeDishes();
        return true;
    });

    private static final BiFunction<Person, Dishwasher, Boolean> cleanFilterDishwasher = ((person, dishwasher) -> {
        dishwasher.cleanFilter();
        return true;
    });

    private static final BiFunction<Person, Dishwasher, Boolean> startDishwasher = ((person, dishwasher) -> {
        while (true) {
            try {
                dishwasher.startWash(DishwasherProgram.getRandomProgram());
                break;
            } catch (DirtyFilterException e) {
                cleanFilterDishwasher.apply(person, dishwasher);
            } catch (EntryProblemException e) {
                putDishesDishwasher.apply(person, dishwasher);
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, dishwasher)) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }
        return true;
    });


    //------------ Dryer ------------//

    private static final BiFunction<Person, Dryer, Boolean> putClothesDryer = ((person, dryer) -> {
        dryer.putClothes();
        return true;
    });

    private static final BiFunction<Person, Dryer, Boolean> takeClothesDryer = ((person, dryer) -> {
        dryer.takeClothes();
        return true;
    });

    private static final BiFunction<Person, Dryer, Boolean> cleanFilterDryer = ((person, dryer) -> {
        dryer.cleanFilter();
        return true;
    });

    private static final BiFunction<Person, Dryer, Boolean> startDryer = ((person, dryer) -> {
        while (true) {
            try {
                dryer.startDry(DryerProgram.getRandomProgram());
                break;
            } catch (DirtyFilterException e) {
                cleanFilterDryer.apply(person, dryer);
            } catch (EntryProblemException e) {
                putClothesDryer.apply(person, dryer);
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, dryer)) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }
        return true;
    });


    //------------ Feeder ------------//

    // TODO - move to event solving?
    private static final BiFunction<Person, Feeder, Boolean> addFoodFeeder = ((person, feeder) -> {
        feeder.addFood();
        return true;
    });

    // TODO - move to event solving?
    private static final BiFunction<Person, Feeder, Boolean> addWaterFeeder = ((person, feeder) -> {
        feeder.addWater();
        return true;
    });


    //------------ Fridge ------------//

    private static final BiFunction<Person, Fridge, Boolean> takeFoodFridge = ((person, fridge) -> {
        try {
            fridge.takeFood(0);  // TODO - depends on hunger?
        } catch (WrongDeviceStatusException e) {
            turnOnDevice.apply(person, fridge);
        }

        return true;
    });

    private static final BiFunction<Person, Fridge, Boolean> putFoodFridge = ((person, fridge) -> {
        fridge.putFood(0);   // TODO - how much?
        return true;
    });

    private static final BiFunction<Person, Fridge, Boolean> increaseTemperatureFridge = ((person, fridge) -> {
        fridge.increaseTemperature();
        return true;
    });

    private static final BiFunction<Person, Fridge, Boolean> decreaseTemperatureFridge = ((person, fridge) -> {
        fridge.decreaseTemperature();
        return true;
    });


    //------------ Gaming console ------------//

    private static final BiFunction<Person, GamingConsole, Boolean> playConsole = ((person, console) -> {
        while (true) {
            try {
                console.play(EntertainmentService.GameService.getRandomGame());
                break;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, console)) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }
        return true;
    });

    private static final BiFunction<Person, GamingConsole, Boolean> stopConsole = ((person, console) -> {
        console.stop();
        return true;
    });


    //------------ Microwave ------------//

    private static final BiFunction<Person, Microwave, Boolean> putFoodMicrowave = ((person, microwave) -> {
        microwave.putFood();
        return true;
    });

    private static final BiFunction<Person, Microwave, Boolean> takeFoodMicrowave = ((person, microwave) -> {
        microwave.takeFood();
        return true;
    });

    private static final BiFunction<Person, Microwave, Boolean> heatFoodMicrowave = ((person, microwave) -> {
        Random random = new Random();
        while (true) {
            try {
                microwave.heatFood(Duration.ofMinutes(random.nextInt(0, 10)), random.nextInt(10, 100));
                break;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, microwave)) return false;
            } catch (EntryProblemException e) {
                putFoodMicrowave.apply(person, microwave);
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }
        return true;
    });


    //------------ Oven ------------//

    private static final BiFunction<Person, Oven, Boolean> putFoodOven = ((person, oven) -> {
        oven.putFood();
        return true;
    });

    private static final BiFunction<Person, Oven, Boolean> takeFoodOven = ((person, oven) -> {
        oven.takeFood();
        return true;
    });

    private static final BiFunction<Person, Oven, Boolean> cookFoodOven = ((person, oven) -> {
        Random random = new Random();
        while (true) {
            try {
                oven.cookFood(Duration.ofMinutes(random.nextInt(20, 120)), random.nextInt(50, 200));
                break;
            } catch (EntryProblemException e) {
                putFoodOven.apply(person, oven);
            } catch (DeviceIsOccupiedException e) {
                return false;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, oven)) return false;
            }
        }
        return true;
    });


    //------------ Stereo system ------------//

    private static final BiFunction<Person, StereoSystem, Boolean> setVolumeStereoSystem = ((person, stereoSystem) -> {
        stereoSystem.setVolume(new Random().nextInt(10, 100));
        return true;
    });

    private static final BiFunction<Person, StereoSystem, Boolean> stopStereoSystem = ((person, stereoSystem) -> {
        stereoSystem.stop();
        return true;
    });

    private static final BiFunction<Person, StereoSystem, Boolean> playSong = ((person, stereoSystem) -> {
        while (true) {
            try {
                stereoSystem.play(EntertainmentService.AudioService.getRandomSong());
                break;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, stereoSystem)) return false;
            }
        }
        return true;
    });

    private static final BiFunction<Person, StereoSystem, Boolean> playPlaylist = ((person, stereoSystem) -> {
        while (true) {
            try {
                stereoSystem.play(EntertainmentService.AudioService.getRandomPlaylist());
                break;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, stereoSystem)) return false;
            }
        }
        return true;
    });


    //------------ Toaster ------------//

    private static final BiFunction<Person, Toaster, Boolean> putToast = ((person, toaster) -> {
        toaster.putToast();
        return true;
    });

    private static final BiFunction<Person, Toaster, Boolean> takeToast = ((person, toaster) -> {
        toaster.takeToast();
        return true;
    });

    private static final BiFunction<Person, Toaster, Boolean> makeToast = ((person, toaster) -> {
        while (true) {
            try {
                toaster.makeToast(ToastType.getRandomType());
                break;
            } catch (EntryProblemException e) {
                putToast.apply(person, toaster);
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, toaster)) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }
        return true;
    });


    //------------ TV ------------//

    private static final BiFunction<Person, TV, Boolean> stopTV = ((person, tv) -> {
        tv.stop();
        return true;
    });

    private static final BiFunction<Person, TV, Boolean> setBrightnessTV = ((person, tv) -> {
        tv.setBrightness(new Random().nextInt(10, 100));
        return true;
    });

    private static final BiFunction<Person, TV, Boolean> setVolumeTV = ((person, tv) -> {
        tv.setVolume(new Random().nextInt(5, 100));
        return true;
    });

    private static final BiFunction<Person, TV, Boolean> watchTV = ((person, tv) -> {
        while (true) {
            try {
                tv.show(EntertainmentService.VideoService.getRandomVideo());
                break;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, tv)) return false;
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }
        return true;
    });


    //------------ Vent ------------//

    private static final BiFunction<Person, Vent, Boolean> cleanFilterVent = ((person, vent) -> {
        vent.cleanFilter();
        return true;
    });

    private static final BiFunction<Person, Vent, Boolean> stopVent = ((person, vent) -> {
        vent.stop();
        return true;
    });

    private static final BiFunction<Person, Vent, Boolean> startVent = ((person, vent) -> {
        while (true) {
            try {
                vent.startVent(VentProgram.getRandomProgram());
                break;
            } catch (DirtyFilterException e) {
                cleanFilterVent.apply(person, vent);
            } catch (DeviceIsOccupiedException e) {
                return false;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, vent)) return false;
            }
        }
        return true;
    });


    //------------ Washer ------------//

    private static final BiFunction<Person, Washer, Boolean> cleanFilterWasher = ((person, washer) -> {
        washer.cleanFilter();
        return true;
    });

    private static final BiFunction<Person, Washer, Boolean> putClothesWasher = ((person, washer) -> {
        washer.putClothes();
        return true;
    });

    private static final BiFunction<Person, Washer, Boolean> takeClothesWasher = ((person, washer) -> {
        washer.takeClothes();
        return true;
    });

    private static final BiFunction<Person, Washer, Boolean> startWasher = ((person, washer) -> {
        while (true) {
            try {
                washer.startWash(WasherProgram.getRandomProgram());
                break;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, washer)) return false;
            } catch (DirtyFilterException e) {
                cleanFilterWasher.apply(person, washer);
            } catch (EntryProblemException e) {
                putClothesWasher.apply(person, washer);
            } catch (DeviceIsOccupiedException e) {
                return false;
            }
        }
        return true;
    });


    //------------ Water tap ------------//

    private static final BiFunction<Person, WaterTap, Boolean> openWaterTap = ((person, waterTap) -> {
        while (true) {
            try {
                waterTap.open(new Random().nextInt(10, 60), new Random().nextInt(10, 100));
                break;
            } catch (DeviceIsOccupiedException e) {
                return false;
            } catch (WrongDeviceStatusException e) {
                if (!turnOnDevice.apply(person, waterTap)) return false;
            }
        }
        return true;
    });

    private static final BiFunction<Person, WaterTap, Boolean> closeWaterTap = ((person, waterTap) -> {
        waterTap.close();
        return true;
    });


    //------------ WC ------------//

    private static final BiFunction<Person, WC, Boolean> makeToiletThings = ((person, wc) -> {
        wc.makeThings();
        return true;
    });

    private static final BiFunction<Person, WC, Boolean> flushAfterPee = ((person, wc) -> {
        try {
            wc.flush(FlushType.SMALL);
        } catch (DeviceIsBrokenException | ResourceNotAvailableException e) {
            return false;
        }
        return true;
    });

    private static final BiFunction<Person, WC, Boolean> flushAfterPoop = ((person, wc) -> {
        try {
            wc.flush(FlushType.BIG);
        } catch (DeviceIsBrokenException | ResourceNotAvailableException e) {
            return false;
        }
        return true;
    });


    //------------ Alarm ------------//

    // TODO - move to event solving?
    private static final BiFunction<Person, Alarm<?>, Boolean> stopAlarm = ((person, alarm) -> {
        alarm.stop();
        return true;
    });
}
