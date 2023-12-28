package com.example.journaljournalservice.core.service.interfaces;

public interface IAccountService {
    public boolean isPatient(String token);
    public boolean isDoctor(String token);
    public String getDoctorId(String token);
    public String getDoctorIdByToken(String token);
    public boolean isStaff(String token);
    public String getLoggedInStaff(String token);
    public String getLoggedInPatient(String token);
}
