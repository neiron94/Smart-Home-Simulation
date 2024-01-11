package consumer.device;

import place.Room;
import utils.HelpFunctions;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

/**
 * Class for creating devices. Is used when devices are read from configuration file.
 */
public class DeviceFactory {
    private final DeviceType type;

    /**
     * Creates factory for creating devices of type, specified by parameter.
     * @param type String which represents type of the device. It's of type String,
     *             because device are created from the json file.
     */
    public DeviceFactory(String type) {
        try {
            this.type = DeviceType.valueOf(type);
        } catch (IllegalArgumentException e) {
            HelpFunctions.logger.warn(String.format("Invalid device type '%s'. Device wasn't created", type));
            throw new NoSuchElementException();
        }
    }

    /**
     * Creation method. Return type is void, because device automatically
     * adds itself to proper places in the constructor.
     * @param id ID of the device.
     * @param room Start room, in which device is located.
     */
    public void createDevice(int id, Room room) {
        try {
            Class<?> deviceClass = type.getDeviceClass();
            Constructor<?> constructor = deviceClass.getConstructor(int.class, Room.class);
            Device device = (Device) constructor.newInstance(id, room);
            HelpFunctions.logger.info(String.format("%s was created in %s", device, room));
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            HelpFunctions.logger.warn(String.format("Problem with creating %s_%d. Device wasn't created", type.getName(), id));
        }
    }
}
