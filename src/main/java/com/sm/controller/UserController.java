package com.sm.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sm.domain.Role;
import com.sm.domain.User;
import com.sm.service.UserService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
	private final UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> findAllUsers(){
		// Returns all the users in the body of the response
		return ResponseEntity.ok().body(userService.findUsers());
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@RequestBody User user){
//       To get and change the response to created (by giving server path) and return uri		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
		return ResponseEntity.created(uri).body(userService.saveUser(user));
	}
	
	@PostMapping("/roles")
	public ResponseEntity<Role> saveRole(@RequestBody Role role){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toUriString());
		return ResponseEntity.created(uri).body(userService.saveRole(role));
	}
	
	@PostMapping("/roles/addtouser")
	public ResponseEntity<?> addRoleToUser(@RequestBody  RoleToUserForm roleForm){
		userService.addRoleToUser(roleForm.getUsername(), roleForm.getRoleName());
		return ResponseEntity.ok().build();
	}	
	
//	@PostMapping("/roles/addtouser")
//	public void refreshToken(HttpServletRequest request, HttpServletResponse response){
//		String authorizationHeader = request.getHeader("Authorization");
//	}
	@GetMapping("/checker")
	public String check() {
		return "Checked";
	}
	
}

@Getter
@Setter
class RoleToUserForm{
	private String username;
	private String roleName;
}
