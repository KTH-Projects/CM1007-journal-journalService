package kth.journalbackendv2.persistance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "observation")
public class ObservationDB {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column()
    private String observation;

    @ManyToOne
    @JoinColumn(name = "encounter_id")
    private EncounterDB encounter;


    public ObservationDB() {
    }

    public ObservationDB(String observation) {
        this.observation = observation;
    }



    public EncounterDB getEncounter() {
        return encounter;
    }

    public void setEncounter(EncounterDB encounter) {
        this.encounter = encounter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

}