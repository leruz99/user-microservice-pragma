package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IUserPersistencePort {
    void saveUser(User user);
    //void deleteUser(User user);
    //List<User> getAllProviders(int page);
    User getUser(Long id);
    //User getEmployee(Long id);
    //User getClient(Long id);
}
