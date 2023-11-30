package com.example.journaljournalservice.persistance.repository;



import com.example.journaljournalservice.*;
import com.example.journaljournalservice.persistance.entity.AccountDB;
import com.example.journaljournalservice.persistance.entity.SessionDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionDB, String> {
    SessionDB findByAccount(AccountDB account);
}
