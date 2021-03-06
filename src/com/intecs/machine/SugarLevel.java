package com.intecs.machine;

public class SugarLevel {

	private int level;
	private final int maxLevel = 5;
	private final int minLevel = 1;
	
	public SugarLevel() {
		this.level = 3;
	}
	
	public SugarLevel(int level) {
		if ((Integer)level==null) 
			throw new NullPointerException("level is null");
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
