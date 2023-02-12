package com.example.monolith_to_hexagonal.infraestructure.output.jpa.repository;

import com.example.monolith_to_hexagonal.infraestructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByCedula(String userCedula);

    Optional<List<UserEntity>> findByName(String userName);

    Optional<List<UserEntity>> findByLastName(String userLastName);

    Optional<List<UserEntity>> findByAge(Integer userAge);

    void deleteByCedula(String userCedula);


}
