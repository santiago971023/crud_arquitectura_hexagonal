package com.example.monolith_to_hexagonal.infraestructure.output.jpa.adapter;

import com.example.monolith_to_hexagonal.domain.model.User;
import com.example.monolith_to_hexagonal.domain.spi.IUserPersistencePort;
import com.example.monolith_to_hexagonal.infraestructure.exception.NoDataFoundException;
import com.example.monolith_to_hexagonal.infraestructure.exception.UserAlreadyExist;
import com.example.monolith_to_hexagonal.infraestructure.exception.UserNotFoundException;
import com.example.monolith_to_hexagonal.infraestructure.output.jpa.entity.UserEntity;
import com.example.monolith_to_hexagonal.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.example.monolith_to_hexagonal.infraestructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;


    @Override
    public void saveUser(User user) {
        if(userRepository.findByCedula(user.getCedula()).isPresent()){
            throw new UserAlreadyExist();
        }
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if(userEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public User getUser(Long userId) {  //No hice un metodo para buscar por id, pero sí por cédula
        return null;
    }

    @Override
    public User getUserByCedula(String userCedula) {
        return userEntityMapper.toUser(userRepository.findByCedula(userCedula).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public List<User> getUsersByName(String userName) {
        return userEntityMapper.toUserList(userRepository.findByName(userName).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public List<User> getUsersByLastName(String userLastName) {
        return userEntityMapper.toUserList(userRepository.findByLastName(userLastName).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public List<User> getUsersByAge(Integer userAge) {
        return userEntityMapper.toUserList(userRepository.findByAge(userAge).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public void deleteUser(String userCedula) {
        userRepository.deleteByCedula(userCedula);
    }


}
