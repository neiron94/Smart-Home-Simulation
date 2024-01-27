/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package creature.person;

import consumer.device.Device;
import consumer.device.sensored.AC;
import event.Event;
import event.FireEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import place.Room;
import smarthome.Simulation;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private static Simulation simulation;
    private static Room room;

    @BeforeAll
    static void init() {
        simulation = Simulation.getInstance();
        room = simulation.getHome().getFloors().get(0).getRooms().get(0);
    }

    @Test
    void personTakesEventTest() {
        Person person = new Person("Ales", Gender.MALE, FamilyStatus.ELDER, room);
        Device device = new AC(0, room);
        Event event = new FireEvent(device, room);
        event.throwEvent();
        person.routine();
        person.routine();

        assertFalse(room.getEvents().contains(event));
    }

    @Test
    void dieOnMaxHunger() {
        Person person = new Person("Ales", Gender.MALE, FamilyStatus.ELDER, room);
        person.setHunger(100);
        person.routine();

        assertFalse(person.isAlive());
    }

    @Test
    void shitOnMaxFullness() {
        Person person = new Person("Ales", Gender.MALE, FamilyStatus.ELDER, room);
        person.setFullness(100);
        person.routine();

        assertEquals(0, person.getFullness());
    }
}