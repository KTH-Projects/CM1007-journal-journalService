package com.example.journaljournalservice.view.controllers;

import com.example.journaljournalservice.core.entity.Account;
import com.example.journaljournalservice.core.service.interfaces.IAccountService;
import com.example.journaljournalservice.core.service.interfaces.IChatService;
import com.example.journaljournalservice.core.service.interfaces.ISessionService;
import com.example.journaljournalservice.util.mapper.Mapper;
import com.example.journaljournalservice.view.entity.AccountView;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.journaljournalservice.core.entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/account")
public class AccountController {


    private final IAccountService accountService;
    private final ISessionService sessionService;
    private final IChatService chatService;
    private final Mapper mapper;

    @Autowired
    public AccountController(IAccountService accountService, ISessionService sessionService, IChatService chatService, Mapper mapper) {
        this.accountService = accountService;
        this.sessionService = sessionService;
        this.chatService = chatService;
        this.mapper = mapper;
    }

    @GetMapping("")
    public ResponseEntity<List<AccountView>> getAll(HttpSession session, @CookieValue("userSessionID") String userSessionID)
    {
        if(!sessionService.isValidSession(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        ArrayList<AccountView> accountViews = new ArrayList<>();
        for(Account a : accountService.findAll() ){
            accountViews.add(mapper.AccountViewFromAcount(a));
        }
        return ResponseEntity.ok(accountViews);

    }

    @GetMapping("/{email}")
    public ResponseEntity<AccountView> getByEmail(@PathVariable String email, HttpSession session,@CookieValue("userSessionID") String userSessionID) {
        if(!sessionService.isDoctorOrStaff(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Account account = accountService.findByEmail(email);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        AccountView accountView = mapper.AccountViewFromAcount(account);
        return ResponseEntity.ok(accountView);
    }

    @GetMapping("/chats")
    public ResponseEntity<List<Chat>> getAllChats(HttpSession session,@CookieValue("userSessionID") String userSessionID){
        if(!sessionService.isValidSession(userSessionID)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.ok(chatService.findAll());
    }

    @GetMapping("/chat")
    public ResponseEntity<List<Chat>> getAllAccountChats(HttpSession session,@CookieValue("userSessionID") String userSessionID){
        if(!sessionService.isValidSession(userSessionID)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Account fromAccount = sessionService.findAccountBySession(userSessionID);
        return ResponseEntity.ok(chatService.findByAccountID(fromAccount.getId()));
    }

    @GetMapping("/chatTo")
    public ResponseEntity<List<Chat>> getChatConversation(@RequestParam String toEmail,@CookieValue("userSessionID") String userSessionID){
        if(!sessionService.isValidSession(userSessionID)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Account fromAccount = sessionService.findAccountBySession(userSessionID);
        Account account = accountService.findByEmail(toEmail);
        if(account==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(chatService.findByMyAccount_IDAndToAccount_ID(fromAccount.getId(),account.getId()));
    }

    @PostMapping("/chat")
    public ResponseEntity<Chat> sendChat(@RequestParam String toEmail,@RequestParam String message, HttpSession session,@CookieValue("userSessionID") String userSessionID){
        if(!sessionService.isValidSession(userSessionID)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Account account = sessionService.findAccountBySession(userSessionID);
        if(account == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        String fromEmail = account.getEmail();
        Account toAccount = accountService.findByEmail(toEmail);
        Account fromAccount = accountService.findByEmail(fromEmail);
        return ResponseEntity.ok(chatService.createByEmail(toAccount,fromAccount,message));
    }


    @PostMapping("/signup")
    public ResponseEntity<AccountView> signUp(@RequestBody Account account, @RequestParam String role, HttpSession session) {
        // Everyone can create account
        // Defaults to patient
        try {
            Account acc = accountService.create(account, role);
            String sessionToken = sessionService.createSession(acc);
            session.setAttribute("session", sessionToken); // login the user



            return new ResponseEntity<>(AccountView.convert(acc), HttpStatus.CREATED);
        }
        catch (EntityExistsException e)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/patient")
    public ResponseEntity<List<AccountView>> getAllPatient(@CookieValue("userSessionID") String userSessionID)
    {
        if(!sessionService.isDoctorOrStaff(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        ArrayList<AccountView> accountViews = new ArrayList<>();
        for(Account a : accountService.findAllPatient() ){
            accountViews.add(mapper.AccountViewFromAcount(a));
        }
        return ResponseEntity.ok(accountViews);
    }


    @PostMapping(path = "/login")
    public ResponseEntity<AccountView> login(@RequestBody Account accountLogin, HttpServletResponse response, HttpServletRequest request) {
        Account accountCore = accountService.findByEmail(accountLogin.getEmail());
        if (accountCore == null || ! accountLogin.getPassword().equals(accountCore.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String sessionToken = sessionService.createSession(accountCore);
        Cookie userIdCookie = new Cookie("userSessionID", sessionToken);
        userIdCookie.setPath("/");
        response.addCookie(userIdCookie);
        AccountView accountView = mapper.AccountViewFromAcount(accountCore);

        return ResponseEntity.ok(accountView);
    }
}
