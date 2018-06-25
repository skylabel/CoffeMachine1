package com.intecs.machine.exception;

public class CreditExceedsBound extends MachineException {

	public CreditExceedsBound() {
		
		super("Il nuovo credito eccederebbe la soglia.");
		
	}
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj) return true;
		
		if (!(obj instanceof CreditExceedsBound)) return false;
		else return true;
		
	}
	
}
