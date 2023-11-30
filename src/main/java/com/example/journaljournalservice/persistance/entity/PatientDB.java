package kth.journalbackendv2.persistance.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity()
@Table(name = "patient")
public class PatientDB {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;

    @OneToOne(mappedBy = "patient")
    private AccountDB account;

    public PatientDB(){
        this(null);
    }
    public PatientDB(AccountDB account){
        this.account = account;
    }

    public AccountDB getAccount() {
        return account;
    }

    public void setAccount(AccountDB account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "PatientDB{" +
                "id='" + id + '\'' +
                ", account=" + account +
                '}';
    }
}

