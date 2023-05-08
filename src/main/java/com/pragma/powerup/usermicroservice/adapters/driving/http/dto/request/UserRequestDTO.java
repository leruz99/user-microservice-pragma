package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequestDTO {

    private String name;
    private String surName;
    @Digits(integer=20, fraction=0, message="El campo debe contener solo números enteros")
    private String  dni;
    private String phone;
    private String birthdate;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    private String password;
    private Long idRole;
}
