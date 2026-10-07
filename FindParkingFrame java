import javax.swing.*;
import java.awt.*;

public class FindParkingFrame extends JFrame {

    public FindParkingFrame() {
        setTitle("Find Parking Spot");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with border padding
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Title
        JLabel titleLabel = new JLabel("FIND PARKING SPOT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Search bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.add(new JLabel("Search Location:"));
        searchPanel.add(new JTextField(25));
        searchPanel.add(new JButton("SEARCH"));

        // Parking locations list
        JPanel parkingPanel = new JPanel(new GridLayout(2, 1, 15, 15));
        parkingPanel.add(createParkingPanel("City Parking Centre", "Kollam", "12", "₹50/hour"));
        parkingPanel.add(createParkingPanel("Railway Parking Area", "Kollam", "8", "₹40/hour"));

        // Center panel (Search + Locations)
        JPanel centerPanel = new JPanel(new BorderLayout(10, 15));
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(parkingPanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(new JButton("BACK"));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    // Helper method to create each parking location card
    private JPanel createParkingPanel(String name, String location, String availableSlots, String price) {
        JPanel panel = new JPanel(new BorderLayout(15, 5));
        panel.setBorder(BorderFactory.createTitledBorder(name));

        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        infoPanel.add(new JLabel("Location: " + location));
        infoPanel.add(new JLabel("Available Slots: " + availableSlots));
        infoPanel.add(new JLabel("Price: " + price));

        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(new JButton("VIEW DETAILS"), BorderLayout.EAST);
        return panel;
    }

    public static void main(String[] args) {
        new FindParkingFrame();
    }
}
