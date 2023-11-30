package kth.journalbackendv2.util.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import kth.journalbackendv2.core.entity.*;
import kth.journalbackendv2.persistance.entity.*;
import kth.journalbackendv2.view.entity.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public abstract class Mapper {

    public ObjectMapper json = new ObjectMapper();

    // Database
    public abstract DiagnosisDB DiagnosisDBFromDiagonsis(Diagnosis diagnosisDB);
    public abstract EncounterDB EncounterDBFromEncounter(Encounter encounterDB);
    public abstract ObservationDB ObservationDBFromObservation(Observation observation);


    // Model

    public abstract Account AccountFromAccountView(AccountView accountView);
    public abstract Account AccountFromAccountDB(AccountDB accountDB);
    public abstract Patient PatientFromPatientDB(PatientDB patientDB);
    public abstract Staff StaffFromStaffView(StaffView staffView);
    public abstract Staff StaffFromStaffDB(StaffDB staffView);



    public abstract Diagnosis DiagnosisFromDiagnosisView(DiagnosisView diagnosisDB);

    public abstract Encounter EncounterFromEncounterDB(EncounterDB encounterDB);
    public abstract Encounter EncounterFromEncounterView(EncounterView encounterView);
    public abstract Observation ObservationFromObservationView(ObservationView observationView);
    public abstract Observation ObservationFromObservationDB(ObservationDB observationDB);
    public abstract Diagnosis DiagnosisFromDiagnosisDB(DiagnosisDB diagnosisDB);



    // Views
    public abstract AccountView AccountViewFromAcount(Account account);

    @Mapping(source = "patient.account.name", target = "patientName")
    @Mapping(source = "staff.account.name", target = "staffName")
    @Mapping(source = "observations", target = "observations")
    @Mapping(source = "dateTime", target = "dateTime")
    public abstract EncounterView EncounterViewFromEncounter(Encounter encounter);

    public abstract ObservationView ObservationViewFromObservation(Observation observation);
    public abstract StaffView StaffViewFromStaff(Staff staff);
    public abstract DiagnosisView DiagnosisViewFromDiagnosis(Diagnosis diagnosisDB);
    public abstract PatientView PatientViewFromPatient(Patient patient);


}
