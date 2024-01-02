package com.example.journaljournalservice.view.controllers;


import com.example.journaljournalservice.core.entity.*;
import com.example.journaljournalservice.core.service.interfaces.*;
import com.example.journaljournalservice.view.dto.EncounterDTO;
import com.example.journaljournalservice.view.dto.ObservationDTO;
import com.example.journaljournalservice.view.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('staff')")
    @GetMapping("/patient")
    public ResponseEntity<PatientView> findPatientById(@RequestParam String id){
        Patient p = patientService.findByIDEagle(id);
        if(p == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(PatientView.convert(p));

    }

    @PreAuthorize("hasRole('staff')")
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

    @PreAuthorize("hasRole('staff')")
    @GetMapping("/staff")
    public ResponseEntity<StaffView> findStaffById(@RequestParam String id){
        Staff s = staffService.findById(id);
        if(s == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(StaffView.convert(s));
    }

    @PreAuthorize("hasRole('doctor')")
    @PostMapping("/encounter")
    public ResponseEntity<EncounterView> createEncounter(@RequestBody EncounterDTO encounter) {
        Encounter e = encounterService.create(encounter);
        return ResponseEntity.ok(EncounterView.convert(e));
    }

    @PreAuthorize("hasRole('staff')")
    @PostMapping("/observation")
    public ResponseEntity<String> createObservation(@RequestBody ObservationDTO observation) {
        String id = observationService.create(observation);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    //TODO: Check if method works. Make sure header is ok.
    @PreAuthorize("hasRole('doctor')")
    @PostMapping("/diagnosis")
    public ResponseEntity<DiagnosisView> postDiagnosis(@RequestParam(value = "patientID") String patientID, @RequestParam(value = "diagnosis") String diagnosis, @RequestHeader("Authorization") String token){
        System.out.println("patientID: " + patientID + "\n" + " diagnosis: " + diagnosis);
        if(diagnosis == null || diagnosis.isEmpty() || patientID.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        String doctorId = accountService.getDoctorIdByToken(token);
        System.out.println("doctorId: " + doctorId);
        Diagnosis returnDiagnosis = diagnosisService.create(doctorId,patientID,diagnosis);
        System.out.println("diagnosis: " + returnDiagnosis);

        if(returnDiagnosis == null) return ResponseEntity.badRequest().build();
        return  ResponseEntity.ok(DiagnosisView.convert(returnDiagnosis));
    }

}
