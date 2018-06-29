package com.intecs.machine.exception;

public class MachineIsOutOfService extends MachineException {
	
	public MachineIsOutOfService() {
			super("Macchina fuori servizio.");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if (!(obj instanceof FullCredit)) return false;
		else return true;
	}

}
