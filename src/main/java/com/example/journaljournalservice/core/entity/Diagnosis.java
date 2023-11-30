package com.example.journaljournalservice.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis {

    private String id;

    private Patient patient;

    private Staff staff;

    private String diagnosis;

    private LocalDateTime dateTime;

}
