package kth.journalbackendv2.core.service.interfaces;

import kth.journalbackendv2.core.entity.Account;
import kth.journalbackendv2.core.entity.Chat;

import java.util.List;

public interface IChatService {
    public List<Chat> findAll();
    public List<Chat> findByAccountID(String ID);
    public List<Chat> findByMyAccount_IDAndToAccount_ID(String toAccount_Id,String fromAccount_Id);
    public Chat createByEmail(Account fromAccount,Account toAccount,String message);
}
