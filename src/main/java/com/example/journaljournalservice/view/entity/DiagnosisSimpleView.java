package com.example.journaljournalservice.view.entity;

import com.example.journaljournalservice.core.entity.Diagnosis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisSimpleView {
    private StaffView staff;
    private String diagnosis;
    private LocalDateTime dateTime;

}
