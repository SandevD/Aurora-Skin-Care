package model;

public class Appointment {
    private static int idCounter = 1;
    private int appointmentId;
    private Patient patient;
    private Dermatologist dermatologist;
    private String day;
    private String time;
    private Treatment treatment;
    private double registrationFee; 

    public Appointment(Patient patient, Dermatologist dermatologist, String day, String time, double registrationFee) {
        this.appointmentId = idCounter++;
        this.patient = patient;
        this.dermatologist = dermatologist;
        this.day = day;
        this.time = time;
        this.registrationFee = registrationFee; 
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setDay(String day) {
        this.day = day;
    }
    
    public void setTime(String time) {
        this.time = time;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }
}
