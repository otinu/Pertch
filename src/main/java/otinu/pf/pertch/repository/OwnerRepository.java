package otinu.pf.pertch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import otinu.pf.pertch.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
	
	@Query(value = "SELECT * FROM owner WHERE username = ?1", nativeQuery=true)
	public Owner findByName(String name);
	
}