package org.serratec.authdemo.services;

import java.util.List;
import java.util.Optional;

import org.serratec.authdemo.entities.UserEntity;
import org.serratec.authdemo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	public UserEntity findById(Long userId) {
		Optional<UserEntity> user = userRepository.findById(userId);

		if (user.isEmpty()) {
			return null;
		}

		return user.get();
	}

	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	public UserEntity findByUsername(String username) {
		Optional<UserEntity> user = userRepository.findByUsername(username);

		if (user.isEmpty()) {
			return null;
		}

		return user.get();
	}

	public UserEntity save(UserEntity user) {
		user.setPassword(encoder.encode(user.getPassword()));

		userRepository.save(user);

		return user;
	}
}
