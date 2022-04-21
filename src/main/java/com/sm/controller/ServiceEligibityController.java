package com.sm.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sm.domain.ServiceEligibility;
import com.sm.service.ServiceEligibiltyService;

import lombok.AllArgsConstructor;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/services")
@AllArgsConstructor
public class ServiceEligibityController {

private final ServiceEligibiltyService eligibilityService;
	
	@PostMapping
	public ResponseEntity<ServiceEligibility> saveServiceEligibility(@RequestBody ServiceEligibility service){
		//To get and change the response to created (by giving server path) and return uri		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/services").toUriString());
		return ResponseEntity.created(uri).body(eligibilityService.saveServiceEligibility(service));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<ServiceEligibility>> showServiceEligibility(@PathVariable(value = "id") int id){
        //To get and change the response to created (by giving server path) and return uri		
		return ResponseEntity.ok().body(eligibilityService.getServiceEligibility(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ServiceEligibility> updateServiceEligibility(@PathVariable(value = "id") int id, @RequestBody ServiceEligibility service){
		return ResponseEntity.ok().body(eligibilityService.updateServiceEligibility(service, id));
	}
	
	@RequestMapping(value = "/checker", method = RequestMethod.POST)
	public String checkEligibility(@RequestBody ServiceEligibility service) {
		return eligibilityService.checkServiceEligibility(service);
	}
	  
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<ServiceEligibility> allEligibility() {
		return eligibilityService.allServiceEligibility();
	}
}
