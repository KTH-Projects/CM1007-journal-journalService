package com.example.journaljournalservice.core.service;

import com.example.journaljournalservice.core.entity.Staff;
import com.example.journaljournalservice.core.service.interfaces.IStaffService;
import com.example.journaljournalservice.persistance.entity.StaffDB;
import com.example.journaljournalservice.persistance.repository.StaffRepository;
import com.example.journaljournalservice.util.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService implements IStaffService {

    private final StaffRepository staffRepository;
    private final Mapper mapper;

    @Autowired
    public StaffService(StaffRepository staffRepository,  Mapper mapper) {
        this.staffRepository = staffRepository;
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
        //TODO: Implement
        return mapper.StaffFromStaffDB(new StaffDB());
    }

}
