package com.intecs.beverage;

public class Beverage {
	
	private final BeverageProperties type;
	private final Sugar sugar;

	public Beverage(BeverageProperties type, Sugar sugar) {
		if(type==null) throw new NullPointerException("Type is null.");
		if(sugar==null) throw new NullPointerException("Sugar is null.");
		this.type = type;
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

	public BeverageProperties getType() {
		return this.type;
	}
	
	public Sugar getSugar() {
		return this.sugar;
	}
	
}
