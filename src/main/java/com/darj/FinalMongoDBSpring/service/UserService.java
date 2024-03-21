package com.darj.FinalMongoDBSpring.service;


import com.darj.FinalMongoDBSpring.dto.UserDto;
import com.darj.FinalMongoDBSpring.dto.UserResponseDto;
import com.darj.FinalMongoDBSpring.model.User;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto findUserById(String id);
    User findByEmail(String email);
    UserResponseDto createUser(UserDto userDto);
    UserResponseDto createUserAdmin(UserDto userDto);
    Boolean updateUser(String id, UserDto userDto);
    Boolean deleteUser(String id);
}