package com.example.journaljournalservice.persistance.repository;

import com.example.journaljournalservice.persistance.entity.StaffDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffDB,String> {

}
