This is a **UML editor** developed using Java and Object-Oriented Programming (OOP) concepts.

## Core Features
* **Shape Generation and Management**: Supports the creation of basic UML objects: `basicClass`, `useClass`
* **Line Drawing**: Supports creating and drawing association lines between objects: `associationLine`, `compositionLine`, `generalizationLine`
* **Dynamic Decoration System**: Supports dynamically adding visual decorations to shapes. Currently includes Border and Shadow effects.

## Technical Architecture and Design Patterns
* **Language**: Java
* **Graphical User Interface (GUI)**: Java Swing (including `JPanel`, `Graphics2D`)
* **Unit Testing Framework**: JUnit 5 (Jupiter)
* **Design Patterns**:
  * **Singleton Pattern**: Implemented in `Init.java` to ensure that only a single, unique instance of the main `JFrame` (application window) is created and maintained throughout the application's lifecycle.
  * **Simple Factory Pattern**: Implemented in `ShapeFactory` and `LineFactory` to centralize the instantiation logic of shapes and lines.
  * **Decorator Pattern**: Implemented for visual decorations (e.g., `BorderDecorator`, `ShadowDecorator`), allowing dynamic addition of display properties to shapes without modifying the base classes.

## Project Structure
* `src/model/`: Contains core data models, interfaces (e.g., `lineObject`), and concrete implementation classes.
* `src/initializeUMLEditor/`: Contains editor initialization and constant settings.
* `test/`: Contains unit test code for factories and specific component logic.

## How to Run
1. Open your Integrated Development Environment (IDE).
2. Locate the main entry point file: `/src/initializeUMLEditor/UMLEditor.java`.
3. Run this file to start the UML Editor interface.

## Testing
This project includes unit tests to ensure the functionality of the factory and decorator patterns. Open the test files under the `test` directory (e.g., `findObjectPortTest.java`, `LineFactoryTest.java`) and execute them using your IDE's built-in test runner.
