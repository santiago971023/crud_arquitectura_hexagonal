package com.example.monolith_to_hexagonal.infraestructure.configuration;

import com.example.monolith_to_hexagonal.domain.api.IPhotoServicePort;
import com.example.monolith_to_hexagonal.domain.api.IUserServicePort;
import com.example.monolith_to_hexagonal.domain.spi.IPhotoPersistencePort;
import com.example.monolith_to_hexagonal.domain.spi.IUserPersistencePort;
import com.example.monolith_to_hexagonal.domain.usecase.PhotoUseCase;
import com.example.monolith_to_hexagonal.domain.usecase.UserUseCase;
import com.example.monolith_to_hexagonal.infraestructure.output.jpa.adapter.UserJpaAdapter;
import com.example.monolith_to_hexagonal.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.example.monolith_to_hexagonal.infraestructure.output.jpa.repository.IUserRepository;
import com.example.monolith_to_hexagonal.infraestructure.output.mongodb.adapter.PhotoMongodbAdapter;
import com.example.monolith_to_hexagonal.infraestructure.output.mongodb.mapper.PhotoEntityMapper;
import com.example.monolith_to_hexagonal.infraestructure.output.mongodb.repository.IPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IPhotoRepository photoRepository;
    private final PhotoEntityMapper photoEntityMapper;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort());
    }


    @Bean
    public IPhotoPersistencePort photoPersistencePort(){
        return new PhotoMongodbAdapter(photoRepository, photoEntityMapper);
    }
    @Bean
    public IPhotoServicePort photoServicePort(){
        return new PhotoUseCase(photoPersistencePort());
    }


}
