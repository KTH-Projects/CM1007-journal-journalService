package kth.journalbackendv2.view.entity;

import kth.journalbackendv2.core.entity.Diagnosis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiagnosisSimpleView {


    private StaffView staff;
    private String diagnosis;

    private LocalDateTime dateTime;

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


    public DiagnosisSimpleView(StaffView staff, String diagnosis, LocalDateTime dateTime) {
        this.staff = staff;
        this.diagnosis = diagnosis;
        this.dateTime = dateTime;
    }

    public StaffView getStaff() {
        return staff;
    }

    public void setStaff(StaffView staff) {
        this.staff = staff;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
