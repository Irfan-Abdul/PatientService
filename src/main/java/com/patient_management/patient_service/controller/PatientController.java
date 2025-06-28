package com.patient_management.patient_service.controller;

import com.patient_management.patient_service.dto.PatientRequestDTO;
import com.patient_management.patient_service.dto.PatientResponseDTO;
import com.patient_management.patient_service.dto.validators.CreatePatientValidationGroup;
import com.patient_management.patient_service.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Patient" , description = "API for managing patients")
public class PatientController {

    private final PatientService patientService;
    PatientController(PatientService patientService){
        this.patientService=patientService;
    }

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
        return ResponseEntity.ok().body(this.patientService.getAllPatients());
    }

    @PostMapping
    @Operation(summary = "Create a patient")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO =this.patientService.createPatient(patientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@Validated(Default.class) @RequestBody PatientRequestDTO patientRequestDTO, @PathVariable UUID id){
        PatientResponseDTO patientResponseDTO =this.patientService.updatePatient(id,patientRequestDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(patientResponseDTO);
    }


    @DeleteMapping("/{id}")
    @ApiResponse(description = "Delete a patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        this.patientService.deletePatient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
