package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

import java.util.List;

public interface IUserServicePort {
    void saveUser(User user);
    //void deleteUser(User user);
    //List<User> getAllProviders(int page);
    User getUser(Long id);
    //User getEmployee(Long id);
    //User getClient(Long id);

}
