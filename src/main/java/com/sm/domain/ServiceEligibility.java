package com.sm.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;



@Entity
public class ServiceEligibility{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "Age cannot be empty")
	private int minimumSubscriberAge;
	private int minimumAgeOnNetwork;
	private double amountRecharged;
	private double amountUsed;	

	public ServiceEligibility() {
		super();
	}
	
	public ServiceEligibility(int minimumSubscriberAge, int minimumAgeOnNetwork, double amountRecharged,
			double amountUsed) {
		super();
		this.minimumSubscriberAge = minimumSubscriberAge;
		this.minimumAgeOnNetwork = minimumAgeOnNetwork;
		this.amountRecharged = amountRecharged;
		this.amountUsed = amountUsed;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMinimumSubscriberAge() {
		return minimumSubscriberAge;
	}
	public void setMinimumSubscriberAge(int minimumSubscriberAge) {
		this.minimumSubscriberAge = minimumSubscriberAge;
	}
	public int getMinimumAgeOnNetwork() {
		return minimumAgeOnNetwork;
	}
	public void setMinimumAgeOnNetwork(int minimumAgeOnNetwork) {
		this.minimumAgeOnNetwork = minimumAgeOnNetwork;
	}
	public double getAmountRecharged() {
		return amountRecharged;
	}
	public void setAmountRecharged(double amountRecharged) {
		this.amountRecharged = amountRecharged;
	}
	public double getAmountUsed() {
		return amountUsed;
	}
	public void setAmountUsed(double amountUsed) {
		this.amountUsed = amountUsed;
	}

	@Override
	public String toString() {
		return "ServiceEligibility [id=" + id + ", minimumSubscriberAge=" + minimumSubscriberAge
				+ ", minimumAgeOnNetwork=" + minimumAgeOnNetwork + ", amountRecharged=" + amountRecharged
				+ ", amountUsed=" + amountUsed + "]";
	}
	
	
	
}
