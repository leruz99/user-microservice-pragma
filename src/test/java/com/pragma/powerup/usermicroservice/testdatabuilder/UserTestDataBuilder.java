package com.pragma.powerup.usermicroservice.testdatabuilder;


import com.pragma.powerup.usermicroservice.domain.model.User;

import java.time.LocalDate;

public class UserTestDataBuilder {

    private Long id;
    private String name;
    private String surName;
    private String dni;
    private String phone;
    private LocalDate birthdate;
    private String email;
    private String password;
    private RoleTestDataBuilder role;

    public UserTestDataBuilder userPorDefecto() {

        name = "Luis";
        surName = "Ruz";
        dni = "1193118199";
        phone = "+573136568154";
        birthdate = LocalDate.of(1999,10,05);
        email = "luis@gmail.com";
        password = "1234";
        role = new RoleTestDataBuilder().rolePorDefecto().conId();
        return this;
    }
    public User crear(){
        return new User(id,name,surName,dni,phone,birthdate,email,password,role.crear());
    }
    public UserTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }
    public UserTestDataBuilder conName(String name){
        this.name = name;
        return this;
    }
    public UserTestDataBuilder conSurName(String surName){
        this.surName = surName;
        return this;
    }
    public UserTestDataBuilder conDni(String dni){
        this.dni = dni;
        return this;
    }
    public UserTestDataBuilder conPhone(String phone){
        this.phone = phone;
        return this;
    }
    public UserTestDataBuilder conBirthdate(LocalDate birthdate){
        this.birthdate = birthdate;
        return this;
    }
    public UserTestDataBuilder conEmail(String email){
        this.email = email;
        return this;
    }
    public UserTestDataBuilder conPassword(String password){
        this.password = password;
        return this;
    }
    public UserTestDataBuilder conRole(RoleTestDataBuilder role){
        this.role = role;
        return this;
    }

}
