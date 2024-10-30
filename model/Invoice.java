package model;

public class Invoice {
    private Appointment appointment;

    public Invoice(Appointment appointment) {
        this.appointment = appointment;
    }

    public void generateInvoice() {
        double subtotal = appointment.getTreatment().getPrice();
        double tax = subtotal * 0.025;
        double total = Math.round((subtotal + tax) * 100.0) / 100.0;

        System.out.println("\n--- Invoice ---");
        System.out.println("Patient: " + appointment.getPatient().getName());
        System.out.println("Appointment ID: " + appointment.getAppointmentId());
        System.out.println("Treatment: " + appointment.getTreatment().getName());
        System.out.println("Subtotal: " + subtotal);
        System.out.println("Tax: 2.5% (" + Math.round(tax * 100.0) / 100.0 + ")");
        System.out.println("Total: " + total);
        System.out.println("-----------------\n");
    }
}
