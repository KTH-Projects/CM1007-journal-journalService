package com.example.journaljournalservice.view.controllers;

import com.example.journaljournalservice.core.entity.Account;
import com.example.journaljournalservice.core.entity.Diagnosis;
import com.example.journaljournalservice.core.entity.Staff;
import com.example.journaljournalservice.core.service.interfaces.IAccountService;
import com.example.journaljournalservice.core.service.interfaces.IDiagnosisService;
import com.example.journaljournalservice.core.service.interfaces.ISessionService;
import com.example.journaljournalservice.core.service.interfaces.IStaffService;
import com.example.journaljournalservice.util.mapper.Mapper;
import com.example.journaljournalservice.view.entity.DiagnosisView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpSession;
import com.example.journaljournalservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class DiagnosisController {

    private final IDiagnosisService diagnosisService;
    private final ISessionService sessionService;
    private final IAccountService accountService;
    private final IStaffService staffService;
    private final Mapper mapper;

    @Autowired
    public DiagnosisController(IDiagnosisService diagnosisService, ISessionService sessionService, IAccountService accountService, IStaffService staffService , Mapper mapper) {
        this.diagnosisService = diagnosisService;
        this.sessionService = sessionService;
        this.accountService = accountService;
        this.staffService = staffService;
        this.mapper = mapper;
    }

    @GetMapping("/diagnosis")
    public ResponseEntity<List<DiagnosisView>> getAll(HttpSession session,@CookieValue("userSessionID") String userSessionID) {
        if(!sessionService.isDoctor(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        ArrayList<DiagnosisView> diagnoses = new ArrayList<>();
        for(Diagnosis diagnosis :diagnosisService.findAll() ){
            diagnoses.add(mapper.DiagnosisViewFromDiagnosis(diagnosis));
        }
        return ResponseEntity.ok(diagnoses);
    }

    @PostMapping("/diagnosis")
    @ResponseBody
    public ResponseEntity<DiagnosisView> postDiagnosis(@RequestParam String patientID,@RequestParam String diagnosis ,
                                                       HttpSession session,@CookieValue("userSessionID") String userSessionID){
        if(diagnosis == null || diagnosis.isEmpty() || patientID.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        if(!sessionService.isDoctor(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        Account doctor = sessionService.findAccountBySession(userSessionID);
        Staff staff = staffService.findByAccountID(doctor);
        Diagnosis returnDiagnosis = diagnosisService.create(staff.getId(),patientID,diagnosis );
        if(returnDiagnosis == null) return ResponseEntity.badRequest().build();
        return  ResponseEntity.ok(mapper.DiagnosisViewFromDiagnosis(returnDiagnosis));
    }

}
