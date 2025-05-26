package com.a2a2lab.common.config;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomUserDetails implements OAuth2User, UserDetails {

    private String email;
    private String password;
    private String name;
    private String memberId;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public CustomUserDetails(String email, String password, String name, String memberId,
                             Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.memberId = memberId;
        this.authorities = authorities;
    }
    
    public CustomUserDetails(String email, String password, String name, String memberId,
            Collection<? extends GrantedAuthority> authorities,
            Map<String, Object> attributes) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.memberId = memberId;
		this.authorities = authorities;
		this.attributes = attributes;
	}

    public String getName() {
        return name;
    }
    
    public String getMemberId() {
        return memberId;
    }
    
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
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
				+ authorities + ", attributes=" + attributes + "]";
	}
    
    
}
