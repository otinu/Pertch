package otinu.pf.pertch.service.Impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import otinu.pf.pertch.entity.Owner;
import otinu.pf.pertch.repository.OwnerRepository;
import otinu.pf.pertch.service.OwnerService;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private OwnerRepository ownerRepository;

	@Override
	public void ownerRegistration(String username, String password, String name, String message, String contact) {
		String hashedPassword = passwordEncoder.encode(password);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		ownerRepository.saveAndFlush(new Owner(username, hashedPassword, name, message, contact, timestamp, timestamp));
	}

}
