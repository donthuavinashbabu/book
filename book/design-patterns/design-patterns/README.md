# Design Patterns

Java examples for creational, structural, and behavioral patterns, plus two SOLID examples. Course links are in [`materials.md`](materials.md).

[`settings.gradle`](settings.gradle) is a Gradle build named `design-patterns`. It includes `creational`, `structural`, and `behavioral`. Each of those modules has an empty [`build.gradle`](creational/build.gradle). Shared dependencies are in the root [`build.gradle`](build.gradle): JUnit 4.12, SLF4J 1.7.5 with Log4j 1.2, and Lombok 1.18.6. Demos live in `src/test/java`. Console logging for the creational and behavioral modules is `log4j.properties` with pattern `%m%n`.

[`solid-principles`](solid-principles/pom.xml) is a separate Maven module. It is not included in `settings.gradle`. It targets Java 8 and JUnit 4.13. `mvn test` compiles it. Its demos are `main` methods, so that command does not run them.

## Prerequisites

- JDK 8
- Gradle 6 for the pattern modules. The root build uses `testCompile`, which Gradle 7 removed. This repo has no Gradle wrapper.
- Maven 3.6+ for `solid-principles`

Lombok `1.18.6` fails on JDK 21. Singleton, builder, prototype, the object-pool `Connection`, and the weather observer use Lombok.

## Run

From this directory, with Gradle 6:

```bash
gradle test
```

One JUnit demo:

```bash
gradle :creational:test --tests com.creational.factory.pattern.FactoryPattern
```

`FlyweightPattern` sleeps one second between draws, so that test takes about 20 seconds.

These classes have `main` and are not picked up by `gradle test` or `mvn test`. Run them from the IDE:

* [`ObjectPoolPattern`](creational/src/test/java/com/creational/object/pool/pattern/ObjectPoolPattern.java)
* [`observer/App`](behavioral/src/test/java/com/behavioral/pattern/observer/App.java)
* [`template/App`](behavioral/src/test/java/com/behavioral/pattern/template/App.java)
* [`nullobject/App`](behavioral/src/test/java/com/behavioral/pattern/nullobject/App.java)
* [`lsp/App`](solid-principles/src/test/java/com/cerebro/lsp/App.java)
* [`SortProcessor`](solid-principles/src/test/java/com/cerebro/ocp/SortProcessor.java)

[`com.cerebro.App`](solid-principles/src/main/java/com/cerebro/App.java) prints `Hello World!`.

```bash
cd solid-principles
mvn -q compile
java -cp target/classes com.cerebro.App
```

## Creational

### Singleton

[Notes](creational/src/test/java/com/creational/singleton/README.md). [`SingletonPattern`](creational/src/test/java/com/creational/singleton/SingletonPattern.java) loads the same instance twice for each style.

* [`EagerSingletonClass`](creational/src/test/java/com/creational/singleton/EagerSingletonClass.java) creates the instance when the class loads. The getter is `getInstnace`.
* [`LazySingletonClass`](creational/src/test/java/com/creational/singleton/LazySingletonClass.java) creates the instance on the first `getInstance` call, inside a `synchronized` block.
* [`Database`](creational/src/test/java/com/creational/singleton/Database.java) is an enum with one constant, `INSTANCE`.

### Factory

[Notes](creational/src/test/java/com/creational/factory/pattern/README.md). [`FactoryPattern`](creational/src/test/java/com/creational/factory/pattern/FactoryPattern.java) asks [`ShapeFactory`](creational/src/test/java/com/creational/factory/pattern/ShapeFactory.java) for `circle`, `square`, and `rectangle`, then calls `draw`.

### Abstract factory

[Notes](creational/src/test/java/com/creational/abstractfactory/pattern/README.md). [`AbstractFactoryPattern`](creational/src/test/java/com/creational/abstractfactory/pattern/AbstractFactoryPattern.java) uses [`FactoryProducer`](creational/src/test/java/com/creational/abstractfactory/pattern/FactoryProducer.java) to get a shape factory and a color factory. It draws circle, square, and rectangle, then paints red, blue, and green.

### Builder

[Notes](creational/src/test/java/com/creational/builder/pattern/README.md). [`BuilderPattern`](creational/src/test/java/com/creational/builder/pattern/BuilderPattern.java) uses [`MealBuilder`](creational/src/test/java/com/creational/builder/pattern/MealBuilder.java).

* Veg meal: veg burger in a wrapper at `1.23`, plus coke in a bottle at `0.5`. Price `1.73`.
* Non-veg meal: chicken burger in a wrapper at `2.34`, plus pepsi in a bottle at `0.5`. Price `2.84`.

### Prototype

[Notes](creational/src/test/java/com/creational/prototype/pattern/README.md). [`PrototypePattern`](creational/src/test/java/com/creational/prototype/pattern/PrototypePattern.java) loads [`ShapeCache`](creational/src/test/java/com/creational/prototype/pattern/ShapeCache.java) and clones shape `1` (circle), `2` (square), and `3` (rectangle).

### Object pool

[Notes](creational/src/test/java/com/creational/object/pool/pattern/README.md). [`ObjectPool`](creational/src/test/java/com/creational/object/pool/pattern/ObjectPool.java) keeps locked and unlocked objects in two `Hashtable`s. An unlocked object older than `50000` ms is discarded. [`JDBCConnectionPool`](creational/src/test/java/com/creational/object/pool/pattern/JDBCConnectionPool.java) creates a [`Connection`](creational/src/test/java/com/creational/object/pool/pattern/Connection.java) from the strings it was given. It does not open a database connection.

[`ObjectPoolPattern`](creational/src/test/java/com/creational/object/pool/pattern/ObjectPoolPattern.java) calls `takeOut` and then `takeIn`. Its constructor arguments are `driver`, `url`, `username`, `password`. `JDBCConnectionPool` stores them as `url`, `driver`, `username`, `password`, so the HSQLDB driver name is stored in `url` and the JDBC URL is stored in `driver`.

## Structural

### Adapter

[Notes](structural/src/test/java/com/structural/adapter/pattern/README.md). [`AdapterPattern`](structural/src/test/java/com/structural/adapter/pattern/AdapterPattern.java) plays `file1` as mp3, `file2` as mp4, and `file3` as vlc. `file4` as mp5 prints `invalid media type`. [`AudioPlayer`](structural/src/test/java/com/structural/adapter/pattern/AudioPlayer.java) plays mp3 itself. [`MediaAdapter`](structural/src/test/java/com/structural/adapter/pattern/MediaAdapter.java) forwards mp4 and vlc to [`Mp4Player`](structural/src/test/java/com/structural/adapter/pattern/Mp4Player.java) and [`VlcPlayer`](structural/src/test/java/com/structural/adapter/pattern/VlcPlayer.java).

### Bridge

[`BridgePattern`](structural/src/test/java/com/structural/bridge/pattern/BridgePattern.java) draws a radius-10 circle with [`RedCircle`](structural/src/test/java/com/structural/bridge/pattern/RedCircle.java) and a radius-12 circle with [`BlueCircle`](structural/src/test/java/com/structural/bridge/pattern/BlueCircle.java).

### Composite

[`CompositePattern`](structural/src/test/java/com/structural/composite/pattern/CompositePattern.java) builds an employee tree and prints it. `jack` (founder) has `john` (engg-head) and `jim` (tech-head). `john` has `tim` and `ana`. `jim` has `jane`, `josh`, and `jack`.

### Decorator

[Notes](structural/src/test/java/com/structural/decorator/pattern/README.md). [`DecoratorPattern`](structural/src/test/java/com/structural/decorator/pattern/DecoratorPattern.java) draws a square and a rectangle, then draws each again through [`BlueShapeDecorator`](structural/src/test/java/com/structural/decorator/pattern/BlueShapeDecorator.java).

### Facade

[Notes](structural/src/test/java/com/structural/facade/pattern/README.md). [`FacadePattern`](structural/src/test/java/com/structural/facade/pattern/FacadePattern.java) calls `drawSquare`, `drawPentagon`, and `drawHexagon` on [`ShapeMaker`](structural/src/test/java/com/structural/facade/pattern/ShapeMaker.java).

### Filter

[`FilterPattern`](structural/src/test/java/com/structural/filter/pattern/FilterPattern.java) filters seven people: jack, jim, json, and john are male; jamy, kathy, and tilda are female; john and tilda are married; the rest are single. It prints males, females, singles, single males (`AndCriteria`), and single-or-female (`OrCriteria`).

### Flyweight

[Notes](structural/src/test/java/com/structural/flyweight/pattern/README.md). [`ShapeFactory`](structural/src/test/java/com/structural/flyweight/pattern/ShapeFactory.java) keeps one [`Circle`](structural/src/test/java/com/structural/flyweight/pattern/Circle.java) per color. [`FlyweightPattern`](structural/src/test/java/com/structural/flyweight/pattern/FlyweightPattern.java) requests 20 circles in red, green, blue, black, or white. A color is created once and then reused.

### Proxy

[`ProxyPattern`](structural/src/test/java/com/structural/proxy/pattern/ProxyPattern.java) displays `file1.jpg` twice through [`ImageProxy`](structural/src/test/java/com/structural/proxy/pattern/ImageProxy.java). The first call constructs [`RealImage`](structural/src/test/java/com/structural/proxy/pattern/RealImage.java), which prints `loading file file1.jpg from disk` and then `display file file1.jpg`. The second call displays the same object and does not load it again.

## Behavioral

[Module notes](behavioral/notes.md).

### Chain of responsibility

[Diagram](behavioral/src/test/java/com/behavioral/chain/of/responsibility/pattern/README.md). [`ChainOfResponsibilityPattern`](behavioral/src/test/java/com/behavioral/chain/of/responsibility/pattern/ChainOfResponsibilityPattern.java) builds `ErrorLogger` (level 3) → `FileLogger` (level 2) → `ConsoleLogger` (level 1). A logger writes when its level is less than or equal to the message level, then forwards the message.

| Message | Level | Loggers that write |
| --- | --- | --- |
| info message | 1 | console |
| debug message | 2 | file, console |
| error message | 3 | error, file, console |

`FileLogger` prints to the console. It does not write a file.

### Command

[Notes](behavioral/src/test/java/com/behavioral/command/pattern/README.md). [`CommandPattern`](behavioral/src/test/java/com/behavioral/command/pattern/CommandPattern.java) queues a sell and a buy for `stock1` (quantity 10), places those orders, then does the same for `stock2` (quantity 20).

### Interpreter

[`InterpreterPattern`](behavioral/src/test/java/com/behavioral/interpreter/pattern/InterpreterPattern.java) builds an OR of `jack` and `john`, and an AND of `jane` and `julie`.

| Expression | Context | Result |
| --- | --- | --- |
| male OR | `jack` | true |
| male OR | `jim` | false |
| female AND | `julie jane` | true |
| female AND | `jean` | false |

### Iterator

[Notes](behavioral/src/test/java/com/behavioral/iterator/pattern/README.md). [`IteratorPattern`](behavioral/src/test/java/com/behavioral/iterator/pattern/IteratorPattern.java) walks [`NameRepository`](behavioral/src/test/java/com/behavioral/iterator/pattern/NameRepository.java) and prints `jack`, `jill`, `john`, `jim`, `tim`, `jane`, `jan`.

### Memento

[`MementoPattern`](behavioral/src/test/java/com/behavioral/memento/pattern/MementoPattern.java) saves `state-0`, `state-1`, and `state-2`, then restores each one. The prints are `index-0: state-0`, `index-1: state-1`, and `index-2: state-2`.

### Observer

[Notes](behavioral/src/test/java/com/behavioral/pattern/observer/README.md). [`App`](behavioral/src/test/java/com/behavioral/pattern/observer/App.java) registers one [`WeatherObserver`](behavioral/src/test/java/com/behavioral/pattern/observer/WeatherObserver.java) on a [`WeatherStation`](behavioral/src/test/java/com/behavioral/pattern/observer/WeatherStation.java), then sets `100`, `200`, and `300`. Each setter notifies the observer.

### Strategy

[Notes](behavioral/src/test/java/com/behavioral/strategy/pattern/README.md). [`StrategyPattern`](behavioral/src/test/java/com/behavioral/strategy/pattern/StrategyPattern.java) runs add, subtract, and multiply on `10` and `5`. The prints are `15`, `5`, and `50`.

### Template

[Notes](behavioral/src/test/java/com/behavioral/pattern/template/README.md). [`Algorithm.sort`](behavioral/src/test/java/com/behavioral/pattern/template/Algorithm.java) calls `initialize`, `sorting`, then `print`. [`App`](behavioral/src/test/java/com/behavioral/pattern/template/App.java) runs that sequence for [`SelectionSort`](behavioral/src/test/java/com/behavioral/pattern/template/SelectionSort.java) and [`BubbleSort`](behavioral/src/test/java/com/behavioral/pattern/template/BubbleSort.java) on `{1, 2, 3, 4, 5}`. Both classes print those three steps. They do not reorder the array.

### Null object

[Notes](behavioral/src/test/java/com/behavioral/pattern/nullobject/README.md). [`App`](behavioral/src/test/java/com/behavioral/pattern/nullobject/App.java) asks [`CustomerFactory`](behavioral/src/test/java/com/behavioral/pattern/nullobject/CustomerFactory.java) for `jack` and `olaf`. The database contains `jack`, `jill`, `john`, and `james`, so `jack` is a [`RealCustomer`](behavioral/src/test/java/com/behavioral/pattern/nullobject/RealCustomer.java). `olaf` is a [`NullCusomer`](behavioral/src/test/java/com/behavioral/pattern/nullobject/NullCusomer.java), whose `getName` is `no name`.

## SOLID

[Notes](solid-principles/notes.md).

### Liskov substitution

[Notes](solid-principles/src/test/java/com/cerebro/lsp/README.md). [`App`](solid-principles/src/test/java/com/cerebro/lsp/App.java) treats an [`ElectricCar`](solid-principles/src/test/java/com/cerebro/lsp/ElectricCar.java) and a [`PetrolCar`](solid-principles/src/test/java/com/cerebro/lsp/PetrolCar.java) as a [`Vehicle`](solid-principles/src/test/java/com/cerebro/lsp/Vehicle.java). Each call prints `speeding up`. Electric fuel prints `charging`. Petrol fuel prints `fueling`.

### Open/closed

[`SortProcessor`](solid-principles/src/test/java/com/cerebro/ocp/SortProcessor.java) passes an empty list to [`InsertionSort`](solid-principles/src/test/java/com/cerebro/ocp/InsertionSort.java), [`SelectionSort`](solid-principles/src/test/java/com/cerebro/ocp/SelectionSort.java), and [`MergeSort`](solid-principles/src/test/java/com/cerebro/ocp/MergeSort.java). Each implementation prints its name.

## Layout

```text
build.gradle
settings.gradle
materials.md
behavioral/notes.md
creational/src/test/java/com/creational/
solid-principles/pom.xml
solid-principles/notes.md
structural/src/test/java/com/structural/
```
