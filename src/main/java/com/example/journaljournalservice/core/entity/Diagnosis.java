package com.example.journaljournalservice.core.entity;

import java.time.LocalDateTime;

public class Diagnosis {

    private String id;

    private Patient patient;

    private Staff staff;

    private String diagnosis;

    private LocalDateTime dateTime;

    public Diagnosis(String id, Patient patient, Staff staff, String diagnosis, LocalDateTime dateTime) {
        this.id = id;
        this.patient = patient;
        this.staff = staff;
        this.diagnosis = diagnosis;
        this.dateTime = dateTime;
    }

    public Diagnosis() {
        patient = new Patient();
        staff = new Staff();
        diagnosis = "diagnosis";
        dateTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id='" + id + '\'' +
                ", patient=" + patient +
                ", staff=" + staff +
                ", diagnosis='" + diagnosis + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
