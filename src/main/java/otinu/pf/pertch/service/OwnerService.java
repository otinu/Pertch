package otinu.pf.pertch.service;

import java.security.Principal;

import otinu.pf.pertch.entity.Owner;

public interface OwnerService {
	
	public void ownerRegistration(String username, String password, String name, String message, String contact);
	
	public Owner findByOwnerId(Integer id);
	
	public Owner findByName(String name);
	
	public Owner getCurrentUser(Principal principal);
	
}