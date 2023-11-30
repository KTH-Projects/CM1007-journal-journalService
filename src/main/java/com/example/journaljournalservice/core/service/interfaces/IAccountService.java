package com.example.journaljournalservice.core.service.interfaces;

import com.example.journaljournalservice.core.entity.Account;

import java.util.List;

public interface IAccountService {
    public List<Account> findAll();
    public List<Account> findAllPatient();
    public Account findByID(String ID);
    public Account findByEmail(String email);
    public Account create(Account account, String role);
    public Account findBySessionID(String ID);

}
