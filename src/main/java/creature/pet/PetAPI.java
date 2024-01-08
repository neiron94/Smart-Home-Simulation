package creature.pet;

import consumer.device.common.Feeder;
import utils.exceptions.WrongDeviceStatusException;

import java.util.function.BiFunction;

public class PetAPI {

    //------------ Feeder ------------//

    private static final BiFunction<Pet, Feeder, Boolean> drinkWaterFeeder = ((pet, feeder) -> {
        try {
            feeder.drinkWater(0); // TODO - depends on hunger?
        } catch (WrongDeviceStatusException e) {
            return false;
        }
        return true;
    });

    private static final BiFunction<Pet, Feeder, Boolean> eatFoodFeeder = ((pet, feeder) -> {
        try {
            feeder.eatFood(0); // TODO - depends on hunger?
        } catch (WrongDeviceStatusException e) {
            return false;
        }
        return true;
    });
}
