package com.example.monolith_to_hexagonal.application.mapper;

import com.example.monolith_to_hexagonal.application.dto.UserRequest;
import com.example.monolith_to_hexagonal.domain.model.Photo;
import com.example.monolith_to_hexagonal.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Base64;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {

    User toUser(UserRequest userRequest);

    @Mapping(target = "photo", qualifiedByName = "base64ToByteArray")  // Este quialifiedByName no hace referencia al nombre del método sino a lo que pusimos dentro de la anotación @Named
    Photo toPhoto(UserRequest userRequest);


    @Named("base64ToByteArray")
    static byte[] base64ToByteArray(String base64Photo){
        return Base64.getDecoder().decode(base64Photo);
    }
}
