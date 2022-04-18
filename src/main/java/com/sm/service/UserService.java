package com.sm.service;

import java.util.List;


import com.sm.domain.Role;
import com.sm.domain.User;

public interface UserService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	User findUser(String username);
	List<User> findUsers();
}
