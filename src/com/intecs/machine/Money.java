package com.intecs.machine;

public class Money implements Comparable<Money>{
	
	private final Float value;
	private static final float ZERO_CREDIT = 0f;
	
	public Money() {
		this(ZERO_CREDIT);
	}
	
	public Money(Float value) {
		this.value = value; 		
	}
	
	public static Money zero() {
		return new Money();
	}


	@Override
	public String toString() {
		return "Credit [value=" + value + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Money)) return false;
		
		Money c = (Money) obj;
		boolean result = Float.compare(this.value, c.value) == 0;
		return result;
	}
	
	public Money sum(Money c1) {
		Float result=c1.value+this.value;
		return new Money(result);
	}

	@Override
	public int compareTo(Money credit) {
		int result=Float.compare(this.value, credit.value);
		return result;
	}
	
}
