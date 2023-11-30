package kth.journalbackendv2.persistance.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "encounter")
public class EncounterDB {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne()
    @JoinColumn(name = "patient_id")
    private PatientDB patient;

    @ManyToOne()
    @JoinColumn(name = "staff_id")
    private StaffDB staff;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime dateTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ObservationDB> observations;



    public List<ObservationDB> getObservations() {
        return observations;
    }

    public void setObservations(List<ObservationDB> observations) {
        this.observations = observations;
    }

    public EncounterDB(PatientDB patient, StaffDB staff, LocalDateTime dateTime) {
        this.patient = patient;
        this.staff = staff;
        this.dateTime = dateTime;
    }

    public EncounterDB() {
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
