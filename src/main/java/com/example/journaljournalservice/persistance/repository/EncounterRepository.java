package com.example.journaljournalservice.persistance.repository;

import com.example.journaljournalservice.persistance.entity.EncounterDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncounterRepository extends JpaRepository<EncounterDB,String> {

    public List<EncounterDB> findByPatient_Id(String id);
    public List<EncounterDB> findByStaff_Id(String id);


}
