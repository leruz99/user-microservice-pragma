package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IRolePersistencePort {
    List<Role> getAllRoles();
}
