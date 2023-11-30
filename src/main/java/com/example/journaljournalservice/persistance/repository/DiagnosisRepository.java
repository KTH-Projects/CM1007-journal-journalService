package kth.journalbackendv2.persistance.repository;


import kth.journalbackendv2.persistance.entity.DiagnosisDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;


public interface DiagnosisRepository extends JpaRepository<DiagnosisDB,String> {
    @Override
    List<DiagnosisDB> findAll();

    List<DiagnosisDB> findByPatient_Id(String id);
    List<DiagnosisDB> findByStaff_Id(String id);


}
