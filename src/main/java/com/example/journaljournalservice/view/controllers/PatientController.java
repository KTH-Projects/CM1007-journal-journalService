package com.example.journaljournalservice.view.controllers;

import com.example.journaljournalservice.core.entity.Account;
import com.example.journaljournalservice.core.entity.Patient;
import com.example.journaljournalservice.core.service.interfaces.IAccountService;
import com.example.journaljournalservice.core.service.interfaces.IDiagnosisService;
import com.example.journaljournalservice.core.service.interfaces.IPatientService;
import com.example.journaljournalservice.core.service.interfaces.ISessionService;
import com.example.journaljournalservice.util.mapper.Mapper;
import com.example.journaljournalservice.view.entity.PatientView;
import jakarta.servlet.http.HttpServletRequest;
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
public class PatientController {

    private final IPatientService patientService;
    private final IAccountService accountService;
    private final ISessionService sessionService;
    private final IDiagnosisService diagnosisService;
    private final Mapper mapper;

   @Autowired
    public PatientController(IPatientService patientService, IAccountService accountService, ISessionService sessionService, IDiagnosisService diagnosisService,  Mapper mapper) {
        this.patientService = patientService;
        this.accountService = accountService;
        this.sessionService = sessionService;
        this.diagnosisService = diagnosisService;
        this.mapper = mapper;
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientView>> getAll(HttpSession session, HttpServletRequest request, @CookieValue("userSessionID") String userSessionID)
    {
        System.out.println(userSessionID);
        if(!sessionService.isDoctor(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        ArrayList<PatientView> patientViews = new ArrayList<>();
        for(Patient p : patientService.findAll()){
            patientViews.add(mapper.PatientViewFromPatient(p));
        }

        return ResponseEntity.ok(patientViews);
    }

    @GetMapping("/patient")
    public ResponseEntity<PatientView> getById(@RequestParam String id, HttpSession session,@CookieValue("userSessionID") String userSessionID)
    {
        if(!sessionService.isDoctor(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Patient p = patientService.findAllByID(id);


        if(p == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(PatientView.convert(p));
    }

    @GetMapping("/patient/details")
    public ResponseEntity<PatientView> getMyDetails(HttpSession session,@CookieValue("userSessionID") String userSessionID)
    {
        if(!sessionService.isValidSession(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        Account a = sessionService.findAccountBySession(userSessionID);

        Patient p = patientService.findAllByAccount_ID(a.getId());

        if(p == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(PatientView.convert(p));
    }

    @GetMapping("/patient/id")
    public ResponseEntity<String> getMyId(@CookieValue("userSessionID") String userSessionID)
    {
        if(!sessionService.isValidSession(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        Account a = sessionService.findAccountBySession(userSessionID);

        Patient p = patientService.findByAccount_ID(a.getId());

        if(p == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(p.getId());
    }



}
