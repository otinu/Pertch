package otinu.pf.pertch.service.Impl;

import java.security.Principal;
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
	private OwnerRepository repository;

	@Override
	public void insertOwner(String username, String password, String name, String message, String contact) {
		String hashedPassword = passwordEncoder.encode(password);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		repository.saveAndFlush(new Owner(username, hashedPassword, name, message, contact, timestamp, timestamp));
	}

	@Override
	public Owner findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Owner getCurrentUser(Principal principal) {
		String loginUserName = principal.getName();
		Owner owner = repository.findByName(loginUserName);
		return owner;
		//return repository.findByName(loginUserName);
	}

	@Override
	public Owner findByOwnerId(Integer id) {
		return repository.findByOwnerId(id);
	}

}
