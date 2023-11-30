package com.example.journaljournalservice.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Observation {

    private String id;
    private Encounter encounter;
    private String observation;

}
