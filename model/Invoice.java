package model;

public class Invoice {
    private Appointment appointment;

    public Invoice(Appointment appointment) {
        this.appointment = appointment;
    }

    public void generateInvoice() {
        double subtotal = appointment.getTreatment().getPrice() + appointment.getRegistrationFee();
        double tax = subtotal * 0.025;
        double total = Math.round((subtotal + tax) * 100.0) / 100.0;

        System.out.println("\n+--------------------- Invoice ----------------------+");
        System.out.format("| %-17s : %-30s |%n", "Appointment ID", appointment.getAppointmentId());
        System.out.format("| %-17s : %-30s |%n", "Day", appointment.getDay());
        System.out.format("| %-17s : %-30s |%n", "Time", appointment.getTime());
        System.out.format("| %-17s : %-30s |%n", "Patient", appointment.getPatient().getName());
        System.out.format("| %-17s : %-30s |%n", "Treatment", appointment.getTreatment().getName());
        System.out.format("| %-17s : %-30s |%n", "Treatment Price", appointment.getTreatment().getPrice());
        System.out.format("| %-17s : %-30s |%n", "Registration Fee", appointment.getRegistrationFee());
        System.out.println("+----------------------------------------------------+");
        System.out.format("| %-17s : %-30.2f |%n", "Subtotal", subtotal);
        System.out.format("| %-17s : %-30s |%n", "Tax", "2.5% (" + Math.round(tax * 100.0) / 100.0 + ")");
        System.out.println("+----------------------------------------------------+");
        System.out.format("| %-17s : %-30.2f |%n", "Total", total);
        System.out.println("+----------------------------------------------------+\n");
    }
}
