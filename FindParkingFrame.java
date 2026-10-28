
import javax.swing.*;
import java.awt.*;

public class FindParkingFrame extends JFrame {

    public FindParkingFrame() {
        setTitle("Find Parking Spot");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Title
        JLabel titleLabel = new JLabel(
                "FIND PARKING SPOT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Search bar
        JPanel searchPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 10));

        searchPanel.add(new JLabel("Search Location:"));

        JTextField locationField = new JTextField(25);
        searchPanel.add(locationField);

        JButton searchButton = new JButton("SEARCH");
        searchPanel.add(searchButton);

        // Parking locations
        JPanel parkingPanel = new JPanel(new GridLayout(2, 1, 15, 15));

        parkingPanel.add(createParkingPanel(
                "City Parking Centre", "Kollam", "12", "₹50/hour"));

        parkingPanel.add(createParkingPanel(
                "Railway Parking Area", "Kollam", "8", "₹40/hour"));

        // Search and location panels
        JPanel centerPanel = new JPanel(new BorderLayout(10, 15));
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(parkingPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Search button action
        searchButton.addActionListener(e -> {
            String location = locationField.getText().trim();

            if (location.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a location.");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Parking locations in " + location
                                + " are displayed below.");
            }
        });

        // Back button
        JPanel bottomPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER));

        JButton backButton = new JButton("BACK");
        bottomPanel.add(backButton);

        backButton.addActionListener(e -> dispose());

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    // Create a parking location card
    private JPanel createParkingPanel(
            String name, String location,
            String availableSlots, String price) {

        JPanel panel = new JPanel(new BorderLayout(15, 5));
        panel.setBorder(BorderFactory.createTitledBorder(name));

        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        infoPanel.add(new JLabel("Location: " + location));
        infoPanel.add(new JLabel("Available Slots: " + availableSlots));
        infoPanel.add(new JLabel("Price: " + price));

        panel.add(infoPanel, BorderLayout.CENTER);

        JButton detailsButton = new JButton("VIEW DETAILS");
        panel.add(detailsButton, BorderLayout.EAST);

        // Connect VIEW DETAILS to parking slots
        detailsButton.addActionListener(e -> {
            new ParkingSlotFrame();
        });

        return panel;
    }

    public static void main(String[] args) {
        new FindParkingFrame();
    }
}

