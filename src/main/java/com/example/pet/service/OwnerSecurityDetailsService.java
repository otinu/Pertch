package com.example.pet.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.pet.model.Owner;
import com.example.pet.repository.OwnerRepository;

@Service
public class OwnerSecurityDetailsService implements UserDetailsService {

	private final OwnerRepository ownerRepository;

	public OwnerSecurityDetailsService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Owner> ownerOptional = this.ownerRepository.findByEmail(email);
		
		return ownerOptional.map(owner -> new OwnerSecurityDetails(owner))
				.orElseThrow(() -> new UsernameNotFoundException("該当の飼い主が見つかりませんでした"));
	}
}
