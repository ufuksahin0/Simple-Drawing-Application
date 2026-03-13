# Simple Drawing Application

## Class Hierarchy

The application follows a clean Object-Oriented design pattern, separating the model, view, and controller.

### Model

The `model` package contains the shape classes:

- **`MyShape`**: An abstract base class that defines the common properties of all shapes, such as coordinates (`x1`, `y1`, `x2`, `y2`) and `color`. It includes an abstract `draw(Graphics g)` method, forcing subclasses to provide their own drawing logic.
- **`MyLine`**: A concrete subclass of `MyShape` that implements the `draw` method to draw a line.
- **`MyRectangle`**: A concrete subclass of `MyShape` that implements the `draw` method to draw a rectangle.
- **`MyOval`**: A concrete subclass of `MyShape` that implements the `draw` method to draw an oval.

This hierarchical structure allows for easy extension with new shapes in the future and promotes code reuse.

### View

The `view` package is responsible for the user interface:

- **`MainFrame`**: The main application window (`JFrame`) that holds all other GUI components. It uses a `BorderLayout` to organize the control panel and the drawing canvas.
- **`DrawingCanvas`**: A `JPanel` that serves as the drawing area. It overrides the `paintComponent` method to iterate through an `ArrayList` of `MyShape` objects and call their respective `draw` methods. This ensures that all drawn shapes are persistent.

### Controller

The `controller` package handles user input and application logic:

- **`DrawingController`**: This class connects the view and the model. It contains inner classes for handling events:
    - **`ShapeSelectorListener`**: An `ActionListener` that updates the current shape type when the user selects a new shape from the `JComboBox`.
    - **`ColorButtonListener`**: An `ActionListener` that opens a `JColorChooser` and updates the current drawing color.
    - **`ClearButtonListener`**: An `ActionListener` that clears all shapes from the drawing canvas.
    - **`DrawingMouseListener`**: A `MouseAdapter` that listens for mouse press, drag, and release events on the `DrawingCanvas` to create, preview, and finalize shapes.

## Event-Handling Logic

Event handling is central to the application's interactivity:

- **Action Events**: The `DrawingController` attaches `ActionListener`s to the shape selector, color button, and clear button. When an event occurs, the controller updates the application's state (e.g., current shape type, current color) or triggers an action (e.g., clearing the canvas).
- **Mouse Events**: A `MouseAdapter` is attached to the `DrawingCanvas`.
    - `mousePressed`: Records the starting coordinates of the shape.
    - `mouseDragged`: Continuously updates the end coordinates and repaints the canvas to show a "preview" of the shape being drawn.
    - `mouseReleased`: Finalizes the shape's coordinates and adds the new shape object to the `ArrayList` in the `DrawingCanvas`. The preview shape is cleared, and the canvas is repainted to show the final, persistent shape.

This separation of concerns makes the code more organized, easier to debug, and maintain.

## Optional Features Implemented

For bonus points, the following optional features were added to the project:

- **Shape Filling:** A "Filled" checkbox has been added to the control panel. When selected, `Rectangle` and `Oval` shapes will be drawn with a solid color instead of just an outline.
- **Undo Last Shape:** An "Undo" button has been added. Clicking it removes the most recently drawn shape from the canvas.
- **Status Bar:** A status bar at the bottom of the window provides real-time feedback to the user, showing the current mouse coordinates, the selected drawing tool, and the last action performed (e.g., "Shape Added", "Canvas Cleared").
