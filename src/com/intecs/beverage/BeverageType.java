package com.intecs.beverage;

import com.intecs.machine.Money;

public class BeverageType {
	
	
	private BeverageName beveragename;
	private Money cost;
	
	
	public BeverageType(BeverageName beveragename, Money cost) {
		if(beveragename==null)
			throw new NullPointerException("No beverage name");
		if(cost==null)
			throw new NullPointerException("No Money for Beverage Type");
		
		this.beveragename = beveragename;
		this.cost=cost;
	}
	public BeverageType() {
		this(new BeverageName(),Money.zero());
	}
	
	public BeverageType(BeverageName beveragename) {
		if(beveragename==null)
			throw new NullPointerException("No beverage name");
		
		
		this.beveragename = beveragename;
		this.cost=Money.zero();
	}
	public BeverageType(Money cost) {
		
		if(cost==null)
			throw new NullPointerException("No Money for Beverage Type");
		
		this.beveragename = new BeverageName();
		this.cost=cost;
	}

	public BeverageName getName() {
		return this.beveragename;
	}
	
	public Money getCost() {
		return this.cost;
	}
	
	public boolean equalsName(BeverageName obj) {
		boolean result = false;
		if (this.beveragename.equals(obj)) return true;
		return result;
	}
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
//	
//	
//	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (this == obj) return true;
		if (!(obj instanceof BeverageType)) return false;
		
		BeverageType c = (BeverageType) obj;
		if(beveragename.equals(c.beveragename) && cost.equals(c.cost)) result = true;
		return result;
	}
}
