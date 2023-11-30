package kth.journalbackendv2.core.entity;

import kth.journalbackendv2.util.enums.Role;

public class Staff {
    private String id;
    private Role role;
    private Account account;

    public Staff(String id, Role role, Account account) {
        this.id = id;
        this.role = role;
        this.account = account;
    }
    public Staff(Role role, Account account) {
        this.role = role;
        this.account = account;
    }

    public Staff() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", role=" + role +
                '}';
    }
}
