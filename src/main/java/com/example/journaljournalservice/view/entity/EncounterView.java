package com.example.journaljournalservice.view.entity;

import com.example.journaljournalservice.core.entity.Diagnosis;
import com.example.journaljournalservice.core.entity.Encounter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncounterView {

    private String id;
    private String patientName;
    private String staffName;
    private List<ObservationView> observations;
    private LocalDateTime dateTime;


}
