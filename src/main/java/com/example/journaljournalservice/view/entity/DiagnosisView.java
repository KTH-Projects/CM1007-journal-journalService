package com.example.journaljournalservice.view.entity;

import com.example.journaljournalservice.core.entity.Patient;
import com.example.journaljournalservice.core.entity.Staff;

import java.time.LocalDateTime;

public class DiagnosisView {

    private Patient patient;

    private Staff staff;

    private String diagnosis;

    private LocalDateTime dateTime;

    public DiagnosisView(Patient patient, Staff staff, String diagnosis, LocalDateTime dateTime) {
        this.patient = patient;
        this.staff = staff;
        this.diagnosis = diagnosis;
        this.dateTime = dateTime;
    }

    public DiagnosisView() {
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
}
