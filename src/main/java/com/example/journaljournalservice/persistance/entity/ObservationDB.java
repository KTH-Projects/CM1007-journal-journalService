package com.example.journaljournalservice.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "observation")
public class ObservationDB {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column()
    private String observation;

    @ManyToOne
    @JoinColumn(name = "encounter_id")
    private EncounterDB encounter;


}