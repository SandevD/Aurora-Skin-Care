package controller;

import model.*;
import view.AppointmentView;

import java.util.*;

import helper.ConsoleColors;

public class AppointmentController {
    private AppointmentView view = new AppointmentView();
    private List<Appointment> appointments = new ArrayList<>();
    
    private List<Dermatologist> dermatologists = Arrays.asList(
            new Dermatologist(1, "Dr. Smith"),
            new Dermatologist(2, "Dr. Johnson"));

    private List<Treatment> treatments = Arrays.asList(
            new Treatment("Acne Treatment", 2750.00),
            new Treatment("Skin Whitening", 7650.00),
            new Treatment("Mole Removal", 3850.00),
            new Treatment("Laser Treatment", 12500.00));

    public void run() {
        while (true) {
            int choice = view.displayMenu();
            switch (choice) {
                case 1:
                    makeAppointment();
                    break;
                case 2:
                    viewAppointmentsByDay();
                    break;
                case 3:
                    searchAppointment();
                    break;
                case 4:
                    updateAppointment();
                    break;
                case 5:
                    generateInvoice();
                    break;
                case 6:
                    System.out.println("\n===============================================================\n");
                    System.out.println("Thank You...");
                    System.out.println("\n===============================================================\n");
                    return;
                default:
                    System.out.println("\n===============================================================\n");
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void makeAppointment() {
        Patient patient = view.collectPatientDetails();
        Dermatologist dermatologist = view.chooseDermatologist(dermatologists);
        String day = view.collectDay();
        String time = view.collectTime(dermatologist, day);
        Treatment treatment = view.chooseTreatment(treatments);
        double registrationFee = view.collectRegistrationFee();
    
        if (isDuplicateAppointment(dermatologist, day, time)) {
            System.out.println(ConsoleColors.RED + "\n Error: Duplicate booking. This dermatologist is already booked at the specified time." + ConsoleColors.RESET);
            return;
        }

        Appointment appointment = new Appointment(patient, dermatologist, day, time, registrationFee);
        appointment.setTreatment(treatment);
        appointments.add(appointment);
        System.out.println(ConsoleColors.GREEN + "\nAppointment booked successfully." + ConsoleColors.RESET);
    }

    private void viewAppointmentsByDay() {
        String day = view.collectDay();
        List<Appointment> matchingAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            if (appointment.getDay().equalsIgnoreCase(day)) {
                matchingAppointments.add(appointment);
            }
        }

        if (matchingAppointments.isEmpty()) {
            System.out.println(ConsoleColors.RED + "\nNo appointments found for " + day + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.GREEN + "\nAll Appointments for " + day + ConsoleColors.RESET + "\n");
            for (Appointment appointment : matchingAppointments) {
                System.out.println("ID: " + appointment.getAppointmentId() + ", Patient: "
                        + appointment.getPatient().getName() + ", Time: " + appointment.getTime());
            }
        }
    }

    private void searchAppointment() {
        String query = view.collectSearchQuery();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getName().equalsIgnoreCase(query)
                    || String.valueOf(appointment.getAppointmentId()).equals(query)) {
                System.out.println("\nAppointment Found:");
                System.out.println(
                        "ID: " + appointment.getAppointmentId() + ", Patient: " + appointment.getPatient().getName() +
                                ", Day: " + appointment.getDay() + ", Time: " + appointment.getTime() + ", Treatment: "
                                + appointment.getTreatment().getName());
            }
        }
        if (appointments.isEmpty()) {
            System.out.println(ConsoleColors.RED + "\nNo appointment found!" + ConsoleColors.RESET);
        }
    }

    private void updateAppointment() {
        int appointmentId = view.collectAppointmentId();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId) {
                int choice = view.displayUpdateOptions(); // Prompt user for choice
                switch (choice) {
                    case 1:
                        updateDermatologistDayTime(appointment);
                        break;
                    case 2:
                        updateTreatment(appointment);
                        break;
                    case 3:
                        updatePatientDetails(appointment);
                        break;
                    case 4:
                        updateDermatologistDayTime(appointment);
                        updateTreatment(appointment);
                        updatePatientDetails(appointment);
                        break;
                    default:
                        System.out.println(ConsoleColors.RED + "\nInvalid option" + ConsoleColors.RESET);
                        return;
                }
                System.out.println("\nAppointment updated successfully.");
                return;
            }
        }
        System.out.println(ConsoleColors.RED + "\nAppointment not found" + ConsoleColors.RESET);
    }

    private void generateInvoice() {
        int appointmentId = view.collectAppointmentId();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId) {
                Invoice invoice = new Invoice(appointment);
                invoice.generateInvoice();
                return;
            }
        }
        System.out.println(ConsoleColors.RED + "\nAppointment not found" + ConsoleColors.RESET);
    }

    private void updateDermatologistDayTime(Appointment appointment) {
        Dermatologist newDermatologist = view.chooseDermatologist(dermatologists);
        String newDay = view.collectDay();
        String newTime = view.collectTime(newDermatologist, newDay);
    
        if (isDuplicateAppointment(newDermatologist, newDay, newTime)) {
            System.out.println(ConsoleColors.RED + "Error: This dermatologist is already booked at the specified time." + ConsoleColors.RESET);
            return;
        }
    
        appointment.setDermatologist(newDermatologist);
        appointment.setDay(newDay);
        appointment.setTime(newTime);
        System.out.println(ConsoleColors.GREEN + "Appointment updated successfully." + ConsoleColors.RESET);
    }

    private void updateTreatment(Appointment appointment) {
        Treatment newTreatment = view.chooseTreatment(treatments);
        appointment.setTreatment(newTreatment);
    }
        
    private void updatePatientDetails(Appointment appointment) {
        Patient newPatient = view.collectPatientDetails();
        appointment.setPatient(newPatient);
    }
    
    private boolean isDuplicateAppointment(Dermatologist dermatologist, String day, String time) {
        for (Appointment appointment : appointments) {
            if (appointment.getDermatologist().equals(dermatologist) &&
                appointment.getDay().equalsIgnoreCase(day) &&
                appointment.getTime().equals(time)) {
                return true;
            }
        }
        return false;
    }
}
