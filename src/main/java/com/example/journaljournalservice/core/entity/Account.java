package kth.journalbackendv2.core.entity;

import kth.journalbackendv2.persistance.entity.AccountDB;
import kth.journalbackendv2.view.entity.AccountView;

public class Account {
    private String id;
    private String email;
    private String password;
    private String name;


    public Account(String id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Account(String email, String name) {
        this(null,email,"pasword", name);
    }

    public Account(String email, String password, String name) {
        this(null,email,password, name);
    }

    public Account() {
        this("0","email","password", "name");
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static Account convertFromDB(AccountDB account){
        return new Account(account.getId(),account.getEmail(),account.getPassword(), account.getName());
    }
    public static Account convertFromView(AccountView account){
        return new Account(account.getEmail(),account.getName());
    }
}
