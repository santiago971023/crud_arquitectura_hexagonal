package com.example.monolith_to_hexagonal.domain.usecase;

import com.example.monolith_to_hexagonal.domain.api.IUserServicePort;
import com.example.monolith_to_hexagonal.domain.model.User;
import com.example.monolith_to_hexagonal.domain.spi.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    //Realizamos inyección de dependencias por medio de constructor porque
    // seguimos en la capa del dominio, y no pueden ser usadas anotaciones de otras tecnologías
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        userPersistencePort.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }

    @Override
    public User getUser(Long userId) {
        return userPersistencePort.getUser(userId);
    }

    @Override
    public User getUserByCedula(String userCedula) {
        return userPersistencePort.getUserByCedula(userCedula);
    }

    @Override
    public List<User> getUsersByName(String userName) {
        return userPersistencePort.getUsersByName(userName);
    }

    @Override
    public List<User> getUsersByLastName(String userLastName) {
        return userPersistencePort.getUsersByLastName(userLastName);
    }

    @Override
    public List<User> getUsersByAge(Integer userAge) {
        return userPersistencePort.getUsersByAge(userAge);
    }

    @Override
    public void updateUser(User user) {
        userPersistencePort.updateUser(user);
    }

    @Override
    public void deleteUser(String userCedula) {
        userPersistencePort.deleteUser(userCedula);
    }
}
