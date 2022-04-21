package com.sm.service;

public class Validator {
	public boolean validateAmoutUsed(double amountRecharge, double amountUsed) {
		if(amountRecharge > amountUsed)
			return true;
		else 
			return false;
	}
}
