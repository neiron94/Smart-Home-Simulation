package consumer.device;

import place.Room;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

public class DeviceFactory {
    private final DeviceType type;

    public DeviceFactory(String type) {
        try {
            this.type = DeviceType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("Invalid Device Type");
        }
    }

    public Device createDevice(int id, Room room) {
        try {
            Class<?> deviceClass = type.getDeviceClass();
            Constructor<?> constructor = deviceClass.getConstructor(int.class, Room.class);
            return (Device) constructor.newInstance(id, room);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to create device");
        }
    }
}
