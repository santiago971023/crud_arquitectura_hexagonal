package com.example.monolith_to_hexagonal.domain.model;


// En esta clase, por ser parte de la capa del dominio, podemos notar que no estamos usando ninguna
// anotación de alguna librería externa como Lombook
// todo lo que usamos es estrictamente lenguaje Java
public class User {

    private Long id;
    private String cedula;
    private String name;
    private String lastName;
    private Integer age;
    private String photoId;


    public User(Long id, String cedula, String name, String lastName, Integer age, String photoId) {
        this.id = id;
        this.cedula = cedula;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.photoId = photoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }
}
