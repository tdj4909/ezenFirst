package com.a2a2lab.common.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private String email;
    private String password;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String email, String password, String name,
                             Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.authorities = authorities;
    }

    public String getName() {
        return name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

	@Override
	public String toString() {
		return "CustomUserDetails [email=" + email + ", password=[PROTECTED], name=" + name + ", authorities="
				+ authorities + "]";
	}
    
    
}
