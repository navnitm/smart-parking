
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookingConfirmationFrame extends JFrame {

    JTextField vehicleNumber, hours;
    JLabel fee;
    String selectedSlot;

    public BookingConfirmationFrame(String slot) {

        selectedSlot = slot;

        setTitle("Booking Confirmation");
        setSize(350, 300);
        setLayout(new GridLayout(6, 2, 5, 5));

        // Display selected parking slot
        add(new JLabel("Selected Slot:"));
        add(new JLabel(selectedSlot));

        // Vehicle number input
        add(new JLabel("Vehicle Number:"));
        vehicleNumber = new JTextField();
        add(vehicleNumber);

        // Parking hours input
        add(new JLabel("Hours:"));
        hours = new JTextField();
        add(hours);

        // Display total fee
        add(new JLabel("Total Fee:"));
        fee = new JLabel("Rs. 0");
        add(fee);

        // Confirm booking button
        JButton confirm = new JButton("Confirm Booking");
        add(new JLabel(""));
        add(confirm);

        confirm.addActionListener(e -> {

            String vehicle = vehicleNumber.getText().trim();

            try {
                int h = Integer.parseInt(hours.getText().trim());

                if (vehicle.isEmpty() || h <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Enter vehicle number and valid hours.");
                    return;
                }

                // Calculate fee at Rs. 50 per hour
                int total = h * 50;

                // SQL query to save booking
                String sql = "INSERT INTO bookings "
                        + "(vehicle_number, slot_number, hours, total_fee) "
                        + "VALUES (?, ?, ?, ?)";

                // Connect to MySQL and save booking
                try (Connection con = DBConnection.getConnection();
                     PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setString(1, vehicle);
                    ps.setString(2, selectedSlot);
                    ps.setInt(3, h);
                    ps.setInt(4, total);

                    ps.executeUpdate();
                }

                fee.setText("Rs. " + total);

                JOptionPane.showMessageDialog(this,
                        "Booking Saved Successfully!\n"
                        + "Vehicle: " + vehicle + "\n"
                        + "Slot: " + selectedSlot + "\n"
                        + "Hours: " + h + "\n"
                        + "Total Fee: Rs. " + total);

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this,
                        "Hours must be a number.");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        "Booking Failed: " + ex.getMessage());
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BookingConfirmationFrame("A1");
    }
}
