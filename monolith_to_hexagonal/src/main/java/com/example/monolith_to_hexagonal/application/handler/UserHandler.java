package com.example.monolith_to_hexagonal.application.handler;

import com.example.monolith_to_hexagonal.application.dto.UserRequest;
import com.example.monolith_to_hexagonal.application.dto.UserResponse;
import com.example.monolith_to_hexagonal.application.mapper.UserRequestMapper;
import com.example.monolith_to_hexagonal.application.mapper.UserResponseMapper;
import com.example.monolith_to_hexagonal.domain.api.IPhotoServicePort;
import com.example.monolith_to_hexagonal.domain.api.IUserServicePort;
import com.example.monolith_to_hexagonal.domain.model.Photo;
import com.example.monolith_to_hexagonal.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler{


    private final IUserServicePort userServicePort;
    private final IPhotoServicePort photoServicePort;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;


    @Override
    public void saveUserInApp(UserRequest userRequest) {
        Photo photo = photoServicePort.savePhoto(userRequestMapper.toPhoto(userRequest));
        User user = userRequestMapper.toUser(userRequest);
        user.setCedula(userRequest.getCedula());
        user.setName(userRequest.getName());
        user.setLastName(userRequest.getLastName());
        user.setAge(userRequest.getAge());
        user.setPhotoId(photo.getId());
        userServicePort.saveUser(user);
    }

    @Override
    public List<UserResponse> getAllUsersFromApp() {
        return userResponseMapper.toResponseList(userServicePort.getAllUsers(), photoServicePort.getAllPhotos());
    }

    @Override
    public UserResponse getUserFromApp(String userCedula) {
        User user = userServicePort.getUserByCedula(userCedula);
        return userResponseMapper.toResponse(user, photoServicePort.getPhoto(user.getPhotoId()));
    }

    @Override
    public List<UserResponse> getUsersFromAppByAge(Integer userAge) {
        List<UserResponse> allUsersResponseList = userResponseMapper.toResponseList(userServicePort.getAllUsers(), photoServicePort.getAllPhotos());
        List<UserResponse> usersByAgeList = allUsersResponseList.stream().filter(userResponse -> userResponse.getAge() == userAge).toList();
        return usersByAgeList;
    }

    @Override
    public List<UserResponse> getUsersFromAppByName(String userName) {
        List<UserResponse> allUsersResponseList = userResponseMapper.toResponseList(userServicePort.getAllUsers(), photoServicePort.getAllPhotos());
        List<UserResponse> usersByNameList = allUsersResponseList.stream().filter(userResponse -> userResponse.getName().equalsIgnoreCase(userName)).toList();
        return usersByNameList;
    }

    @Override
    public List<UserResponse> getUsersFromAppByLastName(String userLastName) {
        List<UserResponse> allUsersResponseList = userResponseMapper.toResponseList(userServicePort.getAllUsers(), photoServicePort.getAllPhotos());
        List<UserResponse> usersByLastNameList = allUsersResponseList.stream().filter(userResponse -> userResponse.getName().equalsIgnoreCase(userLastName)).toList();
        return usersByLastNameList;
    }

    @Override
    public void updateUserInApp(UserRequest userRequest) {
        User oldUser = userServicePort.getUserByCedula(userRequest.getCedula());

        Photo newPhoto = userRequestMapper.toPhoto(userRequest);
        newPhoto.setId(oldUser.getPhotoId());
        photoServicePort.updatePhoto(newPhoto);

        User newUser = userRequestMapper.toUser(userRequest);
        newUser.setCedula(oldUser.getCedula());
        newUser.setName(oldUser.getName());
        newUser.setLastName(oldUser.getLastName());
        newUser.setAge(oldUser.getAge());
        newUser.setPhotoId(oldUser.getPhotoId());

        userServicePort.updateUser(newUser);



    }

    @Override
    public void deleteUserFromApp(String userCedula) {
        User user = userServicePort.getUserByCedula(userCedula);
        photoServicePort.deletePhoto(user.getPhotoId());
        userServicePort.deleteUser(userCedula);
    }
}
