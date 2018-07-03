package com.intecs.machine.exception;

public class FullCreditException extends MachineException {
	
	public FullCreditException() {
		
		super("Limite credito già raggiunto.");
		
	}
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj) return true;
		
		if (!(obj instanceof FullCreditException)) return false;
		else return true;
		
	}

}
