package kth.journalbackendv2.core.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import kth.journalbackendv2.persistance.entity.DiagnosisDB;
import kth.journalbackendv2.persistance.entity.EncounterDB;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Patient {
    private String id;
    private Account account;
    private List<Encounter> encounters;
    private List<Diagnosis> diagnoses;

    public Patient(String id, Account account, List<Encounter> encounters, List<Diagnosis> diagnoses) {
        this.id = id;
        this.account = account;
        this.encounters = encounters;
        this.diagnoses = diagnoses;
    }

    public Patient() {
        account = new Account();
        encounters = new ArrayList<>();
        diagnoses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Encounter> getEncounters() {
        return encounters;
    }

    public void setEncounters(List<Encounter> encounters) {
        this.encounters = encounters;
    }

    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", account=" + account.toString() +
                ", encounters=" + encounters +
                ", diagnoses=" + diagnoses +
                '}';
    }
}
