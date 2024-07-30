import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        System.out.println("----------Flight Reservation System----------");
        System.out.println("---------------------------------------------");

        Scanner scanDetails = new Scanner(System.in);
        System.out.print("Do you want to view Address or add a new one (Y/N): \n");
        String view = scanDetails.nextLine();
        view = view.toLowerCase();
        if (view.equals("y") || view.equals("yes")) {
            viewAddress();
        }

        System.out.println("Enter the correct details below!");

        System.out.println("\nEnter name: ");
        String name = scanDetails.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanDetails.nextLine();
        System.out.print("Email: ");
        String email = scanDetails.nextLine();

        try {
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            FileWriter myPassword = new FileWriter("Address.txt", true);
            myPassword.write(name + "\t\t\t" + phoneNumber + "\t\t\t\t" + email + "\t\t\t\t" + currentDate + "\t" + currentTime + System.lineSeparator());
            myPassword.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void viewAddress() {
        String filePath = "Address.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}