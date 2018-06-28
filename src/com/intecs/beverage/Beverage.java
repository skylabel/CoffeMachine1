package com.intecs.beverage;

public class Beverage {
	private final BeverageType type;
	private final Sugar sugar;

	public Beverage(BeverageType _type, Sugar sugar) {
		this.type = _type;
		this.sugar = sugar;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (this == obj) return true;
		if (!(obj instanceof Beverage)) return false;

		Beverage c = (Beverage) obj;
		if(type.equals(c.type) && sugar.equals(c.sugar)) result = true;
		return result;
	}

	public BeverageType getType() {
		return this.type;
	}
	
	public Sugar getSugar() {
		return this.sugar;
	}
}
