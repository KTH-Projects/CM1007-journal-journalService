package com.example.journaljournalservice.view.controllers;


import com.example.journaljournalservice.core.entity.Encounter;
import com.example.journaljournalservice.core.entity.Patient;
import com.example.journaljournalservice.core.entity.Staff;
import com.example.journaljournalservice.core.service.AccountService;
import com.example.journaljournalservice.core.service.EncounterService;
import com.example.journaljournalservice.core.service.PatientService;
import com.example.journaljournalservice.core.service.StaffService;
import com.example.journaljournalservice.core.service.interfaces.IAccountService;
import com.example.journaljournalservice.core.service.interfaces.IEncounterService;
import com.example.journaljournalservice.core.service.interfaces.IPatientService;
import com.example.journaljournalservice.core.service.interfaces.IStaffService;
import com.example.journaljournalservice.view.dto.EncounterDTO;
import com.example.journaljournalservice.view.entity.EncounterView;
import com.example.journaljournalservice.view.entity.PatientView;
import com.example.journaljournalservice.view.entity.SignUpDTO;
import com.example.journaljournalservice.view.entity.StaffView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/journal")
public class JournalController {

    private IPatientService patientService;
    private IStaffService staffService;
    private IEncounterService encounterService;
    private IAccountService accountService;

    @Autowired
    public JournalController(PatientService patientService, StaffService staffService, EncounterService encounterService, AccountService accountService) {
        this.patientService = patientService;
        this.staffService = staffService;
        this.encounterService = encounterService;
        this.accountService = accountService;
    }


    /*TODO:
    * 1. Check if patient
    * 2. Check if staff
    * 3. check if doctor
    * 4. get patient ID
    * 5. get staff ID
    * */

    @PostMapping("/patient")
    public ResponseEntity<String> createPatient(@RequestBody SignUpDTO info){
        String id = patientService.create(info);
        if(id == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(id);
    }

    @GetMapping("/patient")
    public ResponseEntity<PatientView> findPatientById(@RequestParam String id){
        Patient p = patientService.findByIDEagle(id);
        if(p == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(PatientView.convert(p));

    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientView>> findAllPatient(){
        List<PatientView> patientViews = new ArrayList<>();
        List<Patient> patients =  patientService.findAll();
        if(patients == null){
            return ResponseEntity.badRequest().build();
        }

        for(Patient p : patients){
            patientViews.add(PatientView.convert(p));
        }
        return ResponseEntity.ok(patientViews);
    }


    @PostMapping("/staff")
    public ResponseEntity<String> createStaff(@RequestBody SignUpDTO info){
        String id = staffService.create(info);
        if(id == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(id);
    }

    @GetMapping("/staff")
    public ResponseEntity<StaffView> findStaffById(@RequestParam String id){
        Staff s = staffService.findById(id);
        if(s == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(StaffView.convert(s));
    }


    @PostMapping("/encounter")
    public ResponseEntity<EncounterView> create(@RequestBody EncounterDTO encounter, @CookieValue("userCookieID") String userSessionID) {

        if(!accountService.isDoctor(userSessionID)){
            return ResponseEntity.badRequest().build();
        }
        Encounter e = encounterService.create(encounter);

        return ResponseEntity.ok(EncounterView.convert(e));
    }






}
