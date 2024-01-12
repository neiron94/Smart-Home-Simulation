# Smart Home Simulation

------------
### Functional requirements
------------

- ###### F1
> Entities we work with are house, window (+outside blinds), floor in the house, sensor, device (=appliance), person, car, bike, pet other than farm type, plus any other entities.

Main entities in the program are [Street](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/Street.java), [Home](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/Home.java), [Room](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/Room.java), [Person](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Person.java), [Pet](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/pet/Pet.java), [Device](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java), [Sensor](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/sensored/sensor/Sensor.java).

- ###### F2
> Each device in the house has an API to control it. Devices have a state that can be changed using the API to control it. Actions from the API are applicable according to the state of the device. Selected devices can also have content.

[Most of the devices](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/java/consumer/device/common) have API functions that Person use to interact with them (eg. `startWash()` in [Dishwasher](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/Dishwasher.java)). 
[Other devices](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/java/consumer/device/sensored) do not provide API directly for human. Instead of this they use their sensors to be controlled. Person interacts with [Control Panel](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/ControlPanel.java) to change parameters, which ones have an impact to devices through sensors.
Each device (with few exclusions) can be in one of [different states](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/DeviceStatus.java) at the moment. This state have impact on device consumption and allowed functions to use.
Some devices (eg. [TV](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/TV.java), [CoffeeMachine](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/CoffeeMachine.java), ...) have content (eg. current [Video](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/entertainment/Video.java) in TV; water, milk, coffee beans in CoffeeMachine and so on).

- ###### F3
> Appliances have their consumption in active state, idle state, off state.

[State](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/DeviceStatus.java) of the device impacts device consumption rate using state multiplier. This multiplier applies to default device rate.

- ###### F4
> Each device has an API to collect data about that device. We collect data about devices such as electricity, gas, water consumption and functionality (decreases linearly with time).

Every device is consumer and stores amount of consumed resource in [Electricity](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/supplySystem/ElectricitySupplySystem.java)/[Water](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/supplySystem/WaterSupplySystem.java)/[Gas](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/supplySystem/GasSupplySystem.java) meters where can be easily taken from.
Those three classes have information about consumption of every existing device and have an ability to switch them altogether or by concrete room (changing resource availability in room) just as in real life.

- ###### F5
> Individual persons and animals can perform activities (actions) that have an effect on the device or another person.

[Persons](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Person.java) and [pets](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/pet/Pet.java) are interacting with devices using their API functions. It changes actual state of the device (such as device state `ON`/`OFF`/`STANDBY`, occupancy and so on).

- ###### F6
> Individual devices and persons are in the one room at any time (unless they are doing sports) and randomly generate events (an event can be important information or an alert).

Both [people]() and [devices](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java) can be only in one room at the moment, which is quite physically logic. When person is outside (doing sports, went for a walk etc.) he marked as not `atHome`, but remember the room he lastly was.
[Devices](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/java/consumer/device) communicate with people using [Event](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/Event.java) generation.
Events can have restricted visibility: for example person can see [flood](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/FloodEvent.java) from [washing machine](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/Washer.java) only if stands in the same room. In other hand [alert](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/AlertEvent.java) about [fire](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/FireEvent.java) is loud enough to be heard throughout home.

- ###### F7
> Events are picked up and handled by the appropriate person(s) or device(s).

[Events](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/Event.java) are taken only by persons who can solve it. Solution (as well as reaction) [strategy](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/java/creature/strategy) depends on concrete attributes (in our case [gender](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Gender.java) and [family status](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/FamilyStatus.java) representing age). 
Concrete event can be solved only by one person.

- ###### F8
> Reports generating
> - HouseConfigurationReport:
    > All configuration data of the house maintaining the hierarchy - house -> floor ->
    > -> room -> window -> blinds etc. (+ residents)
> - EventReport:
    > Report where we group events by type, event source and event target (what entity handled the event)
> - ActivityAndUsageReport:
    > Report of actions (activities) of each person and animal, how many times which person used which device.
> - ConsumptionReport:
    > How much electricity, gas, water each appliance consumed. Including a finances.

[House Configuration Report](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/report/HouseConfigurationReport.java) creates once [simulation](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/smarthome/Simulation.java) started. 
At the end of every simulation day remained three [reports](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/java/report) are generated with all information about living in home this day.

- ###### F9
> When a device breaks, the house resident must examine the documentation for the device - find the warranty card, review the repair manual and take corrective action (e.g., do-it-yourself repair, purchase a new one, etc.).

[Devices](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java) can break, which is absolutely natural. It can happen by several reasons:
1. Device is loosing its durability linearly with time
2. Device is in permanently use
3. Pets are able to damage devices
4. [Electricity](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/ElectricityConsumer.java) using device may catch fire 
5. Device can simply randomly break with no reason

In all cases proper person would do something with it. Firstly he will check [warranty card](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Manual.java) if device can be given to Service Center.
If not - following steps depend on [solving strategy](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/strategy/AdultStrategy.java).

- ###### F10
> The family is active and spends their leisure time in roughly the same proportion (50% using appliances in the house and 50% playing sports using bicycles or skis). When there is no free appliance or sports equipment, the person waits.

All home residents have [sequences](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/PersonAPI.java) of actions they can perform.
These include both walking outside and using devices. Ratios of using functions can be easily varied.
If person is trying to use device, which already `isOccupied` by other person, he is reasonly not able to do this.

------------
### Non-Functional requirements
------------

------------
### UML Diagrams
------------

------------
### Design Patterns
------------

------------
### Program Input
------------

<details>
  <summary>
    <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/resources/config/Simulation.json">
      <b>Simulation.json</b>
    </a>
  </summary>
    <table>
      <tr>
        <td><code>"config" : "1"</code></td>
        <td>Current configuration to load (name of folder)</td>
      </tr>
      <tr>
        <td><code>"duration" : 30</code></td>
        <td>Simulation duration (in days)</td>
      </tr>
    </table>
<p>
Configuration folder should exist in <code>config</code> directory and have <code>Home.json</code>, <code>Creature.json</code>, <code>Device.json</code>.<br>
Duration should be integer non-negative value.
</p>
</details>

<details>
  <summary>
    <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/resources/config/1/Home.json">
      <b>Home.json</b>
    </a>
  </summary>
    <table>
      <tr>
        <td><code>"HOME" : [1, 2]</code></td>
        <td>Floors in home</td>
      </tr>
      <tr>
        <td><code>"FLOOR" : [[1, 2], [3, 4]]</code></td>
        <td>Rooms at floor (each floor should have it own pair of brackets) </td>
      </tr>
      <tr>
        <td><code>"ROOM" : ["KITCHEN", "HALL", "TOILET", "BEDROOM"]</code></td>
        <td>List of rooms (types)</td>
      </tr>
    </table>
<p>
Numbers are IDs (integer) and should be unique for floors and rooms. They are connected with values in proper array (1-based).<br>
Room types should match these <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/RoomType.java">values</a>.
</p>
</details>

<details>
  <summary>
    <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/resources/config/1/Device.json">
      <b>Device.json</b>
    </a>
  </summary>
    <table>
      <tr>
        <td><code>"FRIDGE" : [1, 2, 3]</code></td>
        <td>Device type and its instances</td>
      </tr>
    </table>
<p>
Numbers are room IDs (integer).<br>
Device types should match these <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/DeviceType.java">values</a>.
</p>
</details>

<details>
  <summary>
    <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/resources/config/1/Creature.json">
      <b>Creature.json</b>
    </a>
  </summary>
    <table>
      <tr>
        <td><code>"name" : "Jirka"</code></td>
        <td>Person's name</td>
      </tr>
      <tr>
        <td><code>"gender" : "MALE"</code></td>
        <td>Person's gender</td>
      </tr>
      <tr>
        <td><code>"status" : "ADULT"</code></td>
        <td>Person's family status</td>
      </tr>
    </table>
<p>
Gender should match these <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Gender.java">values</a>.<br>
Family status should match these <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/FamilyStatus.java">values</a>.
</p>
    <table>
      <tr>
        <td><code>"name" : "Garfield"</code></td>
        <td>Pet's name</td>
      </tr>
      <tr>
        <td><code>"gender" : "CAT"</code></td>
        <td>Pet's type</td>
      </tr>
    </table>
<p>
Pet type should match these <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/pet/PetType.java">values</a>.
</p>
</details>

<details>
  <summary>
    <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/resources/config/Content.json">
      <b>Content.json</b>
    </a>
  </summary>
    <table>
      <tr>
        <td><code>"author" : "MC Adolfeen"</code></td>
        <td>Author of song</td>
      </tr>
      <tr>
        <td><code>"album" : "Je mi to jedno"</code></td>
        <td>Name of album</td>
      </tr>
      <tr>
        <td><code>"name" : "Corona Firus"</code></td>
        <td>Name of song</td>
      </tr>
      <tr>
        <td><code>"genre" : "HIP_HOP"</code></td>
        <td>Song genre</td>
      </tr>
      <tr>
        <td><code>"duration" : 3</code></td>
        <td>Duration (in minutes)</td>
      </tr>
    </table>
<p>
Duration should be integer non-negative value.<br>
Song genre should match these <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/entertainment/SongGenre.java">values</a>.
</p>
    <table>
      <tr>
        <td><code>"name" : "Baby Shark Dance"</code></td>
        <td>Name of video</td>
      </tr>
      <tr>
        <td><code>"description" : "Baby shark, doo doo doo doo doo doo."</code></td>
        <td>Short video description</td>
      </tr>
      <tr>
        <td><code>"platform" : "YOUTUBE"</code></td>
        <td>Video platform (channel, streaming service, ...)</td>
      </tr>
      <tr>
        <td><code>"duration" : 2</code></td>
        <td>Duration (in minutes)</td>
      </tr>
    </table>
<p>
Duration should be integer non-negative value.<br>
Video platform should match these <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/entertainment/VideoPlatform.java">values</a>.
</p>
    <table>
      <tr>
        <td><code>"name" : "2048"</code></td>
        <td>Name of game</td>
      </tr>
      <tr>
        <td><code>"description" : "Move bricks to win."</code></td>
        <td>Short game description</td>
      </tr>
      <tr>
        <td><code>"genre" : "PUZZLE"</code></td>
        <td>Game genre</td>
      </tr>
    </table>
<p>
Game genre should match these <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/entertainment/GameGenre.java">values</a>.
</p>
</details>

<details>
  <summary>
    <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/resources/config/RoomConfiguration.json">
      <b>RoomConfiguration.json</b>
    </a>
  </summary>
    <table>
      <tr>
        <td><code>"name" : "Bathhouse"</code></td>
        <td>Configuration name</td>
      </tr>
      <tr>
        <td><code>"temperature" : 90</code></td>
        <td>Set temperature (in ºC)</td>
      </tr>
      <tr>
        <td><code>"humidity" : 85</code></td>
        <td>Set humidity (in %)</td>
      </tr>
      <tr>
        <td><code>"brightness" : 20</code></td>
        <td>Set brightness (in %)</td>
      </tr>
      <tr>
        <td><code>"color" : [255, 253, 141]</code></td>
        <td>Set color of light</td>
      </tr>
    </table>
<p>
Temperature can be negative and float.<br>
Humidity should be in range 0-100. Can be float.<br>
Brightness should be in range 0-100. Can be float.<br>
Color should have three integers in range 0-255 representing <code>RED</code>, <code>GREEN</code>, <code>BLUE</code> components.
</p>
</details>

<details>
  <summary>
    <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/resources/config/Weather.json">
      <b>Weather.json</b>
    </a>
  </summary>
    <table>
      <tr>
        <td><code>"temperature" : [[-10.2, -11.2, ...], ...]</code></td>
        <td>Temperature map</td>
      </tr>
      <tr>
        <td><code>"humidity" : [[69.3, 68.1, ...], ...]</code></td>
        <td>Humidity map</td>
      </tr>
      <tr>
        <td><code>"brightness" : [[16.8, 16.9, ...], ...]</code></td>
        <td>Brightness map</td>
      </tr>
    </table>
<p>
Temperature can be negative and float.<br>
Humidity should be in range 0-100. Can be float.<br>
Brightness should be in range 0-100. Can be float.<br>
Each map should contain 12 arrays of 24 values representing temperature/humidity/brightness in concrete hour of concrete month (starting from 00:00 and January).
</p>
</details>

------------
### Program Output
------------

