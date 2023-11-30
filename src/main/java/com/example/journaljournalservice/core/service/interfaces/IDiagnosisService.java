package kth.journalbackendv2.core.service.interfaces;

import kth.journalbackendv2.core.entity.Diagnosis;
import kth.journalbackendv2.core.entity.Patient;
import kth.journalbackendv2.core.entity.Staff;

import java.util.Collection;
import java.util.List;

public interface IDiagnosisService {
    public List<Diagnosis> findAll();
    public Diagnosis create(String doctorID, String patientID, String diagnosis);
    public List<Diagnosis> findByPatientID(String id);
    public List<Diagnosis> findByStaffID(String id);
}
