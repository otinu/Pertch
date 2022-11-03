package otinu.pf.pertch.service.Impl;

import java.io.IOException;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import otinu.pf.pertch.entity.Pet;
import otinu.pf.pertch.repository.PetRepository;
import otinu.pf.pertch.service.PetService;

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

}
