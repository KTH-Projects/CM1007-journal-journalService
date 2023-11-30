package kth.journalbackendv2.core.entity;


import kth.journalbackendv2.persistance.entity.EncounterDB;
import kth.journalbackendv2.persistance.entity.ObservationDB;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Encounter {

    private String id;

    private Patient patient;

    private Staff staff;

    private LocalDateTime dateTime;

    private List<Observation> observations;

    public Encounter(String id, Patient patient, Staff staff, LocalDateTime dateTime, List<Observation> observation) {
        this.id = id;
        this.patient = patient;
        this.staff = staff;
        this.dateTime = dateTime;
        this.observations = observation;
    }

    public Encounter() {
        patient = new Patient();

        staff = new Staff();

        dateTime = LocalDateTime.now();

        observations = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Observation> getObservations() {
        return observations;
    }


    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    public static Encounter convertFromEncounterDB(EncounterDB encounterDB){

        Encounter encounter = new Encounter();
        encounter.setId(encounterDB.getId());

        encounter.patient = new Patient();
        encounter.patient.setId(encounterDB.getPatient().getId());
        encounter.patient.setAccount(new Account(
                encounterDB.getPatient().getAccount().getEmail(),
                encounterDB.getPatient().getAccount().getName()));
        encounter.patient.setEncounters(new ArrayList<>());


        encounter.staff = new Staff();
        encounter.staff.setId(encounterDB.getStaff().getId());
        encounter.staff.setAccount(new Account(
                encounterDB.getStaff().getAccount().getEmail(),
                encounterDB.getStaff().getAccount().getName()));
        encounter.staff.setRole(encounterDB.getStaff().getRole());

        encounter.dateTime = encounterDB.getDateTime();
        encounter.observations = new ArrayList<>();
        for(ObservationDB observationDB : encounterDB.getObservations()){
            Observation observation = new Observation();
            observation.setId(observationDB.getId());
            observation.setObservation(observationDB.getObservation());
            observation.setEncounter(new Encounter());
            encounter.getObservations().add(observation);
        }


        return encounter;
    }

    @Override
    public String toString() {
        return "Encounter{" +
                "id='" + id + '\'' +
                ", patient=" + patient +
                ", staff=" + staff +
                ", dateTime=" + dateTime +
                ", observation=" + observations +
                '}';
    }
}
