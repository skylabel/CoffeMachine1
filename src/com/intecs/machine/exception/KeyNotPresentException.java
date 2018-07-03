package com.intecs.machine.exception;

public class KeyNotPresentException extends MachineException {
	
	public KeyNotPresentException() {
		super("Chiavetta non inserita.");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof KeyNotPresentException)) return false;
		else return true;
	}

}
