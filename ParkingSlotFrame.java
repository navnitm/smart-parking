import javax.swing.*;
import java.awt.*;

public class ParkingSlotFrame extends JFrame {

    public ParkingSlotFrame() {

        // Frame settings
        setTitle("Parking Slot Selection");
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
                "SELECT PARKING SLOT",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        // Parking information
        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 20, 10));

        JLabel locationLabel =
                new JLabel("Parking Location: City Parking");

        JLabel dateLabel =
                new JLabel("Date: 28/09/2026");

        JLabel timeLabel =
                new JLabel("Entry Time: 10:00 AM");

        JLabel durationLabel =
                new JLabel("Duration: 2 Hours");

        Font infoFont = new Font("Arial", Font.PLAIN, 14);

        locationLabel.setFont(infoFont);
        dateLabel.setFont(infoFont);
        timeLabel.setFont(infoFont);
        durationLabel.setFont(infoFont);

        infoPanel.add(locationLabel);
        infoPanel.add(dateLabel);
        infoPanel.add(timeLabel);
        infoPanel.add(durationLabel);

        // Top section
        JPanel topPanel = new JPanel(new BorderLayout(10, 15));

        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(infoPanel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Selected slot label
        JLabel selectedSlotLabel = new JLabel(
                "Selected Slot: None",
                SwingConstants.CENTER
        );

        selectedSlotLabel.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        // Parking slot panel
        JPanel slotPanel = new JPanel(
                new GridLayout(4, 5, 15, 15)
        );

        // Parking slot names
        String[] slots = {
                "A1", "A2", "A3", "A4", "A5",
                "B1", "B2", "B3", "B4", "B5",
                "C1", "C2", "C3", "C4", "C5",
                "D1", "D2", "D3", "D4", "D5"
        };

        // Store currently selected button
        final JButton[] selectedButton = {null};

        // Create slot buttons
        for (String slot : slots) {

            JButton slotButton = new JButton(slot);

            slotButton.setFont(
                    new Font("Arial", Font.BOLD, 16)
            );

            // Occupied slot
    if (slot.equals("A3") ||
       slot.equals("B2") ||
       slot.equals("C4") ||
       slot.equals("D1")) {

    // Occupied slot
    slotButton.setEnabled(false);
    slotButton.setBackground(Color.LIGHT_GRAY);
    slotButton.setForeground(Color.DARK_GRAY);
    slotButton.setToolTipText("Occupied");

} else {

    // Available slot
    slotButton.addActionListener(e -> {

        if (selectedButton[0] != null) {

            selectedButton[0].setBackground(
                    UIManager.getColor("Button.background")
            );

            selectedButton[0].setForeground(
                    UIManager.getColor("Button.foreground")
            );
        }

        selectedButton[0] = slotButton;

        slotButton.setBackground(Color.BLUE);
        slotButton.setForeground(Color.WHITE);

        selectedSlotLabel.setText(
                "Selected Slot: " + slot
        );
    });
}

                // Available slot click action
                slotButton.addActionListener(e -> {

                    // Reset previous selection
                    if (selectedButton[0] != null) {

                        selectedButton[0].setBackground(
                                UIManager.getColor(
                                        "Button.background"
                                )
                        );

                        selectedButton[0].setForeground(
                                UIManager.getColor(
                                        "Button.foreground"
                                )
                        );
                    }

                    // Select new slot
                    selectedButton[0] = slotButton;

                    slotButton.setBackground(Color.BLUE);
                    slotButton.setForeground(Color.WHITE);

                    selectedSlotLabel.setText(
                            "Selected Slot: " + slot
                    );
                });
            }

            slotPanel.add(slotButton);
        }

        // Center section
        JPanel centerPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        centerPanel.add(
                selectedSlotLabel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                slotPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // Bottom panel
        JPanel bottomPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        20,
                        10
                )
        );

        JLabel legendLabel = new JLabel(
                "Available = Selectable    |    Occupied = Not Available"
        );

        legendLabel.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        JButton backButton = new JButton("BACK");
        JButton continueButton = new JButton("CONTINUE");

        bottomPanel.add(legendLabel);
        bottomPanel.add(backButton);
        bottomPanel.add(continueButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // Add main panel to frame
        add(mainPanel);

        // Show frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new ParkingSlotFrame();
    }
}
