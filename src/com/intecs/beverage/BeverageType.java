package com.intecs.beverage;

public class BeverageType {
	
	private String name;
	private float cost;
	
	public BeverageType(String _name, float _cost) {
		
		this.name = _name;
		this.cost = _cost;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public float getCost() {
		
		return cost;
		
	}

	
	@Override
	public boolean equals(Object obj) {
		boolean result=false;
		if (this == obj) return true;
		
		if (!(obj instanceof BeverageType)) return false;
		
		BeverageType c = (BeverageType) obj;
	
	
		if(name.equals(c.name) && (cost==c.cost)) {
			result=true;
		}
		
		return result;
		
	}
	
	
}
