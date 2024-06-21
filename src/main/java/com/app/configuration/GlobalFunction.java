package com.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.entity.UserEntity;
import com.app.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

public class GlobalFunction {

	@Autowired
	static JwtTokenUtil jwtTokenUtil;

	@Autowired
	static UserRepository userRepository;

	@Autowired
	static HttpServletRequest request;

	public static Long getUserId(Long userId) {
		final String header = request.getHeader("Authorization");
		String requestToken = header.substring(7);
		final String email = jwtTokenUtil.getEmailFromToken(requestToken);
		UserEntity userEntity = userRepository.findByEmailContainingIgnoreCase(email);
		System.out.println("TOKEN :" + requestToken);
		return userEntity.getId();

	}
}