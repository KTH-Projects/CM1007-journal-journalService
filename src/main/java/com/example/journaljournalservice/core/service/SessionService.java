package kth.journalbackendv2.core.service;



import jakarta.servlet.http.HttpSession;
import kth.journalbackendv2.core.entity.Account;
import kth.journalbackendv2.core.service.interfaces.ISessionService;
import kth.journalbackendv2.persistance.entity.AccountDB;
import kth.journalbackendv2.persistance.entity.SessionDB;
import kth.journalbackendv2.persistance.repository.AccountRepository;
import kth.journalbackendv2.persistance.repository.SessionRepository;
import kth.journalbackendv2.util.enums.Role;
import kth.journalbackendv2.util.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService implements ISessionService {


    private final SessionRepository sessionRepository;
    private final AccountRepository accountRepository;
    private final Mapper mapper;

    @Autowired
    public SessionService(SessionRepository sessionRepository, AccountRepository accountRepository, Mapper mapper) {
        this.sessionRepository = sessionRepository;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public String createSession(Account account){
        AccountDB accountDB = accountRepository.findByEmail(account.getEmail());

        SessionDB returnSession = sessionRepository.save(new SessionDB(accountDB));

        accountDB.setSession(returnSession);
        accountRepository.save(accountDB);

        return returnSession.getId();
    }

    @Override
    public String findSessionByAccount(Account account){
        AccountDB accountDB = accountRepository.findByEmail(account.getEmail());
        SessionDB sessionDB = sessionRepository.findByAccount(accountDB);
        return sessionDB.getId();
    }

    @Override
    public Account findAccountBySession(String userSessionID) {
        AccountDB accountDB = accountRepository.findBySession_Id(userSessionID);
        if(accountDB == null) return null;
        return mapper.AccountFromAccountDB(accountDB);
    }

    @Override
    public boolean isValidSession(String userSessionID){
        String sessionID = userSessionID;
        if(sessionID == null) return false;
        AccountDB accountDB = accountRepository.findBySession_Id(sessionID);
        SessionDB sessionDB = sessionRepository.findByAccount(accountDB);
        return sessionDB.isValid();
    }

    @Override
    public boolean isDoctor(String userSessionID) {
        if(!isValidSession(userSessionID)) return false;
        String sessionID = userSessionID;
        if(sessionID == null) return false;
        AccountDB accountDB = accountRepository.findBySession_Id(sessionID);
        return accountDB.getStaff().getRole().equals(Role.doctor);
    }
    @Override
    public boolean isOther(String userSessionID) {
        if(!isValidSession(userSessionID)) return false;
        String sessionID = userSessionID;
        if(sessionID == null) return false;
        AccountDB accountDB = accountRepository.findBySession_Id(sessionID);
        return accountDB.getStaff().getRole().equals(Role.other);
    }
    @Override
    public boolean isDoctorOrStaff(String userSessionID) {
        if(!isValidSession(userSessionID)) return false;
        String sessionID = userSessionID;
        if(sessionID == null) return false;
        AccountDB accountDB = accountRepository.findBySession_Id(sessionID);
        return accountDB.getStaff().getRole().equals(Role.doctor) || accountDB.getStaff().getRole().equals(Role.other);
    }


}
