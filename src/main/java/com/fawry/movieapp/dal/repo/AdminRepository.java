package com.fawry.movieapp.dal.repo;


import com.fawry.movieapp.dal.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

    Admin findByUsername(String username);
}
