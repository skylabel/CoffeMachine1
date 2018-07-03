package com.intecs.machine.exception;

public class OutOfAvailableCreditException extends MachineException {
	
	public OutOfAvailableCreditException() {
		super("Credito insufficiente.");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof OutOfAvailableCreditException)) return false;
		else return true;
	}
	
}
