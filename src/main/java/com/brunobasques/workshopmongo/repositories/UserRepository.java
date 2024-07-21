package com.brunobasques.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.brunobasques.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
