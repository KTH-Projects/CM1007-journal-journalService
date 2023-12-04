package com.example.journaljournalservice.core.service;

import com.example.journaljournalservice.core.entity.Diagnosis;
import com.example.journaljournalservice.core.service.interfaces.IDiagnosisService;
import com.example.journaljournalservice.persistance.entity.DiagnosisDB;
import com.example.journaljournalservice.persistance.entity.PatientDB;
import com.example.journaljournalservice.persistance.entity.StaffDB;
import com.example.journaljournalservice.persistance.repository.DiagnosisRepository;
import com.example.journaljournalservice.persistance.repository.PatientRepository;
import com.example.journaljournalservice.persistance.repository.StaffRepository;
import com.example.journaljournalservice.util.enums.Role;
import com.example.journaljournalservice.util.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisService implements IDiagnosisService {

    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final Mapper mapper;

    @Autowired
    public DiagnosisService(
            PatientRepository patientRepository,
            DiagnosisRepository diagnosisRepository,
            StaffRepository staffRepository,
            Mapper mapper) {

        this.patientRepository = patientRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.staffRepository = staffRepository;
        this.mapper = mapper;
    }


    @Override
    public List<Diagnosis> findAll() {
        List<Diagnosis> diagnosis = new ArrayList<>();
        for(DiagnosisDB diagnosisDB : diagnosisRepository.findAll()){
            diagnosis.add(mapper.DiagnosisFromDiagnosisDB(diagnosisDB));
        }

        return diagnosis;
    }

    @Override
    public Diagnosis create(String doctorID, String patientID, String diagnosis) {

        Optional<StaffDB> staffDB = staffRepository.findById(doctorID);
        if(staffDB.isEmpty()) return null;
        if(!staffDB.get().getRole().equals(Role.doctor)) return null;

        Optional<PatientDB> patientDB = patientRepository.findById(patientID);
        if(patientDB.isEmpty()) return null;

        DiagnosisDB diagnosisDB = new DiagnosisDB();
        diagnosisDB.setDiagnosis(diagnosis);
        diagnosisDB.setPatient(patientDB.get());
        diagnosisDB.setStaff(staffDB.get());
        DiagnosisDB returnDiagnosis = diagnosisRepository.save(diagnosisDB);

        return Diagnosis.convert(returnDiagnosis);
    }

    @Override
    public List<Diagnosis> findByPatientID(String id) {

        ArrayList<Diagnosis> diagnoses = new ArrayList<>();
        for(DiagnosisDB diagnosisDB : diagnosisRepository.findByPatient_Id(id)){
            diagnoses.add(Diagnosis.convert(diagnosisDB));
        }
        return diagnoses;
    }

    @Override
    public List<Diagnosis> findByStaffID(String id) {
        ArrayList<Diagnosis> diagnoses = new ArrayList<>();
        for(DiagnosisDB diagnosisDB : diagnosisRepository.findByStaff_Id(id)){
            diagnoses.add(mapper.DiagnosisFromDiagnosisDB(diagnosisDB));
        }
        return diagnoses;

    }
}
