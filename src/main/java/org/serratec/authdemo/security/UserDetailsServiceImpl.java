package org.serratec.authdemo.security;

import java.util.ArrayList;
import java.util.Optional;

import org.serratec.authdemo.entities.UserEntity;
import org.serratec.authdemo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepository.findByUsername(username);

		if (user.isPresent()) {
			UserEntity u = user.get();
			return new User(u.getUsername(), u.getPassword(), new ArrayList<>());

		}

		throw new UsernameNotFoundException("User not found!");
	}

}