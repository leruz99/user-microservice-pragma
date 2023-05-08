package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.IdentificationAlreadyExistException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlredyExistException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepositoty;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


public class UserMysqlAdapter implements IUserPersistencePort {

    private final IUserRepositoty userRepositoty;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    public UserMysqlAdapter(IUserRepositoty userRepositoty, IUserEntityMapper userEntityMapper, PasswordEncoder passwordEncoder) {
        this.userRepositoty = userRepositoty;
        this.userEntityMapper = userEntityMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        if (userRepositoty.existsByEmail(user.getEmail()) ){
            throw new MailAlredyExistException();
        }
        if (userRepositoty.findByDni(user.getDni()).isPresent()){
            throw new IdentificationAlreadyExistException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepositoty.save(userEntityMapper.toUserEntity(user));
    }

    @Override
    public User getUser(Long id) {
        Optional<UserEntity> userEntity = userRepositoty.findById(id);
        return userEntityMapper.toUser(userEntity.get());
    }


}
