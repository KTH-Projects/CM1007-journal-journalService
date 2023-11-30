package com.example.journaljournalservice.core.service;


import com.example.journaljournalservice.core.entity.Encounter;
import com.example.journaljournalservice.core.service.interfaces.IEncounterService;
import com.example.journaljournalservice.persistance.entity.EncounterDB;
import com.example.journaljournalservice.persistance.repository.*;
import com.example.journaljournalservice.util.mapper.Mapper;
import com.example.journaljournalservice.view.dto.EncounterDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EncounterService implements IEncounterService {

    private final EncounterRepository encounterRepository;
    private final ObservationRepository observationRepository;
    private final StaffRepository staffRepository;
    private final PatientRepository patientRepository;

    private final Mapper mapper;

    @Autowired
    public EncounterService(EncounterRepository encounterRepository, ObservationRepository observationRepository, StaffRepository staffRepository, PatientRepository patientRepository, Mapper mapper) {
        this.encounterRepository = encounterRepository;
        this.observationRepository = observationRepository;
        this.staffRepository = staffRepository;
        this.patientRepository = patientRepository;
        this.mapper = mapper;
    }


    @Override
    public List<Encounter> findAllByPatientID(String id) {
        return new ArrayList<>();
        //TODO Implement
    }

    @Override
    public Encounter findByID(String id) {
        Optional<EncounterDB> encounterDB = encounterRepository.findById(id);
        if(encounterDB.isEmpty()) return null;

        return mapper.EncounterFromEncounterDB(encounterDB.get());
    }

    @Override
    public List<Encounter> findAll() {
        return null;
    }

    @Override
    public Encounter create(Encounter encounter) {
        return null;
    }

    @Override
    public Encounter create(EncounterDTO encounter) {
        return null;
    }
}