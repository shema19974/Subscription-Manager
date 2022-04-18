package com.sm.repository;

import org.springframework.data.repository.CrudRepository;

import com.sm.domain.Role;

public interface UserRoleRepository extends CrudRepository<Role, Long> {
	Role findByName(String name);
}
