package com.intecs.machine;

public class Credit implements Comparable<Credit>{
	
	private final float value;
	private static final float ZERO_CREDIT = 0f;
	
	public Credit() {
		
		this(ZERO_CREDIT);
	
	}
	
	public Credit(float _value) {
		
		this.value = _value; 		
		
	}
	
	public static Credit zero() {
		
		return new Credit();
		
	}

	public float getValue() {
		
		return value;
	
	}


	@Override
	public String toString() {
	
		return "Credit [value=" + value + "]";
	
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		
		if (!(obj instanceof Credit)) return false;
		
		Credit c = (Credit) obj;
		boolean result = Float.compare(this.value, c.value) == 0;
		
		return result;
		
	}
	
	
	
	public Credit sum(Credit c1) {
		Float result=c1.getValue()+this.getValue();
		return new Credit(result);
	}

	@Override
	public int compareTo(Credit credit) {
		
		int result=Float.compare(this.value, credit.value);
		return result;
	}
	
}
