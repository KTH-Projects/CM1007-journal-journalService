package com.example.journaljournalservice.core.service.interfaces;

import com.example.journaljournalservice.core.entity.Patient;
import com.example.journaljournalservice.view.entity.SignUpDTO;

import java.util.List;

public interface IPatientService {
    public List<Patient> findAll();
    public Patient findByIDLazy(String id);
    public Patient findByIDEagle(String id);

    public String create(SignUpDTO info);
}
