package kth.journalbackendv2.view.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import kth.journalbackendv2.core.entity.Staff;
import kth.journalbackendv2.util.enums.Role;


public class StaffView {

    private String id;
    private Role role;
    private AccountView accountView;

    public static StaffView convert(Staff staff){
        return new StaffView(staff.getId(),staff.getRole(), AccountView.convert(staff.getAccount()));
    }
    public StaffView(String id, Role role, AccountView accountView) {
        this.id = id;
        this.role = role;
        this.accountView = accountView;
    }

    public StaffView() {
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

    public AccountView getAccountView() {
        return accountView;
    }

    public void setAccountView(AccountView accountView) {
        this.accountView = accountView;
    }

    @Override
    public String toString() {
        return "StaffView{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
