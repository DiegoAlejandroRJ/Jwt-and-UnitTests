package com.darj.FinalMongoDBSpring.repository.mongo;

import com.darj.FinalMongoDBSpring.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
