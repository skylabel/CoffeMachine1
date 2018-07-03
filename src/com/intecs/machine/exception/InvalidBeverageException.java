package com.intecs.machine.exception;

public class InvalidBeverageException extends InvalidSelectionException {
	
	public InvalidBeverageException() {
		
		super("Bevanda inesistente.");
		
	}

	
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj) return true;
		
		if (!(obj instanceof InvalidBeverageException)) return false;
		else return true;
		
	}
}
