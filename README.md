# Smart Home Simulation

------------
### Project information
------------

Present project simulates life in smart home. Residents using devices on their needs and living their happiest life.

#### Main features

- Dynamic weather changes based on real data
- Real existing media content (videos, songs, games)
- Finest adjustment of the room's ecosystem through a control panel
- Random event generation: from device malfunctions to complete shutdowns
- An astonishing variety of devices
- Rich diversity of people's functionality
- Detailed and visual reports on ongoing activities

#### Application running

1. Choose existing configuration or create a new one based on [tutorial](https://github.com/neiron94/Smart-Home-Simulation/tree/develop?tab=readme-ov-file#application-input).
2. Set proper [simulation settings](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/resources/config/Simulation.json). 
3. Run [`main` function](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/smarthome/SmartHome.java).

------------
### Functional requirements
------------

- #### F1
> Entities we work with are house, window (+outside blinds), floor in the house, sensor, device (=appliance), person, car, bike, pet other than farm type, plus any other entities.

Main entities in the project are [Street](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/Street.java), [Home](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/Home.java), [Room](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/Room.java), [Person](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Person.java), [Pet](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/pet/Pet.java), [Device](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java), [Sensor](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/sensored/sensor/Sensor.java).

<br>

- #### F2
> Each device in the house has an API to control it. Devices have a state that can be changed using the API to control it. Actions from the API are applicable according to the state of the device. Selected devices can also have content.

[Most of the devices](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/java/consumer/device/common) have API functions that Person use to interact with them (eg. `startWash()` in [Dishwasher](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/Dishwasher.java)). 
[Other devices](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/java/consumer/device/sensored) do not provide API directly for human. Instead of this they use their sensors to be controlled. Person interacts with [Control Panel](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/ControlPanel.java) to change parameters, which ones have an impact to devices through sensors.
Each device (with few exclusions) can be in one of [different states](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/DeviceStatus.java) at the moment. This state have impact on device consumption and allowed functions to use.
Some devices (eg. [TV](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/TV.java), [CoffeeMachine](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/CoffeeMachine.java), ...) have content (eg. current [Video](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/entertainment/Video.java) in TV; water, milk, coffee beans in CoffeeMachine and so on).

<br>

- #### F3
> Appliances have their consumption in active state, idle state, off state.

[State](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/DeviceStatus.java) of the device impacts device consumption rate using state multiplier. This multiplier applies to default device rate.

<br>

- #### F4
> Each device has an API to collect data about that device. We collect data about devices such as electricity, gas, water consumption and functionality (decreases linearly with time).

Every device is consumer and stores amount of consumed resource in [Electricity](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/supplySystem/ElectricitySupplySystem.java)/[Water](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/supplySystem/WaterSupplySystem.java)/[Gas](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/supplySystem/GasSupplySystem.java) meters where can be easily taken from.
Those three classes have information about consumption of every existing device and have an ability to switch them altogether or by concrete room (changing resource availability in room) just as in real life.

<br>

- #### F5
> Individual persons and animals can perform activities (actions) that have an effect on the device or another person.

[Persons](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Person.java) and [pets](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/pet/Pet.java) are interacting with devices using their API functions. It changes actual state of the device (such as device state `ON`/`OFF`/`STANDBY`, occupancy and so on).

<br>

- #### F6
> Individual devices and persons are in the one room at any time (unless they are doing sports) and randomly generate events (an event can be important information or an alert).

Both [people]() and [devices](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java) can be only in one room at the moment, which is quite physically logic. When person is outside (doing sports, went for a walk etc.) he marked as not `atHome`, but remember the room he lastly was.
[Devices](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/java/consumer/device) communicate with people using [Event](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/Event.java) generation.
Events can have restricted visibility: for example person can see [flood](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/FloodEvent.java) from [washing machine](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/common/Washer.java) only if stands in the same room. In other hand [alert](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/AlertEvent.java) about [fire](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/FireEvent.java) is loud enough to be heard throughout home.

<br>

- #### F7
> Events are picked up and handled by the appropriate person(s) or device(s).

[Events](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/event/Event.java) are taken only by persons who can solve it. Solution (as well as reaction) [strategy](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/java/creature/strategy) depends on concrete attributes (in our case [gender](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Gender.java) and [family status](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/FamilyStatus.java) representing age). 
Concrete event can be solved only by one person.

<br>

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

<br>

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

<br>

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

Project has next [UML diagrams](https://github.com/neiron94/Smart-Home-Simulation/tree/develop?tab=readme-ov-file#uml-diagrams):
- [Class diagram]()
- [Use-case diagram]()

<br>

- #### NF2
> Project must have ReadMe.

Project has its [ReadMe](https://github.com/neiron94/Smart-Home-Simulation/tree/develop?tab=readme-ov-file#smart-home-simulation).

<br>

- #### NF3
> Project must have public API and JavaDoc.

Project has JavaDoc for every public function and attribute (except getters and setters). <br>
Project classes (such as [Device](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/Creature.java), [Creature](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java) etc.) can be freely used outside the main project.

<br>

- #### NF4
> No authentication or authorization required.

Application doesn't demand any authentication nor authorization.

<br>

- #### NF5
> Application can only run in one JVM.

Application runs in one JVM as a single-thread application.

<br>

- #### NF6
> Write the application in such a way that methods and variables that should not be
available to other classes would be private. The generated JavaDoc should have as few public methods and variables as possible.

Project has proper level of encapsulation.

<br>

- #### NF7
> Reports are generated to a text file.

[Required reports](https://github.com/neiron94/Smart-Home-Simulation/tree/develop?tab=readme-ov-file#application-output) are generating as text files. <br>
- HouseConfigurationReport, ActivityAndUsageReport - `.txt`
- ConsumptionReport, EventReport - `.tsv`

<br>

- #### NF8
> The configuration of the house, facilities and residents of the house can be loaded directly from the class or
an external file (json is preferred). Two configurations is required.<br>
> Minimal configuration filling: 6 people, 3 animals, 8 types of devices, 20 devices, 6 rooms.

Application has two [input configurations](https://github.com/neiron94/Smart-Home-Simulation/tree/develop?tab=readme-ov-file#application-input):
- [1](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/resources/config/1): 6 people, 3 animals, 22 types of devices, 40 devices, 6 rooms.
- [2](https://github.com/neiron94/Smart-Home-Simulation/tree/develop/src/main/resources/config/2): 8 people, 6 animals, 26 types of devices, 126 devices, 13 rooms.

------------
### UML Diagrams
------------

#### Class diagram
- [Preview](https://viewer.diagrams.net/?tags=%7B%7D&target=blank&highlight=0000ff&edit=_blank&layers=1&nav=1&title=Untitled%20Diagram.drawio#R7X1rk5tIlvavUUTNREhBJveP5aq23bt2j7fLM737aYOScJXWktAAsl3vr39JIBFkcldeQEpPxHQJCTgkJ895zn2hP%2Bx%2FfQi94%2BvnYOPvFlDb%2FFrojwsIDQs6yX%2FQkbfsiA4NIzvyEm432THtfOBp%2B%2F%2F87CDAR0%2FbjR%2Flx7JDcRDs4u2xenAdHA7%2BOq4c88Iw%2BFn92bdgt6kcOHovfoUMdOBp7e186md%2FbTfxa3bUgfb5%2BEd%2F%2B%2FKK7wwsN%2Fvm2Vt%2FfwmD0yG%2F3yE4%2BNk3ew9fJr9l9Optgp%2BlQ%2FpvC%2F0hDII4%2B2v%2F68HfoXWtrtj7hm8LkkP%2FEPc54b%2BfXt795z%2F%2FcP93v1n%2B%2FE%2FHB8uP90tg6jl18RteDH%2BTrE3%2BMQjj1%2BAlOHi7385H36UP7KPrguTT%2BTefguCYH%2Fw%2FP47f8hftneIgOfQa73f5t%2F6vbfzfyd%2Faysw%2F%2FQ%2F6lP%2F9%2BKv84Q1%2FOMThW%2Bkk9PF%2F8PXQh%2FNp6afzeZjhAMw%2B3iOOST4%2F74L19%2BzQ%2B%2B1ul%2F9%2B5z37u3fFe30IdkGYfJW%2BWf3dt%2BAQ40Mb%2F5t32iWL%2Fy767sfr15yY9Sn84b%2FfxvH28IJXY%2Fvykq4noiBbcLTKjW8SFPyR7Dk%2F2PvJAyU%2FCf2dF29%2FVM%2Fzct5%2FKX5XnPol2CZXhFq%2BUU0nf9n5Nl0mfFy9RhScwrWfn1bmJepKRvVK0NGqV4q98MWPqSslS%2B%2B9lX52RD%2BI2kg26knOr5f8kV0Sfyqt1%2FlQuhMG7AqY3fOHtzvla72AVvqij5WtYv37hHbvu33ysNvDQr9PvjWOv5L%2F14r%2FR%2B9bi%2F1f8dLbbV%2FyH62TN%2B2H2XeIo5ZRyqHoK2DkJ%2BGLJ3%2B95P9NSdiWDnj7I%2BJY%2BtPv6PrfvLVfHK6eUrliskZb8i7PIXUEH7jfJEz%2FFIde7L%2B8la7xTJ6RHDuSx15DtIJYAeBnBM2P27Hi2RJnH5c7%2F1vy5X3XCtbRVX%2Bb5yDc%2BOFynW34%2B5Qnwrvlsnz8b9lrLPOARvBAxhX1JJ3fyeE5OpYoio7e4UKiyLuFvreO795vQ%2F%2B3HwkH%2Fq20INndcqKyiwbJHilxA1NKZDw3%2Bs93%2BQ8%2BUR7ktfbka87exf3OD2P1LiaxL97vgmCj3sUk3sWn4SJqaiumUUAlCnY%2FfPRkd9SjbavP9WkbxVUgc7%2BOt8GheuyLH0bo2MOfQbDvCXDmsETpRpS1RjTgm8uqJXBmjos2EsI2L0SKb%2B4e%2FR%2Fbtc9uKbLrsVgMws3wI0EA27W3u8%2FMoscY%2BQ7e5UbSY7YO74LkV992qbX%2BLTXRUwO8bM%2Bjz%2B%2B9%2FXaHLMSPfrIO6KqLiruh6q8oHDHI2l8H%2B%2B26l%2BUfxWHwvXAPoQt54TqnxEGEJPThUxZQ%2F2ai%2Fy2qHoPkePavuF7pGyv9txjlTUCL6f8a6k%2FADjzdafAL%2FDw7w6CbW%2FivJUdYsm%2BbnRAlG324CW4oE7zFBL8N6%2FsCCZmhqVwrXACZxpNQDxyLl4xN8d2uS3VNkvC%2FvO%2F%2BP4%2BzJL3W%2FdFCeHkb9r1Hvauh500ErhcpzbQ263x65Pd63bXW7TyfpdY6vOBRrhiR2Ys5IzIbB0YxIrOBQyEyUIfIABmIYYbITIXIWhBZui2%2FvobBz3lis1qzMkbPkz7Z3de3Y5tR2S51bk4OzVfynCO5WPSYZj%2FJw03wABNKyVHoShQgEguuN28AAAuu3PM%2FB1Y5xNCJV983jcDRTILXsCeCcRpB8gRE6oORH%2BhNGnECn8QDS%2BnYFh37xVdpB%2FMNbdX6OuYV2hK8Yg1OFrVmrVymkltUEL%2FWz6WSWybxLlRyy62%2BiytIbml00zJdLvYJM%2FGMs2UErjnbDJwhi37jMofP22SZGTR%2BB12dd%2FNaoixA07UGT1Y58cWuS3yBvLydtnIBtbmAwiAO4rejXzr%2Fmfz1%2FB1ALIM46%2BD4dtciAKklVfGbGcu0pUtWzJkWncxXG8CxeYk0R4m0FpH2p38MwoTR13EQzsyvXS9vEmMv9rOnapM72S%2BU0LkCoWMZdlXmANPuGTXmJnQAkBM1rq1sx98Mq2w%2F17LXV7ZvvOi1oBV9%2BOLFiZxC%2FJ6IXM1ZUDHqKPbCuKEaPufLaow7PYEMg6cHb6Jifgl0l4gDOyYRou4b7K65lo1zKzrC3azCy%2B6UFTH6fS0Vs9PoD8EhOu39sEOZZw88dR0v0HTx1mv%2FGN%2F9axtt49SxozLLrggkLIHhEu4WF%2FbMauWHEkwwHZRQAAPW%2FW9UHlv6C6qZjG5NPXGNItmw4DDKiBP45K3hdHWFLHhnwKGq3J5JcHPAF8tFQ0BoSmloky6NF7%2BCnNPSLsKpCg%2FOBg8ajtugjeVVGoApqzHp2ifd8o9%2BlDCFl4fIZ%2BSslhmD8zZZDlpnNoLoimma1I2%2F82O%2FRG0dsblt%2FhwEO98r%2BECZ5zMWx0vLIrqAmEZPH77LTx5Lts5t%2Fk581Z62xUHuQrtceOb2c5fXXNk07RVxbaBprRdv8AUMteDTexNPZbfXntUthC2g%2BgxHzBT8qYc%2FO38dh9v1Nn7r6eG%2FXQS0zhaotGR3OWbYBKfnnco1ugrIAGzc%2F%2BssVG3pLn3NnY5LX2EG4ZhhpF7nB0eYqWddqecW9fzBi5Ra7qeWk6VS6vgK1TEVYJ%2BCOsaxQVnqGCp9LFIfQx0ysrT5aeThNnzyVKRBDjppNS4%2FJU%2BQ4Wv2q167bbjiLy%2B5tkIW%2FZBFulgKW1whtqD6wk0BW6ielG2SK0%2BkVTKrQWb9QOtzh0W7pD7hdAQ2I6vG2zzpuHFGdUVZzoDektNAdTe9BTXmkCX%2FltG3szLgpcbg%2FNOOQn8dhBsuOmzjB3PSYMvkUgdvn6%2FuUyLCE24WI%2BnQrTd%2BtA63xzQ9Sw4Fx50XfwvCfXb79P19wYcErsMp9M6L8Ig%2Fyc5GvTldce4Vk1EyY9VhuXQOiQXsntqDW8MYOP%2BsDW7a4ymoiL5ZKw%2Fhc0kQMd4pfsVVAsI1ibd7Pu0l3fvFP4T4NSRM9CH9ePPqS%2BlN8XrTnbnaNOwatYmVVqfaJEcLsFOb88%2Bm4KY2PyAVdCVq8yZsrpK2Qu9OpLZSMrvO1tFnLrRdSEfxLa2vrcNtKiyAFsU3QkYB5XkhxYc0K%2BScJKJaL3TlhVg2MYDHGjs0aGnbZBsl0jPLKu3DBnY90c20kWdg2rhmcEDWGRxDwnKb7Y%2Fae7TAoabLN5asp%2FDpZy5issrD3ab1Uhf3fPjgRU%2Bn43H39vQWxX7ZddnQV6FS%2F96mAtMFo%2FTxtBGWMDCBwHL0c5uINNRs%2By7ruP2QF5qqllBXFo6jtUJND4BiyHwZY5CdexhCDEMKxMD5qotSP6iilKQjXVXBEmawxCaLmScJS6x6oltgCXmGEFjCOj1LwZIsG1UBEwVMFDARCkwcKB%2BYmHJ8H8PAwlhQcsUgYzQy6INXyEs14BVmOp31iGKl08uNJZRmV5pdaXahmt02pGt2V05U45KOFYR2h13qvd%2BsCow0gqN%2F%2BPqK9vLNeiYAsLqmRQyomyX7tFhkD01mlbI1ZBsd1AGy89yIU%2FAD8a2UdZun8%2FVGAbBZ3zVmemQzlaJFv1SPKlqoESyf0LapCgOsfQroRCqp%2FXazyeSOnzyM95xeD23JnCWSi5vvFuZjtzZhIvHXweGQoKacjkUxj7G8M9uEbaOCSGSZa1TLsPNcpbE7ugig69VTgm%2FfIp8PSGc9RFKB9HeP%2Fo%2Ft2qfHtN0iNEcpR%2BnEyCw7NV2Zr9WhnJOiNy9RT4VoRu1dujWT3aUhuK9A%2FpWXbhTYvXDnye9t68iNMw4A%2FQq7D8XullaTPudYY%2BF77eVwppGo7nOOHPez4tf58avu1FwOw2jmFmct8WZni%2BU6Gk0RXZYdZUTO3ojMhGEz4tBWmq7BKnvNzowEzmz8kkrkSxb5Dqyp8tL6QRRWIt8cQ2P1LF4i31Eif%2FYiv8tvqGkGIwlP2JwcHYXNfKkchWOj%2BYja2Nse9j5y3VzgL6z3Dk7FkYYKDe9wpeMD8UfuUftHWhTp7apSKS8vrRdC%2FGP9Gfmoqr%2BR%2FPIfWzSwq%2BVp8h4Tcp8mbbPT9jhdT4H7LHU%2BxtW5Oefs17RtGgfZVg7iSq5N3ahxbfIbo2hOyFVUmAoN%2BQwd2Y39DIvaBMiBc7qv19hwHIJHx5oZdHIB5FRjQZEMDGsgacQZfCwLXEQrBcBta%2B8wYOLx0K4YDBHjpbQPscIu69NRjwLrfnmlgPop2SVf%2FLhm6vmtxt6Pfr74X%2FxYAJ3cZ5inSFbYFPMoSODlJ9%2F7Tk%2FjpWeYf9pGcXU336%2FzVlylY%2Bl7eMgyh3vt8p52wTWu%2FPtdEGxkLf1zyF7uq3eaoM7Qn%2F5u4toM7%2Brs4lL6z7zN5CJvmjQ%2Fygn%2FVo2BDAxeFnKyGjIs5HSxN%2FxMXy7maLJIqXnYZgXlP8yMv7Z1v9C%2BveilYyqVraZstWu11T57B2Wnley0MMI9Ib9kf1%2BntUaZVIPQXrYwynySYz4NXH1lQc3CglJvdQpvNYETiUzMSk6YvtfskmylpTKPb7FBu%2BlU01SBWVMOX9vl1%2BRlHQNTbpffgYFbDob09caC6Tip%2BGAws%2BArVAa9Muiv26B%2FDF6UQd8aeO03JedmjXsVGFWB0Rt%2BpxMLjNYI3gECWhmNC2YxVXT6fK1GoOmTC6oCc0IdFdrTjoeVKw7ru6rSjgvPRrWN05LKeO%2FbdpVqHwI0gpoGo3Vo2jF9I6u9jp16RuIETlnHrGfnKcNXGb5tKEyC4fvwut1tlOl7e7Hs0PfW8R0C7mVzl%2BwNxngbizRMsgdMQ1M9LBPODz5RI5HX2tcz2%2F0uweLqXUxiX6ReGPUuJvEuPg0XUcqBMBMHAlUuiU2qzumyNresbADlxp0XJbu9e2IKByfC9ToDlsAFYKWd%2F4Eq%2B5n66Obqjk4Y7aZLtOhi1vfIIdueY7JbqKNOcUV4CNREWuYWaaoJ%2F7WNtnEQ3rhBOg989AO9q7vSdJ%2BH4BCd9n5YY1NO%2B0k4Ir1skdKxhuflGYf2bm7VPngRzzUb7SuKXoPTbvP1NUEeqdAqMmBzSZlOVroRxD5fiI7wMImSHLptepF1VwHpGj%2BQriuQfqMg3YAEV00SpCfPQJAN3E6Q7l58ihhcr0Y6M8f1j%2F7ORx38FLBXwP7qIKoC9tcC7FtljULwM0HwhtUTwXMbfAQxPJKTpof%2FLsF3kWl6twveLXJ%2B7jzAu2V2I3H74lPEgHc1u505eM%2FVtELvCr1fIQ5V6F2hd4Xep4PebU0%2Betfmgt6Vy34CqN8ma27mgfptuxvCWxefIgb1q2HwzFH%2F%2FWajEP98ED93xFdrTCi0rGwMZWMoG2NmNoYDZdsYwHWk2Bhc7YVqrX73KOLrNRkMlyj9cMem9Viw60qcO8AB120E12qw70wG%2B2bSpllEaiuom7DKaPnHSztUVC8KjOoFOM791aXGYIdMdo%2BSLRxjabnZevvgsMkFZvpVLjJBT7FKTqxAl8AHjKsWu7pDuFOc0a4Zl7gSNPg4ZiiS8Y24Okygw0CoN2FkMCGxLFnw5kKoTfC6hlsNlAMmcncJqKG5wiQv3juXMJfZwFx%2FV8zVl7kS3rJ0wvK5UKmfK5lFMZPuNPKScu2OTehAyPoU%2Bu%2B9dRyEt975R5jnCHnfMqMmay7UOMv%2BoiQNBsTFZ8qSo4000RygvGKz8YpBsjmFi3MXyz4xvcYnRkJqhhMDTRkW29VODMwh%2FsQnBjYbIqrPYtulVJ%2FF2eCtv4K9mhmYX%2FKm%2Bix2MuOFrddZT3hXUwrVlMKbf61qSuEVvlU1pfBqbfBSv8iZm%2BREBHYKYwpxk7spBFGLuGlDKrzKV89%2BoRvlZHXNrYYAyJSmAcnqVY8RJLpJsgqIVqc9dNeZmi2%2F5xM9xeNdVEyCnY38MXkraTe1y%2BzkeqtYbngBAaX43CiureX%2Bjavy%2BerupVUvZbs6PZMNA9hpbignwXRE%2BlPTWKdzGmp9UqrS%2BJj7XJNM9gDQXrmlfyM7Pzsa1cKcbE3OSO3XPELeX7E%2FbcQZnJQ%2FUMqftfKnnMC36hl%2FSUSWn%2Fs3PmR%2Fi%2FEeoJt%2FS5X%2BU%2BzFpygj4X35iDhCUo9NNqAkJ%2BSzd6z6X9IvF%2FDhU5AAnscEMH7d7mkvzBXEFPTk242PMjMi%2F%2BPp8OKHd1Pv4VEm%2Bf1ptzv4UTQLotevQRD5yN33Yxu%2FzYLkdIjMZ%2B8Xm3VWjsdxjkdkcczXdqGaEACIq9PKnkezxnzhN6gmoWE%2B9ouyQ3JrNp%2FMWoLk5Cjk6VkeNURP1PKAyvJgb3nEyuzQ4rejj9Nx4q%2Fog8LO8klW2Flh5%2BvHzu6ssbNJjp4DEMOUziGPOjfsbNpSsDP73PnrhcrAIEvjHWscUgbUnFFIlucxQso0zcCwBtJGnMEJKesSkbIqKqhcVxUV8LFcHrxYlRSUSgriwoDpC6rmZs6cd0eeNEvVAAxKYI1Vpr%2BsTP8hS68SwmeR5n%2FpbqoRvAMEtLJHF8ySyNHp8zVIgU42fsF2RTmWY9XZo6SrnmEsB0qN5SzKDdVhz47qRbtEaR3VK427rtsaXkKyDZaujUxZWxo24ZIxyUG9rHqq00TD9sDRUieHIg0%2Fw%2BwITjU%2FP9%2BOYpolc4%2BDUXt8YBdUxn36qMZ8U3V%2B9eghYZt85ALZhm%2B0WLDJuQSchIJbT28TWeSAYyFbFY9VVs3%2F%2BPZny0RiW%2FM%2FB9rV5IP808XqlMzoIXzL%2FPq1GYZE1%2BuVuvpyu%2FLWfXyI79A6pawuLit6F2AHw%2Fv0T3G3PqdlIA7oystgfHO%2FlH5NO0Hy%2FGsJde6INg%2BlGfiliSR4JwY732ttJsOFkHS8h1wSPniRLAIS%2FRiHwe6Ld0C6HNHwUD4icLv4%2B6Mfpp0tMzo2wQkpbXEUvJ4SdFKwo%2FDbP4dIK6FUlr4EzCr4oXmbTXsJ7dRc2trG3%2FmxTxMtdpdmtCRGZIJ9%2Fe4spyt27tqLOft2axxOfZONOBYa21IdP0NahAzz0fadrzAZNw37HCW7aW7SiHEKVMo8GW5glaREEe10tBChaCPO4OOFAXbz0Equc3VOkRqqw8K9kwu%2BNvcOJJvvwMvcO8XmIWaR8JzlIL8nMx%2BxLasts5EvaKdL3WTiUr9YfJpue4rnuVKv4QxOTuz8Jmos2aRd4G4DE%2BfXT9CbDcgM%2BstkZH5lygNOoA2eIlNK9n0PKUl2uh86r3HyIjMLPogWmbpBcLAN24N%2B1An4AGeJ2TxKR0nMQmKGQeylCX76I4o%2BT0CE2h0iFPCZIEY2ZIKGuCCi2cipKog4MoiIGiy%2BO2131Z5CtxpLfE1W4zH0vuXL%2Fx9RcPgj2Ih01qdxRbkkhEGwl0sBeg3ZzRF7TpVhcv%2B5n0g7KTXC6O4J8EFLhO%2FftVzM7%2F%2BMREeZAuErkFKQhuDvtkUgRWRIvkQGisffpdryARORE%2FSPIwIP3q4KgWorAxqk6g3n3s%2B8FrzJW17OvDfqgjNkLiK7kWoalGGLjuvfXt8FtsjDF5d4f70RnSK0UTCpoY8sO6%2B5lEtcqiGiw8xKaPYAKivhglTDh%2BDwbftyCr28zu3WbYWDh0EqHouqUpqmm9LEPLutyBDKhP5EGTWf4Bsci95LjVtZQc6rad25dB2jQZ93th%2FiNzQIw9nrBZ1tlZrG4pJKsQWJTa8YjJqYBc95DWSYrjcarbkWmfLGquyz5lZmR01m5ymccow0elgBDroJDaqURb%2FRLa8lJ%2FZk8qsl4sKmSgvAKk%2B4osIrAHsFFFswZIultrK48AUUxhdYYlYsanmavFrU3VXTPTgDrIfnaMLKtxBGmoYzaC7Vxwalpaghf731MdRh17XYtWEwGm7VTF3XKVV9fL6AgE1o08J5sHtH5QVL1BNWF3wwLI3oRsAmY2NpkftXWNIbwHXsU2jx064pVBXIUK5iqBZssqFHb7XAjlObOxQo%2BToH%2BZqJmhb5CiEkmiZBNsBcmng1mmuFVKhrZKjrKQ59Nf0judTvfzx9vf%2Fj4bcixlVdFt4Rlp%2B%2BF7%2Fi%2Fgl%2F5R9UiG3CITb5ca4EIPx%2BiGLvsC7y0YTybWdFvYqxlWJsCFLPOMZG498iTFHO7MJRL0Hz8XS5BtegIJtKv8Kc41SLKnraPt0Xcgk%2BazDImOHR5lIihUdH4tE0i%2Fjra7JRLpvrUI9E5evsGD1ae%2F8epTXnrCfJ7jQurSRrM1H4tabBMYg56MjRiShKt%2BbRK82oZb%2FBPsqRSnpw3Iok2OzoU0PSVf09nwwSo7lVsFL0F%2BRYKz2v9Pxc9bzhurWCVp6et%2Bqzl3jr%2BZFjBG4uVeViq1mzVlrpH9FUC%2FlC7JVb%2BjcyOmkZRNgImJya1KFHargV34RQS1LXJBX8rDD%2FBcklXelgUHerIwUuDX6eOzZWT%2BEX7TSbM6AU6BwLOv1jouIeUCueShHTxYBzFiHOZL9Fp%2F0x6%2BsjsU%2B7xFb160opmCQi0qECvW8u35bJWldleyeS1vkio6JSy5eRJIGiZUHPfc7QeHWy4liZFD2cd%2Fl0iEptYHkcdCYksS78u4wQNEpEDik%2Fwy1FyQI%2BNL4m5Sm4nlpVuiRA1x3KdVDfIUXnFyNw5foOLio87XT%2FX69XwDQMgpssF46z%2B22DZEzbbXUoMPIC0I9g556sAZRWz%2BDjNjB1ZZCxNsg%2BBqeoDtONs82uKiH1deuHidZ7zW2lzx6isORMwm1YHui5ZEWHllFD2Bk%2FB%2FI1bdqmqDVQK8ayUABrMfdQjAHcJh1YRlRmDaLilpgItQlVgtl8hr0PRWG3Hb7RIdFJ2rLs1fSnu%2BuWRZHdTht1hqDJ64bMDQdGbbiBIdNbnbzOIUuK3V6UNFLdspxWsuSMVGcwzEuNVO8xUr1hwgdmb23lmHo1%2Fsmoth6QSQfCpiEUzq8ppLHib4alsZ4luhD53tWe7YrlO5kkurRG1%2BpTlzJJo4FzqT60m7NPlVRlKFX1ro4lGtAJXmDTAm1JltRDgsM4SlVcrXAJc5kNzPV3xVx9mStR2UDTGXWwIlqnAVucjnZk6OjKRDfBSlTWNDgzN3c7p8HBSwdoXuYHaxlipZQXS%2FnidCgv2zXYZkQW0oa4rEhxIyVqrcRN2ztpGActStwwaGulxE0PceN2iBsL%2B4m5ixtxHghDigdCiZvWdyJX3DSnhChxw1DcGN1Np3XNZCJgyDAdbissQr4wKEtS3HQxNyVYmZrqxcp9TjKXMOVlNgMjlbw2OnmtMn70VhPV0hm%2BjdldeDapnOSztNSmkba0KkEebbtEfIXb9TYBf6fjcff29BbF%2Fj4j9reGLwUWKXjJXqQJ%2B4s6LI6kFy%2BiCfpAHJzoTsmKmLzNpr0phJBSqk3CenlNToUQohpNZUVeT%2FtGKv3Mdmvm8uJokKDujQ3zdibYLn9sjiSHubwVI%2F3K87TIZmp0dhX%2FlEl2GYqmTG4HY7hdJShKYnxyQMRovh%2BbnsguJ7C5SapyarDMCWyI855dZInGryYGMPLIkz18xDnkMTJRyStSeUtbWQbRyYlNYhShsYWNhizSp1WuaU9VrXJNy8OnWfVcXeqic00delSu0tYcJCrsip%2FrAJKBbjbaGhDV9lCcVHWgUtfTYC6b6vHIiLmqAFNYJpjZnGWoolsjxTUO3Nx6eAsxHlqolNPFdU0Ign1j3Ar1zr6OkBqjl%2FschBs%2FXK4zDJnGVLzwbrksH%2F9behWtvPM1YudnskBI9EYQxT0jPdLabNxM%2BMhdzDp8RDvisWXS1e8ckM2j2WXfYSgrumlZT891P%2Bt6Nk7uPmmbuROoM23TuDRLvAF%2FOqTfPNkzI21xKgUMOsSlWLXYoIk222v7a2irnsGpvN9mkO2q7Ldu%2Bw20l%2Fcn9ptmVw0tTtX94lJfWYwjV36ni1kLJVLbVINSRsxF%2BLN0Yc6B5rRq5RwYuacekt%2BFwe6Ld0h2i%2FIRIGs9W7HcNBdljFf62bf6CypNNqU5DxKRh0TUA92Gv47GSb7vzLDeBd6mQu4d9QCysju9zVQpoxdNVmf6jJ7I%2B%2BH3pEflwpacGfZi1s4MoMEmM7GcDGvVeTO4JcO6UvsXikg5UImuzeDYtrQVWRtmOgSv9fZdmKZGXotsKcTKeWFb1K1MbTB11VP4uC%2FM5uaEszIDnpmD%2BafT4fD2l%2B%2FFr36PgB91%2F3rcLh%2BieagoKH%2BsupkzSqXPWYk7pklJTAtnXnUFJUxeWrzoEDenJP%2BV68KKIl%2BZOsNU%2F64IR7%2F8wuTmX%2Fxwm7wmJB%2BvfKwL0Mh4G9DdkZEMaBkrwy5NcSHytQ3XXBnmea4skbTDCCgAoJMpR%2FiJmginTzHymjW%2BYY6Wae%2FKF81wiKveWbHgmqSEXzBxRevk5hKXA4k1j2IuqcyV8JYDGLXbJMIl4pq3AtwwRizYUKUDjLQ8Ld70sekK9KXgqOFt7OSc6owlRM41ZdIUck4zHU4BXJzsLUJrqvSAaXCTYeJMDbY1pMAUxUvAkuxot8ue9sIq7zLR7aEDUofq3ZuezgU0YFPeJMMcqYxrr2b1NKzZ8TmDwnvY7OoupRdUP2VztiMqxaA%2B4UC2xA2DOE9CeERNFzpdqJIFtNWZv6VBos834xkVItp62zIkdA8BWGnFOzicKSkpGwfoOpOyGzrID2OcwWPNKMPFyHNb%2B09%2FJs7g4xa0mh03swofTimLMK1IygY%2Bv%2FfWcYD46OZzCdP6urwJZF5MJyo77%2BiHEc7H%2B5L9Le7mUbD74X%2Fd7nOm%2BRQkCv0xQRLpoYm%2BryzQnCGejI9xoLnE2irefHX1cA4VQSiMha6R04D0D7OLPcuZT9CUDtbDRh00PvqKrU%2FdJCGNrRHOtd793ih0ZI9yBA%2BO79Y8g6UPJbV6Bicg19ziRgG5kUDufp2w%2FzZ%2Buz9s%2Fhl5L35V8d0wmEuhwSnM1y%2FLLu%2Br%2FbkQ5GVvaus3FosURMqpEDkh%2FpkicQrGXUHaICiaEtGqtgu5udyAmy4ljl%2Bf%2B987IqHA3iiwV4xjPUcG3JlhPfoR7BxUDYF6lTM4QT2ooB6Hyt%2FotD%2BimIlCeTlm2fg%2FtutajCcEMPmb0rQYiVSko2Ek3v%2BDF0m6e3RMNvHnRDnJWH4FTGv8iwCdPl%2Bc6pL6sgAJEv2Lcqa7K5g6jdxUm2znOnWYWvMIE%2FVINufKKph6eWhZAdTMDZkg9nN4VTxECnYnySSkqxDIQqhpfFvWzeO3Y49Vv2I8eG3x5kIbS8SDttRaZyKTum%2FPkjIc1Fawq855RCb19YLCpWPQic1Ug9%2FebUos06Wuphl8wCAiveFWXLEdsKUaTdWGADafsX%2FDhlyqbbS0yY6%2FADhwZY7bRtAi9pDLrdcPnhVTJrt9i9ecorntp%2Biac%2FEpeAk4b20GjQLGVFicotmUV0y7msJuSHkvyt0sy6wWU%2BT%2BgbEC4Kx7qqdwLHfDQ5ckgbSK%2FhmnfjoxWkX%2FjCx9u6Vqt5oGiADCkepnCQxSm1lk4zpWCgjYOqkbMN3N5FHnYPL4KgeHQQG8Ug4SlUMmONtK7UzLrtoUrLSDsNbowJWjHbCkLz6MMDSUoBcu6HVI2es25DQVpbAthsh6%2BhxMH19h7yphf2WF1m6n9Nds3EmC8dRM4gI8pX9dWxWZHl3Ivwv1zTmrCmNW0zSTDcO6Fq0IxruBASA9WA4vJ7BrEfrBNdsTV2uII06papTzBURs3%2Bapt0rrzMHEcNu7eWgr3cRdX%2BfjcbLqlIrKZum1G1uSrs9DSv70vU2fRu1XndUiKHciK%2FAPkxV%2F2u5Pu3T9s5dxdz4ga3wNIutjsl8xQeXZNf8RBYc%2Fgk1r5wQuBD3kFY91RElZocc0OX8y5FDDmHLKZNKEprkVU7mkUpKPa2im5CzAUFoTgufDuZ%2BAISrNaSppTnX%2BKh0jl3KiE06HEjOayWrG2QrRjEQ0f3qH7%2F7mv07%2Bya8aF1972hK3AXOQkPt3ukjpcj76%2F%2B6%2FXty7RYXbICwqzr7gTxNdyUzHHIPdDquVr0K123rne7VjiITc3dts7r7KuvnR97%2FLWfTjKXqV99zJot8n7EZ3sSD2rCz6ttFv%2B2P8hl%2FNcxAkPCqq5Vu%2BK4I9ggdfg7u%2BCiEndiusMV6zTrnh8sp5TwAF0CKSgV23BmWCGpQJyeRKdun0mi4z%2BDIyU3jwCNDWWR%2BMR4tMOCpzaQjG0Knc%2BaLecXgExiEuZYseF2I0N2VTAx4YRhwyGdMW1nZMSOTLsYpyV3nMERWPgLioV%2FGWVN5KWMs2qjxwYb5cwVnViYYCk6s1ybNEhHREGJILYVy31jWNmoo1si%2FcBWO6qSQK3kNCgFTMOao6YGDOqIKcrJi%2FtlxTG531o2sE91sap6wf2yD3GSa7uW6NPMXpmmqf6ILLT8FLwHfArakG3AoBRKATbLvUUGQmiIhIKBU5qa9lTEpv3jIbeOvvirf68lbCWobLyHCjMj2FwWsoZRBUvUcMtsOTYTCiFZHcOMyo8WzRXSH6wgwLimowoeO5Ak39JYafwUv920r9ixDRmfRqVf8mLiC82AVCspI495rJYM6k0vgXs1PCTaxSxcUpeFwoLknBrzQNVpS81bvmyB4W90qu8sUPt8mCITa8zUIkFsBAM6muoUX4y9FWoNRUlJCAvdvp6IQodciCWVZd9MkbuWL6EjCItKmiIZl9CbQuWKHlc9yZhfAKzSBsSrtlNXKpSrkdic3PBSuNe%2B12kmp%2F%2F%2BPp6%2F0fD7%2FlTVHrloZ7O9pTiPb6iJGnjAl5DTAFqJpI4I2zuRl4aINPZElmhTuNWkJIt%2BBT2EgeLnTqT6D89OcEt%2Fx%2BiGLvsPZx1qcU1s%2BLjs5FUFF7OZ3wUXnZckUZTb6UtO2bzzFFmaHzzTGtD5fWpZli6FcpZiJbqDGcEiwnZ2Vk%2FskwK7l%2BSsfNmMg1Q3gNndnUDpNTfL6OanvooA7iDE7GM64EnETH894JXzbPjC%2FYb5Ne8aYzgEEJ%2BtHbrvZqFhnA550WZjEIEY1xEqWYezZ%2Bopm1NMuEV6vjyCIdR2zyUYqOluLDUy2crBxJIx1JeNp7Nl7pvbeOg%2FCtcV%2Fejk%2BpOuy95B%2BYJMV5AWYqcbM3ic1c%2FH7vD5t%2Fomns1TFaqg5yxlZpzfzzAqrIm3%2BO0bwySa8OHZvY3XDmN0iA2elZpDVET9UglVu%2FUW3Y2tsghcog5bjlDAfQ9ujYTVd3MYu8GG9z1JaUs6DMUZ7maGd1BCRkKmRkjBKXFZfU0JwpqWzRkbYo0RlVGaRSAqV1VuTH4BT5Na9H2ZFXYEcagJzrVyCMLjPS4mdGArlgmH81vjI9Byh6CChXh0FcZHqmZw3RUzU95ThtlOk54S0HzJpQ6NhNV3cx0xZtejKoZ1am59RMT9hlegJHq8phVoFQcWM4mps8KGNzvLEZnfZHZWo2pXLjLtV54vYkqW22WKnXq0zVKzBV8YhTGpBIC3gWqup6LVVlhpaSn8hSVZ3M7h7QNYu8lkF2feXdqhX7eabAu73LtYd2Ke60%2Bir9Xkq%2FKjd7KX5XZvRb5ntKoo7ne8sWzPVyO32W5XXfRomsJXY%2F5%2BOtMLeBebkQ6vro9ttF58NSg0J7VeplILgdd4vTY5K2o9bXgGNmCXKwTrf4wJN%2FiILQ3xD1r%2BmPvja6gLY1a9vb6mywWuuNUQbLNj2bUEcXSxe%2Bx5wa%2BeZrIk8Tmeg3jG5R9mqpjBSg0%2BdrvtZpGmw%2BlAxY4Ao1YHEBxxSMAP6xn5u3YE1yQEjRvGg42DEMang8xaisOj%2FXEG4a3fS1nsInnFq8linsKHlm9S3vKTA2UFpzLVMXazA0J%2BhO0mAQCeeZ2RxfvNBLGwgOMwwasP1F9sIco1QZxj8GP%2F2w1whB%2BSCfYqXQ99bx3R%2Bn%2FbMf%2Fq32XfbonKNMgfmaApZDmAIFDpNmCuCEnLmI%2Fmv0FSkfkTD9Uc5zmLiPiGKW9au%2F%2Fv774Vtw11N5CKkd8OLYW79mmKZ2VrNSYdekwkhvlkVXn9cqMECmzjF0ZkkdMzLS9B7YgFw5s87lf0S0zRprdeuGvRoVlR7uxyJp1ttHiyDanJYT%2BPiw8rKD2UDBWXoBfvuRPEAB%2B5SRXwfSfLRGX9%2BO%2BVr%2BVnycLNUICJXhmQJBV9wZlh596RiSrfiEpBliIL5Zfe1T3OrGtt0GgnLIbCXbJtiyL4Sir0Q2BuSd1meoWWhCKq709qEl2gq6eMzO5Zl5FZ4S1t0DGgwGoSleYsBLhuZWRyuxKt6jcgmE8ZYupc8X49noXYNPr1dlUmNIHYMI0Pf2OpDJYBD0C%2FUP9TkUdSUkyY2dwsgTMGV882YcpcFFSN1cArXVTLuWTrZ5ZiJ3yV6MwkqooaM0%2BhR4qzwol3FjcvKqwnjLNhpZS%2FlKmdXr0y24laO07Cg9eHjy21Mcov0uKvCbllvEoRf7L28FAdknkSR4eafzat9zoSSEQbDPbv8n%2Bqunc5cTMXt%2Fj9pZpOR8CbdBmKzGf538E5Fs%2BKd3%2BO5var5AK5gOkqudk5dIykT%2FvF%2B097QRP%2B0ve%2FZ8HON9hSW4Pqm4Z3s9HV5w2uMmOCENLfL230673cGPImkEbKMEFf3wa4ulxAia%2BGMxZLPH%2FeUHo4gCM6FZQWjBKFCw8VHLlsj%2FmLLyZDKYWml9n%2FP9tKldvwZB5GO5l7%2FxOmQ0AVrTDOjP3q%2BJrWw%2B0zQlrWBQGUQcgvjLzkvssM0d1t%2BqRLQznmws5hxPppyLQMMBuVI8Wcd9d8vx5CIQzT6pTpfSMEN5rFk1zyVDs8bo9haUx9rm47GmSIYi%2BuBCHGtSTkLOTkKjywHtuBbpK2bjJQSAuCwU5oLG3esUd0nlroS5oM2Km0insy6Km2xTOZ3FJOg27qzb9Tgj8yQxSAp%2BKrs7BVhGacNX3N2nu0Mt47uPyUjmQcKjF%2BckfAoSQY0%2Bft3uJ54YHb8mMD1dMpUZvWi3ZJ3FnC1Zm5g%2BbVomZcfWF4fp3OxYPOhmConRvTsd2arT0UhsRhuRgGF9GMdORzThZnu%2BVkpf6ymcDGYoeWbpNEoNbnlPAXP0nqKuhUtnRHU6spQJJcaEojC6MqRKoDhPJTg8R8fqq6i5y3MQbvxwuc4ESIqfvfBuuSwf%2F1vtrbKGQ6NbDXGPEd483gcIwM8X8NeUQhZ4qwvzc5sjWVAwK4ACVT%2BIkfCkprnpWHRC9zbl1hGCpDq3gPv3Na2ewKkjRHNxhkJLrNBS0Rdy0l0hlN6%2F5SFcNa0LcYvaLj3Pz7WnSW25jP%2FOZrrYfYe6VLT82S2hBkazgAIUGjVJ7uuf9YIdE%2BdLEQTx7m2gQxnsrTKw%2BHGjMbbRxhJSjWMtPklYSwDIJBxMdTM0pU7B1HEuHW5Oh1C5NSxza2A7NkClw0SKFZfyTkBcgGflcLObWLGWONZClcOWw4SXyMxCKIqX7OYidGVDs9pTKtjQXj%2B3WfQZmcD8vnGRMZW9IIEpU3lBrHc4ebuMhM%2FZ3yJvX18cLKg624tPUXnxn7IjYus2%2F7Fen45bfyOvdHMbvT8d0mpfzAijqegfiNPKQlwjhHgm1psp3pxC73m7K4qYd8GYyn7uxC7TM389DqRWeuh18PBF7myaEuUfva2MssM8R%2FMUHv5xkHv7b9%2Bk3Z9CJuvg%2BDaR4tRclfhRHIS5EJdUnfocet9rS8pVDm8ppp969Gfs6afmldpmzzRefuWoQFLSIXbbFx%2FK3n6VQdjTjeNS%2FbIhwSgDknKpa9nmyhg8an2wX5R%2BBrtzhkPHKZxi9q7yN3D3Nzydjsfd29NbFPvIrFODHAc328h2%2FP0Pb7vL%2FIbi7cNl5rqMTnt%2F89k7Yi%2FBkXqhD4%2B45VHPBlDybZzo5zbRDPe73V2%2BprKQdUYIcn%2FcZT6QB8kEeZtEt6YvPawdVyaEiI2%2F82NfPh3FYhyRg%2BQO8XrW3ksWQbmZUyJKhrGTs%2B7rKU6MA3lWceivkc0j5f7KsKMMO3sxb8NOo2pvMAwvGXZFvlbZsIMkXmeYxOVIMexwsvainMTVN4drWDVZvxyuMrf3sR6v2Fy0SbsJGMboNC6qdk0nm0TzzuPSmg2y3nYIbJbTJaha%2FXSK%2FIiCrH1ME%2FFpCB0yWXKSQiai2vJfdB33xMrZDF6WsyA%2BSwHnlymvAUevwV%2B%2BF7%2F6ofIJNAHu2IurTYYzAVH%2Bf5H4%2B2f2vnKSPm2juCphz%2B9TfGNo5Lw4hsFzFnvd%2BlGD%2B6Kg8aH92wYXh5wHQxHwtD93lr6AP02aedev3uHFzxd0OoFMDzkqz1RNI6B586aku5i1KUkjdLPGlCziH5VWP9zqfp3mRp6ThDDabnvwl68lSALQIpqt9540vvnTPya2uII3jSkcaHnOXeT%2BPH%2BWLXhvThTPV%2FhaZNcn4JgOJXvFtlxwb6klVH2VZaWyLXfi3Uh6h2lqZJ80YDoEr%2FX22CVXa8IWgjx2LXVlkwQSjWKeuYJ%2F2AWnzVtvNwZFQL36lm%2B7EUaSym%2B8JoW5tAyTkk%2BWTtsrtTrT5KYz59iliGuTIkqFXrHO1I1p6MzBeZC6QQ5TwDcfQl31FD6JkC01vDeuxv%2F0tgelxZUWn5UWN%2FRJanG56StKi0vV4kCfqRYH%2Bmy0%2BJW0IGSvxf%2FaHpQtrrT4vLR4ooqnqMXNm9fiN%2By%2Fdu2ZanHXno0Wv5ImSOy1%2BB9BuPd2So0rNT4nNQ40a4p6HDhznPY1aNiX6s%2FKzHi3NLLb%2FxIYcPRoo9rLuf3mBzArNrHUNGsRNSG5oGmtCTFZdUHFVSA5VznVC3DsgmoxaLBrNjDT3xUz9WWmREM4WrV%2FKRvOIhhrKWyQdYEKJLXzUTpXls41NWqmJtANa6WV%2FhEdfAb0BnLqNDAUrIF1pYFFCM1MhLRpYBu6LiMN7JJcJU4HM%2BhErnTwxeykrRzNJGULGyVM%2BvCE8RZw5QzQUVpYtha2XI5a2IH0xRO1L1gLQ6WFhYjNDkdhooVtLNEuFZTkCAeBSthQSngC3JQo4aLWiashLIyzsL967nGxy0eGo1vE3vawT2725Ic9xnlMsTpUSNP9F2%2BP2z3QPSk%2BpF9e2LhByGNEweGl8TGe0i9n8Rivwc%2FGx%2FjXduMH%2FR%2Bjhnv7crn8iG%2BC5%2F70Dptgj1gQh3zR38NfHAsqEAdhKp5GTdBgQUXKAJgMzA0qAE72w3AW8w6H1xlURSixKxzOryUGMKQktilLnZGlDqADV6Xe9g6BUxOjnbCr%2B5rprkOyq6Y7bfY%2Fo5y15Il0%2BsYEo%2FMYFmqqwLgQEy6TOG0OAYeZV57cDeLmg5rKvTQFbtJWLqwygc6ItQhfE76LAKe8KSc0fklDZJAoDntRcuVrK%2BN8oDEjPfn0xQ%2B3yYohNuXh46egws0ktZtEVMmGY%2F35hr7S9BIGIWd9J1deOeCMGwjfGW9Hv8kgR0lJ4m5JbHbFRw0N2myELyR4F4jLeDPV3O9pcBO0NciEmwwSJNrCmAnHPOVocvx3eWJdn2x0rYjDZ2daptLkMjU5Zak7xkhVDqDlrkqamsgpRRdeGUT6O2%2F1bSuzXITAzWVRq1muGTxyjJfAkiaAbWWlT4G5Et6CZKUqG%2BVO6nZxGSFwjhXjrutUdbtj8dbtXXr7tkcdAXJbGPr45HdNW%2BkOcTnXWLllnz4npz71GFpHGbpNbt0RZ5h2x4oAFqe4Akb2Qls5MYQoKtjpTrZMRk4MQHbpFgh7lBNjCtyUYGrXxGHUi9mJaNtBunN5Qh25jWorboz%2BXgyt2h5nBWBXfYFyY%2FCEOi6hWIHujq60Nxx3pZlnWENc2gbOyi6hnn6lB0NBDyTzGPADNdFNneBUEx9qHOEa5b0ccY7jdkAr4ADSxTT8FDuvkeMMlJq7ASrVxlK1WZ2qzdEIY4ONv4gK%2FkBxuKm5SZViLnHMhXiLSBJjw1pU4pk4CCUlEjQOu1R%2FeIYvoIpWjDo0c73wZQkdAoEDsrFAb%2FxiW12XYtasH6fakXdqpK3rDE563VFNE8SI3h5hICpBl41eJ9GCsGYz0GFQvamY62LmQtWbBhkMZMJbS3k9FOCFo%2B80lt4R2Ddfk3CN5J6Iga6RjRe9FvAEffjixcm3iGmTva45PV0iZHUHDziAig2Rcm55kTipIlO9LT80jQsBxkUVPdBlIMxgc7FwqXax%2Bmkd%2Bl5cU1laX9l466IwkwptehboXFq6LUnlzcB8Tj6GAWKU889D7%2Fj6OdigV%2Fjb%2Fwc%3D)
- [File](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/uml/class.svg)
 
#### Use-case diagram
- [Preview]()
- [File]()

------------
### Design Patterns
------------

- #### [Visitor](https://refactoring.guru/design-patterns/visitor) 
  _(4 implementations)_

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

<br>

- #### [Memento](https://www.baeldung.com/java-memento-design-pattern) 
  _(1 implementation)_

**Memento** - [RoomConfiguration](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/RoomConfiguration.java)<br>

**Originator** - [ControlPanel](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/ControlPanel.java)<br>
_save()_ = `saveConfiguration()`<br>
_restore()_ = `loadConfiguration()`<br>

**Caretaker** - [Person](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/creature/person/Person.java)<br>
_hitSave()_ = `saveConfiguration()`<br>
_hitUndo()_ = `loadConfiguration()`<br>

State - preferred temperature, humidity, brightness and light color (fields in the ControlPanel).
State is saved in static field in ControlPanel.

<br>

- #### [Prototype](https://refactoring.guru/design-patterns/prototype) 
  _(2 implementations)_

**Prototype** - [Prototype](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/utils/Prototype.java)<br>

**ConcretePrototype** - [RoomConfiguration](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/RoomConfiguration.java)<br>
_clone()_ = `copy()`<br>

**Prototype** - [Prototype](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/utils/Prototype.java)<br>

**ConcretePrototype** - [Device](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/consumer/device/Device.java) (+ all inheritors)<br>
_clone()_ = `clone()` <br>

<br>

- #### [Builder](https://refactoring.guru/design-patterns/builder) 
  _(1 implementation)_

**Client** - [Simulation](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/smarthome/Simulation.java) <br>

**ConcreteBuilder** - [HomeBuilder](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/HomeBuilder.java) <br>
_reset()_ = `reset()` <br>
_getResult()_ = `getHome()` <br>
_buildStepA()_ = `buildHome()` <br>
_buildStepB()_ = `buildFloor()` <br>
_buildStepC()_ = `buildRoom()` <br>

There is no need in project in Builder interface as long as there is only one type of Home creating.
We don't also need a Director class because this role is played by home draft read from configuration file.

<br>

- #### [AbstractFactory](https://refactoring.guru/design-patterns/abstract-factory) 
  _(1 implementation)_

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

<br>

- #### [Singleton](https://refactoring.guru/design-patterns/singleton) 
  _(2 implementations)_

**Singleton** - [Simulation](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/smarthome/Simulation.java) <br>

**Client** - [SmartHome](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/smarthome/SmartHome.java) and so on <br>

<br>

**Singleton** - [Street](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/place/Street.java) <br>

**Client** - [Simulation](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/smarthome/Simulation.java) and so on <br>

<br>

- #### [Facade](https://refactoring.guru/design-patterns/facade) 
  _(2 implementations)_

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

<br>

- #### [SimpleFactory](https://dragonprogrammer.com/design-patterns-java-simple-factory/) 
  _(3 implementations)_

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

<br>

- #### [Strategy](https://refactoring.guru/design-patterns/strategy) 
  _(2 implementations)_

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

<br>

- #### [TemplateMethod](https://refactoring.guru/design-patterns/template-method) 
  _(1 implementation)_

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

<br>

- #### [Observer](https://refactoring.guru/design-patterns/observer) 
  _(1 implementation)_

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

<br>

- #### [State](https://refactoring.guru/design-patterns/state) 
  _(1 implementation)_

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

<br>

- #### [Adapter](https://refactoring.guru/design-patterns/adapter) 
  _(1 implementation)_

**Client** - [PriorityQueue](https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html)

**Service** - [Deque](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html)

**Adapter** - [RankedQueue](https://github.com/neiron94/Smart-Home-Simulation/blob/develop/src/main/java/utils/RankedQueue.java)

In the project Creature is need to work with its sequences of future actions (called memory). Those sequences have different priorities (what should be done next). Also, implementation requires to have ability in iteration by action sequences. For this reason PriorityQueue is used (is easily iterable + can add elements respecting priorities).
Every element of memory should be specific action sequence.
That's why adapter was implemented to store those action sequences and its priority to let PriorityQueue work with it later.

------------
### Application Input
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
### Application Output
------------
<details>
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

<details>
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

<details>
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

<details>
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
