package com.example.journaljournalservice.core.service.interfaces;

import com.example.journaljournalservice.core.entity.Observation;
import com.example.journaljournalservice.view.dto.ObservationDTO;

import java.util.List;
import java.util.Optional;

public interface IObservationService {

    Observation create(Observation observation);
    String create(ObservationDTO observation);

    List<Observation> getAllObservations();
    List<Observation> findAllByEncounter_Id(String id);

    Optional<Observation> getObservationById(String id);

    void deleteObservation(String id);
}


