package com.intecs.beverage;

public class BeverageType {
	private String name;
	
	public BeverageType(String _name) {
		this.name = _name;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (this == obj) return true;
		if (!(obj instanceof BeverageType)) return false;
		
		BeverageType c = (BeverageType) obj;
		if(name.equals(c.name)) result = true;
		return result;
	}
}
