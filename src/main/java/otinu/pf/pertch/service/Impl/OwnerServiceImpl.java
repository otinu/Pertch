package otinu.pf.pertch.service.Impl;

import java.security.Principal;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import otinu.pf.pertch.entity.Owner;
import otinu.pf.pertch.form.OwnerForm;
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
		String subContact = "";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		repository.saveAndFlush(new Owner(username, hashedPassword, name, message, contact, subContact, timestamp, timestamp));
	}

	@Override
	public Owner findByUserName(String name) {
		return repository.findByUserName(name);
	}
	
	@Override
	public Owner getCurrentUser(Principal principal) {
		String loginUserName = principal.getName();
		Owner owner = repository.findByUserName(loginUserName);
		return owner;
	}

	@Override
	public Owner findByOwnerId(Integer id) {
		return repository.findByOwnerId(id);
	}
	
	@Override
	public Integer countRecordByUserName(String userName) {
		return repository.countRecordByUserName(userName);
	}
	
	public OwnerForm makeOwnerForm(Owner owner) {
		OwnerForm ownerForm = new OwnerForm();
		this.setOwnerToForm(ownerForm, owner);
		return ownerForm;
	}

	public OwnerForm setOwnerToForm(OwnerForm ownerForm, Owner owner) {
		ownerForm.setId(owner.getId());
		ownerForm.setOwnerName(owner.getOwnerName());
		ownerForm.setMessage(owner.getMessage());
		ownerForm.setContact(owner.getContact());
		ownerForm.setSubContact(owner.getSubContact());

		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		ownerForm.setUpdatedAt(timeStamp);
		return ownerForm;
	}

	@Override
	public Owner makeOwner(OwnerForm ownerForm, Owner currentUser) {
		Owner owner = new Owner();
		owner.setId(currentUser.getId());
		owner.setUsername(currentUser.getUsername());
		owner.setPassword(currentUser.getPassword());
		owner.setOwnerName(currentUser.getOwnerName());
		owner.setMessage(ownerForm.getMessage());
		owner.setContact(ownerForm.getContact());
		owner.setSubContact(ownerForm.getSubContact());
		owner.setCreatedAt(currentUser.getCreatedAt());
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		owner.setUpdatedAt(timeStamp);
		return owner;
	}

	@Override
	public void updateOwner(Owner owner) {
		repository.saveAndFlush(owner);
	}

}
