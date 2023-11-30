package com.example.journaljournalservice.view.controllers;

import com.example.journaljournalservice.core.entity.Account;
import com.example.journaljournalservice.core.entity.Encounter;
import com.example.journaljournalservice.core.entity.Staff;
import com.example.journaljournalservice.core.service.AccountService;
import com.example.journaljournalservice.core.service.EncounterService;
import com.example.journaljournalservice.core.service.SessionService;
import com.example.journaljournalservice.core.service.StaffService;
import com.example.journaljournalservice.util.mapper.Mapper;
import com.example.journaljournalservice.view.dto.EncounterDTO;
import com.example.journaljournalservice.view.entity.EncounterView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpSession;
import com.example.journaljournalservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class EncounterController {

    private final EncounterService encounterService;
    private final AccountService accountService;
    private final SessionService sessionService;
    private final StaffService staffService;
    private final Mapper mapper;


    @Autowired
    public EncounterController(EncounterService encounterService, AccountService accountService, SessionService sessionService, StaffService staffService, Mapper mapper) {
        this.encounterService = encounterService;
        this.accountService = accountService;
        this.sessionService = sessionService;
        this.staffService = staffService;
        this.mapper = mapper;
    }

    @GetMapping("/encounter")
    public ResponseEntity<List<EncounterView>> getAll(@CookieValue("userSessionID") String userSessionID)
    {
        if(!sessionService.isDoctorOrStaff(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        ArrayList<EncounterView> encounterViews = new ArrayList<>();
        for(Encounter encounter : encounterService.findAll() ){
            encounterViews.add(mapper.EncounterViewFromEncounter(encounter));
        }
        return ResponseEntity.ok(encounterViews);
    }

    @PostMapping("/encounter")
    @ResponseBody
    public ResponseEntity<EncounterView> create(@RequestBody EncounterDTO encounter, @CookieValue("userSessionID") String userSessionID) {
        if(!sessionService.isDoctorOrStaff(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        Account account = sessionService.findAccountBySession(userSessionID);
        Staff staff = staffService.findByAccountID(account);
        encounter.setStaffID(staff.getId());
        Encounter createdEncounter = encounterService.create(encounter);


        if(createdEncounter == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(mapper.EncounterViewFromEncounter(createdEncounter));
    }
}
