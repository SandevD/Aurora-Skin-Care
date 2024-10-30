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

        Appointment appointment = new Appointment(patient, dermatologist, day, time);
        appointment.setTreatment(treatment);
        appointments.add(appointment);
        System.out.println("\nAppointment booked successfully.");
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
                System.out.println("Appointment Found:");
                System.out.println(
                        "ID: " + appointment.getAppointmentId() + ", Patient: " + appointment.getPatient().getName() +
                                ", Day: " + appointment.getDay() + ", Time: " + appointment.getTime() + ", Treatment: "
                                + appointment.getTreatment().getName());
            }
        }
    }

    private void updateAppointment() {
        int appointmentId = view.collectAppointmentId();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId) {
                String newDay = view.collectDay();
                String newTime = view.collectTime(appointment.getDermatologist(), newDay);
                appointment = new Appointment(appointment.getPatient(), appointment.getDermatologist(), newDay,
                        newTime);
                System.out.println("Appointment updated successfully.");
                return;
            }
        }
        System.out.println("Appointment not found.");
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
        System.out.println("Appointment not found.");
    }
}
