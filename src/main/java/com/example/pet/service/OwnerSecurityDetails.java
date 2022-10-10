package com.example.pet.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.pet.model.Owner;

// ログイン機能詳細のクラス
public class OwnerSecurityDetails implements UserDetails { // SpringSecurityのインタフェースを具体化

	private final Owner owner;

	public OwnerSecurityDetails(Owner owner) {
		this.owner = owner;
	}
	
	public Owner getLoginOwner() {
        return owner;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null ;
    }

    @Override
    public String getPassword() {
        return owner.getPassword();
    }

    // 認証に利用するユーザーのアカウント名を取得
    @Override
    public String getUsername() {
        return owner.getEmail();
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
