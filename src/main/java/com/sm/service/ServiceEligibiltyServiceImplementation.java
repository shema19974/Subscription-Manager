package com.sm.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sm.domain.ProductAmount;
import com.sm.domain.ServiceEligibility;
import com.sm.repository.ServiceEligibilityRepository;

@Service
@AllArgsConstructor
@Slf4j
public class ServiceEligibiltyServiceImplementation implements ServiceEligibiltyService{
	
	private final ServiceEligibilityRepository repository;
	
	@Override
	public ServiceEligibility saveServiceEligibility(ServiceEligibility service) {
		return repository.save(service);
	}

	@Override
	public String updateServiceEligibility(ServiceEligibility service, int id) {
		// find the service and update it
		String message = "";
		ServiceEligibility oldService = repository.findById(id).get();
		if(oldService == null) {
			log.error("No such criteria defined");
			throw new IllegalStateException("No such criteria diffined");
		}
		try {
			oldService.setMinimumSubscriberAge(service.getMinimumSubscriberAge());
			oldService.setMinimumAgeOnNetwork(service.getMinimumAgeOnNetwork());
			oldService.setAmountRecharged(service.getAmountRecharged());
			oldService.setAmountUsed(service.getAmountUsed());	
			repository.save(oldService);
			log.info("Service updated successfully.");
		}catch (Exception e) {
			message = "Service does not exist";
			log.error(message);
			throw new NoSuchElementException(message);
		}
		
		return message;
		
	}

	@Override
	public Optional<ServiceEligibility> getServiceEligibility(int id) {
		return Optional.of(repository.findById(id).orElseThrow(()->new IllegalArgumentException("Service not Found")));
	}


	@Override
	public String checkServiceEligibility(ServiceEligibility helper) {
		String message = "";
		Optional<ServiceEligibility> savedService = repository.findById(1);
		
		if(helper.getMinimumSubscriberAge() < savedService.get().getMinimumSubscriberAge() || helper.getMinimumAgeOnNetwork() < savedService.get().getMinimumAgeOnNetwork()) {
			message = "You are not eligible to any service.";
			System.out.println(message);
			
		}else {
			if((helper.getAmountUsed() > savedService.get().getAmountUsed() + 100 && helper.getAmountUsed() <= savedService.get().getAmountUsed() + 200) && (helper.getAmountRecharged() >= savedService.get().getAmountRecharged() + 100 && helper.getAmountRecharged() <= savedService.get().getAmountRecharged() + 200 )) {
				message = "You are eligible to "+ ProductAmount.ONE_THOURSAND;
				System.out.println(message);
				
			} else if((helper.getAmountUsed() >= savedService.get().getAmountUsed() + 200 && helper.getAmountUsed() <= savedService.get().getAmountUsed() + 500) && (helper.getAmountRecharged() >= savedService.get().getAmountRecharged() + 200 && helper.getAmountRecharged() <= savedService.get().getAmountRecharged() + 500 )) {
				message = "You are eligible to "+ ProductAmount.ONE_THOURSAND + " and "+ ProductAmount.TWO_THOURSAND ;
				System.out.println(message);
			}
			else {
				message = "You are eligible to "+ ProductAmount.TWO_THOURSAND + " and "+ ProductAmount.THEE_THOURSAND + " and "+ ProductAmount.THEE_THOURSAND ;
				System.out.println(message);
			}
		}
		return message;
	}

	@Override
	public List<ServiceEligibility> allServiceEligibility() {
		return (List<ServiceEligibility>) repository.findAll();
	}

	



	
	
}