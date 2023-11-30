package com.example.journaljournalservice.core.entity;


import com.example.journaljournalservice.persistance.entity.EncounterDB;
import com.example.journaljournalservice.persistance.entity.ObservationDB;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Encounter {

    private String id;

    private Patient patient;

    private Staff staff;

    private LocalDateTime dateTime;

    private List<Observation> observations;

}
