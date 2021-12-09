package com.sk.blog.temp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.blog.model.ERole;
import com.sk.blog.model.User;
import com.sk.blog.repository.UserRepository;

@RestController
public class DummyController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/dummy/join")
	public String join (User user) {
		System.out.print(user.toString());
		user.setRole(ERole.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다 : " + user.toString();
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable(name = "id") Long id) {
		User targetUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다.. id : "+id));
		return targetUser;
	}
	
	@GetMapping("/dummy/user")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user/page")
	public Page<User> pageList (@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> users = userRepository.findAll(pageable);
		
		return users;
	}
	
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User requestUser) {
		User targetUser = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No Such UserId : "+id));
		targetUser.setPassword(requestUser.getPassword());
		targetUser.setEmail(requestUser.getEmail());
		
		userRepository.save(targetUser);
		
		return targetUser;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable Long id) {
		User u = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No Such User id : "+id));
		userRepository.delete(u);
		return "삭제되었습니다. (ID : "+id+" )";
	}
	
}
