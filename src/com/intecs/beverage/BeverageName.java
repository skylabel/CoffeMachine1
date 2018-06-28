package com.intecs.beverage;

public class BeverageName {
	private String name;
	
	public BeverageName(String _name) {
		this.name = _name;
	}
	
	public BeverageName() {
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
		if (!(obj instanceof BeverageName)) return false;
		
		BeverageName c = (BeverageName) obj;
		if(name.equals(c.name)) result = true;
		return result;
	}

	public static BeverageName of(String string) {
		return new BeverageName(string);
	}
}
