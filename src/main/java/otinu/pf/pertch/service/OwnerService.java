package otinu.pf.pertch.service;

import java.security.Principal;

import otinu.pf.pertch.entity.Owner;
import otinu.pf.pertch.form.OwnerForm;

public interface OwnerService {

	public void insertOwner(String username, String password, String name, String message, String contact);

	public Owner findByOwnerId(Integer id);

	public Owner findByUserName(String name);
	
	public Integer countRecordByUserName(String userName);

	public Owner getCurrentUser(Principal principal);
	
	public void updateOwner(Owner owner);

	OwnerForm makeOwnerForm(Owner owner);

	OwnerForm setOwnerToForm(OwnerForm ownerForm, Owner owner);

	Owner makeOwner(OwnerForm ownerForm, Owner currentUser);

}