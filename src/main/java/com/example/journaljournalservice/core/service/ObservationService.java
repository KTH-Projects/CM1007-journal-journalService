package com.example.journaljournalservice.core.service;

import com.example.journaljournalservice.*;

import com.example.journaljournalservice.core.entity.Observation;
import com.example.journaljournalservice.core.service.interfaces.IObservationService;
import com.example.journaljournalservice.persistance.entity.EncounterDB;
import com.example.journaljournalservice.persistance.entity.ObservationDB;
import com.example.journaljournalservice.persistance.repository.EncounterRepository;
import com.example.journaljournalservice.persistance.repository.ObservationRepository;
import com.example.journaljournalservice.util.mapper.Mapper;
import com.example.journaljournalservice.view.dto.ObservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObservationService implements IObservationService {

    private final ObservationRepository observationRepository;
    private final EncounterRepository encounterRepository;
    private final Mapper mapper;

    @Autowired
    public ObservationService(ObservationRepository observationRepository, EncounterRepository encounterRepository, Mapper mapper) {
        this.observationRepository = observationRepository;
        this.encounterRepository = encounterRepository;
        this.mapper = mapper;
    }

    @Override
    public Observation create(Observation observation) {
        ObservationDB observationDB = mapper.ObservationDBFromObservation(observation);
        ObservationDB savedObservationDB = observationRepository.save(observationDB);
        return mapper.ObservationFromObservationDB(savedObservationDB);
    }
    @Override
    public Observation create(ObservationDTO observation) {
        Optional<EncounterDB> encounter = encounterRepository.findById(observation.getEncounterID());
        if(encounter.isEmpty())
            return null;
        ObservationDB observationDB = new ObservationDB(observation.getObservation());
        observationDB.setEncounter(encounter.get());
        ObservationDB savedObservationDB = observationRepository.save(observationDB);
        return mapper.ObservationFromObservationDB(savedObservationDB);
    }

    @Override
    public List<Observation> getAllObservations() {
        List<ObservationDB> observationDBs = observationRepository.findAll();
        List<Observation> observations = new ArrayList<>();
        for (ObservationDB observationDB : observationDBs) {
            observations.add(mapper.ObservationFromObservationDB(observationDB));
        }
        return observations;
    }

    @Override
    public Optional<Observation> getObservationById(String id) {
        Optional<ObservationDB> observationDB = observationRepository.findById(id);
        if (observationDB.isPresent()) {
            return Optional.of(mapper.ObservationFromObservationDB(observationDB.get()));
        } else {
            return Optional.empty();
        }
    }


    @Override
    public void deleteObservation(String id) {
        observationRepository.deleteById(id);
    }
}

