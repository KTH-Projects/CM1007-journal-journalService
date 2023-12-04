package com.example.journaljournalservice.core.service;

import com.example.journaljournalservice.core.entity.Diagnosis;
import com.example.journaljournalservice.core.entity.Encounter;
import com.example.journaljournalservice.core.entity.Patient;
import com.example.journaljournalservice.core.service.interfaces.IDiagnosisService;
import com.example.journaljournalservice.core.service.interfaces.IEncounterService;
import com.example.journaljournalservice.core.service.interfaces.IObservationService;
import com.example.journaljournalservice.core.service.interfaces.IPatientService;
import com.example.journaljournalservice.persistance.entity.PatientDB;
import com.example.journaljournalservice.persistance.repository.PatientRepository;
import com.example.journaljournalservice.util.mapper.Mapper;
import com.example.journaljournalservice.view.entity.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {


    private final PatientRepository patientRepository;
    private final IDiagnosisService diagnosisService;
    private final IEncounterService encounterService;
    private final IObservationService observationService;
    private final Mapper mapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, IDiagnosisService diagnosisService, IEncounterService encounterService, IObservationService observationService, Mapper mapper) {
        this.patientRepository = patientRepository;
        this.diagnosisService = diagnosisService;
        this.encounterService = encounterService;
        this.observationService = observationService;
        this.mapper = mapper;
    }

    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        List<PatientDB> patientDBS = patientRepository.findAll();
        System.out.println(patientDBS);
        for(PatientDB p :  patientDBS){
            patients.add(Patient.convert(p));
        }

        return patients;
    }

    @Override
    public Patient findByIDLazy(String id) {
        Optional<PatientDB> patientDB = patientRepository.findById(id);
        if(patientDB.isEmpty()) return null;

        Patient p = Patient.convert(patientDB.get());
        System.out.println(p);
        return p;
    }

    @Override
    public Patient findByIDEagle(String id) {
        Patient patient = findByIDLazy(id);
        if(patient == null)
            return null;
        List<Encounter> encounters = encounterService.findAllByPatientID(patient.getId());

        for(Encounter e : encounters){
            e.setObservations(observationService.findAllByEncounter_Id(e.getId()));
        }

        List<Diagnosis> diagnoses = diagnosisService.findByPatientID(patient.getId());
        patient.setDiagnoses(diagnoses);
        patient.setEncounters(encounters);
        return patient;
    }

    @Override
    public String create(SignUpDTO info) {
        PatientDB p = new PatientDB();

        p.setName(info.getName());
        p.setAge(info.getAge());
        p.setSex(info.getSex());

        PatientDB patientDB = patientRepository.save(p);
        System.out.println(patientDB);

        return patientDB.getId();
    }

}
