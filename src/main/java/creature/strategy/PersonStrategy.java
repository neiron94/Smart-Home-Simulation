package creature.strategy;

import consumer.device.Device;
import consumer.device.common.AlarmClock;
import consumer.device.common.Feeder;
import consumer.device.sensored.Alarm;
import creature.Action;
import creature.Creature;
import creature.person.FamilyStatus;
import creature.person.Person;
import event.*;
import event.throwStrategy.*;
import place.Room;
import smarthome.Simulation;
import utils.HelpFunctions;
import utils.RankedQueue;
import utils.exceptions.NotRepairableDeviceException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public interface PersonStrategy extends Strategy {
    Person person();

    @Override
    default void react(WakeUpEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());
        sequence.add(new Action<>(1, true, person(), event, PersonActions.takeEvent));
        sequence.add(new Action<>(1, true, person(), (AlarmClock) event.getCreator(), PersonActions.stopAlarmClock));
        sequence.add(new Action<>(1, true, person(), event, PersonActions.recordEvent));
        person().addToMemory(sequence);
    }

    @Override
    default void react(FillEvent event) {
        RankedQueue<Action<Person, ?>> sequence = new RankedQueue<>(event.getPriority());
        sequence.add(new Action<>(1, true, person(), event, PersonActions.takeEvent));
        sequence.add(new Action<>(1, true, person(), event.getOrigin(), PersonActions.goToRoom));
        sequence.add(new Action<>(3, true, person(), (Feeder) event.getCreator(), PersonActions.addFoodFeeder));
        sequence.add(new Action<>(3, true, person(), (Feeder) event.getCreator(), PersonActions.addWaterFeeder));
        sequence.add(new Action<>(1, true, person(), event, PersonActions.recordEvent));
        person().addToMemory(sequence);
    }

    interface PersonActions {
        //------------ Child ------------//

        Function<Action<Person, Event>, Boolean> callAdult = action -> {
            Person adult = Simulation.getInstance().getCreatures().stream()
                            .filter(creature -> creature instanceof Person)
                            .map(person -> (Person) person)
                            .filter(person -> person.isAlive() && !person.isBusy() && person.isAtHome() && person.getStatus() != FamilyStatus.KID)
                            .findFirst()
                            .orElse(null);

            if (adult != null) {
                adult.setRoom(action.getSubject().getOrigin());
                HelpFunctions.makeRecord(action.getExecutor(), String.format("Call %s to solve %s in %s", adult.getName(), action.getSubject(), action.getSubject().getOrigin()));
                return true;
            } else {
                Room room = Simulation.getInstance().getHome().getFloors().stream()
                        .flatMap(floor -> floor.getRooms().stream())
                        .findAny().orElseThrow(NoSuchElementException::new);
                action.getExecutor().setRoom(room);
                HelpFunctions.makeRecord(action.getExecutor(), String.format("Search an adult to tell about %s", action.getSubject()));
                return false;
            }
        };

        Function<Action<Person, Void>, Boolean> panic = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), "Panic");
            return true;
        };

        //------------ Work with events ------------//

        Function<Action<Person, Room>, Boolean> goToRoom = action -> {
            if (action.getExecutor().getRoom() != action.getSubject()) {
                action.getExecutor().setRoom(action.getSubject());
                HelpFunctions.makeRecord(action.getExecutor(), String.format("Go to %s", action.getSubject()));
            }
            return true;
        };

        Function<Action<Person, Event>, Boolean> takeEvent = action -> {
            boolean deleteResult = false;
            if (action.getSubject().getThrowStrategy() instanceof HomeThrowStrategy) deleteResult = Simulation.getInstance().getHome().deleteEvent(action.getSubject());
            else if (action.getSubject().getThrowStrategy() instanceof FloorThrowStrategy) deleteResult = action.getSubject().getOrigin().getFloor().deleteEvent(action.getSubject());
            else if (action.getSubject().getThrowStrategy() instanceof RoomThrowStrategy) deleteResult = action.getSubject().getOrigin().deleteEvent(action.getSubject());

            if (deleteResult) {
                HelpFunctions.makeRecord(action.getExecutor(), String.format("Take %s", action.getSubject().getEventType()));
                return true;
            } else return false;
        };

        Function<Action<Person, Event>, Boolean> recordEvent = action -> {
            action.getExecutor().addSolvedEvent(action.getSubject());
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Solve %s", action.getSubject().getEventType()));
            return true;
        };

        //------------ Fire/Flood/Leak events ------------//

        Function<Action<Person, Room>, Boolean> turnOnElectricity = action -> {
            Simulation.getInstance().getHome().getElectricitySupplySystem().switchRoom(action.getSubject(), true);
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Turn on electricity in %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> turnOffElectricity = action -> {
            Simulation.getInstance().getHome().getElectricitySupplySystem().switchRoom(action.getSubject(), false);
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Turn off electricity in %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> turnOnGas = action -> {
            Simulation.getInstance().getHome().getGasSupplySystem().switchRoom(action.getSubject(), true);
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Turn on gas in %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> turnOffGas = action -> {
            Simulation.getInstance().getHome().getGasSupplySystem().switchRoom(action.getSubject(), false);
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Turn off gas in %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> turnOnWater = action -> {
            Simulation.getInstance().getHome().getWaterSupplySystem().switchRoom(action.getSubject(), true);
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Turn on water in %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> turnOffWater = action -> {
            Simulation.getInstance().getHome().getWaterSupplySystem().switchRoom(action.getSubject(), false);
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Turn off water in %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> cleanFloor = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Clean floor in %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> callFireman = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Call Fire Department to solve fire in %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> callWaterService = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Call Water Service Company to solve flood in %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> callGasService = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Call Gas Service Company to solve leak in %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> putOutFire = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Put out fire in %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> repairWaterSystem = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Solve problem with water system in %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Room>, Boolean> repairGasSystem = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Solve problem with gas system in %s", action.getSubject()));
            return true;
        };

        //------------ Alert event ------------//

        Function<Action<Person, Alarm<?>>, Boolean> stopAlarm = action -> {
            action.getSubject().stop();
            HelpFunctions.makeRecord(action.getExecutor(), action.getSubject(), String.format("Stop %s", action.getSubject()));
            return true;
        };

        //------------ Fill event ------------//

        Function<Action<Person, Feeder>, Boolean> addFoodFeeder = action -> {
            if (action.getSubject().getFoodLevel() == 0) {
                action.getSubject().addFood();
                HelpFunctions.makeRecord(action.getExecutor(), action.getSubject(), String.format("Add food to %s", action.getSubject()));
            }
            return true;
        };

        Function<Action<Person, Feeder>, Boolean> addWaterFeeder = action -> {
            if (action.getSubject().getWaterLevel() == 0) {
                action.getSubject().addWater();
                HelpFunctions.makeRecord(action.getExecutor(), action.getSubject(), String.format("Add water to %s", action.getSubject()));
            }
            return true;
        };

        //------------ WakeUp event ------------//

        Function<Action<Person, AlarmClock>, Boolean> stopAlarmClock = action -> {
            action.getSubject().stopAlarm();

            HelpFunctions.makeRecord(action.getExecutor(), action.getSubject(), String.format("Stop %s", action.getSubject()));
            return true;
        };

        //------------ Brake event ------------//

        Function<Action<Person, Device>, Boolean> findManual = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Find manual of %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Device>, Boolean> readManual = action -> {
            action.getSubject().getManual().read();
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Read manual of %s", action.getSubject()));
            return true;
        };

        Function<Action<Person, Device>, Boolean> checkWarranty = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Check warranty of %s", action.getSubject()));
            return action.getSubject().getManual().getGuaranteeExpirationDate().isBefore(Simulation.getInstance().getCurrentTime());
        };

        Function<Action<Person, Device>, Boolean> bringDeviceToService = action -> {
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Bring %s to Service Center", action.getSubject()));
            return action.getSubject().getManual().getGuaranteeExpirationDate().isBefore(Simulation.getInstance().getCurrentTime());
        };

        Function<Action<Person, Device>, Boolean> takeDeviceFromService = action -> {
            try {
                action.getSubject().repair();
                HelpFunctions.makeRecord(action.getExecutor(), String.format("Take repaired %s from Service Center", action.getSubject()));
            } catch (NotRepairableDeviceException e) {
                action.getSubject().copy();
                action.getSubject().setFunctional(false);
                HelpFunctions.makeRecord(action.getExecutor(), String.format("Take new %s from Service Center", action.getSubject()));
            }
            return true;
        };

        Function<Action<Person, Device>, Boolean> repairDevice = action -> {
            try {
                action.getSubject().repair();
                HelpFunctions.makeRecord(action.getExecutor(), String.format("Repair %s", action.getSubject()));
            } catch (NotRepairableDeviceException e) {
                action.getSubject().setFunctional(false);
                HelpFunctions.makeRecord(action.getExecutor(), String.format("Throw old %s", action.getSubject()));
                action.getSubject().copy();
                HelpFunctions.makeRecord(action.getExecutor(), String.format("Buy new %s", action.getSubject()));
            }
            return true;
        };

        Function<Action<Person, Device>, Boolean> buyNewDevice = action -> {
            action.getSubject().setFunctional(false);
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Throw old %s", action.getSubject()));
            action.getSubject().copy();
            HelpFunctions.makeRecord(action.getExecutor(), String.format("Buy new %s", action.getSubject()));
            return true;
        };
    }
}
