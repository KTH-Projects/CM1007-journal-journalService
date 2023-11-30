package com.example.journaljournalservice.core.service.interfaces;

import com.example.journaljournalservice.core.entity.Patient;

import java.util.List;

public interface IPatientService {
    public List<Patient> findAll();
    public Patient findByID(String id);
    public Patient findAllByID(String id);

    public Patient findAllByAccount_ID(String id);

    public Patient findByAccount_ID(String id);

}
