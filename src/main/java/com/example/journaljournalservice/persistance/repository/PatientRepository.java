package kth.journalbackendv2.persistance.repository;

import kth.journalbackendv2.persistance.entity.PatientDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientDB, String> {

    public PatientDB findByAccount_Id(String id);
}
