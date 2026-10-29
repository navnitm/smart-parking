import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

    public HomeFrame() {

        // Basic window settings
        setTitle("Smart Parking System");
        setSize(850, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Heading
        JLabel titleLabel = new JLabel(
                "SMART PARKING SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel descLabel = new JLabel(
                "Find and manage parking spaces easily.",
                SwingConstants.CENTER);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        topPanel.add(titleLabel);
        topPanel.add(descLabel);

        // Buttons and descriptions
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 20, 20));

        JButton findButton = new JButton("Find Parking Spot");
        JButton statusButton = new JButton("My Parking Status");

        centerPanel.add(findButton);
        centerPanel.add(new JLabel(
                "Search and reserve available parking spots."));

        centerPanel.add(statusButton);
        centerPanel.add(new JLabel(
                "Manage your active reservations and vehicles."));

        // Find Parking button navigation
        findButton.addActionListener(e -> {
            new FindParkingFrame();
        });

        // Parking Status button navigation
        statusButton.addActionListener(e -> {
            new ParkingStatusFrame();
        });

        // Main layout
        JPanel mainPanel = new JPanel(new BorderLayout(20, 30));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(40, 50, 40, 50));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HomeFrame();
    }
}