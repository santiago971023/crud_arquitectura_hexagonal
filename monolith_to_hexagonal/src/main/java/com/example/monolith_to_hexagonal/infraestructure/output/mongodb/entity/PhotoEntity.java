package com.example.monolith_to_hexagonal.infraestructure.output.mongodb.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document(collection = "photo")
@Data
public class PhotoEntity {
    @Id
    private String id;
    private byte[] photo;
}
