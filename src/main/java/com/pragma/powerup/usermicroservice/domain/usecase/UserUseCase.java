package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.RoleNotAllowedForCreationException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserNotCreated;
import com.pragma.powerup.usermicroservice.configuration.Constanst;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        if (user.getRole().getId() != Constanst.OWNER_ROLE_ID){
            throw new RoleNotAllowedForCreationException();
        }
        userPersistencePort.saveUser(user);
    }

    @Override
    public User getUser(Long id) {
        return userPersistencePort.getUser(id);
    }


}
