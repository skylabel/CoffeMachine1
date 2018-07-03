package com.intecs.beverage;

import com.intecs.machine.Money;

public class BeverageProperties {
	
	private BeverageType beveragetype;
	private Money cost;
	
	public BeverageProperties(BeverageType beveragename, Money cost) {
		if(beveragename==null)
			throw new NullPointerException("No beverage name");
		if(cost==null)
			throw new NullPointerException("No Money for Beverage Type");
		
		this.beveragetype = beveragename;
		this.cost=cost;
	}
	public BeverageProperties() {
		this(new BeverageType(),Money.zero());
	}
	
	public BeverageProperties(BeverageType beveragename) {
		if(beveragename==null)
			throw new NullPointerException("No beverage name");
		
		
		this.beveragetype = beveragename;
		this.cost=Money.zero();
	}
	public BeverageProperties(Money cost) {
		
		if(cost==null)
			throw new NullPointerException("No Money for Beverage Type");
		
		this.beveragetype = new BeverageType();
		this.cost=cost;
	}

	public BeverageType getName() {
		return this.beveragetype;
	}
	
	public Money getCost() {
		return this.cost;
	}
	
	public boolean equalsName(BeverageProperties obj) {
		boolean result = false;
		if (this == obj) return true;
		if (!(obj instanceof BeverageProperties)) return false;
		
		BeverageProperties c = (BeverageProperties) obj;
		if(beveragetype.equals(c.beveragetype)) result = true;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (this == obj) return true;
		if (!(obj instanceof BeverageProperties)) return false;
		
		BeverageProperties c = (BeverageProperties) obj;
		if(beveragetype.equals(c.beveragetype) && cost.equals(c.cost)) result = true;
		return result;
	}

}
