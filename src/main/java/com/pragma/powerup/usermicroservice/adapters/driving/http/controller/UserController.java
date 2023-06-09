package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDTO;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDTO;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.configuration.Constanst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserHandler userHandler;

    public UserController(IUserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Operation(summary = "Add a new user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "User already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/owner")
    public ResponseEntity<Map<String,String>> saveUser(@Valid  @RequestBody UserRequestDTO userResponseDTO){
        userHandler.saveUser(userResponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(Collections.singletonMap(Constanst.RESPONSE_MESSAGE_KEY,Constanst.USER_CREATED_MESSAGE));
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userHandler.getUser(id));
    }
}
