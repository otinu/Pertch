package otinu.pf.pertch.service;

import java.util.List;

import otinu.pf.pertch.entity.Pet;

public interface PetSearchService {

	List<Pet> searchPet(String name, Integer age, Boolean sex, String charmPoint, 
			String postCord, String address, String ownerName);

}
