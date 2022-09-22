package com.example.pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pet.model.Pet;
import com.example.pet.repository.PetRepository;

@Service
@Transactional
public class PetServiceImpl implements PetService {
	
	@Autowired
	PetRepository repository;

	@Override
	public Iterable<Pet> selectAll() {
		return repository.findAll();
	}

	@Override
	public void insertPet(Pet pet) {
		repository.save(pet);
	}

}
