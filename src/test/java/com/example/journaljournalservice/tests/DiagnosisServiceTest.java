package com.example.journaljournalservice.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.journaljournalservice.core.entity.Diagnosis;
import com.example.journaljournalservice.core.service.DiagnosisService;
import com.example.journaljournalservice.persistance.repository.DiagnosisRepository;
import com.example.journaljournalservice.persistance.entity.DiagnosisDB;
import com.example.journaljournalservice.util.mapper.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisServiceTest {

    @Mock
    private DiagnosisRepository diagnosisRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private DiagnosisService diagnosisService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll_Success() {
        // Arrange
        List<DiagnosisDB> diagnosisDBList = new ArrayList<>();
        diagnosisDBList.add(new DiagnosisDB()); // Add mock DiagnosisDB objects
        diagnosisDBList.add(new DiagnosisDB());
        when(diagnosisRepository.findAll()).thenReturn(diagnosisDBList);
        when(mapper.DiagnosisFromDiagnosisDB(any())).thenAnswer(i -> new Diagnosis()); // Mock the conversion method

        // Act
        List<Diagnosis> result = diagnosisService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(diagnosisDBList.size(), result.size());
        verify(diagnosisRepository).findAll(); // Verify findAll was called
        verify(mapper, times(diagnosisDBList.size())).DiagnosisFromDiagnosisDB(any()); // Verify conversion method was called
    }

    @Test
    public void testFindAll_EmptyList() {
        // Arrange
        when(diagnosisRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Diagnosis> result = diagnosisService.findAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(diagnosisRepository).findAll(); // Verify findAll was called
    }

    // Note: This is a template for the create method, details will depend on its actual parameters and behavior
    @Test
    public void testCreate_Success() {
        // Arrange (Add the necessary mock setup for a successful creation scenario)

        // Act (Call the create method with the arranged parameters)

        // Assert (Verify the diagnosis was created and persisted correctly)
    }

    @Test
    public void testCreate_InvalidInput() {
        // Arrange (Set up conditions to simulate invalid input)

        // Act (Call the create method with the invalid input)

        // Assert (Verify the method handles the invalid input appropriately, e.g., by throwing an exception)
    }
}
