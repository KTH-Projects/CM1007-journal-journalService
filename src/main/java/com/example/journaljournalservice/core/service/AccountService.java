package kth.journalbackendv2.core.service;

import kth.journalbackendv2.core.entity.Account;
import kth.journalbackendv2.core.service.interfaces.IAccountService;
import kth.journalbackendv2.persistance.entity.AccountDB;
import kth.journalbackendv2.persistance.entity.PatientDB;
import kth.journalbackendv2.persistance.entity.StaffDB;
import kth.journalbackendv2.persistance.repository.AccountRepository;
import kth.journalbackendv2.persistance.repository.PatientRepository;
import kth.journalbackendv2.persistance.repository.StaffRepository;
import kth.journalbackendv2.util.enums.Role;
import kth.journalbackendv2.util.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;
    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;
    private final Mapper mapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, PatientRepository patientRepository, StaffRepository staffRepository, Mapper mapper) {
        this.accountRepository = accountRepository;
        this.patientRepository = patientRepository;
        this.staffRepository = staffRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        for(AccountDB a :  accountRepository.findAll()){
            accounts.add(mapper.AccountFromAccountDB(a));
        }

        return accounts;
    }

    @Override
    public List<Account> findAllPatient() {
        List<Account> accounts = new ArrayList<>();
        for(AccountDB a :  accountRepository.findAllByPatientIsNotNull()){
            accounts.add(mapper.AccountFromAccountDB(a));
        }

        return accounts;
    }

    @Override
    public Account findByID(String ID){
        Optional<AccountDB> accountDB = accountRepository.findById(ID);
        if(accountDB.isEmpty()) return null;
        return mapper.AccountFromAccountDB(accountDB.get());
    }

    public Account findByEmail(String email){
        AccountDB accountDB = accountRepository.findByEmail(email);
        return mapper.AccountFromAccountDB(accountDB);
    }

    public Account create(Account account, String role){
        if(accountRepository.findByEmail(account.getEmail()) != null) return null;

        AccountDB createdAccount = new AccountDB(account.getEmail(), account.getPassword(), account.getName());
        createdAccount = accountRepository.save(createdAccount);
        switch (role) {
            case "doctor" : {

                StaffDB staffDB = new StaffDB(createdAccount,Role.doctor);
                staffDB = staffRepository.save(staffDB);
                createdAccount.setStaff(staffDB);
                break;
            }
            case "other" : {
                StaffDB staffDB = staffRepository.save(new StaffDB(createdAccount,Role.other));
                createdAccount.setStaff(staffDB);
                break;
            }
            case "patient" : {
                PatientDB patientDB = patientRepository.save(new PatientDB(createdAccount));
                createdAccount.setPatient(patientDB);
                break;
            }
            case "admin" : {
                break;
            }
        }
        createdAccount = accountRepository.save(createdAccount);


        return mapper.AccountFromAccountDB(createdAccount);
    }

    public Account findBySessionID(String ID){
        AccountDB account = accountRepository.findBySession_Id(ID);
        if(account==null) return null;

        return mapper.AccountFromAccountDB(account);
    }

}
