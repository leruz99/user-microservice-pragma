package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepositoty extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByDni(String identification);
    Boolean existsByEmail(String email);
    List<UserEntity> findAllById(Long id);



}
