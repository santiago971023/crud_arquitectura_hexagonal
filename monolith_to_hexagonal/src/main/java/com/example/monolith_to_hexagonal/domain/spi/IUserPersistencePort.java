package com.example.monolith_to_hexagonal.domain.spi;

import com.example.monolith_to_hexagonal.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {

    void saveUser(User user);

    List<User> getAllUsers();

    User getUser(Long userId);

    List<User> getUsersByName(String userName);

    List<User> getUsersByLastName(String userLastName);

    List<User> getUsersByAge(Integer userAge);

    void updateUser(User user);

    void deleteUser(String userCedula);

    User getUserByCedula(String userCedula);
}
