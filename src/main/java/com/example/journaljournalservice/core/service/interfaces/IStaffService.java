package com.example.journaljournalservice.core.service.interfaces;

import com.example.journaljournalservice.core.entity.Staff;

import java.util.List;

public interface IStaffService {
    public List<Staff> findAll();
    public Staff create(Staff staff);

}
