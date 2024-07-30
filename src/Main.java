import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static int seatNumber;
    public static void main (String[] args) {

        System.out.println("Select an option");
        System.out.println("1. Make a reservation");
        System.out.println("2. Show reservations");
        System.out.println("3. Exit");

        int option = scan.nextInt();

        if (option == 1) {
            makeReservation();
        } if (option == 2) {
            showReservation();
        } if (option == 3) {
            exit();
        }
    }

    public static void makeReservation() {

        System.out.println("Please enter your details below.");

        System.out.println(" ");

        System.out.print("Enter name: ");
        String name = scan.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scan.nextLine();
        System.out.print("Enter email: ");
        String email = scan.nextLine();
        System.out.println("Enter seat number: ");
        seatNumber = scan.nextInt();

        try {
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            FileWriter myPassword = new FileWriter("Address.txt", true);
            myPassword.write(name + "\t\t\t" + phoneNumber + "\t\t\t\t" + email + "\t\t\t\t" + seatNumber + "\t\t\t" + currentDate + "\t" + currentTime + System.lineSeparator());
            myPassword.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        confirmReservation(seatNumber);
    }

    public static void showReservation() {
        System.out.println("These are the available seats");
        String filePath = "..\\Flight Reservation System\\src\\Reservation.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exit() {
        System.out.println("..exiting.");
        System.exit(0);
    }

    public static void confirmReservation(int seatNumber) {
        String fileName = "..\\Flight Reservation System\\src\\Reservation.txt";
        String no = String.valueOf(seatNumber);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.contains(no)) {
                    System.out.println("Seat taken");
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Seat is available");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}