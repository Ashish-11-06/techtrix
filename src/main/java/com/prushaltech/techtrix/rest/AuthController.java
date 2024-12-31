package com.prushaltech.techtrix.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prushaltech.techtrix.dto.LoginUserRequest;
import com.prushaltech.techtrix.dto.LoginUserResponse;
import com.prushaltech.techtrix.service.UserService;
/*import com.prushaltech.techtrix.service.CustomUserDetailsService;
import com.prushaltech.techtrix.util.JwtUtil;*/

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	/*
	 * @Autowired private AuthenticationManager authenticationManager;
	 * 
	 * @Autowired private CustomUserDetailsService userDetailsService;
	 * 
	 * @Autowired private JwtUtil jwtUtil;
	 */
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginUserResponse> loginUser(@RequestBody LoginUserRequest userRequest) {
		LoginUserResponse userResponse = userService.loginUser(userRequest);
		if(userResponse.getHttpStatus() == HttpStatus.OK) {
			/*
			 * authenticationManager.authenticate( new
			 * UsernamePasswordAuthenticationToken(userRequest.getEmail(),
			 * userRequest.getPassword()) ); final UserDetails userDetails =
			 * userDetailsService.loadUserByUsername(userRequest.getEmail()); final String
			 * jwt = jwtUtil.generateToken(userDetails); userResponse.setToken(jwt);
			 */
			userResponse.setToken(null);
		}
		return ResponseEntity.ok(userResponse);
	}
	
}
