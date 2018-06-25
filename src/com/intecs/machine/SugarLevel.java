package com.intecs.machine;

public class SugarLevel {

	int level;
	final int maxLevel = 5;
	final int minLevel = 1;
	
	public SugarLevel() {

		this.level = 3;
	
	}
	
	public SugarLevel(int level) {

		this.level = level;
	
	}

	public int getLevel() {
	
		return level;
	
	}
	
	public Boolean setLevel(int selectedlevel) {
	
		Boolean result;
		
		if (selectedlevel > maxLevel) {
			
			level = maxLevel;
			result = false;
			
		} else if (selectedlevel < minLevel) {
			
			level = minLevel;
			result = false;
			
		} else {
			
			level = selectedlevel;
			result = true;
			
		}
		
		return result;
	
	}
	
	
	
	
	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		
		if (!(obj instanceof SugarLevel)) return false;
		
		SugarLevel c = (SugarLevel) obj;
		boolean result = this.level==c.level;		
		
		return result;
		
	}
	
}
