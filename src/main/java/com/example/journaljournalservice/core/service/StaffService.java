package kth.journalbackendv2.core.service;

import kth.journalbackendv2.core.entity.Account;
import kth.journalbackendv2.core.entity.Staff;
import kth.journalbackendv2.core.service.interfaces.IStaffService;
import kth.journalbackendv2.persistance.entity.AccountDB;
import kth.journalbackendv2.persistance.entity.StaffDB;
import kth.journalbackendv2.persistance.repository.AccountRepository;
import kth.journalbackendv2.persistance.repository.StaffRepository;
import kth.journalbackendv2.util.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService implements IStaffService {

    private final StaffRepository staffRepository;
    private final AccountRepository accountRepository;
    private final Mapper mapper;

    @Autowired
    public StaffService(StaffRepository staffRepository, AccountRepository accountRepository, Mapper mapper) {
        this.staffRepository = staffRepository;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Staff> findAll() {
        ArrayList<Staff> staffs = new ArrayList<>();
        for(StaffDB staffDB : staffRepository.findAll()){
            staffs.add(mapper.StaffFromStaffDB(staffDB));
        }
        return staffs;
    }



    @Override
    public Staff create(Staff staff) {
        AccountDB accountDB = accountRepository.findByEmail(staff.getAccount().getEmail());
        if(accountDB.getStaff() != null) return null;

        StaffDB staffDB = new StaffDB(accountDB, staff.getRole());

        accountDB.setStaff(staffDB);

        accountDB = accountRepository.save(accountDB);


        return mapper.StaffFromStaffDB(accountDB.getStaff());
    }

    @Override
    public Staff findByAccountID(Account account) {
        return mapper.StaffFromStaffDB(staffRepository.findByAccount_Id(account.getId()));
    }
}
