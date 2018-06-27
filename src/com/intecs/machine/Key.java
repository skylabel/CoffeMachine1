package com.intecs.machine;

public class Key {
	
	private Credit credit;
	private String id;
	final static Credit KEY_BOUND = new Credit(10f);
	
	
	public Key() {
		
		this(Credit.zero(), "");
		
	}
	
	public Key(Credit credit, String id) {
		
		this.credit = credit;
		this.id = id;
	
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

	public Credit getCredit() {
		
		return credit;
	
	}
	
	public void setCredit(Credit credit) {
	
		this.credit = credit;
	
	}
	
}
