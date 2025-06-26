package com.patient_management.patient_service.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponseDTO {
    private String id;
    private String name;
    private String email;
    private String address;
    private LocalDate dateOfBirth;


}
