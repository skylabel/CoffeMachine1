package com.intecs.machine.exception;

public class InvalidSugarLevel extends InvalidSelection {

	public InvalidSugarLevel() {
		
		super("Livello di zucchero non valido.");
		
	}
	
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj) return true;
		
		if (!(obj instanceof InvalidSugarLevel)) return false;
		else return true;
		
	}
}
