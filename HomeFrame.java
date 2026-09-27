import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

    // Constructor
    public HomeFrame() {

        // Frame settings
        setTitle("Smart Parking System");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        // Title
        JLabel titleLabel = new JLabel(
                "SMART PARKING SYSTEM",
                SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        // Description
        JLabel descriptionLabel = new JLabel(
                "Find and manage parking spaces easily.",
                SwingConstants.CENTER
        );
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Panel for title and description
        JPanel headingPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        headingPanel.add(titleLabel);
        headingPanel.add(descriptionLabel);

        // Buttons
        JButton findParkingButton = new JButton("Find Parking Spot");
        JButton parkingStatusButton = new JButton("My Parking Status");
        JButton blueprintButton = new JButton("Parking Slot Blueprint");

        // Descriptions for each button
        JTextArea findParkingDesc = createDescriptionArea("Search and reserve available parking spots in the location.");
        JTextArea parkingStatusDesc = createDescriptionArea("Manage your active reservations and vehicle information.");
        JTextArea blueprintDesc = createDescriptionArea("View the interactive map and layout of any parking lot.");

        // Grid panel: 3 rows, 2 columns
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 20, 15));

        buttonPanel.add(findParkingButton);
        buttonPanel.add(findParkingDesc);

        buttonPanel.add(parkingStatusButton);
        buttonPanel.add(parkingStatusDesc);

        buttonPanel.add(blueprintButton);
        buttonPanel.add(blueprintDesc);

        // Add components to main panel
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);

        // Make frame visible
        setVisible(true);
    }

    // Helper method to configure text wrapping for explanations
    private JTextArea createDescriptionArea(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));
        return textArea;
    }

    public static void main(String[] args) {
        new HomeFrame();
    }
}