package otinu.pf.pertch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import otinu.pf.pertch.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
	
	@Query(value = "SELECT * FROM owner WHERE id = ?1", nativeQuery=true)
	public Owner findByOwnerId(Integer id);
	
	@Query(value = "SELECT * FROM owner WHERE username = ?1", nativeQuery=true)
	public Owner findByUserName(String name);
	
	@Query(value = "SELECT * FROM owner WHERE owner_name = ?1", nativeQuery=true)
	public Owner findByOwnerName(String name);
	
	@Query(value = "SELECT COUNT(*) FROM owner WHERE username = ?1", nativeQuery=true)
	public Integer countRecordByUserName(String name);
	
}