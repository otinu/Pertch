package com.example.pet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.pet.model.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Integer> {
	
}
