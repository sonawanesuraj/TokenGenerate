package com.app.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entity.UserEntity;
import com.app.repository.UserRepository;

@Service
public class AuthServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException{
		UserEntity userEntity = userRepository.findByEmailContainingIgnoreCase(email);

	    // Check if userEntity is null (user not found)
	    if (userEntity == null) {
	        throw new UsernameNotFoundException("User not found with email");
	    }

	    // Return UserDetails object for authentication
	    return new org.springframework.security.core.userdetails.User(
	            userEntity.getEmail(),
	            userEntity.getPassword(),
	            getAuthority(userEntity)
	    );
	}

	public Boolean comparePassword(String password, String hashPassword) {

		return passwordEncoder.matches(password, hashPassword);

	}
	
	private ArrayList<SimpleGrantedAuthority> getAuthority(UserEntity user) {
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

		return authorities;
	}
	}


