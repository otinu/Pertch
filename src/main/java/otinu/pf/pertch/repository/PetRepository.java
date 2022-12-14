package otinu.pf.pertch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import otinu.pf.pertch.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {
	
	@Query(value = "SELECT * FROM pet WHERE id = ?1", nativeQuery=true)
	public Optional<Pet> findById(Integer id);

}
