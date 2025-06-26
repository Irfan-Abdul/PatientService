package com.patient_management.patient_service.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.annotation.processing.Generated;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientRequestDTO {

    @NotNull(message = "Please enter name")
    private String name;

    @Email(message = "Enter valid email")
    @NotNull(message = "Please enter mail id")
    private String email;
    @NotNull(message = "Please enter address")
    private String address;

    @NotNull(message = "Please enter Date of Birth")
    private String dateOfBirth;

    @NotNull(message = "Please enter the date")
    private String registeredDate;
}
