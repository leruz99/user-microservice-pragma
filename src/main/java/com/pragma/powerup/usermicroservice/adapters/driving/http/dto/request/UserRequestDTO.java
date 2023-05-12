package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserRequestDTO {

    @NotEmpty(message = "Name can´t be Empty")
    private String name;
    @NotEmpty(message = "SurName can´t be Empty")
    private String surName;
    @Digits(integer=20, fraction=0, message="The field must have only integers")
    private String  dni;
    @NotEmpty(message = "phone can´t be empty")
    @Pattern(regexp = "^\\+?[0-9]{1,12}$", message = "Invalid phone")
    private String phone;
    @NotEmpty
    private String birthdate;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @NotEmpty
    private String password;
    //@NotEmpty
    private Long idRole;
}
