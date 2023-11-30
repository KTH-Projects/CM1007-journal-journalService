package com.example.journaljournalservice.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
