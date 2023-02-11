package com.example.monolith_to_hexagonal.domain.api;

import com.example.monolith_to_hexagonal.domain.model.User;

import java.util.List;

// Esta interface es un puerto, nos sirve para exponer al exterior los métodos que necesitamos para User
public interface IUserServicePort {

    void saveUser(User user);

    List<User> getAllUsers();

    User getUser(Long userId);

    User getUserByCedula(String userCedula);

    List<User> getUsersByName(String userName);

    List<User> getUsersByLastName(String userLastName);

    List<User> getUsersByAge(Integer userAge);

    void updateUser(User user);

    void deleteUser(String userCedula);




}
