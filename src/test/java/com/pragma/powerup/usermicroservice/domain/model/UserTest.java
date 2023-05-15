package com.pragma.powerup.usermicroservice.domain.model;

import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.usecase.UserUseCase;
import com.pragma.powerup.usermicroservice.testdatabuilder.RoleTestDataBuilder;
import com.pragma.powerup.usermicroservice.testdatabuilder.UserTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserTest {
    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @Test
    void shouldCreateUser(){
        User user = new UserTestDataBuilder().userPorDefecto().
                conName("Luis").
                conSurName("Ruz").
                conPhone("+573136568154").
                conBirthdate(LocalDate.of(1999,10,05)).
                conEmail("luis@gmail.com").
                conPassword("1234").
                conRole(new RoleTestDataBuilder().rolePorDefecto().conId()).
                crear();
        Assertions.assertEquals("Luis",user.getName());
        Assertions.assertEquals("Ruz",user.getSurName());
        Assertions.assertEquals("+573136568154",user.getPhone());
        Assertions.assertEquals(LocalDate.of(1999,10,05),user.getBirthdate());
        Assertions.assertEquals("luis@gmail.com",user.getEmail());
        Assertions.assertEquals("1234",user.getPassword());
        Assertions.assertEquals(2L,user.getRole().getId());

    }
    @Test
    void saveUser_OwnerRoleAndOver18_Success() {
        //Arrange
        User user = new UserTestDataBuilder().userPorDefecto().
                conName("Luis").
                conSurName("Ruz").
                conPhone("+573136568154").
                conBirthdate(LocalDate.of(1999,10,05)).
                conEmail("luis@gmail.com").
                conPassword("1234").
                conRole(new RoleTestDataBuilder().rolePorDefecto().conId()).
                crear();
        //Act
        userUseCase.saveUser(user);
        //Assert
        Mockito.verify(userPersistencePort, times(1)).saveUser(user);
    }



}
