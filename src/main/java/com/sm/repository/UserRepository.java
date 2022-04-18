package com.sm.repository;

import org.springframework.data.repository.CrudRepository;

import com.sm.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
