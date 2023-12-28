package com.example.journaljournalservice.core.service;

import com.example.journaljournalservice.core.service.interfaces.IAccountService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AccountService implements IAccountService {

    private final WebClient webClient;

    public AccountService() {
        this.webClient = WebClient.create(System.getenv("ACCOUNT_SERVICE_URL"));
        System.out.println(System.getenv("ACCOUNT_SERVICE_URL"));
    }

    @Override
    public boolean isPatient(String token) {
        try {
            String staffID = this.webClient.get()
                    .uri("/security/patient")
                    .cookie("userCookieID", token)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return staffID != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isDoctor(String token) {
        try {
            String staffID = this.webClient.get()
                    .uri("/security/doctor")
                    .cookie("userCookieID", token)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token) // Include the token in the Authorization header
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return staffID != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getDoctorId(String token) {
        try {
            String doctorId = this.webClient.get()
                    .uri("/security/doctor")
                    .cookie("userCookieID", token)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            if(doctorId == null  || doctorId.isEmpty()) return null;

            return doctorId;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getDoctorIdByToken(String token) {
        try {
            String doctorId = this.webClient.get()
                    .uri("/keycloak/doctorId")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            if(doctorId == null  || doctorId.isEmpty()) return null;

            return doctorId;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean isStaff(String token) {
        try {
            String staffID = this.webClient.get()
                    .uri("/security/staff")
                    .cookie("userCookieID", token)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return staffID != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getLoggedInStaff(String token) {
        return null;
    }

    @Override
    public String getLoggedInPatient(String token) {
        return null;
    }
}
