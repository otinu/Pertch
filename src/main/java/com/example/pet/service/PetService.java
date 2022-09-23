package com.example.pet.service;

import java.util.Optional;

import com.example.pet.model.Pet;

public interface PetService {
	
	Iterable<Pet> selectAll();
	
	void insertPet(Pet pet);
	
	Optional<Pet> selectById(Integer id);
	
	void updatePet(Pet pet);
	
	void deleteById(Integer id);
}
