package com.intecs.machine;

public class Key {
	
	private Money credit;
	private String id;
	final static Money KEY_BOUND = new Money(10f);
	
	
	public Key() {
		
		this(Money.zero(), "");
		
	}
	
	public Key(Money credit, String id) {
		if(credit==null)
			throw new NullPointerException();
		
		this.credit = credit;
		this.id = id;
		
	
	}
	
	public Key(Money credit) {
		this(credit, "");
		
	}

	public static Key empty() {

		return new Key();
	
	}
	
	public String getId() {
		
		return id;
		
	}
	
	public void setId(String _id) {
		
		this.id = _id;
		
	}

	public Money getCredit() {
		
		return credit;
	
	}
	
	public void setCredit(Money credit) {
	
		this.credit = credit;
	
	}
	
}
