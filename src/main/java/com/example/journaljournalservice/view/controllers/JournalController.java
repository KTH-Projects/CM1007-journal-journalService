package com.example.journaljournalservice.view.controllers;

import com.example.journaljournalservice.core.entity.Patient;
import com.example.journaljournalservice.core.service.PatientService;
import com.example.journaljournalservice.view.entity.PatientView;
import com.example.journaljournalservice.view.entity.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patient")
    public String createPatient(@RequestBody SignUpDTO info){
        String id = patientService.create(info);
        return id;
    }

    @GetMapping("/patient")
    public PatientView findPatientById(@RequestParam String id){
        Patient p = patientService.findByID(id);
        return PatientView.convert(p);
    }

    @GetMapping("/patients")
    public List<PatientView> findAllPatient(){
        List<PatientView> patientViews = new ArrayList<>();
        List<Patient> patients =  patientService.findAll();
        for(Patient p : patients){
            patientViews.add(PatientView.convert(p));
        }
        return patientViews;
    }



}
