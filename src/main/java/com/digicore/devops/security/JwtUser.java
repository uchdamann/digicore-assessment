package com.digicore.devops.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.digicore.devops.models.Customer;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtUser implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3506963642690832808L;
	private Customer customer;
	private String password;
	private Collection<SimpleGrantedAuthority> authorities;
	private boolean enabled;
	private Date lastPasswordResetDate;
	
	public static JwtUserBuilder builder() {
		return new JwtUserBuilder();
	}

	@Override
	public String getUsername() {
		return customer.getAccountDetails().getAccountNumber();
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
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}