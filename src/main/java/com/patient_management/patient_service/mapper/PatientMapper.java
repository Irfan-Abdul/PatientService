package com.patient_management.patient_service.mapper;

import com.patient_management.patient_service.dto.PatientRequestDTO;
import com.patient_management.patient_service.dto.PatientResponseDTO;
import com.patient_management.patient_service.mdoel.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDto(Patient patient){
        return PatientResponseDTO.builder().id(patient.getId().toString())
                .address(patient.getAddress())
                .name(patient.getName())
                .email(patient.getEmail())
                .dateOfBirth(patient.getDateOfBirth())
                .build();
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO){
        return Patient.builder().name(patientRequestDTO.getName())
                .email(patientRequestDTO.getEmail())
                .address(patientRequestDTO.getAddress())
                .dateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()))
                .registeredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()))
                .build();
    }
}
