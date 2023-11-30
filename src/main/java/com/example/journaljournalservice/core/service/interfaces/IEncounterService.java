package kth.journalbackendv2.core.service.interfaces;

import kth.journalbackendv2.core.entity.Encounter;
import kth.journalbackendv2.view.dto.EncounterDTO;

import java.util.List;

public interface IEncounterService {

    public List<Encounter> findAll();
    public List<Encounter> findAllByPatientID(String id);
    public Encounter findByID(String id);

    public Encounter create(Encounter encounter);
    public Encounter create(EncounterDTO encounter);
}
