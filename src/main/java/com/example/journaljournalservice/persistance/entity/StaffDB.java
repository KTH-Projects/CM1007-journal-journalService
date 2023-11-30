package kth.journalbackendv2.persistance.entity;

import jakarta.persistence.*;
import kth.journalbackendv2.util.enums.Role;

import java.util.List;

@Entity
@Table(name = "staff")
public class StaffDB {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;

    @OneToOne(mappedBy = "staff")
    private AccountDB account;

    @Column
    private Role role;

    public StaffDB(AccountDB account, Role role) {
        this.account = account;
        this.role = role;
    }


    public StaffDB() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccountDB getAccount() {
        return account;
    }

    public void setAccount(AccountDB account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
