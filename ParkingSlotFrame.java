import javax.swing.*;
import java.awt.*;

public class ParkingSlotFrame extends JFrame {
    private JButton selectedButton = null;

    public ParkingSlotFrame() {
        setTitle("Parking Slot Selection");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // --- TOP SECTION ---
        JPanel topPanel = new JPanel(new BorderLayout(10, 15));
        JLabel titleLabel = new JLabel("SELECT PARKING SLOT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        // 2x2 Info Grid
        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 20, 10));
        Font infoFont = new Font("Arial", Font.PLAIN, 14);
        String[] info = {"Parking Location: City Parking", "Date: 28/09/2026", "Entry Time: 10:00 AM", "Duration: 2 Hours"};
        for (String text : info) {
            JLabel lbl = new JLabel(text);
            lbl.setFont(infoFont);
            infoPanel.add(lbl);
        }
        topPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- CENTER SECTION ---
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        JLabel selectedSlotLabel = new JLabel("Selected Slot: None", SwingConstants.CENTER);
        selectedSlotLabel.setFont(new Font("Arial", Font.BOLD, 15));
        centerPanel.add(selectedSlotLabel, BorderLayout.NORTH);

        // 4x5 Slots Grid (A1 to D5)
        JPanel slotPanel = new JPanel(new GridLayout(4, 5, 15, 15));
        for (char row = 'A'; row <= 'D'; row++) {
            for (int col = 1; col <= 5; col++) {
                String slot = "" + row + col;
                JButton btn = new JButton(slot);
                btn.setFont(new Font("Arial", Font.BOLD, 16));

                if ("A3 B2 C4 D1".contains(slot)) {
                    btn.setEnabled(false);
                    btn.setBackground(Color.LIGHT_GRAY);
                    btn.setForeground(Color.DARK_GRAY);
                    btn.setToolTipText("Occupied");
                } else {
                    btn.addActionListener(e -> {
                        if (selectedButton != null) {
                            selectedButton.setBackground(null);
                            selectedButton.setForeground(null);
                        }
                        selectedButton = btn;
                        btn.setBackground(Color.BLUE);
                        btn.setForeground(Color.WHITE);
                        selectedSlotLabel.setText("Selected Slot: " + slot);
                    });
                }
                slotPanel.add(btn);
            }
        }
        centerPanel.add(slotPanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // --- BOTTOM SECTION ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.add(new JLabel("Available = Selectable    |    Occupied = Not Available"));
        bottomPanel.add(new JButton("BACK"));
        bottomPanel.add(new JButton("CONTINUE"));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ParkingSlotFrame();
    }
}
