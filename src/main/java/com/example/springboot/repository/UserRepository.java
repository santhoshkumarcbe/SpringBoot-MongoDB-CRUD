package com.example.springboot.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.springboot.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User,ObjectId>{

    void findByUserName(String username);

    
    
}
