package com.darj.FinalMongoDBSpring.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Document(collection = "user_collection")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private LocalDateTime creation;
    private LocalDateTime update;
    private String email;
    private String password;
    private List<RoleEnum> roles;


    public User(String name, String lastName, LocalDate birthDate, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.creation = LocalDateTime.now();
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.update = null;
        this.roles = new ArrayList<>(Collections.singleton(RoleEnum.USER));
    }

    /*public User(UserDto userDto){
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.birthDate = userDto.getBirthDate();
        this.creation = LocalDateTime.now();
        this.update = null;
    }*/

    public String getId() {
        return id;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public LocalDateTime getUpdate() {
        return update;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }

    public void addRole(RoleEnum role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }

    public void updateUser(User user){
        setName(user.getName());
        setLastName(user.getLastName());
        setBirthDate(user.getBirthDate());
        setRoles(user.getRoles());
        this.update = LocalDateTime.now();
    }
}
