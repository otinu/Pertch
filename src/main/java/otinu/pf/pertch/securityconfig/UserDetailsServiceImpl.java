package otinu.pf.pertch.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import otinu.pf.pertch.entity.Owner;
import otinu.pf.pertch.repository.OwnerRepository;

// ★ 具体化やオーバーライドの都合上、「User」から「Owner」への変更は不可

@Service
public class UserDetailsServiceImpl implements UserDetailsService { //★
	@Autowired
	private OwnerRepository ownerRepository;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{ //★
		Owner owner = ownerRepository.findByUserName(username);
		if(owner == null) {
			throw new UsernameNotFoundException("Not Found");
		}
		return new UserDetailsImpl(owner);
	}
}
