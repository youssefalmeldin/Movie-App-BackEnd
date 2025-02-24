package com.fawry.movieapp.dal.repo;


import com.fawry.movieapp.dal.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
