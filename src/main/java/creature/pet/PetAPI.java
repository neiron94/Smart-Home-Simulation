package creature.pet;

import consumer.device.Device;
import consumer.device.common.Feeder;
import creature.Action;
import utils.exceptions.WrongDeviceStatusException;
import java.util.function.Function;

public class PetAPI {
    private static void makeRecord(Pet pet, String description) {
        pet.getActivity().addActivity(description);
    }

    private static void makeRecord(Pet pet, Device device, String description) {
        pet.getActivity().addActivity(description);
        pet.getActivity().increaseUsage(device);
    }

    //---------------------------- Complex functions ----------------------------//



    //---------------------------- Simple functions ----------------------------//

    //------------ Feeder ------------//

    private static final Function<Action<Pet, Feeder>, Boolean> drinkWaterFeeder = action -> {
        try {
            action.getSubject().drinkWater(0); // TODO - depends on hunger?
        } catch (WrongDeviceStatusException e) {
            return false;
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Drink from %s", action.getSubject()));
        return true;
    };

    private static final Function<Action<Pet, Feeder>, Boolean> eatFoodFeeder = action -> {
        try {
            action.getSubject().eatFood(0); // TODO - depends on hunger?
        } catch (WrongDeviceStatusException e) {
            return false;
        }

        makeRecord(action.getExecutor(), action.getSubject(), String.format("Eat from %s", action.getSubject()));
        return true;
    };
}
