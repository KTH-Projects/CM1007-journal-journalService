package com.example.journaljournalservice.persistance.repository;

import com.example.journaljournalservice.*;
import com.example.journaljournalservice.persistance.entity.StaffDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<StaffDB,String> {

    @Override
    List<StaffDB> findAll();
    StaffDB findByAccount_Id(String id);
}
