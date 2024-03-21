package com.darj.FinalMongoDBSpring.dto;

import com.darj.FinalMongoDBSpring.model.User;

public class UserMapper {

    public static UserResponseDto userToUserResponseDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getEmail()
        );
    }

    public static User userDtoToUser(UserDto userDto){
        return new User(
                userDto.getName(),
                userDto.getLastName(),
                userDto.getBirthDate(),
                userDto.getEmail(),
                userDto.getPassword()
        );
    }

}