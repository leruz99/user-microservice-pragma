package com.pragma.powerup.usermicroservice.testdatabuilder;


import com.pragma.powerup.usermicroservice.domain.model.Role;

public class RoleTestDataBuilder {
    private Long id;
    private String name;
    private String description;

    public RoleTestDataBuilder rolePorDefecto() {
        id = 2L;
        name = "ROLE_OWNER";
        description = "ROLE_OWNER";
        return this;
    }
    public Role crear(){
        return new Role(id,name,description);
    }
    public RoleTestDataBuilder conId(){
        this.id = id;
        return this;
    }
    public RoleTestDataBuilder conName(String name){
        this.name = name;
        return this;
    }
    public RoleTestDataBuilder conDescription(String description){
        this.description = description;
        return this;
    }


}
