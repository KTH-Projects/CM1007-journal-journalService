package com.example.journaljournalservice.core.service.interfaces;

import com.example.journaljournalservice.core.entity.Account;
import com.example.journaljournalservice.core.entity.Staff;

import java.util.List;

public interface IStaffService {
    public List<Staff> findAll();
    public Staff create(Staff staff);

    public Staff findByAccountID(Account account);

}
