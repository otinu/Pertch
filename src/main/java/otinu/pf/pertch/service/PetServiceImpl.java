package otinu.pf.pertch.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import otinu.pf.pertch.model.Pet;
import otinu.pf.pertch.repository.PetRepository;

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
	
	@Override
	public Optional<Pet> selectById(Integer id) {
		return repository.findById(id);
	}
	
	@Override
	public void updatePet(Pet pet) {
		repository.save(pet);
	}
	
	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

}
