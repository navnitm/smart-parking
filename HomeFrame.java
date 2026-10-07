import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

    // Constructor: This runs when the window is created
    public HomeFrame() {

        // 1. Basic Window (Frame) Settings
        setTitle("Smart Parking System");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 2. Heading Section (Title + Subtitle)
        JLabel titleLabel = new JLabel("SMART PARKING SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel descLabel = new JLabel("Find and manage parking spaces easily.", SwingConstants.CENTER);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Panel to stack title and subtitle (2 rows, 1 column)
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        topPanel.add(titleLabel);
        topPanel.add(descLabel);

        // 3. Grid Section (3 Rows, 2 Columns) for Buttons and Descriptions
        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 20, 20));

        // Row 1
        centerPanel.add(new JButton("Find Parking Spot"));
        centerPanel.add(new JLabel("Search and reserve available parking spots."));

        // Row 2
        centerPanel.add(new JButton("My Parking Status"));
        centerPanel.add(new JLabel("Manage your active reservations and vehicles."));

        // Row 3
        centerPanel.add(new JButton("Parking Slot Blueprint"));
        centerPanel.add(new JLabel("View interactive map and parking lot layout."));

        // 4. Main Panel to hold topPanel and centerPanel with padding around the edges
        JPanel mainPanel = new JPanel(new BorderLayout(20, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // 5. Add Main Panel to the Frame and show it
        add(mainPanel);
        setVisible(true);
    }

    // Main Method: Program starting point
    public static void main(String[] args) {
        new HomeFrame();
    }
}