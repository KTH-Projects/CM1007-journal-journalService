package kth.journalbackendv2.persistance.repository;

import kth.journalbackendv2.persistance.entity.AccountDB;
import kth.journalbackendv2.persistance.entity.ChatDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountDB, String> {

    @Override
    List<AccountDB> findAll();

    AccountDB findByEmail(String email);

    AccountDB findBySession_Id(String session);

    List<AccountDB> findAllByPatientIsNotNull();

}
