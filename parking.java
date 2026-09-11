import javax.swing.*;

class Parking {
    int total = 5;
    int occupied = 0;

    void entry() {
        if (occupied < total) {
            occupied++;
            JOptionPane.showMessageDialog(null,
                    "Vehicle parked!\nSlot: " + occupied);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Parking Full!");
        }
    }

    void exit() {
        if (occupied > 0) {
            occupied--;
            JOptionPane.showMessageDialog(null,
                    "Vehicle exited!\nSlot released.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "No vehicles in parking.");
        }
    }

    void status() {
        JOptionPane.showMessageDialog(null,
                "Total Slots: " + total +
                "\nOccupied: " + occupied +
                "\nAvailable: " + (total - occupied));
    }
}

public class SmartParking {

    public static void main(String[] args) {

        Parking parking = new Parking();

        while (true) {

            String choice = JOptionPane.showInputDialog(
                    "SMART PARKING SYSTEM\n\n" +
                    "1. Vehicle Entry\n" +
                    "2. Vehicle Exit\n" +
                    "3. Check Status\n" +
                    "4. Exit Program\n\n" +
                    "Enter choice:"
            );

            if (choice == null || choice.equals("4")) {
                break;
            }

            switch (choice) {

                case "1":
                    parking.entry();
                    break;

                case "2":
                    parking.exit();
                    break;

                case "3":
                    parking.status();
                    break;

                default:
                    JOptionPane.showMessageDialog(null,
                            "Invalid choice!");
            }
        }
    }
}