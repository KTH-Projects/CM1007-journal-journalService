package com.example.journaljournalservice.view.controllers;

import com.example.journaljournalservice.core.entity.Account;
import com.example.journaljournalservice.core.entity.Staff;
import com.example.journaljournalservice.core.service.interfaces.ISessionService;
import com.example.journaljournalservice.core.service.interfaces.IStaffService;
import com.example.journaljournalservice.util.mapper.Mapper;
import jakarta.servlet.http.HttpSession;
import com.example.journaljournalservice.view.entity.StaffView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/staff")
public class StaffController {
    private final IStaffService staffService;
    private final ISessionService sessionService;
    private final Mapper mapper;

    @Autowired
    public StaffController(IStaffService staffService, ISessionService sessionService, Mapper mapper) {
        this.staffService = staffService;
        this.sessionService = sessionService;
        this.mapper = mapper;
    }


    @GetMapping("")
    public ResponseEntity<List<StaffView>> getAllStaff(@CookieValue("userSessionID") String userSessionID) {
        if(!sessionService.isDoctorOrStaff(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        List<Staff> staffList = staffService.findAll();
        List<StaffView> staffViews = new ArrayList<>();
        for (Staff staff : staffList) {
            staffViews.add(mapper.StaffViewFromStaff(staff));
        }
        return ResponseEntity.ok(staffViews);

    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<StaffView> create(@RequestBody StaffView staffView, HttpSession session,@CookieValue("userSessionID") String userSessionID) {
        if(!sessionService.isDoctor(userSessionID)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        try {

            Account account = mapper.AccountFromAccountView(staffView.getAccountView());
            Staff staff = mapper.StaffFromStaffView(staffView);
            staff.setAccount(account);

            Staff createdStaff = staffService.create(staff);

            StaffView createdStaffView = mapper.StaffViewFromStaff(createdStaff);

            return new ResponseEntity<>(createdStaffView, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
