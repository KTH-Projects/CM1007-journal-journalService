package com.example.journaljournalservice.view.entity;

import com.example.journaljournalservice.core.entity.Diagnosis;
import com.example.journaljournalservice.core.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientView {
    private String id;
    private List<DiagnosisSimpleView> diagnoses;
    private List<EncounterView> encounters;

}
