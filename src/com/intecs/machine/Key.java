package com.intecs.machine;

import com.intecs.machine.exception.CreditExceedsBound;
import com.intecs.machine.exception.FullCredit;

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
	
	public static Key full() {
		return new Key(KEY_BOUND);
	}
	
	public Money getCredit() {
		return credit;
	}
	
	private void setCredit(Money credit) {
		this.credit = credit;
	}

	public void increaseCredit(Money credit) throws CreditExceedsBound, FullCredit {
		Money total = credit.sum(getCredit());

 		if(total.compareTo(KEY_BOUND)<=0) 
			setCredit(total);
		else if (total.compareTo(KEY_BOUND) == 1 && KEY_BOUND.compareTo(getCredit()) == 1)
			throw new CreditExceedsBound();
		else
			throw new FullCredit();
	}
	
	public Boolean hasEnoughCredit(Money cost) {
		Boolean result = false;
		if (cost.compareTo(getCredit())<=0)
			result = true;
		return result;
	}
	
}
