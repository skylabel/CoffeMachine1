package com.intecs.machine.exception;

public class MachineIsOutOfServiceException extends MachineException {
	
	public MachineIsOutOfServiceException() {
			super("Macchina fuori servizio.");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof FullCreditException)) return false;
		else return true;
	}

}
