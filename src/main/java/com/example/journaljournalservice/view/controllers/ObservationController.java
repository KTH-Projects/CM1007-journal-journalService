package kth.journalbackendv2.view.controllers;

import jakarta.servlet.http.HttpSession;
import kth.journalbackendv2.core.entity.Observation;
import kth.journalbackendv2.core.service.interfaces.IObservationService;
import kth.journalbackendv2.core.service.interfaces.ISessionService;
import kth.journalbackendv2.util.mapper.Mapper;
import kth.journalbackendv2.view.dto.ObservationDTO;
import kth.journalbackendv2.view.entity.ObservationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/observation")
public class ObservationController {

    private final IObservationService observationService;
    private final ISessionService sessionService;
    private final Mapper mapper;

    @Autowired
    public ObservationController(IObservationService observationService, ISessionService sessionService, Mapper mapper) {
        this.observationService = observationService;
        this.sessionService = sessionService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ObservationView> createObservation(@RequestBody ObservationDTO observation, @CookieValue("userSessionID") String userSessionID) {
        if(!sessionService.isDoctorOrStaff(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Observation savedObservation = observationService.create(observation);
        ObservationView savedObservationView = mapper.ObservationViewFromObservation(savedObservation);
        return new ResponseEntity<>(savedObservationView, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ObservationView>> getAllObservations(@CookieValue("userSessionID") String userSessionID) {
        if(!sessionService.isDoctorOrStaff(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        List<Observation> observations = observationService.getAllObservations();
        List<ObservationView> observationViews = new ArrayList<>();
        for (Observation observation : observations) {
            observationViews.add(mapper.ObservationViewFromObservation(observation));
        }
        return new ResponseEntity<>(observationViews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObservationView> getObservationById(@PathVariable String id, @CookieValue("userSessionID") String userSessionID) {
        if(!sessionService.isDoctorOrStaff(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Optional<Observation> observationOpt = observationService.getObservationById(id);
        if (observationOpt.isPresent()) {
            ObservationView observationView = mapper.ObservationViewFromObservation(observationOpt.get());
            return new ResponseEntity<>(observationView, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObservation(@PathVariable String id, @CookieValue("userSessionID") String userSessionID) {
        if(!sessionService.isDoctorOrStaff(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        observationService.deleteObservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

