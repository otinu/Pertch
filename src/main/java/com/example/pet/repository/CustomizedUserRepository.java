package com.example.pet.repository;

import java.util.Optional;

import com.example.pet.model.User;

public interface CustomizedUserRepository {

	public Optional<User> findByEmail(String email);
}
