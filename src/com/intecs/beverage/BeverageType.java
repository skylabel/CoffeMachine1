package com.intecs.beverage;

public class BeverageType {
	
	private String name;
	
	public BeverageType(String _name) {
		this.name = _name;
	}
	
	public BeverageType() {
		this("");
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
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

	public static BeverageType of(String string) {
		return new BeverageType(string);
	}
	
}
