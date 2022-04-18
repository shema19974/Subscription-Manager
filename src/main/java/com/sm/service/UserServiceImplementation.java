package com.sm.service;

import java.util.List;

import javax.transaction.Transactional;

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
public class UserServiceImplementation implements UserService{

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		log.info("Saving new user {} to the database", user.getName());
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
