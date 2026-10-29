package stackvisualizer;

public class StackVisualizer {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new StackGUI().setVisible(true);
        });
    }
}