package com.example.journaljournalservice.core.service;

import com.example.journaljournalservice.core.service.interfaces.IAccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AccountService implements IAccountService {

    private final WebClient webClient;

    public AccountService() {
        this.webClient = WebClient.create("http://localhost:8081");;
    }

    @Override
    public boolean isPatient(String token) {
        return false;
    }

    @Override
    public boolean isDoctor(String token) {
        try {
            String role = this.webClient.get()
                    .uri("/api/") // Replace with actual endpoint
                    .header("Authorization", "Bearer " + token) // Assuming token is sent as a Bearer token
                    .retrieve()
                    .bodyToMono(String.class) // Assuming the response is the role as a String
                    .block(); // Blocks to wait for the response (consider using async approach in real scenarios)

            return "doctor".equalsIgnoreCase(role); // Assuming the role returned is a string like "doctor"
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isStaff(String token) {
        return false;
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
