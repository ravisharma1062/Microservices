package com.application.jpa.configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	private List<User> users = new ArrayList<>();
	
	public MyUserDetailsService() {
		
		Set<GrantedAuthority> admin = new HashSet<>();
		admin.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		Set<GrantedAuthority> user = new HashSet<>();
		user.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		users.add(new User("Ravi", "1234", admin));
		users.add(new User("Umesh", "1234", user));
		users.add(new User("Ashish", "1234", user));
		users.add(new User("Sevadas", "1234", user));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return users.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList()).get(0);
	}

}
