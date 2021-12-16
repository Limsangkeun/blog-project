package com.sk.blog.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sk.blog.model.user.User;
import com.sk.blog.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public int regist(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
		
	}
}
