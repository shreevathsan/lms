package com.library.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.AuthenticationRequest;
import com.library.dto.AuthenticationResponse;
import com.library.jwt.JWTUtils;
import com.library.spring.security.MyUserDetailsService;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JWTUtils jwtUtils;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (UsernameNotFoundException e) {
			throw new Exception("Invalid userName/password");
		}

		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());

		AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtUtils.generateToken(userDetails));
		return ResponseEntity.ok(authenticationResponse);
	}

}
