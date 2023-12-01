package com.example.journaljournalservice.view.controllers;

import com.example.journaljournalservice.core.service.PatientService;
import com.example.journaljournalservice.view.entity.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patient")
    public String createPatient(@RequestBody SignUpDTO info){
        String id = patientService.create(info);
        System.out.println(id);
        return id;
    }



}
