package com.intecs.machine.exception;

public class InvalidSugarLevelException extends InvalidSelectionException {

	public InvalidSugarLevelException() {
		
		super("Livello di zucchero non valido.");
		
	}
	
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj) return true;
		
		if (!(obj instanceof InvalidSugarLevelException)) return false;
		else return true;
		
	}
}
