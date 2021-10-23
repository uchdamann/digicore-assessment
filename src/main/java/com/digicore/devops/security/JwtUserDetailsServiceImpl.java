package com.digicore.devops.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digicore.devops.models.Customer;
import com.digicore.devops.utilities.AccountUtil;

import static com.digicore.devops.enums.Roles.*;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AccountUtil accountUtil;

	@Override
	public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		Customer customer = accountUtil.validateAccount(accountNumber);

		if (customer == null) {
			throw new UsernameNotFoundException("No such customer in record");
		}

		String role = "";

		if (customer.getRole().equals(CUSTOMER)) {
			role = "ROLE_CUSTOMER";
		}

		authorities.add(new SimpleGrantedAuthority(role));
		JwtUser user = JwtUser.builder().authorities(authorities).enabled(true).customer(customer).build();

		return user;
	}
}