package com.example.journaljournalservice.core.service.interfaces;

import com.example.journaljournalservice.core.entity.Encounter;
import com.example.journaljournalservice.view.dto.EncounterDTO;

import java.util.List;

public interface IEncounterService {

    public List<Encounter> findAll();
    public List<Encounter> findAllByPatientID(String id);
    public Encounter findByID(String id);

    public Encounter create(Encounter encounter);
    public Encounter create(EncounterDTO encounter);
}
