package com.patient_management.patient_service.service;

import com.patient_management.patient_service.dto.PatientRequestDTO;
import com.patient_management.patient_service.dto.PatientResponseDTO;
import com.patient_management.patient_service.exceptions.EmailAlreadyExistsException;
import com.patient_management.patient_service.exceptions.UserNotFoundException;
import com.patient_management.patient_service.mapper.PatientMapper;
import com.patient_management.patient_service.mdoel.Patient;
import com.patient_management.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
 if(this.patientRepository.existsByEmail(patientRequestDTO.getEmail())){
     throw new EmailAlreadyExistsException("Email already exists "+ patientRequestDTO.getEmail());
 }

        Patient patient = this.patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        return PatientMapper.toDto(patient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO){
        Patient patient = this.patientRepository.findById(id)
                .orElseThrow(()->  new UserNotFoundException("User not found "+ id));

        if(this.patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)){
            throw new EmailAlreadyExistsException("Email already exists " + patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        return PatientMapper.toDto(this.patientRepository.save(patient));
    }
}
