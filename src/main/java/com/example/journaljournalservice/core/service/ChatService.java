package com.example.journaljournalservice.core.service;

import com.example.journaljournalservice.core.entity.Account;
import com.example.journaljournalservice.core.entity.Chat;
import com.example.journaljournalservice.core.service.interfaces.IChatService;
import com.example.journaljournalservice.persistance.entity.AccountDB;
import com.example.journaljournalservice.persistance.entity.ChatDB;
import com.example.journaljournalservice.persistance.repository.ChatRepository;
import com.example.journaljournalservice.view.entity.AccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService implements IChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
    @Override
    public List<Chat> findAll() {
        List<Chat> chats = new ArrayList<>();
        for(ChatDB c :  chatRepository.findAll()){
            chats.add(new Chat(
                        AccountView.convert(Account.convertFromDB(c.getToAccount())),
                        AccountView.convert(Account.convertFromDB(c.getFromAccount())),
                        c.getMessage()));
        }
        return chats;
    }

    @Override
    public List<Chat> findByAccountID(String ID) {
        List<Chat> chats = new ArrayList<>();
        for(ChatDB c :  chatRepository.findByFromAccount_IDOrToAccount_ID(ID)){
            chats.add(new Chat(
                    AccountView.convert(Account.convertFromDB(c.getToAccount())),
                    AccountView.convert(Account.convertFromDB(c.getFromAccount())),
                    c.getMessage()));
        }
        return chats;
    }

    @Override
    public List<Chat> findByMyAccount_IDAndToAccount_ID(String toAccount_Id, String fromAccount_Id) {
        List<Chat> chats = new ArrayList<>();
        for(ChatDB c :  chatRepository.findChatDBByToAccount_IdAndFromAccount_IdOrToAccount_IdAndFromAccount_Id(toAccount_Id,fromAccount_Id,fromAccount_Id,toAccount_Id)){
            chats.add(new Chat(
                    AccountView.convert(Account.convertFromDB(c.getToAccount())),
                    AccountView.convert(Account.convertFromDB(c.getFromAccount())),
                    c.getMessage()));
        }
        return chats;
    }

    @Override
    public Chat createByEmail(Account fromAccount, Account toAccount, String message) {

        chatRepository.save(new ChatDB(AccountDB.convertFromCore(fromAccount),AccountDB.convertFromCore(toAccount),message));
        return new Chat(AccountView.convert( fromAccount), AccountView.convert( toAccount ),message);
    }
}
