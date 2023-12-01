package com.example.journaljournalservice.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "patient")
public class PatientDB {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String sex;

}

