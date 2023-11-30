package com.example.journaljournalservice.view.entity;

import com.example.journaljournalservice.core.entity.Observation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObservationView {

    private String id;
    private String observation;
    private EncounterView encounter;

}
