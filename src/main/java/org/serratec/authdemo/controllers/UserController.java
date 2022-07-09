package org.serratec.authdemo.controllers;

import org.serratec.authdemo.entities.UserEntity;
import org.serratec.authdemo.security.JwtUtil;
import org.serratec.authdemo.security.UserAuthenticationRequest;
import org.serratec.authdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;

	@GetMapping
	public ResponseEntity<UserEntity> findByUsername(@RequestParam String username) {
		return ResponseEntity.ok(userService.findByUsername(username));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserEntity> findById(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.findById(userId));
	}

	@PostMapping("/save")
	public ResponseEntity<UserEntity> save(@RequestBody UserEntity user) {
		return ResponseEntity.ok(userService.save(user));
	}

	@PostMapping("/authenticate")
	public String criarAutenticacao(@RequestBody UserAuthenticationRequest user) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (Exception e) {
			throw new Exception("Incorrect password!", e);
		}
		UserDetails usuarioDetalhe = userDetailsService.loadUserByUsername(user.getUsername());
		String token = jwtUtil.generateToken(usuarioDetalhe);
		return token;
	}
}
