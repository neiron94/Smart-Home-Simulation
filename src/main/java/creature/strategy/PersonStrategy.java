package creature.strategy;

import consumer.device.Device;
import consumer.device.common.AlarmClock;
import consumer.device.common.Feeder;
import consumer.device.sensored.Alarm;
import creature.Action;
import creature.person.Person;
import event.*;
import place.Room;
import smarthome.Simulation;
import utils.RankedQueue;
import utils.exceptions.NotRepairableDeviceException;

import java.util.function.Function;

public interface PersonStrategy extends Strategy {
    Person getPerson();

    @Override
    default void react(WakeUpEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        getPerson().addToMemory(sequence);
    }

    @Override
    default void react(FillEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());

        // TODO Implement

        getPerson().addToMemory(sequence);
    }

    interface Actions {
        private static void makeRecord(Person person, String description) {
            person.getActivity().addActivity(description);
        }

        private static void makeRecord(Person person, Device device, String description) {
            person.getActivity().addActivity(description);
            person.getActivity().increaseUsage(device);
        }

        //------------ Work with events ------------//

        Function<Action<Person, Event>, Boolean> takeEvent = action -> {
            try {
                action.getSubject().getOrigin().deleteEvent(action.getSubject());
                return true;
            } catch (Exception ignored) {
                return false;
            }
        };

        Function<Action<Person, Event>, Boolean> recordEvent = action -> {
            action.getExecutor().addSolvedEvent(action.getSubject());
            // makeRecord(action.getExecutor(), String.format("Solve %s", action.getSubject().getEventType().toString())); // TODO I'd rather prefer not to write this string
            return true;
        };

        //------------ Fire/Flood/Leak events ------------//

        Function<Action<Person, Room>, Boolean> turnOnElectricity = action -> {
            Simulation.getInstance().getHome().getElectricitySupplySystem().switchRoom(action.getSubject(), true);
            makeRecord(action.getExecutor(), String.format("Turn on electricity in %s", action.getSubject().toString()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> turnOffElectricity = action -> {
            Simulation.getInstance().getHome().getElectricitySupplySystem().switchRoom(action.getSubject(), false);
            makeRecord(action.getExecutor(), String.format("Turn off electricity in %s", action.getSubject().toString()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> turnOnGas = action -> {
            Simulation.getInstance().getHome().getGasSupplySystem().switchRoom(action.getSubject(), true);
            makeRecord(action.getExecutor(), String.format("Turn on gas in %s", action.getSubject().toString()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> turnOffGas = action -> {
            Simulation.getInstance().getHome().getGasSupplySystem().switchRoom(action.getSubject(), false);
            makeRecord(action.getExecutor(), String.format("Turn off gas in %s", action.getSubject().toString()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> turnOnWater = action -> {
            Simulation.getInstance().getHome().getWaterSupplySystem().switchRoom(action.getSubject(), true);
            makeRecord(action.getExecutor(), String.format("Turn on water in %s", action.getSubject().toString()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> turnOffWater = action -> {
            Simulation.getInstance().getHome().getWaterSupplySystem().switchRoom(action.getSubject(), false);
            makeRecord(action.getExecutor(), String.format("Turn off water in %s", action.getSubject().toString()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> cleanFloor = action -> {
            makeRecord(action.getExecutor(), String.format("Clean floor in %s", action.getSubject().toString()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> callFireman = action -> {
            makeRecord(action.getExecutor(), String.format("Call Fire Department to solve fire in %s", action.getSubject().toString()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> callWaterService = action -> {
            makeRecord(action.getExecutor(), String.format("Call Water Service Company to solve flood in %s", action.getSubject().toString()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> callGasService = action -> {
            makeRecord(action.getExecutor(), String.format("Call Gas Service Company to solve leak in %s", action.getSubject().toString()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> putOutFire = action -> {
            makeRecord(action.getExecutor(), String.format("Put out fire in %s", action.getSubject().toString()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> repairWaterSystem = action -> {
            makeRecord(action.getExecutor(), String.format("Solve problem with water system in %s", action.getSubject().toString()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> repairGasSystem = action -> {
            makeRecord(action.getExecutor(), String.format("Solve problem with gas system in %s", action.getSubject().toString()));
            return true;
        };

        //------------ Other ------------// // TODO Rename category

        Function<Action<Person, Device>, Boolean> repairDevice = action -> {
            try {
                action.getSubject().repair();
            } catch (NotRepairableDeviceException e) {
                // TODO Implement
            }

            makeRecord(action.getExecutor(), action.getSubject(), String.format("Repair %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, AlarmClock>, Boolean> stopAlarmClock = action -> {
            action.getSubject().stopAlarm();

            makeRecord(action.getExecutor(), action.getSubject(), String.format("Stop %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Feeder>, Boolean> addFoodFeeder = action -> {
            action.getSubject().addFood();

            makeRecord(action.getExecutor(), action.getSubject(), String.format("Add food to %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Feeder>, Boolean> addWaterFeeder = action -> {
            action.getSubject().addWater();

            makeRecord(action.getExecutor(), action.getSubject(), String.format("Add water to %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Alarm<?>>, Boolean> stopAlarm = action -> {
            action.getSubject().stop();

            makeRecord(action.getExecutor(), action.getSubject(), String.format("Stop %s", action.getSubject()));
            return true;
        };
    }
}
