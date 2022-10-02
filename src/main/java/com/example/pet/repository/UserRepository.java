package com.example.pet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.pet.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
