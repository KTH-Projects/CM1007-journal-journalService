package com.example.journaljournalservice.core.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import com.example.journaljournalservice.persistance.entity.DiagnosisDB;
import com.example.journaljournalservice.persistance.entity.EncounterDB;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private String id;
    private List<Encounter> encounters;
    private List<Diagnosis> diagnoses;

    private String name;
    private int age;
    private String sex;

}
