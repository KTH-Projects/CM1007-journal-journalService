package kth.journalbackendv2.core.service;

import kth.journalbackendv2.core.entity.Encounter;
import kth.journalbackendv2.core.entity.Observation;
import kth.journalbackendv2.core.service.interfaces.IObservationService;
import kth.journalbackendv2.persistance.entity.EncounterDB;
import kth.journalbackendv2.persistance.entity.ObservationDB;
import kth.journalbackendv2.persistance.repository.EncounterRepository;
import kth.journalbackendv2.persistance.repository.ObservationRepository;
import kth.journalbackendv2.util.mapper.Mapper;
import kth.journalbackendv2.view.dto.ObservationDTO;
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

