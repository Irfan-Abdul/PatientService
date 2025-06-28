package com.patient_management.patient_service.dto;


import com.patient_management.patient_service.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientRequestDTO {

    @NotBlank(message = "Please enter name")
    private String name;

    @Email(message = "Enter valid email")
    @NotBlank(message = "Please enter mail id")
    private String email;
    @NotBlank(message = "Please enter address")
    private String address;

    @NotBlank(message = "Please enter Date of Birth")
    private String dateOfBirth;

    @NotNull(groups = CreatePatientValidationGroup.class, message = "Please enter the date")
    private String registeredDate;
}
