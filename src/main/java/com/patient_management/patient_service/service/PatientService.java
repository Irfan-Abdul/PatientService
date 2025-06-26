package com.patient_management.patient_service.service;

import com.patient_management.patient_service.dto.PatientRequestDTO;
import com.patient_management.patient_service.dto.PatientResponseDTO;
import com.patient_management.patient_service.mapper.PatientMapper;
import com.patient_management.patient_service.mdoel.Patient;
import com.patient_management.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    PatientService(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }

    public List<PatientResponseDTO> getAllPatients(){
        return this.patientRepository.findAll().stream().map(PatientMapper::toDto).toList();
    }


    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        Patient patient = this.patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        return PatientMapper.toDto(patient);


    }
}
