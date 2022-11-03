package otinu.pf.pertch.service.Impl;

import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import otinu.pf.pertch.entity.Pet;
import otinu.pf.pertch.form.PetForm;
import otinu.pf.pertch.repository.PetRepository;
import otinu.pf.pertch.service.OwnerService;
import otinu.pf.pertch.service.PetService;

@Service
@Transactional
public class PetServiceImpl implements PetService {
	
	@Autowired
	PetRepository repository;
	
	@Autowired
	OwnerService ownerService;

	@Override
	public Iterable<Pet> selectAll() {
		return repository.findAll();
	}

	@Override
	public void insertPet(Pet pet) {
		repository.saveAndFlush(pet);
	}
	
	@Override
	public Optional<Pet> findById(Integer id) {
		return repository.findById(id);
	}
	
	@Override
	public void updatePet(Pet pet) {
		repository.saveAndFlush(pet);
	}
	
	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public void settingImage(Pet pet, MultipartFile multipartFile) throws IOException {
		String base64;
		try {
			base64 = new String(Base64.encodeBase64(multipartFile.getBytes()),"ASCII");
			String imageType = multipartFile.getContentType();
			if(imageType.equals("image/png")) {
				pet.setImage("data:image/png;base64," + base64);
			} else if(imageType.equals("image/jpeg")) {
				pet.setImage("data:image/jpeg," + base64);
			} else if(imageType.equals("image/gif")) {
				pet.setImage("data:image/gif;base64," + base64);
			}
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Pet setFormToPet(Pet pet, PetForm petForm) {
		pet.setName(petForm.getName());
		pet.setAge(petForm.getAge());
		pet.setSex(petForm.getSex());
		pet.setCharmPoint(petForm.getCharmPoint());
		pet.setPostCord(petForm.getPostCord());
		pet.setAddress(petForm.getAddress());

		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		pet.setCreatedAt(timeStamp);
		pet.setUpdatedAt(timeStamp);
		
		return pet;
	}

	@Override
	public PetForm setPetToForm(PetForm petForm, Pet pet) {
		petForm.setId(pet.getId());
		petForm.setName(pet.getName());
		petForm.setAge(pet.getAge());
		petForm.setSex(pet.getSex());
		petForm.setCharmPoint(pet.getCharmPoint());
		petForm.setPostCord(pet.getPostCord());
		petForm.setAddress(pet.getAddress());
		petForm.setImage(pet.getImage());
		petForm.setCreatedAt(pet.getCreatedAt());

		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		petForm.setUpdatedAt(timeStamp);
		return petForm;
	}

	@Override
	public PetForm makePetForm(Pet pet) {
		PetForm petForm = new PetForm();
		this.setPetToForm(petForm, pet);
		return petForm;
	}

	@Override
	public Pet makePet(Pet pet, PetForm petForm, MultipartFile multipartFile, Principal principal) {
		pet.setId(petForm.getId());
		pet.setName(petForm.getName());
		pet.setAge(petForm.getAge());
		pet.setSex(petForm.getSex());
		pet.setCharmPoint(petForm.getCharmPoint());
		pet.setPostCord(petForm.getPostCord());
		pet.setAddress(petForm.getAddress());

		if (multipartFile.getOriginalFilename().isEmpty()) {
			pet.setImage(petForm.getImage());
		} else {
			try {
				this.settingImage(pet, multipartFile);
			} catch (IOException e) {
				System.out.println("イメージデータのエンコーディング時に問題が発生しました。");
				e.printStackTrace();
				return pet;
			}
		}

		pet.setCreatedAt(petForm.getCreatedAt());
		pet.setUpdatedAt(petForm.getUpdatedAt());
		pet.setOwner(ownerService.getCurrentUser(principal));
		return pet;
	}

}
