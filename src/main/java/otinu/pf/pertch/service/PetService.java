package otinu.pf.pertch.service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import otinu.pf.pertch.entity.Pet;
import otinu.pf.pertch.entity.PetComment;
import otinu.pf.pertch.form.PetForm;

public interface PetService {

	Iterable<Pet> selectAll();
	
	void insertPet(Pet pet);
	
	Optional<Pet> findById(Integer id);
	
	void updatePet(Pet pet);
	
	void deleteById(Integer id);
	
	void settingImage(Pet pet, MultipartFile multipartFile) throws IOException;
	
	PetForm makePetForm(Pet pet);
	
	Pet makePet(Pet pet, PetForm petForm, MultipartFile multipartFile, Principal principal) throws IOException;
	
	Pet setFormToPet(Pet pet, PetForm petForm);
	
	PetForm setPetToForm(PetForm petForm, Pet pet);

	Pet findByIdToPet(Integer id);

	List<PetComment> findPetComment(Integer id);
}
