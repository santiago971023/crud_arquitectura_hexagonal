package com.example.monolith_to_hexagonal.infraestructure.input.rest;


import com.example.monolith_to_hexagonal.application.dto.UserRequest;
import com.example.monolith_to_hexagonal.application.dto.UserResponse;
import com.example.monolith_to_hexagonal.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monolith")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveUserInApp(@RequestBody UserRequest userRequest){
        userHandler.saveUserInApp(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUsersFromApp(){
        return ResponseEntity.ok(userHandler.getAllUsersFromApp());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<UserResponse>> getUsersFromAppByName(@PathVariable(name = "name") String userName){
        return ResponseEntity.ok(userHandler.getUsersFromAppByName(userName));
    }

    @GetMapping("/{lastname}")
    public ResponseEntity<List<UserResponse>> getUsersFromAppByLastName(@PathVariable(name = "lastname") String userLastName){
        return ResponseEntity.ok(userHandler.getUsersFromAppByLastName(userLastName));
    }

    @GetMapping("/{age}")
    public ResponseEntity<List<UserResponse>> getUsersFromAppByAge(@PathVariable(name = "age") Integer userAge){
        return ResponseEntity.ok(userHandler.getUsersFromAppByAge(userAge));
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<UserResponse> getUsersFromAppByCedula(@PathVariable(name = "cedula") String userCedula){
        return ResponseEntity.ok(userHandler.getUserFromApp(userCedula));
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateUserInApp(@RequestBody UserRequest userRequest){
        userHandler.updateUserInApp(userRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cedula")
    public ResponseEntity<Void> deleteUserFromApp(@PathVariable(name = "cedula") String userCedula){
        userHandler.deleteUserFromApp(userCedula);
        return ResponseEntity.noContent().build();
    }

}
