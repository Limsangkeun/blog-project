package com.sk.blog.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sk.blog.model.user.ERole;
import com.sk.blog.model.user.User;
import com.sk.blog.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@GetMapping("/join")
	public String joinPage() {
		return "user/join";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "user/login";
	}
	
	@PostMapping("/join")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> joinProcess (User user) {
		Map<String, Object> resultMap = new HashMap<>();
		if(user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
			resultMap.put("message", "입력 정보를 확인해주세요.");
			resultMap.put("result", 0);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		}
		user.setRole(ERole.ADMIN);
		String message = "";
		int result = userService.regist(user);
		if(result == 1) {
			message = "회원가입이 완료되었습니다. 로그인해주세요";
		} else {
			message = "회원가입에 실패하였습니다.";
		}
		resultMap.put("message", message);
		resultMap.put("result", result);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
}
