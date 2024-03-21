package com.darj.FinalMongoDBSpring.service;

import com.darj.FinalMongoDBSpring.dto.UserDto;
import com.darj.FinalMongoDBSpring.dto.UserMapper;
import com.darj.FinalMongoDBSpring.dto.UserResponseDto;
import com.darj.FinalMongoDBSpring.model.RoleEnum;
import com.darj.FinalMongoDBSpring.model.User;
import com.darj.FinalMongoDBSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        userRepository.getAllUsers().forEach(user -> userResponseDtos.add(UserMapper.userToUserResponseDto(user)));
        //userRepository.getAllUsers().forEach(user -> userResponseDtos.add(new UserResponseDto(user)));
        return userResponseDtos;
    }

    @Override
    public UserResponseDto findUserById(String id) {
        return UserMapper.userToUserResponseDto(userRepository.findUserById(id));
    }

    @Override
    public User findByEmail(String email) {
        User userFound = userRepository.findByEmail(email).get();
        if (userFound != null){
            return userFound;
        }
        return null;
    }

    @Override
    public UserResponseDto createUser(UserDto userDto) {
        return UserMapper.userToUserResponseDto(userRepository.createUser(UserMapper.userDtoToUser(userDto)));
    }

    @Override
    public UserResponseDto createUserAdmin(UserDto userDto) {
        User userAdmin = UserMapper.userDtoToUser(userDto);
        userAdmin.addRole(RoleEnum.ADMIN);
        return UserMapper.userToUserResponseDto(userRepository.createUser(userAdmin));
    }

    @Override
    public Boolean updateUser(String id, UserDto userDto) {
        return userRepository.updateUser(id, UserMapper.userDtoToUser(userDto));
        //return userRepository.updateUser(id, new User(userDto));
    }

    @Override
    public Boolean deleteUser(String id) {
        return userRepository.deleteUser(id);
    }
}
