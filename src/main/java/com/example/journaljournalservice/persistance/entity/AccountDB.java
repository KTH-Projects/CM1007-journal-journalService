package com.example.journaljournalservice.persistance.entity;

import jakarta.persistence.*;
import com.example.journaljournalservice.core.entity.Account;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Entity()
@Table(name = "account")
public class AccountDB {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private SessionDB session;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientDB patient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private StaffDB staff;

    @OneToMany(mappedBy = "fromAccount",cascade = CascadeType.ALL)
    private List<ChatDB> sentChats = new ArrayList<>();;
    @OneToMany(mappedBy = "toAccount",cascade = CascadeType.ALL)
    private List<ChatDB> receivedChats = new ArrayList<>();


    public static AccountDB convertFromCore(Account account){
        return new AccountDB(account.getId(),account.getEmail(),account.getPassword(), account.getName());
    }


    public AccountDB(String id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }
    public AccountDB(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    public AccountDB() {
        this.email = "email";
        this.password = "password";
        this.name = "name";
    }

    public List<ChatDB> getSentChats() {
        return sentChats;
    }

    public void setSentChats(List<ChatDB> sentChats) {
        this.sentChats = sentChats;
    }

    public List<ChatDB> getReceivedChats() {
        return receivedChats;
    }

    public void setReceivedChats(List<ChatDB> receivedChats) {
        this.receivedChats = receivedChats;
    }

    public StaffDB getStaff() {
        return staff;
    }

    public void setStaff(StaffDB staff) {
        this.staff = staff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PatientDB getPatient() {
        return patient;
    }

    public void setPatient(PatientDB patient) {
        this.patient = patient;
    }

    public SessionDB getSession() {
        return session;
    }

    public void setSession(SessionDB session) {
        this.session = session;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
