package com.sm.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sm.domain.Role;
import com.sm.domain.User;
import com.sm.repository.UserRepository;
import com.sm.repository.UserRoleRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService{

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		if(user == null) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		}else {
			log.info("User found in the database");
		}
		Collection<SimpleGrantedAuthority> autorities = new ArrayList<>();
		user.getRoles().forEach(role->{
			autorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(), autorities);
	}
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		log.info("Saving new user {} to the database", user.getFirstName());
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		log.info("Saving new role {} to the database", role.getName());
		return userRoleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		// TODO Auto-generated method stub
		log.info("Saving the role {} to the user {} in the database with ", roleName, username);
		User user = userRepository.findByUsername(username);
		Role role = userRoleRepository.findByName(roleName);
		user.getRoles().add(role); // Add the role to the user 
		
	}

	@Override
	public User findUser(String username) {
		// TODO Auto-generated method stub
		log.info("Retrieving user {}", username);
		log.info("Saving new ge to the database");
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findUsers() {
		// TODO Auto-generated method stub
		log.info("Retrieving all users");
		return (List<User>) userRepository.findAll();
	}

}
