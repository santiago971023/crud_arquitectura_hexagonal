package com.example.monolith_to_hexagonal.application.dto;

import lombok.Getter;
import lombok.Setter;

// Este DTO será para la respuesta o el response, estos son los campos que nos interesa ver, corresponden a los que
// recibimos cuando hacemos una solicitud (dto de salida)

@Getter
@Setter
public class UserResponse {

    private String cedula;
    private String name;
    private String lastName;
    private Integer age;
    private String photo;

}
