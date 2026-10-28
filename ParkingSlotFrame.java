
import javax.swing.*;
import java.awt.*;

public class ParkingSlotFrame extends JFrame {

    JButton selectedButton;

    public ParkingSlotFrame() {

        setTitle("Parking Slot Selection");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30));

        // Title
        JLabel title = new JLabel(
                "SELECT PARKING SLOT", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        mainPanel.add(title, BorderLayout.NORTH);

        // Slot selection label
        JLabel selectedLabel = new JLabel(
                "Selected Slot: None", SwingConstants.CENTER);

        // Parking slots grid
        JPanel slotPanel = new JPanel(new GridLayout(4, 5, 12, 12));

        for (char row = 'A'; row <= 'D'; row++) {
            for (int col = 1; col <= 5; col++) {

                String slot = "" + row + col;
                JButton button = new JButton(slot);

                // Mark occupied slots
                if (slot.equals("A3") || slot.equals("B2")
                        || slot.equals("C4") || slot.equals("D1")) {

                    button.setEnabled(false);
                    button.setToolTipText("Occupied");

                } else {

                    button.addActionListener(e -> {

                        // Reset previous selection
                        if (selectedButton != null) {
                            selectedButton.setBackground(null);
                            selectedButton.setForeground(null);
                        }

                        // Select new slot
                        selectedButton = button;
                        button.setBackground(Color.BLUE);
                        button.setForeground(Color.WHITE);

                        selectedLabel.setText(
                                "Selected Slot: " + slot);
                    });
                }

                slotPanel.add(button);
            }
        }

        // Slot information
        JPanel centerPanel = new JPanel(new BorderLayout(10, 15));
        centerPanel.add(selectedLabel, BorderLayout.NORTH);
        centerPanel.add(slotPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Bottom buttons
        JButton backButton = new JButton("BACK");
        JButton continueButton = new JButton("CONTINUE");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel(
                "Grey = Occupied | Blue = Selected"));
        bottomPanel.add(backButton);
        bottomPanel.add(continueButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Back button
        backButton.addActionListener(e -> dispose());

        // Continue button
        continueButton.addActionListener(e -> {

            if (selectedButton == null) {

                JOptionPane.showMessageDialog(this,
                        "Please select a parking slot.");

            } else {

                new BookingConfirmationFrame(
                        selectedButton.getText());
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ParkingSlotFrame();
    }
}
