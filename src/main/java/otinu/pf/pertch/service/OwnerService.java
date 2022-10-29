package otinu.pf.pertch.service;

import otinu.pf.pertch.entity.Owner;

public interface OwnerService {
	
	public void ownerRegistration(String username, String password, String name, String message, String contact);
	
	public Owner findByName(String name);
}