package com.intecs.beverage;

public class Sugar {
	
	private float quantity;
	
	public Sugar(float quantity) {

		this.quantity = quantity;

	}

	public float getQuantity() {
		return quantity;
	}

	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		
		if (!(obj instanceof Sugar)) return false;
		
		Sugar c = (Sugar) obj;
		boolean result = Float.compare(this.quantity, c.quantity) == 0;
		
		return result;
		
	}
	
	
}
