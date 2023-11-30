package com.example.journaljournalservice.core.service.interfaces;

import com.example.journaljournalservice.core.entity.Diagnosis;

import java.util.Collection;
import java.util.List;

public interface IDiagnosisService {
    public List<Diagnosis> findAll();
    public Diagnosis create(String doctorID, String patientID, String diagnosis);
    public List<Diagnosis> findByPatientID(String id);
    public List<Diagnosis> findByStaffID(String id);
}
