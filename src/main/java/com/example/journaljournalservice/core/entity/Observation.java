package kth.journalbackendv2.core.entity;

public class Observation {

    private String id;
    private Encounter encounter;
    private String observation;

    public Observation(String id, Encounter encounter, String observation) {
        this.id = id;
        this.encounter = encounter;
        this.observation = observation;
    }

    public Observation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public String toString() {
        return "Observation{" +
                "id='" + id + '\'' +
                ", encounter=" + encounter +
                ", observation=" + observation +
                '}';
    }
}
