package com.example.journaljournalservice.view.controllers;

import com.example.journaljournalservice.*;
import com.example.journaljournalservice.core.entity.*;
import com.example.journaljournalservice.core.service.interfaces.*;
import com.example.journaljournalservice.util.enums.Role;
import com.example.journaljournalservice.util.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class InitController {

    private final IAccountService accountService;
    private final IChatService chatService;
    private final IDiagnosisService diagnosisService;
    private final IEncounterService encounterService;
    private final IObservationService observationService;
    private final IPatientService patientService;
    private final ISessionService sessionService;
    private final IStaffService staffService;
    private final Mapper mapper;


    @Autowired
    public InitController(IAccountService accountService, IChatService chatService, IDiagnosisService diagnosisService, IEncounterService encounterService, IObservationService observationService, IPatientService patientService, ISessionService sessionService, IStaffService staffService, Mapper mapper) {
        this.accountService = accountService;
        this.chatService = chatService;
        this.diagnosisService = diagnosisService;
        this.encounterService = encounterService;
        this.observationService = observationService;
        this.patientService = patientService;
        this.sessionService = sessionService;
        this.staffService = staffService;
        this.mapper = mapper;
        setUp();
    }

    private void setUp() {
        if(!accountService.findAll().isEmpty()) return;
        ArrayList<Account> accounts= new ArrayList<>();
        ArrayList<Patient> patients = new ArrayList<>();
        ArrayList<Staff> staffs = new ArrayList<>();
        ArrayList<Encounter> encounters = new ArrayList<>();
        ArrayList<Observation> observations = new ArrayList<>();
        ArrayList<Diagnosis> diagnoses = new ArrayList<>();

        List<Role> roles = new ArrayList<>();
        roles.add(Role.doctor);
        roles.add(Role.other);
        roles.add(Role.patient);

        for(int i=0;i<3;i++){
            accounts.add(accountService.create(new Account("email"+i,"password" + i, "tim"+i), roles.get(i).toString()));
            patients.add(patientService.findByAccount_ID(accounts.get(i).getId()));

        }

        for(Staff staff: staffService.findAll()){
            staffs.add(staff);
        }


        encounters.add(encounterService.create(new Encounter(
                UUID.randomUUID().toString(),
                patients.get(2),
                staffs.get(0), // other staff did the observation
                LocalDateTime.now(),
                new ArrayList<>()
                )));

        encounters.add(encounterService.create(new Encounter(
                UUID.randomUUID().toString(),
                patients.get(2),
                staffs.get(1), // other staff did the observation
                LocalDateTime.now(),
                new ArrayList<>()
        )));

        observations.add(observationService.create(new Observation(
                    "id",
                    encounters.get(0),
                    "Tall"
                )));
        observations.add(observationService.create(new Observation(
                "id",
                encounters.get(0),
                "Rash on hands"
        )));
        /*
        diagnoses.add(diagnosisService.create(new Diagnosis(
                "id",
                patients.get(2),
                staffs.get(0), // Only doctor can give diagnosis
                "cancer",
                LocalDateTime.now())));

         */

    }
}
