import javax.swing.*;
import java.awt.*;

public class ParkingStatusFrame extends JFrame {

    public ParkingStatusFrame() {
        // 1. Basic Window Setup
        setTitle("My Parking Status");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 2. Master Canvas with Margins
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // 3. Title Section (North)
        JLabel titleLabel = new JLabel("MY PARKING STATUS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // 4. Search Bar Section
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.add(new JLabel("Vehicle / Booking ID:"));
        searchPanel.add(new JTextField(20));
        searchPanel.add(new JButton("CHECK STATUS"));

        // 5. Active Reservation Status Card
        JPanel statusCard = new JPanel(new BorderLayout(15, 10));
        statusCard.setBorder(BorderFactory.createTitledBorder("Active Booking Details"));

        // Vehicle info listed vertically
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoPanel.add(new JLabel("Vehicle No: KL-02-AB-1234"));
        infoPanel.add(new JLabel("Location: City Parking Centre, Kollam"));
        infoPanel.add(new JLabel("Duration: 2 Hours (Slot A-05)"));
        infoPanel.add(new JLabel("Total Fee: ₹100"));

        // Action button on the right side of the card
        JButton cancelButton = new JButton("CANCEL RESERVATION");

        statusCard.add(infoPanel, BorderLayout.CENTER);
        statusCard.add(cancelButton, BorderLayout.EAST);

        // 6. Middle Section Assembly (Search + Status Card)
        JPanel centerPanel = new JPanel(new BorderLayout(10, 20));
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(statusCard, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // 7. Bottom Section (Back Button)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(new JButton("BACK"));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // 8. Mount Master Canvas to Frame and Show
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ParkingStatusFrame();
    }
}
