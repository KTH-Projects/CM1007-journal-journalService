package kth.journalbackendv2.view.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpSession;
import kth.journalbackendv2.core.entity.Account;
import kth.journalbackendv2.core.entity.Diagnosis;
import kth.journalbackendv2.core.entity.Encounter;
import kth.journalbackendv2.core.entity.Staff;
import kth.journalbackendv2.core.service.AccountService;
import kth.journalbackendv2.core.service.EncounterService;
import kth.journalbackendv2.core.service.SessionService;
import kth.journalbackendv2.core.service.StaffService;
import kth.journalbackendv2.core.service.interfaces.IFhirService;
import kth.journalbackendv2.util.mapper.Mapper;
import kth.journalbackendv2.view.dto.EncounterDTO;
import kth.journalbackendv2.view.entity.EncounterView;
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
    private final IFhirService fhirService;
    private final Mapper mapper;


    @Autowired
    public EncounterController(EncounterService encounterService, AccountService accountService, SessionService sessionService, StaffService staffService, IFhirService fhirService, Mapper mapper) {
        this.encounterService = encounterService;
        this.accountService = accountService;
        this.sessionService = sessionService;
        this.staffService = staffService;
        this.fhirService = fhirService;
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

        fhirService.createEncounter(createdEncounter);

        if(createdEncounter == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(mapper.EncounterViewFromEncounter(createdEncounter));
    }
}
