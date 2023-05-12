package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PrincipalUser;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    IUserRepositoty userRepositoty;


    @Override
    public UserDetails loadUserByUsername(String documentID) throws UsernameNotFoundException {
        UserEntity usuarios = userRepositoty.findByDni(documentID).orElseThrow( () -> new UserNotFoundException());
        List<UserEntity> userEntities = userRepositoty.findAllById(usuarios.getId());
        if (userEntities.isEmpty()){
            throw new UserNotFoundException();
        }
        List<RoleEntity> roles = new ArrayList<>();
        for (UserEntity user : userEntities){
            roles.add(user.getRoleEntity());
        }
        return PrincipalUser.build(usuarios,roles);
    }
}
