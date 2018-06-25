package com.intecs.machine.exception;

public class OutOfAvailableCredit extends MachineException {
	
	public OutOfAvailableCredit() {
		
		super("Credito insufficiente.");
		
	}

	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj) return true;
		
		if (!(obj instanceof OutOfAvailableCredit)) return false;
		else return true;
		
	}
	
}
