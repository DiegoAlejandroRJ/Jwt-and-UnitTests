package com.darj.FinalMongoDBSpring.repository.mongo;


import com.darj.FinalMongoDBSpring.model.User;
import com.darj.FinalMongoDBSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserMongoRepository userMongoRepository;

    @Override
    public List<User> getAllUsers() {
        return userMongoRepository.findAll();
    }

    @Override
    public User findUserById(String id) {
        return userMongoRepository.findById(id).get();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> userFound = userMongoRepository.findByEmail(email);
        if (userFound.isPresent()){
            return userFound;
        }else {
            return Optional.empty();
        }
    }

    @Override
    public User createUser(User user) {
        return userMongoRepository.save(user);
    }

    @Override
    public Boolean updateUser(String id, User user) {
        User userFound = findUserById(id);
        if (userFound != null){
            userFound.updateUser(user);
            userMongoRepository.save(userFound);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean deleteUser(String id) {
        User userFound = findUserById(id);
        if (userFound != null){
            userMongoRepository.delete(userFound);
            return true;
        }else {
            return false;
        }
    }
}