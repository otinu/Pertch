package com.example.pet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.pet.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	// ※更新系のカスタムクエリには、@Query + @Modifying が必要
	
	@Query("SELECT * FROM public.\"user\" WHERE email= :email")
	Optional<User> findByEmail(String email);
}
