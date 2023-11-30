package kth.journalbackendv2.persistance.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "session")
public class SessionDB {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;

    @OneToOne(mappedBy = "session")
    private AccountDB account;
    @Column(nullable = false)
    private LocalDateTime end;

    public SessionDB(AccountDB account) {
        this.id = null;
        this.account = account;
        this.end = LocalDateTime.now().plusMinutes(30);
    }

    public SessionDB() {
        this(new AccountDB());
    }



    public AccountDB getAccount() {
        return account;
    }

    public void setAccount(AccountDB account) {
        this.account = account;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isValid(){
        return this.end.isAfter(LocalDateTime.now());
    }
}
