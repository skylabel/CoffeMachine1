package com.intecs.machine.exception;

public class KeyNotPresent extends MachineException {
	
	public KeyNotPresent() {
		
		super("Chiavetta non inserita.");
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		
		if (!(obj instanceof KeyNotPresent)) return false;
		else return true;
			
	}

}
