package com.example.pet;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.pet.model.User;

// ログイン機能詳細のクラス
public class UserSecurityDetails implements UserDetails { // SpringSecurityのインタフェースを継承

	private final User user;
	private final Collection<? extends GrantedAuthority> authorities;
	
	public UserSecurityDetails(User user) {
		this.user = user;
		this.authorities = user.getRoleList()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.toList();
	}
	
	public User getLoginUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // 認証に利用するユーザーのアカウント名を取得
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // パスワードが期限切れでなければtrueを返す
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // ユーザーが有効であれば、trueを返す
    @Override
    public boolean isEnabled() {
        return true;
    }
}
