package com.intecs.machine;

import java.util.HashSet;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageProperties;
import com.intecs.beverage.BeverageType;
import com.intecs.beverage.Sugar;
import com.intecs.machine.exception.CreditExceedsBound;
import com.intecs.machine.exception.FullCredit;
import com.intecs.machine.exception.InvalidBeverage;
import com.intecs.machine.exception.KeyNotPresent;
import com.intecs.machine.exception.MachineException;
import com.intecs.machine.exception.MachineIsOutOfService;
import com.intecs.machine.exception.OutOfAvailableCredit;

public class KeyPresent extends InService {

	private Key key;
	
	public KeyPresent(Key key) {
		if (key == null) 
			throw new NullPointerException();
		
		this.key = key;
	}
	
	@Override
	public Beverage buy(BeverageType type,Machine machine) throws MachineIsOutOfService, KeyNotPresent, InvalidBeverage, OutOfAvailableCredit {
		Beverage beverage = null;
		Sugar sugar=new Sugar(getSugarLevel());
		
		BeverageProperties typetoBuy=machine.getBeverages().get(type);     //bTypes.get(name);
		if(typetoBuy==null)
			throw new InvalidBeverage();	
		
	   Money cost=machine.getBeverageCost(type);
		if (!(key.hasEnoughCredit(cost))) 
			throw new OutOfAvailableCredit();
		
		beverage = new Beverage(typetoBuy,sugar);    //factory.createBeverage(resultQuery, sugar);
		return beverage;
	}
	
	@Override
	public void chargeKey(Money credit) throws MachineIsOutOfService, CreditExceedsBound, FullCredit {
		key.increaseCredit(credit);	
	}
	
	
	@Override
	public void insertKey(Key _key, Machine machine) {
		//do nothing
//		throw new MachineException();
		
	}
	
    @Override
	public void removeKey(Machine machine) {
    	machine.setState(new NoKey());
	}
	
}
