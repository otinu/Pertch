package com.example.pet.repository;

import java.util.Optional;

import com.example.pet.model.User;

public class CustomizedUserRepositoryImpl implements CustomizedUserRepository {

	@Override
	public Optional<User> findByEmail(String email) {
		return Optional.empty();
	}

}
