package com.example.journaljournalservice.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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



}
