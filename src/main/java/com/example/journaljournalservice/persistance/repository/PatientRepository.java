package com.example.journaljournalservice.persistance.repository;

import com.example.journaljournalservice.*;
import com.example.journaljournalservice.persistance.entity.PatientDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientDB, String> {

    public PatientDB findByAccount_Id(String id);
}
