package com.example.journaljournalservice.core.entity;

import com.example.journaljournalservice.persistance.entity.PatientDB;
import com.example.journaljournalservice.view.entity.StaffView;
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
    private String name;
    private int age;
    private String sex;
    private List<Encounter> encounters;
    private List<Diagnosis> diagnoses;

    public static Patient convert(PatientDB pDB){
        System.out.println(pDB);
        Patient p = new Patient();
        p.setId(pDB.getId());
        p.setAge(pDB.getAge());
        p.setSex(pDB.getSex());
        p.setName(pDB.getName());
        return p;
    }

}
