package com.example.pet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.pet.model.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Integer> {
	
	// ※更新系のカスタムクエリには、@Query + @Modifying が必要
	@Query("SELECT * FROM owner WHERE email= :email")
	public Optional<Owner> findByEmail(String email);
}
