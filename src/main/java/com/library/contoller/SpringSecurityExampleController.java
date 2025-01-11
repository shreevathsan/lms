package com.library.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.User;
import com.library.repository.UserRepository;

@RestController
public class SpringSecurityExampleController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String home() {
		return ("<h1> Welcome </h1>");
	}

	@GetMapping("/user")
	public String user() {
		return ("<h1> Welcome User</h1>");
	}

	@GetMapping("/admin")
	public String admin() {
		return ("<h1> Welcome Admin</h1>");
	}

	@GetMapping("/users")
	public List<User> fetchAllUserDetails() {
		return userRepository.findAll();
	}

}
