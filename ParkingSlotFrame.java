import javax.swing.*;
import java.awt.*;

public class ParkingSlotFrame extends JFrame {

    public ParkingSlotFrame() {

        setTitle("Parking Slot Selection");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        );

        JLabel titleLabel = new JLabel(
                "SELECT PARKING SLOT",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ParkingSlotFrame();
    }
}
