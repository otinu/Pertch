package com.example.pet.service;

import com.example.pet.model.Pet;

public interface PetService {
	
	Iterable<Pet> selectAll();
	
	void insertPet(Pet pet);
}