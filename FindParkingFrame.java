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
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        );

        // Title
        JLabel titleLabel = new JLabel(
                "FIND PARKING SPOT",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Search panel
        JPanel searchPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 10)
        );

        JLabel searchLabel = new JLabel("Search Location:");

        JTextField searchField = new JTextField(25);

        JButton searchButton = new JButton("SEARCH");

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Parking locations
        JPanel parkingPanel = new JPanel(
                new GridLayout(2, 1, 15, 15)
        );

        JPanel parking1 = createParkingPanel(
                "City Parking Centre",
                "Kollam",
                "12",
                "₹50/hour"
        );

        JPanel parking2 = createParkingPanel(
                "Railway Parking Area",
                "Kollam",
                "8",
                "₹40/hour"
        );

        parkingPanel.add(parking1);
        parkingPanel.add(parking2);

        // Center panel
        JPanel centerPanel = new JPanel(
                new BorderLayout(10, 15)
        );

        centerPanel.add(
                searchPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                parkingPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // Bottom panel
        JPanel bottomPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        JButton backButton = new JButton("BACK");

        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // Add main panel
        add(mainPanel);

        // Show frame
        setVisible(true);
    }

    // Creates a parking location panel
    private JPanel createParkingPanel(
            String name,
            String location,
            String availableSlots,
            String price) {

        JPanel panel = new JPanel(
                new BorderLayout(15, 5)
        );

        panel.setBorder(
                BorderFactory.createTitledBorder(name)
        );

        JPanel infoPanel = new JPanel(
                new GridLayout(3, 1, 5, 5)
        );

        JLabel locationLabel =
                new JLabel("Location: " + location);

        JLabel slotsLabel =
                new JLabel("Available Slots: " + availableSlots);

        JLabel priceLabel =
                new JLabel("Price: " + price);

        infoPanel.add(locationLabel);
        infoPanel.add(slotsLabel);
        infoPanel.add(priceLabel);

        JButton detailsButton =
                new JButton("VIEW DETAILS");

        panel.add(
                infoPanel,
                BorderLayout.CENTER
        );

        panel.add(
                detailsButton,
                BorderLayout.EAST
        );

        return panel;
    }

    public static void main(String[] args) {
        new FindParkingFrame();
    }
}