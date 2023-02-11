package com.example.monolith_to_hexagonal.application.mapper;


import com.example.monolith_to_hexagonal.application.dto.UserResponse;
import com.example.monolith_to_hexagonal.domain.model.Photo;
import com.example.monolith_to_hexagonal.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Base64;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserResponseMapper {

    @Mapping(target = "photo", qualifiedByName = "byteArrayToBase64")
    UserResponse toResponse(User user, Photo photo);

    @Named("byteArrayToBase64")
    static String byteArrayToBase64(byte[] byteArrayPhoto){
        return Base64.getEncoder().encodeToString(byteArrayPhoto);
    }

    default List<UserResponse> toResponseList(List<User> userList, List<Photo> photoList){
        return userList.stream()
                .map(user -> {
                    UserResponse userResponse = new UserResponse();
                    userResponse.setCedula(user.getCedula());
                    userResponse.setName(user.getName());
                    userResponse.setLastName(user.getLastName());
                    userResponse.setAge(user.getAge());
                    userResponse.setPhoto(byteArrayToBase64(photoList.stream().filter(photo -> photo.getId().equals(user.getPhotoId())).findFirst().orElse(null).getPhoto()));
                    return userResponse;
                        }).toList();
    }

}
