package com.sm.service;

import java.util.List;
import java.util.Optional;

import com.sm.domain.ServiceEligibility;

public interface ServiceEligibiltyService {
	ServiceEligibility saveServiceEligibility(ServiceEligibility service);
	String updateServiceEligibility(ServiceEligibility service, int id);
	Optional<ServiceEligibility> getServiceEligibility(int id);
	List<ServiceEligibility> allServiceEligibility();
	String checkServiceEligibility(ServiceEligibility helper);
}
