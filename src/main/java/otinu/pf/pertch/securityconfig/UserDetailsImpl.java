package otinu.pf.pertch.securityconfig;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import otinu.pf.pertch.model.Owner;

public class UserDetailsImpl implements UserDetails {
	private Owner user;
	
	public UserDetailsImpl(Owner user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 現状、管理者権限などは想定していないため、一旦何もしない
		// return AuthorityUtils.createAuthorityList("ROLE_" + this.user.getRolename());
		return null;
	}
	
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}