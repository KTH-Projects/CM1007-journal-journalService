package kth.journalbackendv2.view.entity;

import kth.journalbackendv2.core.entity.Diagnosis;
import kth.journalbackendv2.core.entity.Encounter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class EncounterView {

    private String id;

    private String patientName;
    private String staffName;

    private List<ObservationView> observations;
    private LocalDateTime dateTime;


    public EncounterView(String id, String patientName, String staffName, List<ObservationView> observations, LocalDateTime dateTime) {
        this.id = id;
        this.patientName = patientName;
        this.staffName = staffName;
        this.observations = observations;
        this.dateTime = dateTime;
    }

    public EncounterView() {
    }
    /*
    public static DiagnosisSimpleView convert(Diagnosis d){
        return new DiagnosisSimpleView(StaffView.convert(d.getStaff()),d.getDiagnosis(),d.getDateTime());
    }
    public static List<DiagnosisSimpleView> convertList(List<Diagnosis> dl){
        ArrayList<DiagnosisSimpleView> dSimple = new ArrayList<>();
        for (Diagnosis d:dl) {
            dSimple.add( convert(d) );
        }
        return dSimple;
    }

     */

    public static EncounterView convert(Encounter e){
        return new EncounterView(e.getId(),
                e.getPatient().getAccount().getName(),
                e.getStaff().getAccount().getName(),
                ObservationView.convertList( e.getObservations() ),
                e.getDateTime());
    }

    public static List<EncounterView> convertList(List<Encounter> encounters){
        ArrayList<EncounterView> eViewList = new ArrayList<>();
        for(Encounter e : encounters){
            eViewList.add(EncounterView.convert(e));
        }
        return eViewList;
    }

    public List<ObservationView> getObservations() {
        return observations;
    }

    public void setObservations(List<ObservationView> observations) {
        this.observations = observations;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    @Override
    public String toString() {
        return "EncounterView{" +
                "id='" + id + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
