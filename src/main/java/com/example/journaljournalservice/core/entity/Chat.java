package com.example.journaljournalservice.core.entity;

import com.example.journaljournalservice.view.entity.AccountView;

import java.util.UUID;

public class Chat {
    private String id;
    private AccountView toAccount;
    private AccountView fromAccount;
    private String message;

    public Chat(String id, AccountView toAccount, AccountView fromAccount, String message) {
        this.id = id;
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.message = message;
    }
    public Chat(AccountView toAccount, AccountView fromAccount, String message) {
        this(
            UUID.randomUUID().toString(),
            toAccount,
            fromAccount,
            message);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccountView getToAccount() {
        return toAccount;
    }

    public void setToAccount(AccountView toAccount) {
        this.toAccount = toAccount;
    }

    public AccountView getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(AccountView fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id='" + id + '\'' +
                ", toAccountID='" + toAccount + '\'' +
                ", fromAccountID='" + fromAccount + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
