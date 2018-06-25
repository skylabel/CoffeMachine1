package com.intecs.machine;

public class Key {
	
	private Credit credit;
	private String id;
	
	public Key(Credit credit, String id) {
		
		this.credit = credit;
		this.id = id;
	
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
