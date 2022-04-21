package com.sm.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServiceHelper {
	private int minimumSubscriberAge;
	private int minimumAgeOnNetwork;
	private double amountRecharged;
	private double amountUsed;	
}
