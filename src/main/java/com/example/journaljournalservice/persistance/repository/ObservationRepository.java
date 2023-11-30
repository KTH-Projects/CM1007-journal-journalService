package com.example.journaljournalservice.persistance.repository;

import com.example.journaljournalservice.persistance.entity.ObservationDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservationRepository extends JpaRepository<ObservationDB, String> {
    public List<ObservationDB> findAllByEncounter_Id(String id);
}
