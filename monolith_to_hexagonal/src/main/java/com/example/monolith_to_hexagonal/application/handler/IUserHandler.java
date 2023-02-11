package com.example.monolith_to_hexagonal.application.handler;

import com.example.monolith_to_hexagonal.application.dto.UserRequest;
import com.example.monolith_to_hexagonal.application.dto.UserResponse;

import java.util.List;

public interface IUserHandler {

    void saveUserInApp(UserRequest userRequest);

    List<UserResponse> getAllUsersFromApp();

    UserResponse getUserFromApp(String userCedula);

    List<UserResponse> getUsersFromAppByAge(Integer userAge);

    List<UserResponse> getUsersFromAppByName(String userName);

    List<UserResponse> getUsersFromAppByLastName(String userLastName);

    void updateUserInApp(UserRequest userRequest);

    void deleteUserFromApp(String userCedula);

}
