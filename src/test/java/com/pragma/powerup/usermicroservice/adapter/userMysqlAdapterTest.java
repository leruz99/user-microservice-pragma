package com.pragma.powerup.usermicroservice.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.UserMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.IdentificationAlreadyExistException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlredyExistException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepositoty;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.testdatabuilder.RoleTestDataBuilder;
import com.pragma.powerup.usermicroservice.testdatabuilder.UserTestDataBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class userMysqlAdapterTest {
    @Mock
    private IUserRepositoty userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @InjectMocks
    private UserMysqlAdapter userMysqlAdapter;
    @Test
    void crearUsuarioTest(){
        User user = new UserTestDataBuilder().userPorDefecto().
                conName("Luis").
                conSurName("Ruz").
                conDni("1193118199").
                conPhone("+573136568154").
                conBirthdate(LocalDate.of(1999,10,05)).
                conEmail("luis@gmail.com").
                conPassword("1234").
                conRole(new RoleTestDataBuilder().rolePorDefecto().conId()).
                crear();
        RoleEntity role = new RoleEntity(2L,"OWNER_ROLE","OWNER_ROLE");
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Luis");
        userEntity.setSurName("Ruz");
        userEntity.setDni("1193118199");
        userEntity.setPhone("+573136568154");
        userEntity.setBirthdate("1999-10-05");
        userEntity.setEmail("luis@gmail.com");
        userEntity.setPassword("1234");
        userEntity.setRoleEntity(role);
        Mockito.when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        Mockito.when(userRepository.findByDni(user.getDni())).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        Mockito.when(userEntityMapper.toUserEntity(user)).thenReturn(userEntity);

        // Act
        userMysqlAdapter.saveUser(user);

        // Assert
        verify(userRepository, times(1)).save(userEntity);

    }
    @Test
    void emailAlreadyExistTest(){
        User user = new UserTestDataBuilder().userPorDefecto().
                conName("Luis").
                conSurName("Ruz").
                conDni("1193118199").
                conPhone("+573136568154").
                conBirthdate(LocalDate.of(1999,10,05)).
                conEmail("luis@gmail.com").
                conPassword("1234").
                conRole(new RoleTestDataBuilder().rolePorDefecto().conId()).
                crear();

        Mockito.when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        Assert.assertThrows(MailAlredyExistException.class, () -> userMysqlAdapter.saveUser(user));

    }
    @Test
    void dniAlAlreadyExistTest(){
        User user = new UserTestDataBuilder().userPorDefecto().
                conName("Luis").
                conSurName("Ruz").
                conDni("1193118199").
                conPhone("+573136568154").
                conBirthdate(LocalDate.of(1999,10,05)).
                conEmail("luis@gmail.com").
                conPassword("1234").
                conRole(new RoleTestDataBuilder().rolePorDefecto().conId()).
                crear();
        RoleEntity role = new RoleEntity(2L,"OWNER_ROLE","OWNER_ROLE");
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Luis");
        userEntity.setSurName("Ruz");
        userEntity.setDni("1193118199");
        userEntity.setPhone("+573136568154");
        userEntity.setBirthdate("1999-10-05");
        userEntity.setEmail("luis@gmail.com");
        userEntity.setPassword("1234");
        userEntity.setRoleEntity(role);

        Mockito.when(userRepository.findByDni(user.getDni())).
                thenReturn(Optional.of(userEntity));

        Assert.assertThrows(IdentificationAlreadyExistException.class, () -> userMysqlAdapter.saveUser(user));

    }
    @Test
    void getUserTest(){
        User user = new UserTestDataBuilder().userPorDefecto().
                conName("Luis").
                conSurName("Ruz").
                conDni("1193118199").
                conPhone("+573136568154").
                conBirthdate(LocalDate.of(1999,10,05)).
                conEmail("luis@gmail.com").
                conPassword("1234").
                conRole(new RoleTestDataBuilder().rolePorDefecto().conId()).
                crear();
        RoleEntity role = new RoleEntity(2L,"OWNER_ROLE","OWNER_ROLE");
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Luis");
        userEntity.setSurName("Ruz");
        userEntity.setDni("1193118199");
        userEntity.setPhone("+573136568154");
        userEntity.setBirthdate("1999-10-05");
        userEntity.setEmail("luis@gmail.com");
        userEntity.setPassword("1234");
        userEntity.setRoleEntity(role);
        when(userRepository.findById(1L)).
                thenReturn(Optional.of(userEntity));

        when(userEntityMapper.toUser(userEntity)).thenReturn(user);

        User expectedUser = userMysqlAdapter.getUser(1L);
        Assert.assertEquals(expectedUser, user);

    }
    @Test
    void userNotFoundTest(){
        User user = new UserTestDataBuilder().userPorDefecto().
                conName("Luis").
                conSurName("Ruz").
                conDni("1193118199").
                conPhone("+573136568154").
                conBirthdate(LocalDate.of(1999,10,05)).
                conEmail("luis@gmail.com").
                conPassword("1234").
                conRole(new RoleTestDataBuilder().rolePorDefecto().conId()).
                crear();
        when(userRepository.findById(1L)).
                thenReturn(Optional.empty());

        Assert.assertThrows(UserNotFoundException.class,
                () -> userMysqlAdapter.getUser(1L));



    }


}
