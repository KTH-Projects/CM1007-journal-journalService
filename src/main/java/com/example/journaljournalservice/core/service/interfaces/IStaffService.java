package com.example.journaljournalservice.core.service.interfaces;

import com.example.journaljournalservice.core.entity.Staff;
import com.example.journaljournalservice.view.entity.SignUpDTO;

import java.util.List;

public interface IStaffService {
    public List<Staff> findAll();
    public String create(SignUpDTO info);
    public Staff findById(String id);

}
