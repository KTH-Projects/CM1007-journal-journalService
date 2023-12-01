package com.example.journaljournalservice.core.service;

import com.example.journaljournalservice.core.entity.Staff;
import com.example.journaljournalservice.core.service.interfaces.IStaffService;
import com.example.journaljournalservice.persistance.entity.PatientDB;
import com.example.journaljournalservice.persistance.entity.StaffDB;
import com.example.journaljournalservice.persistance.repository.StaffRepository;
import com.example.journaljournalservice.util.enums.RoleConverter;
import com.example.journaljournalservice.util.mapper.Mapper;
import com.example.journaljournalservice.view.entity.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public String create(SignUpDTO info) {
        StaffDB s = new StaffDB();

        s.setSex(info.getSex());
        s.setName(info.getName());
        s.setAge(info.getAge());

        RoleConverter converter = new RoleConverter();
        s.setRole(converter.convertToEntityAttribute(info.getRole()));

        StaffDB sDB = staffRepository.save(s);

        return sDB.getId();
    }

    @Override
    public Staff findById(String id) {
        Optional<StaffDB> sDB = staffRepository.findById(id);
        if(sDB.isEmpty()) return null;

        Staff s = new Staff();
        s.setId(sDB.get().getId());
        s.setAge(sDB.get().getAge());
        s.setName(sDB.get().getName());
        s.setSex(sDB.get().getSex());
        s.setRole(sDB.get().getRole());

        return s;
    }

}
