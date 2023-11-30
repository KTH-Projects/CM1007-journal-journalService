package kth.journalbackendv2.view.dto;

import java.time.LocalDateTime;

public class EncounterDTO {
    public void setId(String id) {
        this.id = id;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private String id;
    private String staffID;
    private String patientID;
    private LocalDateTime date;

    public String getId() {
        return id;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getPatientID() {
        return patientID;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
