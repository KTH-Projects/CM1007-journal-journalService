package kth.journalbackendv2.view.entity;

import kth.journalbackendv2.core.entity.Encounter;
import kth.journalbackendv2.core.entity.Observation;

import java.util.ArrayList;
import java.util.List;

public class ObservationView {

    private String id;
    private String observation;
    private EncounterView encounter;

    public static ObservationView convert(Observation o){
        return new ObservationView(o.getId(),o.getObservation());
    }
    public static List<ObservationView> convertList(List<Observation> oL){
        ArrayList<ObservationView> oViewList = new ArrayList<>();
        if(oL==null)
            return oViewList;
        for(Observation o : oL){
            oViewList.add(ObservationView.convert(o));
        }
        return oViewList;
    }

    public ObservationView(String id, String observation) {
        this.id = id;
        this.observation = observation;
    }

    public ObservationView() {
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

    public EncounterView getEncounter() {
        return encounter;
    }

    public void setEncounter(EncounterView encounter) {
        this.encounter = encounter;
    }

    @Override
    public String toString() {
        return "ObservationView{" +
                "id='" + id + '\'' +
                ", observation=" + observation +
                ", encounter=" + encounter +
                '}';
    }
}
