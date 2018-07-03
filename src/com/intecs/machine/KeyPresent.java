package com.intecs.machine;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageProperties;
import com.intecs.beverage.BeverageType;
import com.intecs.beverage.Sugar;
import com.intecs.machine.exception.CreditExceedsBoundException;
import com.intecs.machine.exception.FullCreditException;
import com.intecs.machine.exception.InvalidBeverageException;
import com.intecs.machine.exception.KeyNotPresentException;
import com.intecs.machine.exception.MachineIsOutOfServiceException;
import com.intecs.machine.exception.OutOfAvailableCreditException;

public class KeyPresent extends InService {

	private Key key;
	
	public KeyPresent(Key key) {
		if (key == null) 
			throw new NullPointerException();
		
		this.key = key;
	}
	
	@Override
	public Beverage buy(BeverageType type,Machine machine) throws MachineIsOutOfServiceException, KeyNotPresentException, InvalidBeverageException, OutOfAvailableCreditException {
		Beverage beverage = null;
		Sugar sugar=new Sugar(getSugarLevel());
		
		BeverageProperties typetoBuy=machine.getBeveragesMap().get(type);     
		if(typetoBuy==null)
			throw new InvalidBeverageException();	
		
	   Money cost=machine.getBeverageCost(type);
	   if (!(key.hasEnoughCredit(cost))) 
		   throw new OutOfAvailableCreditException();
		key.decreaseCredit(cost);
		
		beverage = new Beverage(typetoBuy,sugar);   
		return beverage;
	}
	
	@Override
	public void chargeKey(Money credit) throws MachineIsOutOfServiceException, CreditExceedsBoundException, FullCreditException {
		key.increaseCredit(credit);	
	}
	
	@Override
	public void insertKey(Key _key, Machine machine) {
		//do nothing
	}
	
    @Override
	public void removeKey(Machine machine) {
    	machine.setState(new NoKey());
	}
	
}
