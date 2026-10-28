
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ParkingStatusFrame extends JFrame {

    JTextField searchField;
    JLabel vehicleLabel, slotLabel, hoursLabel, feeLabel, idLabel;

    public ParkingStatusFrame() {

        setTitle("My Parking Status");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30));

        // Title
        JLabel title = new JLabel(
                "MY PARKING STATUS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        mainPanel.add(title, BorderLayout.NORTH);

        // Search section
        JPanel searchPanel = new JPanel(new FlowLayout());

        searchField = new JTextField(18);
        JButton checkButton = new JButton("CHECK STATUS");

        searchPanel.add(new JLabel("Vehicle No. / Booking ID:"));
        searchPanel.add(searchField);
        searchPanel.add(checkButton);

        // Booking details
        JPanel detailsPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        detailsPanel.setBorder(
                BorderFactory.createTitledBorder("Booking Details"));

        idLabel = new JLabel("Booking ID: -");
        vehicleLabel = new JLabel("Vehicle Number: -");
        slotLabel = new JLabel("Parking Slot: -");
        hoursLabel = new JLabel("Hours: -");
        feeLabel = new JLabel("Total Fee: -");

        detailsPanel.add(idLabel);
        detailsPanel.add(vehicleLabel);
        detailsPanel.add(slotLabel);
        detailsPanel.add(hoursLabel);
        detailsPanel.add(feeLabel);

        // Center section
        JPanel centerPanel = new JPanel(new BorderLayout(10, 15));
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(detailsPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Back button
        JButton backButton = new JButton("BACK");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        checkButton.addActionListener(e -> checkBooking());
        backButton.addActionListener(e -> dispose());

        add(mainPanel);
        setVisible(true);
    }

    // Search booking in MySQL
    private void checkBooking() {

        String search = searchField.getText().trim();

        if (search.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Enter a vehicle number or booking ID.");
            return;
        }

        String sql = "SELECT * FROM bookings "
                + "WHERE vehicle_number = ?";

        try (Connection con = DBConnection.getConnection()) {

            // Search by vehicle number
            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, search);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        showBooking(rs);
                        return;
                    }
                }
            }

            // If not found, search by booking ID
            try {
                int id = Integer.parseInt(search);

                sql = "SELECT * FROM bookings WHERE id = ?";

                try (PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setInt(1, id);

                    try (ResultSet rs = ps.executeQuery()) {

                        if (rs.next()) {
                            showBooking(rs);
                            return;
                        }
                    }
                }

            } catch (NumberFormatException ex) {
                // Search text was not a numeric booking ID
            }

            clearDetails();

            JOptionPane.showMessageDialog(this,
                    "No booking found.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage());
        }
    }

    // Display booking details
    private void showBooking(ResultSet rs) throws Exception {

        idLabel.setText("Booking ID: " + rs.getInt("id"));
        vehicleLabel.setText(
                "Vehicle Number: " + rs.getString("vehicle_number"));
        slotLabel.setText(
                "Parking Slot: " + rs.getString("slot_number"));
        hoursLabel.setText("Hours: " + rs.getInt("hours"));
        feeLabel.setText("Total Fee: Rs. " + rs.getInt("total_fee"));
    }

    // Clear displayed details
    private void clearDetails() {

        idLabel.setText("Booking ID: -");
        vehicleLabel.setText("Vehicle Number: -");
        slotLabel.setText("Parking Slot: -");
        hoursLabel.setText("Hours: -");
        feeLabel.setText("Total Fee: -");
    }

    public static void main(String[] args) {
        new ParkingStatusFrame();
    }
}
