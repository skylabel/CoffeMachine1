package com.intecs.machine;

import com.intecs.machine.exception.CreditExceedsBoundException;
import com.intecs.machine.exception.FullCreditException;
import com.intecs.machine.exception.OutOfAvailableCreditException;

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
		if(credit.compareTo(new Money(0f))==-1){
			throw new IllegalArgumentException();
		}
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
	
	public String getId() {
		return id;
	}
	
	private void setCredit(Money credit) {
		this.credit = credit;
	}

	public void increaseCredit(Money credit) throws CreditExceedsBoundException, FullCreditException {
		Money total = credit.sum(getCredit());
 		if(total.compareTo(KEY_BOUND)<=0) 
			setCredit(total);
		else if (total.compareTo(KEY_BOUND) == 1 && KEY_BOUND.compareTo(getCredit()) == 1)
			throw new CreditExceedsBoundException();
		else
			throw new FullCreditException();
	}
	
	public void decreaseCredit(Money cost) throws OutOfAvailableCreditException {
		if(credit.compareTo(cost)<0)
			throw new OutOfAvailableCreditException();
		Money total = credit.sub(cost);
		setCredit(total);
	}
	
	public Boolean hasEnoughCredit(Money cost) {
		Boolean result = false;
		if (cost.compareTo(getCredit())<=0)
			result = true;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Key)) return false;
		
		Key c = (Key) obj;
		boolean result = credit.equals(c.getCredit()) && id.equals(c.getId());
		return result;
	}
	
}
