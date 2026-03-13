import controller.DrawingController;
import view.MainFrame;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            new DrawingController(frame);
            frame.setVisible(true);
        });
    }
}
