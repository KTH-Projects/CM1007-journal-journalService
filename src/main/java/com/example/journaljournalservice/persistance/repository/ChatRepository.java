package kth.journalbackendv2.persistance.repository;

import kth.journalbackendv2.persistance.entity.AccountDB;
import kth.journalbackendv2.persistance.entity.ChatDB;
import kth.journalbackendv2.persistance.entity.PatientDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatDB,String> {
    @Query("SELECT c FROM ChatDB c where :id = c.toAccount.ID OR  :id = c.fromAccount.ID")
    List<ChatDB> findByFromAccount_IDOrToAccount_ID(@Param("id") String id);

    //@Query("SELECT a FROM AccountDB a where :id = a.toAccount.ID OR  :id = c.fromAccount.ID")
    List<ChatDB> findChatDBByToAccount_IdAndFromAccount_IdOrToAccount_IdAndFromAccount_Id(
            String toAccountID1, String fromAccountID1, String toAccountID2, String fromAccountID2);

    //List<ChatDB> findByToAccount_ID(String id);
    //List<ChatDB> findByFromAccount_ID(String id);
}


