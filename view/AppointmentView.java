package view;

import model.*;
import helper.*;

import java.util.List;
import java.util.Scanner;

public class AppointmentView {
    private Scanner scanner = new Scanner(System.in);

    public int displayMenu() {
        System.out.println(ConsoleColors.RESET + "\n===============================================================");
        System.out.println(ConsoleColors.BLUE + "\n\tWelcome to Aurora Skin Care Appointment System");
        System.out.println(ConsoleColors.RESET + "\n===============================================================\n");
        System.out.println("\t1. Make an Appointment \n");
        System.out.println("\t2. View Appointments by Day \n");
        System.out.println("\t3. Search for an Appointment \n");
        System.out.println("\t4. Update an Appointment \n");
        System.out.println("\t5. Generate Invoice\n");
        System.out.println("\t6. Exit");
        System.out.println("\n===============================================================\n");
        System.out.print("Choose an option: " + ConsoleColors.GREEN);
        return scanner.nextInt();
    }

    public Patient collectPatientDetails() {
        scanner.nextLine();
        System.out.println(ConsoleColors.RESET + "\n===============================================================\n");
        System.out.println(ConsoleColors.BLUE + "\nEnter Patient Details\n");
        System.out.print(ConsoleColors.RESET + "Enter Patient NIC: " + ConsoleColors.GREEN);
        String nic = scanner.nextLine();
        System.out.print(ConsoleColors.RESET + "Enter Patient Name: " + ConsoleColors.GREEN);
        String name = scanner.nextLine();
        System.out.print(ConsoleColors.RESET + "Enter Patient Email: " + ConsoleColors.GREEN);
        String email = scanner.nextLine();
        System.out.print(ConsoleColors.RESET + "Enter Patient Phone: " + ConsoleColors.GREEN);
        String phone = scanner.nextLine();
        return new Patient(nic, name, email, phone);
    }

    public Dermatologist chooseDermatologist(List<Dermatologist> dermatologists) {
        System.out.println(ConsoleColors.RESET + "\n===============================================================\n");
        System.out.println(ConsoleColors.BLUE + "\nSelect an Available Dermatologist\n" + ConsoleColors.RESET);
        for (int i = 0; i < dermatologists.size(); i++) {
            System.out.println((i + 1) + ". " + dermatologists.get(i).getName());
        }
        System.out.print("\nChoose Dermatologist: " + ConsoleColors.GREEN);
        return dermatologists.get(scanner.nextInt() - 1);
    }

    public String collectDay() {
        scanner.nextLine();
        System.out.println(ConsoleColors.RESET + "\n===============================================================\n");
        System.out.println(ConsoleColors.BLUE + "\nSelect an Available Day\n" + ConsoleColors.RESET);
        System.out.println("Available Days: Monday, Wednesday, Friday, Saturday");
        System.out.print("\nChoose Day: " + ConsoleColors.GREEN);
        return scanner.nextLine();
    }

    public String collectTime(Dermatologist dermatologist, String day) {
        System.out.println(ConsoleColors.RESET + "\n===============================================================\n");
        System.out.println(ConsoleColors.BLUE + "\nSelect an Available Time Slots\n" + ConsoleColors.RESET);
        List<String> availableSlots = dermatologist.getWeeklySchedule().getOrDefault(day, List.of());
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println((i + 1) + ". " + availableSlots.get(i));
        }
        System.out.print("\nChoose Time Slot: " + ConsoleColors.GREEN);
        return availableSlots.get(scanner.nextInt() - 1);
    }

    public Treatment chooseTreatment(List<Treatment> treatments) {
        System.out.println(ConsoleColors.RESET + "\n===============================================================\n");
        System.out.println(ConsoleColors.BLUE + "\nSelect an Available Treatment\n" + ConsoleColors.RESET);
        for (int i = 0; i < treatments.size(); i++) {
            System.out.println((i + 1) + ". " + treatments.get(i).getName() + " - LKR " + treatments.get(i).getPrice());
        }
        System.out.print("\nChoose Treatment: " + ConsoleColors.GREEN);
        return treatments.get(scanner.nextInt() - 1);
    }

    public String collectSearchQuery() {
        scanner.nextLine();
        System.out.println(ConsoleColors.RESET + "\n===============================================================\n");
        System.out.print("Enter Patient Name or Appointment ID to Search: " + ConsoleColors.GREEN);
        return scanner.nextLine();
    }

    public int collectAppointmentId() {
        System.out.println(ConsoleColors.RESET + "\n===============================================================\n");
        System.out.print("Enter Appointment ID: " + ConsoleColors.GREEN);
        return scanner.nextInt();
    }

    public double collectRegistrationFee() {
        System.out.println(ConsoleColors.RESET + "\n===============================================================");
        System.out.print("\nChange Registration Fee \n(leave empty to proceed with default of 500.00): " + ConsoleColors.GREEN);

        scanner.nextLine();
        String input = scanner.nextLine().trim();
        double registrationFee;

        if (input.isEmpty()) {
            registrationFee = 500.00;
        } else {
            try {
                registrationFee = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(
                        ConsoleColors.RED + "\nInvalid input. Setting to default fee of 500.00." + ConsoleColors.RESET);
                registrationFee = 500.00;
            }
        }

        return registrationFee;
    }

}
