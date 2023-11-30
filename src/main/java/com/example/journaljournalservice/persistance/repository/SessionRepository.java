package kth.journalbackendv2.persistance.repository;



import kth.journalbackendv2.persistance.entity.AccountDB;
import kth.journalbackendv2.persistance.entity.SessionDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionDB, String> {
    SessionDB findByAccount(AccountDB account);
}
