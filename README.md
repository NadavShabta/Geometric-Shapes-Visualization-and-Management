# Geometric-Shapes-Visualization-and-Management

## Abstract
This project is an implementation of a set of geometric shapes, a shape container, a basic GUI for visualization, and save/load capabilities. The project includes JUnit tests for the geometric shapes and collections to ensure robust functionality.

## Features
- **Geometric Shapes**: Implementation of various geometric shapes including:
  - `Point2D`
  - `Circle2D`
  - `Rect2D`
  - `Segment2D`
  - `Triangle2D`
  - `Polygon2D`
- **Shape Container**: A collection to manage and manipulate shapes.
- **GUI**: A basic graphical user interface to visualize and interact with the shapes.
- **Save/Load**: Functionality to save shapes to and load shapes from files.
- **JUnit Tests**: Comprehensive tests to verify the functionality of the geometric shapes and shape collections.

## General Techniques Used
- **Object-Oriented Programming (OOP)**: Design and implementation of classes and interfaces to model geometric shapes and their behaviors.
- **Graphical User Interface (GUI)**: Development of a simple GUI for visualizing and interacting with geometric shapes.
- **File I/O**: Implementation of save and load functionalities to persist and retrieve shapes from files.
- **Unit Testing**: Use of JUnit to create extensive test cases for validating the correctness of the implemented classes.

## Project Structure
```plaintext
GeometricShapesGUI/
├── src/
│   ├── Geo/
│   │   ├── Point2D.java
│   │   ├── Circle2D.java
│   │   ├── Rect2D.java
│   │   ├── Segment2D.java
│   │   ├── Triangle2D.java
│   │   ├── Polygon2D.java
│   │   └── ...
│   ├── GUI/
│   │   ├── GUIShape.java
│   │   ├── ShapeCollection.java
│   │   └── ...
│   ├── Ex4.java
│   └── ...
├── test/
│   ├── Geo/
│   │   ├── Point2DTest.java
│   │   ├── Circle2DTest.java
│   │   ├── Rect2DTest.java
│   │   ├── Segment2DTest.java
│   │   ├── Triangle2DTest.java
│   │   ├── Polygon2DTest.java
│   │   └── ...
│   ├── GUI/
│   │   ├── GUIShapeTest.java
│   │   ├── ShapeCollectionTest.java
│   │   └── ...
│   ├── Ex4Test.java
│   └── ...
├── lib/
│   └── ...
├── Ex4.jar
└── README.md




## Provided Files
- The following files and initial resources were provided as part of the assignment:
  - `Ex4_v0.1-out.jar`
  - Initial structure and some skeleton classes in the `Geo` package

## Running the Project
1. **Clone the Repository**:
    ```bash
    git clone https://github.com/NadavShabta/GeometricShapesGUI.git
    cd GeometricShapesGUI
    ```

2. **Compile and Run**:
    ```bash
    javac -d bin src/**/*.java
    java -jar Ex4.jar
    ```

3. **Run JUnit Tests**:
    Use your preferred IDE or a build tool like Maven/Gradle to run the JUnit tests.

---

*Feel free to reach out to me for any collaboration or job opportunities!*
