package otinu.pf.pertch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import otinu.pf.pertch.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
	public Owner findByUsername(String username);
}
