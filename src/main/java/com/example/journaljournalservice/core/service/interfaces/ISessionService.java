package kth.journalbackendv2.core.service.interfaces;

import jakarta.servlet.http.HttpSession;
import kth.journalbackendv2.core.entity.Account;
import kth.journalbackendv2.persistance.entity.AccountDB;

public interface ISessionService {
    public String createSession(Account account);
    public String findSessionByAccount(Account account);
    public Account findAccountBySession(String userSessionID);
    public boolean isValidSession(String userSessionID);
    public boolean isDoctor(String userSessionID);
    public boolean isOther(String userSessionID);
    public boolean isDoctorOrStaff(String userSessionID);

}
