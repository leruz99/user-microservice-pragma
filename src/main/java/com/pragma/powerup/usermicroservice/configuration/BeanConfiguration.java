package com.pragma.powerup.usermicroservice.configuration;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.UserMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRoleRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepositoty;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {
    private final IRoleRepository roleRepository;
    private final IUserRepositoty userRepositoty;
    private final IRoleEntityMapper roleEntityMapper;
    private final IUserEntityMapper userEntityMapper;

    private final PasswordEncoder passwordEncoder;


    public BeanConfiguration(IRoleRepository roleRepository, IUserRepositoty userRepositoty, IRoleEntityMapper roleEntityMapper, IUserEntityMapper userEntityMapper, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepositoty = userRepositoty;
        this.roleEntityMapper = roleEntityMapper;
        this.userEntityMapper = userEntityMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort());
    }
    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserMysqlAdapter(userRepositoty,userEntityMapper, passwordEncoder);
    }
}
