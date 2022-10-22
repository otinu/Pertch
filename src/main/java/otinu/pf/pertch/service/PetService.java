package otinu.pf.pertch.service;

import java.util.Optional;

import otinu.pf.pertch.model.Pet;

public interface PetService {
	
	Iterable<Pet> selectAll();
	
	void insertPet(Pet pet);
	
	Optional<Pet> selectById(Integer id);
	
	void updatePet(Pet pet);
	
	void deleteById(Integer id);
}
