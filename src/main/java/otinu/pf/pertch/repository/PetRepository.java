package otinu.pf.pertch.repository;

import org.springframework.data.repository.CrudRepository;

import otinu.pf.pertch.entity.Pet;

public interface PetRepository extends CrudRepository<Pet, Integer> {

}
