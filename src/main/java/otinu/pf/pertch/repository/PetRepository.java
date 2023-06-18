package otinu.pf.pertch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import otinu.pf.pertch.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer>, JpaSpecificationExecutor<Pet> {
	
	@Query(value = "SELECT * FROM pet WHERE id = ?1", nativeQuery=true)
	public Optional<Pet> findById(Integer id);
	
	@Query(value = "SELECT * FROM pet WHERE id = ?1", nativeQuery=true)
	public Pet findByIdToPet(Integer id);

}
