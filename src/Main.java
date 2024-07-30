import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Flight {
    private String flightNumber;
    private String destination;
    private int capacity;
    private int seatsBooked;

    public Flight(String flightNumber, String destination, int capacity) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.capacity = capacity;
        this.seatsBooked = 0;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public boolean bookSeat() {
        if (seatsBooked < capacity) {
            seatsBooked++;
            return true;
        } else {
            return false;
        }
    }
}

class Passenger {
    private String name;
    private String passportNumber;

    public Passenger(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
}

class Reservation {
    private Flight flight;
    private Passenger passenger;

    public Reservation(Flight flight, Passenger passenger) {
        this.flight = flight;
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "flight=" + flight.getFlightNumber() +
                ", passenger=" + passenger.getName() +
                '}';
    }
}

public class FlightReservationSystem {
    private List<Flight> flights;
    private List<Reservation> reservations;

    public FlightReservationSystem() {
        flights = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addFlight(String flightNumber, String destination, int capacity) {
        flights.add(new Flight(flightNumber, destination, capacity));
    }

    public Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    public boolean makeReservation(String flightNumber, String passengerName, String passportNumber) {
        Flight flight = findFlight(flightNumber);
        if (flight != null && flight.bookSeat()) {
            Passenger passenger = new Passenger(passengerName, passportNumber);
            reservations.add(new Reservation(flight, passenger));
            return true;
        }
        return false;
    }

    public void showReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public static void main(String[] args) {
        FlightReservationSystem system = new FlightReservationSystem();
        system.addFlight("FL123", "New York", 100);
        system.addFlight("FL124", "Los Angeles", 150);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Make a reservation");
            System.out.println("2. Show reservations");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (option == 1) {
                System.out.print("Enter flight number: ");
                String flightNumber = scanner.nextLine();
                System.out.print("Enter passenger name: ");
                String passengerName = scanner.nextLine();
                System.out.print("Enter passport number: ");
                String passportNumber = scanner.nextLine();
                boolean success = system.makeReservation(flightNumber, passengerName, passportNumber);
                if (success) {
                    System.out.println("Reservation made successfully!");
                } else {
                    System.out.println("Failed to make reservation. Flight might be full or does not exist.");
                }
            } else if (option == 2) {
                system.showReservations();
            } else if (option == 3) {
                break;
            } else {
                System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close();
    }
}
