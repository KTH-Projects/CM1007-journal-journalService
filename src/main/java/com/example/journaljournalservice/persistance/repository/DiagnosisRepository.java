package com.example.journaljournalservice.persistance.repository;


import com.example.journaljournalservice.persistance.entity.DiagnosisDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface DiagnosisRepository extends JpaRepository<DiagnosisDB,String> {
    List<DiagnosisDB> findByPatient_Id(String id);
    List<DiagnosisDB> findByStaff_Id(String id);


}
