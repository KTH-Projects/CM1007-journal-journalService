package com.example.journaljournalservice.core.service;
import com.example.journaljournalservice.*;

import com.example.journaljournalservice.core.entity.Diagnosis;
import com.example.journaljournalservice.core.entity.Encounter;
import com.example.journaljournalservice.core.entity.Patient;
import com.example.journaljournalservice.core.service.interfaces.IDiagnosisService;
import com.example.journaljournalservice.core.service.interfaces.IEncounterService;
import com.example.journaljournalservice.core.service.interfaces.IPatientService;
import com.example.journaljournalservice.persistance.entity.PatientDB;
import com.example.journaljournalservice.persistance.repository.PatientRepository;
import com.example.journaljournalservice.util.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {


    private final PatientRepository repository;
    private final IDiagnosisService diagnosisService;
    private final IEncounterService encounterService;
    private final Mapper mapper;

    @Autowired
    public PatientService(PatientRepository repository, IDiagnosisService diagnosisService, IEncounterService encounterService, Mapper mapper) {
        this.repository = repository;
        this.diagnosisService = diagnosisService;
        this.encounterService = encounterService;
        this.mapper = mapper;
    }

    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        for(PatientDB p :  repository.findAll()){
            patients.add(mapper.PatientFromPatientDB(p));
        }

        return patients;
    }

    @Override
    public Patient findByID(String id) {
        Optional<PatientDB> patientDB = repository.findById(id);
        if(patientDB.isEmpty()) return null;
        return mapper.PatientFromPatientDB(patientDB.get());
    }

    @Override
    public Patient findAllByID(String id) {
        Patient patient = findByID(id);
        if(patient == null)
            return null;
        List<Encounter> encounters = encounterService.findAllByPatientID(patient.getId());
        List<Diagnosis> diagnoses = diagnosisService.findByPatientID(patient.getId());
        patient.setDiagnoses(diagnoses);
        patient.setEncounters(encounters);
        return patient;
    }

    @Override
    public Patient findAllByAccount_ID(String id) {
        Patient p = findByAccount_ID(id);
        if(p == null)
            return null;
        List<Encounter> encounters = encounterService.findAllByPatientID(p.getId());
        List<Diagnosis> diagnoses = diagnosisService.findByPatientID(p.getId());
        p.setDiagnoses(diagnoses);
        p.setEncounters(encounters);
        return p;
    }

    @Override
    public Patient findByAccount_ID(String id) {
        PatientDB patientDB = repository.findByAccount_Id(id);
        if(patientDB == null) return null;
        return mapper.PatientFromPatientDB(patientDB);
    }

}
