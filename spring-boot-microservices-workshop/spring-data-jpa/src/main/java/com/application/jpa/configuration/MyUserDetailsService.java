package com.application.jpa.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.jpa.dto.LoginDetails;
import com.application.jpa.exception.DataJPAException;
import com.application.jpa.repositories.LoginRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
//	private List<User> users = new ArrayList<>();
	
	@Autowired
	private LoginRepository loginRepository;
//	
//	public MyUserDetailsService() {
//		
//		Set<GrantedAuthority> admin = new HashSet<>();
//		admin.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		
//		Set<GrantedAuthority> user = new HashSet<>();
//		user.add(new SimpleGrantedAuthority("ROLE_USER"));
//		
//		users.add(new User("Ravi", "1234", admin));
//		users.add(new User("Umesh", "1234", user));
//		users.add(new User("Ashish", "1234", user));
//		users.add(new User("Sevadas", "1234", user));
//	}

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		try {
			return getUserDetails(loginId);
		} catch (DataJPAException e) {
			throw new UsernameNotFoundException("Invalid Login Id : " + loginId);
		}
	}
	
	private User getUserDetails(String loginId) throws DataJPAException {
		LoginDetails loginDetails = loginRepository.findById(loginId)
				.orElseThrow(() -> new DataJPAException("INVALID_USER_ID", "Invalid User Id"));
		
		Set<GrantedAuthority> userRole = new HashSet<>();
		userRole.add(new SimpleGrantedAuthority(loginDetails.getRole()));
		
		return new User(loginDetails.getLoginId(), loginDetails.getPassword(), userRole);
	}

}
