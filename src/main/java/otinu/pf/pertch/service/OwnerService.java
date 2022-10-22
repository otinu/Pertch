package otinu.pf.pertch.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import otinu.pf.pertch.model.Owner;
import otinu.pf.pertch.repository.OwnerRepository;

@Service
public class OwnerService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	public void ownerRegistration(String username, String password, String name, String message, String contact) {
		String hashedPassword = passwordEncoder.encode(password);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		ownerRepository.saveAndFlush(new Owner(username, hashedPassword, name, message, contact, timestamp, timestamp));
	}
}