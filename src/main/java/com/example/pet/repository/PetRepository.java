package com.example.pet.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.pet.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Integer> {

}
