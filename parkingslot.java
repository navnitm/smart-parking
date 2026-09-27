import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * ==========================================================
 *  SMART PARKING MANAGEMENT SYSTEM  (single-file version)
 * ==========================================================
 * All classes are combined into this one file for simplicity.
 * Compile:  javac SmartParkingSystem.java
 * Run:      java SmartParkingSystem
 *
 * Class map (all in this one file):
 *   VehicleType              - enum: BIKE, CAR, TRUCK
 *   SpotType                 - enum: SMALL, MEDIUM, LARGE
 *   Vehicle                  - a vehicle (plate + type)
 *   ParkingSpot               - a single spot in the lot
 *   Ticket                    - issued on entry, closed on exit
 *   ParkingLot                 - holds all spots, finds free ones
 *   ParkingManagementSystem    - park/unpark logic, fee calculation
 *   SmartParkingSystem (Main)  - console menu (entry point)
 * ==========================================================
 */
public class SmartParkingSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Set up a lot: 3 small (bike), 5 medium (car), 2 large (truck) spots
        ParkingLot lot = new ParkingLot(3, 5, 2);
        ParkingManagementSystem system = new ParkingManagementSystem(lot);

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Enter license plate: ");
                    String plate = sc.nextLine().trim();
                    VehicleType type = askVehicleType(sc);
                    system.parkVehicle(new Vehicle(plate, type));
                }
                case "2" -> {
                    System.out.print("Enter license plate to exit: ");
                    String plate = sc.nextLine().trim();
                    system.unparkVehicle(plate);
                }
                case "3" -> system.printAllSpots();
                case "4" -> system.printActiveTickets();
                case "5" -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice, try again.");
            }
            System.out.println();
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("===== SMART PARKING MANAGEMENT SYSTEM =====");
        System.out.println("1. Park a Vehicle");
        System.out.println("2. Remove a Vehicle (Exit)");
        System.out.println("3. View All Spots");
        System.out.println("4. View Currently Parked Vehicles");
        System.out.println("5. Exit Program");
    }

    private static VehicleType askVehicleType(Scanner sc) {
        while (true) {
            System.out.print("Enter vehicle type (BIKE / CAR / TRUCK): ");
            String input = sc.nextLine().trim().toUpperCase();
            try {
                return VehicleType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type. Please enter BIKE, CAR, or TRUCK.");
            }
        }
    }
}

/* ================= VehicleType ================= */
enum VehicleType {
    BIKE,
    CAR,
    TRUCK
}

/* ================= SpotType ================= */
enum SpotType {
    SMALL,   // for bikes
    MEDIUM,  // for cars
    LARGE    // for trucks
}

/* ================= Vehicle ================= */
class Vehicle {
    private final String licensePlate;
    private final VehicleType type;

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + " [" + licensePlate + "]";
    }
}

/* ================= ParkingSpot ================= */
class ParkingSpot {
    private final int spotId;
    private final SpotType type;
    private boolean occupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotId, SpotType type) {
        this.spotId = spotId;
        this.type = type;
        this.occupied = false;
        this.parkedVehicle = null;
    }

    public int getSpotId() {
        return spotId;
    }

    public SpotType getType() {
        return type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    /** A spot can only hold a vehicle that matches its size category. */
    public boolean canFit(VehicleType vehicleType) {
        if (occupied) return false;
        return switch (vehicleType) {
            case BIKE -> type == SpotType.SMALL;
            case CAR -> type == SpotType.MEDIUM;
            case TRUCK -> type == SpotType.LARGE;
        };
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.occupied = true;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.occupied = false;
    }

    @Override
    public String toString() {
        String status = occupied ? "OCCUPIED by " + parkedVehicle : "FREE";
        return "Spot #" + spotId + " (" + type + ") - " + status;
    }
}

/* ================= Ticket ================= */
class Ticket {
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final int ticketId;
    private final Vehicle vehicle;
    private final int spotId;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double fee;

    public Ticket(int ticketId, Vehicle vehicle, int spotId, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.spotId = spotId;
        this.entryTime = entryTime;
        this.exitTime = null;
        this.fee = 0.0;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSpotId() {
        return spotId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void closeTicket(LocalDateTime exitTime, double fee) {
        this.exitTime = exitTime;
        this.fee = fee;
    }

    @Override
    public String toString() {
        String exitStr = (exitTime == null) ? "Still parked" : exitTime.format(FORMAT);
        return "Ticket #" + ticketId + " | " + vehicle + " | Spot #" + spotId
                + " | Entry: " + entryTime.format(FORMAT)
                + " | Exit: " + exitStr
                + (exitTime != null ? String.format(" | Fee: Rs.%.2f", fee) : "");
    }
}

/* ================= ParkingLot ================= */
class ParkingLot {
    private final List<ParkingSpot> spots;

    // Rate per hour (or part thereof), by spot type
    private static final double RATE_SMALL = 10.0;   // bikes
    private static final double RATE_MEDIUM = 20.0;  // cars
    private static final double RATE_LARGE = 50.0;   // trucks

    public ParkingLot(int numSmall, int numMedium, int numLarge) {
        spots = new ArrayList<>();
        int id = 1;
        for (int i = 0; i < numSmall; i++) spots.add(new ParkingSpot(id++, SpotType.SMALL));
        for (int i = 0; i < numMedium; i++) spots.add(new ParkingSpot(id++, SpotType.MEDIUM));
        for (int i = 0; i < numLarge; i++) spots.add(new ParkingSpot(id++, SpotType.LARGE));
    }

    /** Finds the first free spot that fits the given vehicle type, or null if full. */
    public ParkingSpot findAvailableSpot(VehicleType type) {
        for (ParkingSpot spot : spots) {
            if (spot.canFit(type)) {
                return spot;
            }
        }
        return null;
    }

    public ParkingSpot getSpotById(int spotId) {
        for (ParkingSpot spot : spots) {
            if (spot.getSpotId() == spotId) return spot;
        }
        return null;
    }

    public double getRateForType(SpotType type) {
        return switch (type) {
            case SMALL -> RATE_SMALL;
            case MEDIUM -> RATE_MEDIUM;
            case LARGE -> RATE_LARGE;
        };
    }

    public List<ParkingSpot> getAllSpots() {
        return spots;
    }

    public long countAvailable() {
        return spots.stream().filter(s -> !s.isOccupied()).count();
    }

    public long countOccupied() {
        return spots.stream().filter(ParkingSpot::isOccupied).count();
    }
}

/* ================= ParkingManagementSystem ================= */
class ParkingManagementSystem {
    private final ParkingLot lot;
    private final Map<String, Ticket> activeTicketsByPlate; // licensePlate -> open ticket
    private int nextTicketId;

    public ParkingManagementSystem(ParkingLot lot) {
        this.lot = lot;
        this.activeTicketsByPlate = new HashMap<>();
        this.nextTicketId = 1;
    }

    /** Parks a vehicle. Returns the issued Ticket, or null if full / already parked. */
    public Ticket parkVehicle(Vehicle vehicle) {
        if (activeTicketsByPlate.containsKey(vehicle.getLicensePlate())) {
            System.out.println("Error: Vehicle " + vehicle.getLicensePlate() + " is already parked.");
            return null;
        }

        ParkingSpot spot = lot.findAvailableSpot(vehicle.getType());
        if (spot == null) {
            System.out.println("Sorry, no available spot for a " + vehicle.getType() + " right now.");
            return null;
        }

        spot.parkVehicle(vehicle);
        Ticket ticket = new Ticket(nextTicketId++, vehicle, spot.getSpotId(), LocalDateTime.now());
        activeTicketsByPlate.put(vehicle.getLicensePlate(), ticket);

        System.out.println("Parked successfully! " + ticket);
        return ticket;
    }

    /** Removes a vehicle by plate, frees its spot, calculates and prints the fee. */
    public Ticket unparkVehicle(String licensePlate) {
        Ticket ticket = activeTicketsByPlate.get(licensePlate);
        if (ticket == null) {
            System.out.println("Error: No active ticket found for plate " + licensePlate);
            return null;
        }

        ParkingSpot spot = lot.getSpotById(ticket.getSpotId());
        LocalDateTime exitTime = LocalDateTime.now();
        double fee = calculateFee(ticket.getEntryTime(), exitTime, spot.getType());

        ticket.closeTicket(exitTime, fee);
        spot.removeVehicle();
        activeTicketsByPlate.remove(licensePlate);

        System.out.println("Vehicle exited. " + ticket);
        return ticket;
    }

    /** Fee = ceil(hours parked) * hourly rate for that spot type. Minimum charge: 1 hour. */
    private double calculateFee(LocalDateTime entry, LocalDateTime exit, SpotType type) {
        long minutes = Duration.between(entry, exit).toMinutes();
        long hours = (long) Math.ceil(minutes / 60.0);
        if (hours < 1) hours = 1;
        return hours * lot.getRateForType(type);
    }

    public void printAllSpots() {
        System.out.println("---- Parking Lot Status ----");
        for (ParkingSpot spot : lot.getAllSpots()) {
            System.out.println(spot);
        }
        System.out.println("Available: " + lot.countAvailable() + " | Occupied: " + lot.countOccupied());
    }

    public void printActiveTickets() {
        if (activeTicketsByPlate.isEmpty()) {
            System.out.println("No vehicles currently parked.");
            return;
        }
        System.out.println("---- Currently Parked Vehicles ----");
        for (Ticket t : activeTicketsByPlate.values()) {
            System.out.println(t);
        }
    }
}