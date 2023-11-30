package com.example.journaljournalservice.core.service.interfaces;

import com.example.journaljournalservice.core.entity.Account;

public interface ISessionService {
    public String createSession(Account account);
    public String findSessionByAccount(Account account);
    public Account findAccountBySession(String userSessionID);
    public boolean isValidSession(String userSessionID);
    public boolean isDoctor(String userSessionID);
    public boolean isOther(String userSessionID);
    public boolean isDoctorOrStaff(String userSessionID);

}
