package otinu.pf.pertch.service.Impl;

import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import otinu.pf.pertch.Constant;
import otinu.pf.pertch.entity.Pet;
import otinu.pf.pertch.entity.PetComment;
import otinu.pf.pertch.form.PetForm;
import otinu.pf.pertch.repository.PetCommentRepository;
import otinu.pf.pertch.repository.PetRepository;
import otinu.pf.pertch.service.OwnerService;
import otinu.pf.pertch.service.PetService;

@Service
@Transactional
public class PetServiceImpl implements PetService {
	
	@Autowired
	PetRepository petRepository;
	
	@Autowired
	PetCommentRepository petCommentRepository;
	
	@Autowired
	OwnerService ownerService;

	@Override
	public Iterable<Pet> selectAll() {
		return petRepository.findAll();
	}

	@Override
	public void insertPet(Pet pet) {
		petRepository.saveAndFlush(pet);
	}
	
	@Override
	public Optional<Pet> findById(Integer id) {
		return petRepository.findById(id);
	}
	
	@Override
	public Pet findByIdToPet(Integer id) {
		return petRepository.findByIdToPet(id);
	}
	
	@Override
	public void updatePet(Pet pet) {
		petRepository.saveAndFlush(pet);
	}
	
	@Override
	public void deleteById(Integer id) {
		petRepository.deleteById(id);
	}

	@Override
	public void settingImage(Pet pet, MultipartFile multipartFile) throws IOException {
		String base64;
		try {
			base64 = new String(Base64.encodeBase64(multipartFile.getBytes()), Constant.DATA_FORMAT);
			String imageType = multipartFile.getContentType();
			if(imageType.equals(Constant.IMAGE_PNG)) {
				pet.setImage(Constant.BASE64_PNG + base64);
			} else if(imageType.equals(Constant.IMAGE_JPEG)) {
				pet.setImage(Constant.BASE64_JPEG + base64);
			} else if(imageType.equals(Constant.IMAGE_GIF)) {
				pet.setImage(Constant.BASE64_GIF + base64);
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
	public PetForm makePetForm(Pet pet) {
		PetForm petForm = new PetForm();
		this.setPetToForm(petForm, pet);
		return petForm;
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
		petForm.setOwner(pet.getOwner());

		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		petForm.setUpdatedAt(timeStamp);
		return petForm;
	}

	@Override
	public Pet makePet(Pet pet, PetForm petForm, MultipartFile multipartFile, Principal principal) throws IOException {
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
				throw e;
			}
		}

		pet.setCreatedAt(petForm.getCreatedAt());
		pet.setUpdatedAt(petForm.getUpdatedAt());
		pet.setOwner(ownerService.getCurrentUser(principal));
		return pet;
	}

	@Override
	public List<PetComment> findPetComment(Integer petId) {
		return petCommentRepository.findAllByPetId(petId);
	}

}
