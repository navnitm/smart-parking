import javax.swing.*;
import java.awt.*;

public class ParkingStatusFrame extends JFrame {

    // Constructor
    public ParkingStatusFrame() {

        // Frame settings
        setTitle("My Parking Status");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 50, 30, 50)
        );

        // Title
        JLabel titleLabel = new JLabel(
                "MY PARKING STATUS",
                SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        // Subtitle
        JLabel subtitleLabel = new JLabel(
                "View the details of your currently parked vehicle.",
                SwingConstants.CENTER
        );
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Heading panel
        JPanel headingPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        headingPanel.add(titleLabel);
        headingPanel.add(subtitleLabel);

        // Vehicle information labels
        JLabel vehicleNumberLabel = new JLabel("Vehicle Number:");
        JLabel vehicleTypeLabel = new JLabel("Vehicle Type:");
        JLabel parkingAreaLabel = new JLabel("Parking Area:");
        JLabel slotNumberLabel = new JLabel("Parking Slot:");
        JLabel entryTimeLabel = new JLabel("Entry Time:");
        JLabel parkingStatusLabel = new JLabel("Status:");

        // Example information
        JLabel vehicleNumber = new JLabel("KL-02-AB-1234");
        JLabel vehicleType = new JLabel("Car");
        JLabel parkingArea = new JLabel("Parking Area A");
        JLabel slotNumber = new JLabel("P-05");
        JLabel entryTime = new JLabel("10:30 AM");
        JLabel parkingStatus = new JLabel("PARKED");

        // Information panel
        JPanel informationPanel = new JPanel(
                new GridLayout(6, 2, 15, 15)
        );

        informationPanel.add(vehicleNumberLabel);
        informationPanel.add(vehicleNumber);

        informationPanel.add(vehicleTypeLabel);
        informationPanel.add(vehicleType);

        informationPanel.add(parkingAreaLabel);
        informationPanel.add(parkingArea);

        informationPanel.add(slotNumberLabel);
        informationPanel.add(slotNumber);

        informationPanel.add(entryTimeLabel);
        informationPanel.add(entryTime);

        informationPanel.add(parkingStatusLabel);
        informationPanel.add(parkingStatus);

        // Buttons
        JButton viewSlotButton = new JButton("View Parking Slot");
        JButton backButton = new JButton("Back");

        // Button panel
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 20, 10)
        );

        buttonPanel.add(viewSlotButton);
        buttonPanel.add(backButton);

        // Add components to main panel
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainPanel.add(informationPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        // Make frame visible
        setVisible(true);
    }

    // Main method
    public static void main(String[] args) {
        new ParkingStatusFrame();
    }
}