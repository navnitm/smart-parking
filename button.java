import javax.swing.*;
import java.awt.*;

public class ButtonExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JButton Example");
        JButton button = new JButton("Click Me");

        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Button clicked!");
        });

        frame.setLayout(new FlowLayout());
        frame.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 120);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}