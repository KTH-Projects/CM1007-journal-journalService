package com.example.journaljournalservice.view.entity;

import com.example.journaljournalservice.core.entity.Diagnosis;
import com.example.journaljournalservice.core.entity.Patient;

import java.util.List;

public class    PatientView {
    private String id;
    private AccountView account;
    private List<DiagnosisSimpleView> diagnoses;
    private List<EncounterView> encounters;

    public static PatientView convert(Patient patient){
        return new PatientView(
                patient.getId(),
                AccountView.convert(patient.getAccount()),
                DiagnosisSimpleView.convertList( patient.getDiagnoses() ),
                EncounterView.convertList( patient.getEncounters() ));
    }

    public List<EncounterView> getEncounters() {
        return encounters;
    }

    public void setEncounters(List<EncounterView> encounters) {
        this.encounters = encounters;
    }

    public PatientView(String id, AccountView account, List<DiagnosisSimpleView> diagnoses, List<EncounterView> encounters) {
        this.id = id;
        this.account = account;
        this.diagnoses = diagnoses;
        this.encounters = encounters;
    }

    public PatientView() {
    }

    public List<DiagnosisSimpleView> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<DiagnosisSimpleView> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public AccountView getAccount() {
        return account;
    }

    public void setAccount(AccountView account) {
        this.account = account;
    }

    public PatientView(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
