package com.example.pet.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.pet.UserSecurityDetails;
import com.example.pet.model.User;
import com.example.pet.repository.UserRepository;

@Service
public class UserSecurityDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public UserSecurityDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByEmail(mail);
		
		return userOptional.map(user -> new UserSecurityDetails(user))
				.orElseThrow(() -> new UsernameNotFoundException("該当のユーザーが見つかりませんでした"));
	}
}
