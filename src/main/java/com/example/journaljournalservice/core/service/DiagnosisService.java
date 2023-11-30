package kth.journalbackendv2.core.service;

import kth.journalbackendv2.core.entity.Diagnosis;
import kth.journalbackendv2.core.entity.Patient;
import kth.journalbackendv2.core.entity.Staff;
import kth.journalbackendv2.core.service.interfaces.IDiagnosisService;
import kth.journalbackendv2.persistance.entity.DiagnosisDB;
import kth.journalbackendv2.persistance.entity.PatientDB;
import kth.journalbackendv2.persistance.entity.StaffDB;
import kth.journalbackendv2.persistance.repository.AccountRepository;
import kth.journalbackendv2.persistance.repository.DiagnosisRepository;
import kth.journalbackendv2.persistance.repository.PatientRepository;
import kth.journalbackendv2.persistance.repository.StaffRepository;
import kth.journalbackendv2.util.enums.Role;
import kth.journalbackendv2.util.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisService implements IDiagnosisService {

    private final AccountRepository accountRepository;
    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final Mapper mapper;

    @Autowired
    public DiagnosisService(
            AccountRepository accountRepository,
            PatientRepository patientRepository,
            DiagnosisRepository diagnosisRepository,
            StaffRepository staffRepository,
            Mapper mapper) {

        this.accountRepository = accountRepository;
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

        return mapper.DiagnosisFromDiagnosisDB(returnDiagnosis);
    }

    @Override
    public List<Diagnosis> findByPatientID(String id) {

        ArrayList<Diagnosis> diagnoses = new ArrayList<>();
        for(DiagnosisDB diagnosisDB : diagnosisRepository.findByPatient_Id(id)){
            diagnoses.add(mapper.DiagnosisFromDiagnosisDB(diagnosisDB));
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
