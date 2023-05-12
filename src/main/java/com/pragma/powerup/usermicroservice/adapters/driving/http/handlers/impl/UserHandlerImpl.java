package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDTO;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDTO;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRespondeMapper;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import org.springframework.stereotype.Service;


@Service
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserRespondeMapper userRespondeMapper;


    public UserHandlerImpl(IUserServicePort userServicePort, IUserRequestMapper userRequestMapper, IUserRespondeMapper userRespondeMapper) {
        this.userServicePort = userServicePort;
        this.userRequestMapper = userRequestMapper;
        this.userRespondeMapper = userRespondeMapper;
    }


    @Override
    public void saveUser(UserRequestDTO userRequestDTO) {
        userServicePort.saveUser(userRequestMapper.toUser(userRequestDTO));

    }

    @Override
    public UserResponseDTO getUser(Long id) {
        return userRespondeMapper.toResponse(userServicePort.getUser(id));
    }
}
