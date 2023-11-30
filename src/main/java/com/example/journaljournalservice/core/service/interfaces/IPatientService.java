package kth.journalbackendv2.core.service.interfaces;

import kth.journalbackendv2.core.entity.Patient;

import java.util.List;

public interface IPatientService {
    public List<Patient> findAll();
    public Patient findByID(String id);
    public Patient findAllByID(String id);

    public Patient findAllByAccount_ID(String id);

    public Patient findByAccount_ID(String id);

}
