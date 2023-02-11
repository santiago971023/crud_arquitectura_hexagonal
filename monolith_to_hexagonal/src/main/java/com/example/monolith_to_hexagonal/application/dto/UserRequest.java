package com.example.monolith_to_hexagonal.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


// Este DTO será para la consulta o el request (De entrada)

@Getter
@Setter
public class UserRequest {

    private String cedula;
    private String name;
    private String lastName;
    private Integer age;
    private String photo;


}
