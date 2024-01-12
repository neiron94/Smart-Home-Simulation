# Smart Home Simulation

------------
### Project information
------------

Present project simulates life in smart home. Residents using devices on their needs and living their happiest life.

### Main features

- Dynamic weather changes based on real data
- Real existing media content (videos, songs, games)
- Finest adjustment of the room's ecosystem through a control panel
- Random event generation: from device malfunctions to complete shutdowns
- An astonishing variety of devices
- Rich diversity of people's functionality
- Detailed and visual reports on ongoing activities

### Application running

1. Choose existing configuration or create a new one based on [tutorial](https://github.com/neiron94/Smart-Home-Simulation/edit/develop/README.md#application-input).
2. Set proper [simulation settings](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/resources/config/Simulation.json). 
3. Run [`main` function](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/smarthome/SmartHome.java).

------------
### Functional requirements
------------

- #### F1
> Entities we work with are house, window (+outside blinds), floor in the house, sensor, device (=appliance), person, car, bike, pet other than farm type, plus any other entities.

Main entities in the project are [Street](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/Street.java), [Home](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/Home.java), [Room](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/Room.java), [Person](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Person.java), [Pet](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/pet/Pet.java), [Device](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java), [Sensor](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/sensored/sensor/Sensor.java).

------------
- #### F2
> Each device in the house has an API to control it. Devices have a state that can be changed using the API to control it. Actions from the API are applicable according to the state of the device. Selected devices can also have content.

[Most of the devices](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/java/consumer/device/common) have API functions that Person use to interact with them (eg. `startWash()` in [Dishwasher](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/Dishwasher.java)). 
[Other devices](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/java/consumer/device/sensored) do not provide API directly for human. Instead of this they use their sensors to be controlled. Person interacts with [Control Panel](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/ControlPanel.java) to change parameters, which ones have an impact to devices through sensors.
Each device (with few exclusions) can be in one of [different states](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/DeviceStatus.java) at the moment. This state have impact on device consumption and allowed functions to use.
Some devices (eg. [TV](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/TV.java), [CoffeeMachine](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/CoffeeMachine.java), ...) have content (eg. current [Video](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/entertainment/Video.java) in TV; water, milk, coffee beans in CoffeeMachine and so on).

------------
- #### F3
> Appliances have their consumption in active state, idle state, off state.

[State](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/DeviceStatus.java) of the device impacts device consumption rate using state multiplier. This multiplier applies to default device rate.

------------
- #### F4
> Each device has an API to collect data about that device. We collect data about devices such as electricity, gas, water consumption and functionality (decreases linearly with time).

Every device is consumer and stores amount of consumed resource in [Electricity](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/supplySystem/ElectricitySupplySystem.java)/[Water](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/supplySystem/WaterSupplySystem.java)/[Gas](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/supplySystem/GasSupplySystem.java) meters where can be easily taken from.
Those three classes have information about consumption of every existing device and have an ability to switch them altogether or by concrete room (changing resource availability in room) just as in real life.

------------
- #### F5
> Individual persons and animals can perform activities (actions) that have an effect on the device or another person.

[Persons](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Person.java) and [pets](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/pet/Pet.java) are interacting with devices using their API functions. It changes actual state of the device (such as device state `ON`/`OFF`/`STANDBY`, occupancy and so on).

------------
- #### F6
> Individual devices and persons are in the one room at any time (unless they are doing sports) and randomly generate events (an event can be important information or an alert).

Both [people]() and [devices](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java) can be only in one room at the moment, which is quite physically logic. When person is outside (doing sports, went for a walk etc.) he marked as not `atHome`, but remember the room he lastly was.
[Devices](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/java/consumer/device) communicate with people using [Event](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/Event.java) generation.
Events can have restricted visibility: for example person can see [flood](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/FloodEvent.java) from [washing machine](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/Washer.java) only if stands in the same room. In other hand [alert](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/AlertEvent.java) about [fire](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/FireEvent.java) is loud enough to be heard throughout home.

------------
- #### F7
> Events are picked up and handled by the appropriate person(s) or device(s).

[Events](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/Event.java) are taken only by persons who can solve it. Solution (as well as reaction) [strategy](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/java/creature/strategy) depends on concrete attributes (in our case [gender](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Gender.java) and [family status](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/FamilyStatus.java) representing age). 
Concrete event can be solved only by one person.

------------
- #### F8
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

------------
- #### F9
> When a device breaks, the house resident must examine the documentation for the device - find the warranty card, review the repair manual and take corrective action (e.g., do-it-yourself repair, purchase a new one, etc.).

[Devices](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java) can break, which is absolutely natural. It can happen by several reasons:
1. Device is loosing its durability linearly with time
2. Device is in permanently use
3. Pets are able to damage devices
4. [Electricity](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/ElectricityConsumer.java) using device may catch fire 
5. Device can simply randomly break with no reason

In all cases proper person would do something with it. Firstly he will check [warranty card](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Manual.java) if device can be given to Service Center.
If not - following steps depend on [solving strategy](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/strategy/AdultStrategy.java).

------------
- #### F10
> The family is active and spends their leisure time in roughly the same proportion (50% using appliances in the house and 50% playing sports using bicycles or skis). When there is no free appliance or sports equipment, the person waits.

All home residents have [sequences](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/PersonAPI.java) of actions they can perform.
These include both walking outside and using devices. Ratios of using functions can be easily varied.
If person is trying to use device, which already `isOccupied` by other person, he is reasonly not able to do this.

------------
### Non-Functional requirements
------------

- #### NF1
> Project must have UML Diagrams.

Project has next [UML diagrams](https://github.com/neiron94/Smart-Home-Simulation/edit/develop/README.md#uml-diagrams):
- [Class diagram]()
- [Use-case diagram]()

------------

- #### NF2
> Project must have ReadMe.

Project has its [ReadMe](https://github.com/neiron94/Smart-Home-Simulation/edit/develop/README.md#smart-home-simulation).

------------

- #### NF3
> Project must have public API and JavaDoc.

Project has JavaDoc for every public function and attribute (except getters and setters). <br>
Project classes (such as [Device](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/Creature.java), [Creature](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java) etc.) can be freely used outside the main project.

------------

- #### NF4
> No authentication or authorization required.

Application doesn't demand any authentication nor authorization.

------------

- #### NF5
> Application can only run in one JVM.

Application runs in one JVM as a single-thread application.

------------

- #### NF6
> Write the application in such a way that methods and variables that should not be
available to other classes would be private. The generated JavaDoc should have as few public methods and variables as possible.

Project has proper level of encapsulation.

------------

- #### NF7
> Reports are generated to a text file.

[Required reports](https://github.com/neiron94/Smart-Home-Simulation/edit/develop/README.md#application-output) are generating as text files. <br>
- HouseConfigurationReport, ActivityAndUsageReport - `.txt`
- ConsumptionReport, EventReport - `.tsv`

------------

- #### NF8
> The configuration of the house, facilities and residents of the house can be loaded directly from the class or
an external file (json is preferred). Two configurations is required.<br>
> Minimal configuration filling: 6 people, 3 animals, 8 types of devices, 20 devices, 6 rooms.

Application has two [input configurations](https://github.com/neiron94/Smart-Home-Simulation/edit/develop/README.md#application-input):
- [1](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/resources/config/1): 6 people, 3 animals, 22 types of devices, 40 devices, 6 rooms.
- [2](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/resources/config/2): 8 people, 6 animals, 26 types of devices, 126 devices, 13 rooms.

------------
### UML Diagrams
------------

------------
### Design Patterns
------------

- #### [Visitor](https://refactoring.guru/design-patterns/visitor) _(4 implementations)_

**Element** - [Consumer](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/Consumer.java)<br>
accept() = `accept()`<br>

**Element A** - [ElectricityConsumer](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/ElectricityConsumer.java)<br>
**Element B** - [WaterConsumer](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/WaterConsumer.java)<br>
**Element C** - [GasConsumer](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/GasConsumer.java)<br>

**Visitor** - [Visitor](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/Visitor.java)<br>
_visit()_ = `visit()`<br>
Visit function controls Element type<br>

**ConcreteVisitor A** - [AddVisitor](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/AddVisitor.java)<br>
_visit(Element A)_ = `visit(ElectricityConsumer)`<br>
_visit(Element B)_ = `visit(WaterConsumer)`<br>
_visit(Element C)_ = `visit(GasConsumer)`<br>
Visitor feature - add consumer device to all places (Simulation + proper SupplySystem)<br>

**ConcreteVisitor B** - [DeleteVisitor](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/DeleteVisitor.java)<br>
_visit(Element A)_ = `visit(ElectricityConsumer)`<br>
_visit(Element B)_ = `visit(WaterConsumer)`<br>
_visit(Element C)_ = `visit(GasConsumer)`<br>
Visitor feature - delete consumer device from all places (Simulation + proper SupplySystem)<br>

**ConcreteVisitor C** - [ConsumeVisitor](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/ConsumeVisitor.java)<br>
_visit(Element A)_ = `visit(ElectricityConsumer)`<br>
_visit(Element B)_ = `visit(WaterConsumer)`<br>
_visit(Element C)_ = `visit(GasConsumer)`<br>
Visitor feature - make device consumption and write it to proper SupplySystem<br>

**ConcreteVisitor D** - [EventVisitor](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/EventVisitor.java)<br>
_visit(Element A)_ = `visit(ElectricityConsumer)`<br>
_visit(Element B)_ = `visit(WaterConsumer)`<br>
_visit(Element C)_ = `visit(GasConsumer)`<br>
Visitor feature - creates disaster events (fire/flood/leak) for proper consumer<br>

------------
- #### [Memento](https://www.baeldung.com/java-memento-design-pattern) _(1 implementation)_

**Memento** - [RoomConfiguration](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/RoomConfiguration.java)<br>

**Originator** - [ControlPanel](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/ControlPanel.java)<br>
_save()_ = `saveConfiguration()`<br>
_restore()_ = `loadConfiguration()`<br>

**Caretaker** - [Person](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Person.java)<br>
_hitSave()_ = `saveConfiguration()`<br>
_hitUndo()_ = `loadConfiguration()`<br>

State - preferred temperature, humidity, brightness and light color (fields in the ControlPanel).
State is saved in static field in ControlPanel.

------------
- #### [Prototype](https://refactoring.guru/design-patterns/prototype) _(2 implementations)_

**Prototype** - [Prototype](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/utils/Prototype.java)<br>

**ConcretePrototype** - [RoomConfiguration](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/RoomConfiguration.java)<br>
_clone()_ = `copy()`<br>

**Prototype** - [Prototype](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/utils/Prototype.java)<br>

**ConcretePrototype** - [Device](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java) (+ all inheritors)<br>
_clone()_ = `clone()` <br>

------------
- #### [Builder](https://refactoring.guru/design-patterns/builder) _(1 implementation)_

**Client** - [Simulation](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/smarthome/Simulation.java) <br>

**ConcreteBuilder** - [HomeBuilder](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/HomeBuilder.java) <br>
_reset()_ = `reset()` <br>
_getResult()_ = `getHome()` <br>
_buildStepA()_ = `buildHome()` <br>
_buildStepB()_ = `buildFloor()` <br>
_buildStepC()_ = `buildRoom()` <br>

There is no need in project in Builder interface as long as there is only one type of Home creating.
We don't also need a Director class because this role is played by home draft read from configuration file.

------------
- #### [AbstractFactory](https://refactoring.guru/design-patterns/abstract-factory) _(1 implementation)_

**AbstractFactory** - [ReportFactory](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/report/factory/ReportFactory.java) <br>
_createProduct()_ = `createReport()` <br>

**ConcreteFactory1** - [ConfigurationReportFactory](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/report/factory/ConfigurationReportFactory.java) <br>
_createProduct()_ = `createReport()` <br>

**ConcreteFactory2** - [ConsumptionReportFactory](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/report/factory/ConsumptionReportFactory.java) <br>
_createProduct()_ = `createReport()` <br>

**ConcreteFactory3** - [ActivityReportFactory](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/report/factory/ActivityReportFactory.java) <br>
_createProduct()_ = `createReport()` <br>

**ConcreteFactory4** - [EventReportFactory](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/report/factory/EventReportFactory.java) <br>
_createProduct()_ = `createReport()` <br>

**AbstractProduct** - [Report](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/report/Report.java) <br>
**ConcreteProduct1** - [HouseConfigurationReport](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/report/HouseConfigurationReport.java) <br>
**ConcreteProduct2** - [ConsumptionReport](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/report/ConsumptionReport.java) <br>
**ConcreteProduct3** - [ActivityAndUsageReport](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/report/ActivityAndUsageReport.java) <br>
**ConcreteProduct4** - [EventReport](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/report/EventReport.java) <br>

**Client** - [ReportCreator](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/report/ReportCreator.java) <br>
_someOperationA()_ = `createConfigurationReport()` <br>
_someOperationB()_ = `createConsumptionReports()` <br>
_someOperationC()_ = `createActivityReports()` <br>
_someOperationD()_ = `createEventReports()` <br>

------------
- #### [Singleton](https://refactoring.guru/design-patterns/singleton) _(2 implementations)_

**Singleton** - [Simulation](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/smarthome/Simulation.java) <br>

**Client** - [SmartHome](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/smarthome/SmartHome.java) and so on <br>

<br>

**Singleton** - [Street](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/Street.java) <br>

**Client** - [Simulation](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/smarthome/Simulation.java) and so on <br>

------------
- #### [Facade](https://refactoring.guru/design-patterns/facade) _(2 implementations)_

**ClientA** - [Simulation]() <br>
**ClientB** - [HomeBuilder]() <br>
**ClientC** - [ControlPanel]() <br>
**ClientD** - [EntertainmentService]() <br>
**ClientE** - [Street]() <br>

**Facade** - [ConfigurationReader]() <br>
`readSimulationConfig()` - (A) <br>
`readHomeConfig()` - (B) <br>
`readDeviceConfig()` - (A) <br>
`readCreatureConfig()` - (A) <br>
`readRoomConfigurationConfig()` - (C) <br>
`readContentConfig()` - (D) <br>
`readWeatherConfig()` - (E) <br>

Facade provides working with JSON files. Inside it reads, parse them and creates objects depending on configuration info or sets different parameters.

<br>

**Client** - [Simulation]() <br>

**Facade** - [ReportCreator]() <br>
`createReports()` <br>
`createConfigurationReport()` <br>

Facade provides reports creation. Inside it collects all necessary information, creates reports and writes it into files.

------------
- #### [SimpleFactory](https://dragonprogrammer.com/design-patterns-java-simple-factory/) _(3 implementations)_

**Client** - [ConfigurationReader](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/utils/ConfigurationReader.java) <br>

**Factory** - [CreatureFactory](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/CreatureFactory.java) <br>
`createPerson()` <br>
`createPet()` <br>

Factory provides Creature creations. Hidden complexity is control of validity of given parameters.

<br>

**Client** - [ConfigurationReader](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/utils/ConfigurationReader.java) <br>

**Factory** - [DeviceFactory](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/DeviceFactory.java) <br>
`createDevice()` <br>

Factory provides Device creations. Hidden complexity is control of validity of given parameters.

<br>

**Client** - [ConfigurationReader](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/utils/ConfigurationReader.java) <br>

**Factory** - [EntertainmentFactory](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/entertainment/EntertainmentFactory.java) <br>
`createSong()` <br>
`createVideo()` <br>
`createGame()` <br>

Factory provides Song, Video, Game creations. Hidden complexity is control of validity of given parameters.

------------
- #### [Strategy](https://refactoring.guru/design-patterns/strategy) _(2 implementations)_

**Strategy** - [EventThrowStrategy](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/throwStrategy/EventThrowStrategy.java) <br>
**ConcreteStrategyA** - [HomeThrowStrategy](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/throwStrategy/HomeThrowStrategy.java) <br>
**ConcreteStrategyB** - [FloorThrowStrategy](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/throwStrategy/FloorThrowStrategy.java) <br>
**ConcreteStrategyC** - [RoomThrowStrategy](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/throwStrategy/RoomThrowStrategy.java) <br>
_execute()_ = `throwEvent()` <br>

**Client** - [Device](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java) <br>

**Context** - [Event](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/Event.java) <br>
_doSomething()_ = `throwEvent()` <br>
_setStrategy()_ is missing because it sets in constructor <br>

<br>

**Strategy** - [Strategy](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/strategy/Strategy.java) <br>
**ConcreteStrategyA** - [ManStrategy](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/strategy/ManStrategy.java) <br>
**ConcreteStrategyB** - [WomanStrategy](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/strategy/WomanStrategy.java) <br>
**ConcreteStrategyC** - [ChildStrategy](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/strategy/ChildStrategy.java) <br>
**ConcreteStrategyD** - [DogStrategy](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/strategy/DogStrategy.java) <br>
**ConcreteStrategyE** - [CatStrategy](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/strategy/CatStrategy.java) <br>
**ConcreteStrategyF** - [SomePetStrategy](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/strategy/SomePetStrategy.java) <br>
_execute()_ = `react()` <br>

**ClientA** - [Person](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Person.java) <br>
**ClientB** - [Pet](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/pet/Pet.java) <br>

**Context** - [Creature](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/Creature.java) <br>
_doSomething()_ is used in Creature routine function <br>
_setStrategy()_ is missing because strategies sets in creatures' constructors <br>

------------
- #### [TemplateMethod](https://refactoring.guru/design-patterns/template-method) _(1 implementation)_

**AbstractClass** - [Creature](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/Creature.java) <br>
_templateMethod()_ = `routine()` <br>
_step1()_ (abstract) = `decreaseFullness()` <br>
_step2()_ (abstract) = `decreaseHunger()` <br>
_step3()_ (abstract) = `chooseActivity()` <br>
_step4()_ (abstract) = `reactMaxFullness()` <br>
_step5()_ = `reactMaxHunger()` <br>

**ConcreteClass1** - [Person](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Person.java) <br>
_step1()_ = `decreaseFullness()` <br>
_step2()_ = `decreaseHunger()` <br>
_step3()_ = `chooseActivity()` <br>
_step4()_ = `reactMaxFullness()` <br>

**ConcreteClass2** - [Pet](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/pet/Pet.java) <br>
_step1()_ = `decreaseFullness()` <br>
_step2()_ = `decreaseHunger()` <br>
_step3()_ = `chooseActivity()` <br>
_step4()_ = `reactMaxFullness()` <br>

------------
- #### [Observer](https://refactoring.guru/design-patterns/observer) _(1 implementation)_

**Publisher** - [SupplySystem](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/supplySystem/SupplySystem.java) <br>
_subscribers_ = `consumedMap` <br>
_subscribe()_ = `addConsumer()` <br>
_unsubscribe()_ = `deleteConsumer()` <br>
_notifySubscribers()_ = `switchRoom()` <br>
Subscribers are keys (Devices) in map with consumed resource value. Notification calls different subscriber functions depends on notify function parameter.

**SubscriberA** - [ElectricityConsumer](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/ElectricityConsumer.java) <br>
**SubscriberB** - [WaterConsumer](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/WaterConsumer.java) <br>
**SubscriberC** - [GasConsumer](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/GasConsumer.java) <br>
**ConcreteSubscriber** - [Device](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java) <br>
_update()_ = `turnOn()` <br>
_update()_ = `turnOff()` <br>
Concrete subscribers are all devices (depends on what resource they consume).

------------
- #### [State](https://refactoring.guru/design-patterns/state) _(1 implementation)_

**State** - [Weather](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/weather/Weather.java) <br>
**ConcreteStateA** - [NormalWeather](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/weather/NoramlWeather.java) <br>
**ConcreteStateB** - [SunnyWeather](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/weather/SunnyWeather.java) <br>
**ConcreteStateC** - [CloudyWeather](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/weather/CloudyWeather.java) <br>
**ConcreteStateD** - [RainyWeather](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/weather/RainyWeather.java) <br>
**ConcreteStateE** - [WindyWeather](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/weather/WindyWeather.java) <br>
_doThis()_ = `applyWeather()` <br>
There is no need in _setContext()_ because context is Singleton.

**Context** - [Street](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/Street.java) <br>
_changeState()_ = `setWeather()` <br>
There is no need in explicit _doThis()_ - context uses `applyWeather()` and `changeWeather()` of its state.
InitialState is random Weather.

------------
- #### [Adapter](https://refactoring.guru/design-patterns/adapter) _(1 implementation)_

**Client** - [TreeSet](https://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html)

**Service** - [Deque](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html)

**Adapter** - [RankedQueue](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/utils/RankedQueue.java)

In the project Creature is need to work with its sequences of future actions (called memory). Those sequences have different priorities (what should be done next). Also, implementation requires to have ability in iteration by action sequences. For this reason TreeSet is used (is easily iterable + can add elements respecting priorities).
Every element of memory should be specific action sequence.
That's why adapter was implemented to store those action sequences and its priority to let TreeSet work with it later.

------------
### Application Input
------------

- <details>
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

- <details>
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

- <details>
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

- <details>
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

- <details>
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
        <td><code>"name" : "Mafia: The City of Lost Heaven"</code></td>
        <td>Name of game</td>
      </tr>
      <tr>
        <td><code>"description" : "Classic Czech game about italian mafia in USA."</code></td>
        <td>Short game description</td>
      </tr>
      <tr>
        <td><code>"genre" : "ACTION"</code></td>
        <td>Game genre</td>
      </tr>
    </table>
    <p> 
    Game genre should match these <a href="https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/entertainment/GameGenre.java">values</a>.
    </p>
</details>

- <details>
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

- <details>
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
### Application Output
------------
- <details>
  <summary>
    <b>Configuration.txt</b>
  </summary>
    <u><i>House Configuration Report</i></u><br>
    Stores all configuration information about home hierarchy and its residents.<br>
    <pre>
    Home                           <b><i><- Information about home</i></b>
        Floor_1                    <b><i><- First floor</i></b>
            Room_1 (Hall)          <b><i><- First room</i></b>
                TV_1               <b><i><- Device</i></b>
                Light_1            <b><i><- Device</i></b>
        Floor_2                    <b><i><- Second floor</i></b>
            Room_2 (Kitchen)       <b><i><- Second room</i></b>
                Microwave_1        <b><i><- Device</i></b>
                Light_2            <b><i><- Device</i></b>
            Room_3 (Toilet)        <b><i><- Third room</i></b>
                WC_1               <b><i><- Device</i></b>
    Residents                      <b><i><- Information about residents</i></b>
        Bob (Male, Adult)          <b><i><- Resident</i></b>
        Bark (Dog)                 <b><i><- Resident</i></b>
    </pre>  
</details>

- <details>
  <summary>
    <b>Consumption.tsv</b>
  </summary>
    <u><i>Consumption Report</i></u><br>
    Stores all information about every device consumption during simulation in easy-readable table form.<br>
    All information can be sorted, summed and filtered by auxiliary application<br>
    <pre>
    <table>
        <tr>
            <td><code>Date</code></td>
            <td><code>Device</code></td>
            <td><code>Electricity</code></td>
            <td><code>Water</code></td>
            <td><code>Gas</code></td>
            <td><code>Money</code></td>
        </tr>
        <tr>
            <td><b><i>Simulation day</i></b></td>
            <td><b><i>Device_ID</i></b></td>
            <td><b><i>Consumed electricity</i></b></td>
            <td><b><i>Consumed water</i></b></td>
            <td><b><i>Consumed gas</i></b></td>
            <td><b><i>Spent money</i></b></td>
        </tr>
    </table>
    </pre>
</details>

- <details>
  <summary>
    <b>Activity.txt</b>
  </summary>
    <u><i>Activity and Usage Report</i></u><br>
    Stores all daily performed activity of every person during simulation.<br>
    <pre>
    12/01/2024                                                          <b><i><- Simulation date</i></b>
        Bob (Male, Adult)                                               <b><i><- Person</i></b>
            Activity                                                    <b><i><- Information about activity</i></b>
                20:25 - Go to Room_2 (Shower)                           <b><i><- Action</i></b>
                20:26 - Put clothes to Washer_1                         <b><i><- Action</i></b>
                20:26 - Start Washer_1 with 'Washer Intensive program'  <b><i><- Action</i></b>
                21:26 - Go to Room_3 (Toilet)                           <b><i><- Action</i></b>
                21:27 - Use WC_1                                        <b><i><- Action</i></b>
            Usage                                                       <b><i><- Information about usage</i></b>
                Washer_1 - 2                                            <b><i><- Device usage</i></b>
                WC_1 - 1                                                <b><i><- Device usage</i></b>
    </pre>
</details>

- <details>
  <summary>
    <b>Event.tsv</b>
  </summary>
    <u><i>Event Report</i></u><br>
    Stores all information about handled events during simulation in easy-readable table form.<br>
    All information can be sorted, summed and filtered by auxiliary application<br>
    <pre>
    <table>
        <tr>
            <td><code>Created</code></td>
            <td><code>Solved</code></td>
            <td><code>Type</code></td>
            <td><code>Creator</code></td>
            <td><code>Solver</code></td>
        </tr>
        <tr>
            <td><b><i>Date, time of creation</i></b></td>
            <td><b><i>Date, time of solution</i></b></td>
            <td><b><i>Event type</i></b></td>
            <td><b><i>Device_ID</i></b></td>
            <td><b><i>Person name</i></b></td>
        </tr>
    </table>    
    </pre>
</details>
