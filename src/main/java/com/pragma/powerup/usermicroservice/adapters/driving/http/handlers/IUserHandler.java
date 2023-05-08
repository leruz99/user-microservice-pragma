package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDTO;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDTO;

public interface IUserHandler {
    void saveUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUser(Long id);
}
