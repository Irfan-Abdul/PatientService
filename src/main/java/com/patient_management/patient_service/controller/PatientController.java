package com.patient_management.patient_service.controller;

import com.patient_management.patient_service.dto.PatientRequestDTO;
import com.patient_management.patient_service.dto.PatientResponseDTO;
import com.patient_management.patient_service.dto.validators.CreatePatientValidationGroup;
import com.patient_management.patient_service.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;
    PatientController(PatientService patientService){
        this.patientService=patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
        return ResponseEntity.ok().body(this.patientService.getAllPatients());
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO =this.patientService.createPatient(patientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@Validated(Default.class) @RequestBody PatientRequestDTO patientRequestDTO, @PathVariable UUID id){
        PatientResponseDTO patientResponseDTO =this.patientService.updatePatient(id,patientRequestDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(patientResponseDTO);

    }
}
