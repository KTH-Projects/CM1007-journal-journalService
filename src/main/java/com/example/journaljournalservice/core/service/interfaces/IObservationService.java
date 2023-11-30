package kth.journalbackendv2.core.service.interfaces;

import kth.journalbackendv2.core.entity.Observation;
import kth.journalbackendv2.view.dto.ObservationDTO;

import java.util.List;
import java.util.Optional;

public interface IObservationService {

    Observation create(Observation observation);
    Observation create(ObservationDTO observation);

    List<Observation> getAllObservations();

    Optional<Observation> getObservationById(String id);

    void deleteObservation(String id);
}


