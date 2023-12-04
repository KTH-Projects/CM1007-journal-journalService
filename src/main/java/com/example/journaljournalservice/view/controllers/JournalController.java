package com.example.journaljournalservice.view.controllers;


import com.example.journaljournalservice.core.entity.*;
import com.example.journaljournalservice.core.service.AccountService;
import com.example.journaljournalservice.core.service.EncounterService;
import com.example.journaljournalservice.core.service.PatientService;
import com.example.journaljournalservice.core.service.StaffService;
import com.example.journaljournalservice.core.service.interfaces.*;
import com.example.journaljournalservice.view.dto.EncounterDTO;
import com.example.journaljournalservice.view.dto.ObservationDTO;
import com.example.journaljournalservice.view.entity.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private IObservationService observationService;
    private IDiagnosisService diagnosisService;

    @Autowired
    public JournalController(IPatientService patientService, IStaffService staffService, IEncounterService encounterService, IAccountService accountService, IObservationService observationService, IDiagnosisService diagnosisService) {
        this.patientService = patientService;
        this.staffService = staffService;
        this.encounterService = encounterService;
        this.accountService = accountService;
        this.observationService = observationService;
        this.diagnosisService = diagnosisService;
    }

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
    public ResponseEntity<EncounterView> createEncounter(@RequestBody EncounterDTO encounter, @CookieValue("userCookieID") String userSessionID) {

        if(!accountService.isDoctor(userSessionID)){
            return ResponseEntity.badRequest().build();
        }
        Encounter e = encounterService.create(encounter);

        return ResponseEntity.ok(EncounterView.convert(e));
    }

    @PostMapping("/observation")
    public ResponseEntity<String> createObservation(@RequestBody ObservationDTO observation, @CookieValue("userCookieID") String userSessionID) {
        if(!(accountService.isDoctor(userSessionID) || accountService.isStaff(userSessionID))) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        String id = observationService.create(observation);

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/diagnosis")
    public ResponseEntity<DiagnosisView> postDiagnosis(
            @RequestParam String patientID,
            @RequestParam String diagnosis ,
            @CookieValue("userCookieID") String userSessionID){

        if(diagnosis == null || diagnosis.isEmpty() || patientID.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        if(!accountService.isDoctor(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        String doctorId = accountService.getDoctorId(userSessionID);
        Diagnosis returnDiagnosis = diagnosisService.create(doctorId,patientID,diagnosis);

        if(returnDiagnosis == null) return ResponseEntity.badRequest().build();
        return  ResponseEntity.ok(DiagnosisView.convert(returnDiagnosis));
    }






}
