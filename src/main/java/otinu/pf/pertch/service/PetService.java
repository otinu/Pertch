package otinu.pf.pertch.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import otinu.pf.pertch.entity.Pet;

public interface PetService {
	
	Iterable<Pet> selectAll();
	
	void insertPet(Pet pet);
	
	Optional<Pet> findById(Integer id);
	
	void updatePet(Pet pet);
	
	void deleteById(Integer id);
	
	void settingImage(Pet pet, MultipartFile multipartFile) throws IOException;
}
