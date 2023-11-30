package kth.journalbackendv2.persistance.repository;

import kth.journalbackendv2.persistance.entity.EncounterDB;
import kth.journalbackendv2.persistance.entity.ObservationDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EncounterRepository extends JpaRepository<EncounterDB,String> {

    public List<EncounterDB> findByPatient_Id(String id);
    public List<EncounterDB> findByStaff_Id(String id);


}
