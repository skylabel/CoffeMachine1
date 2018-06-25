package com.intecs.machine.exception;

public class FullCredit extends MachineException {
	
	public FullCredit() {
		
		super("Limite credito gi� raggiunto.");
		
	}
	
	@Override
	public boolean equals(Object obj) {
	
		if (this == obj) return true;
		
		if (!(obj instanceof FullCredit)) return false;
		else return true;
		
	}

}
