package com.example.journaljournalservice.persistance.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity()
@Table(name = "diagnosis")
public class DiagnosisDB {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;

    @ManyToOne()
    @JoinColumn(name = "patient_id")
    private PatientDB patient;

    @ManyToOne()
    @JoinColumn(name = "staff_id")
    private StaffDB staff;

    @Column(nullable = false)
    private String diagnosis;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime dateTime;

    public DiagnosisDB(String id, PatientDB patient, StaffDB staff, String diagnosis, LocalDateTime dateTime) {
        this.id = id;
        this.patient = patient;
        this.staff = staff;
        this.diagnosis = diagnosis;
        this.dateTime = dateTime;
    }

    public DiagnosisDB() {
        this.dateTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PatientDB getPatient() {
        return patient;
    }

    public void setPatient(PatientDB patient) {
        this.patient = patient;
    }

    public StaffDB getStaff() {
        return staff;
    }

    public void setStaff(StaffDB staff) {
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
