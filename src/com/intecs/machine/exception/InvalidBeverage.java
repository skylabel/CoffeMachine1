package com.intecs.machine.exception;

public class InvalidBeverage extends InvalidSelection {
	
	public InvalidBeverage() {
		
		super("Bevanda inesistente.");
		
	}

	
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj) return true;
		
		if (!(obj instanceof InvalidBeverage)) return false;
		else return true;
		
	}
}
