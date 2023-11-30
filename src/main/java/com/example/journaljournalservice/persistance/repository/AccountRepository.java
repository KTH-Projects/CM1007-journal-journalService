package com.example.journaljournalservice.persistance.repository;

import com.example.journaljournalservice.*;
import com.example.journaljournalservice.persistance.entity.AccountDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountDB, String> {

    @Override
    List<AccountDB> findAll();

    AccountDB findByEmail(String email);

    AccountDB findBySession_Id(String session);

    List<AccountDB> findAllByPatientIsNotNull();

}
