package com.intecs.machine.exception;

public class CreditExceedsBoundException extends MachineException {

	public CreditExceedsBoundException() {
		
		super("Il nuovo credito eccederebbe la soglia.");
		
	}
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj) return true;
		
		if (!(obj instanceof CreditExceedsBoundException)) return false;
		else return true;
		
	}
	
}
