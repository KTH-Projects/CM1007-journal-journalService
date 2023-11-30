package kth.journalbackendv2.core.service.interfaces;

import kth.journalbackendv2.core.entity.Account;
import kth.journalbackendv2.core.entity.Staff;

import java.util.List;

public interface IStaffService {
    public List<Staff> findAll();
    public Staff create(Staff staff);

    public Staff findByAccountID(Account account);

}
