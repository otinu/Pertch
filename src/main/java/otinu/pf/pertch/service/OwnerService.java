package otinu.pf.pertch.service;

import org.springframework.stereotype.Service;

@Service
public interface OwnerService {
	
	public void ownerRegistration(String username, String password, String name, String message, String contact);
}