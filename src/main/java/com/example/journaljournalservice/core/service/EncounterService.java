package kth.journalbackendv2.core.service;

import kth.journalbackendv2.core.entity.Encounter;
import kth.journalbackendv2.core.entity.Observation;
import kth.journalbackendv2.core.service.interfaces.IEncounterService;
import kth.journalbackendv2.persistance.entity.*;
import kth.journalbackendv2.persistance.repository.*;
import kth.journalbackendv2.util.mapper.Mapper;
import kth.journalbackendv2.view.dto.EncounterDTO;
import org.hibernate.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EncounterService implements IEncounterService {

    private final EncounterRepository encounterRepository;
    private final AccountRepository accountRepository;
    private final ObservationRepository observationRepository;
    private final StaffRepository staffRepository;
    private final PatientRepository patientRepository;

    private final Mapper mapper;

    @Autowired
    public EncounterService(EncounterRepository encounterRepository, AccountRepository accountRepository, ObservationRepository observationRepository, StaffRepository staffRepository, PatientRepository patientRepository, Mapper mapper) {
        this.encounterRepository = encounterRepository;
        this.accountRepository = accountRepository;
        this.observationRepository = observationRepository;
        this.staffRepository = staffRepository;
        this.patientRepository = patientRepository;
        this.mapper = mapper;
    }


    @Override
    public List<Encounter> findAllByPatientID(String id) {
        List<Encounter> encounters = new ArrayList<>();
        List<EncounterDB> encounterDBList = encounterRepository.findByPatient_Id(id);

        for(EncounterDB encounterDB : encounterDBList){
            List<ObservationDB> observationDBList =  observationRepository.findAllByEncounter_Id(encounterDB.getId());
            encounterDB.setObservations(observationDBList);
            encounters.add(Encounter.convertFromEncounterDB(encounterDB));
        }
        return encounters;
    }

    @Override
    public Encounter findByID(String id) {
        Optional<EncounterDB> encounterDB = encounterRepository.findById(id);
        if(encounterDB.isEmpty()) return null;

        return mapper.EncounterFromEncounterDB(encounterDB.get());
    }

    @Override
    public List<Encounter> findAll() {
        List<Encounter> encounters = new ArrayList<>();
        List<EncounterDB> encounterDBList = encounterRepository.findAll();

        for(EncounterDB encounterDB : encounterDBList){
            List<ObservationDB> observationDBList =  observationRepository.findAllByEncounter_Id(encounterDB.getId());
            encounterDB.setObservations(observationDBList);
            encounters.add(Encounter.convertFromEncounterDB(encounterDB));

        }
        return encounters;
    }

    @Override
    public Encounter create(Encounter encounter) {

        Optional<StaffDB> staffDB = staffRepository.findById(encounter.getStaff().getId());
        if(staffDB.isEmpty()) return null;

        Optional<PatientDB> patientDB = patientRepository.findById(encounter.getPatient().getId());
        if(patientDB.isEmpty()) return null;

        EncounterDB encounterDB = new EncounterDB(patientDB.get(), staffDB.get(), LocalDateTime.now());

        ArrayList<ObservationDB> observationDBS = new ArrayList<>();
        for(Observation ob : encounter.getObservations()){
            ObservationDB observationDB = mapper.ObservationDBFromObservation(ob);
            observationDBS.add(observationRepository.save(observationDB));
        }
        encounterDB.setObservations(observationDBS);

        EncounterDB returnEncounter = encounterRepository.save(encounterDB);

        return mapper.EncounterFromEncounterDB(returnEncounter);
    }

    @Override
    public Encounter create(EncounterDTO encounter) {
        Optional<StaffDB> staffDB = staffRepository.findById(encounter.getStaffID());
        if(staffDB.isEmpty()) return null;

        Optional<PatientDB> patientDB = patientRepository.findById(encounter.getPatientID());
        if(patientDB.isEmpty()) return null;

        EncounterDB encounterDB = new EncounterDB(patientDB.get(), staffDB.get(), encounter.getDate());
        encounterDB.setObservations(new ArrayList<>());
        EncounterDB returnEncounter = encounterRepository.save(encounterDB);

        return mapper.EncounterFromEncounterDB(returnEncounter);
    }
}
