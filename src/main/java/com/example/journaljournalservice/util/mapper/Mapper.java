package com.example.journaljournalservice.util.mapper;

import com.example.journaljournalservice.core.entity.*;
import com.example.journaljournalservice.persistance.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;


@org.mapstruct.Mapper(componentModel = "spring")
public abstract class Mapper {

    public ObjectMapper json = new ObjectMapper();

    // Database
    public abstract ObservationDB ObservationDBFromObservation(Observation observation);

    // Model
    public abstract Patient PatientFromPatientDB(PatientDB patientDB);
    public abstract Staff StaffFromStaffDB(StaffDB staffView);
    public abstract Encounter EncounterFromEncounterDB(EncounterDB encounterDB);
    public abstract Observation ObservationFromObservationDB(ObservationDB observationDB);
    public abstract Diagnosis DiagnosisFromDiagnosisDB(DiagnosisDB diagnosisDB);

    // Views

}
