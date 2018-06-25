package com.intecs.beverage;

public class Beverage {
	
	private BeverageType type;
	private Sugar sugar;

	public Beverage(BeverageType _type, Sugar sugar) {
		
		this.type = _type;
		this.sugar = sugar;
	
	}
	
	public void getName() {
		
		System.out.println(this.type.getName());
		
	}
	
	public void getSugar() {
	
		System.out.println(this.sugar.getQuantity());
	
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result=false;
		if (this == obj) return true;
		
		if (!(obj instanceof Beverage)) return false;
		
		Beverage c = (Beverage) obj;
		System.out.println("-->>>> Equals Type");
		this.getName();
		c.getName();
		
		System.out.println("-->>>> Equals sugar");
		this.getSugar();
		c.getSugar();
		
		if(type.equals(c.type) && sugar.equals(c.sugar)) result=true;
		
		return result;
		
	}
	
}
