package com.pragma.powerup.usermicroservice.adapters.driving.http.mapper;


import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDTO;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDTO;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {

    @Mapping(target = "role.id", source = "idRole")
    User toUser(UserRequestDTO userRequestDTO);

    UserResponseDTO toUserResponseDTO(User user);


}
