package com.intecs.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageFactory;
import com.intecs.beverage.BeverageName;
import com.intecs.beverage.BeverageType;
import com.intecs.beverage.Cappuccino;
import com.intecs.beverage.Chocolate;
import com.intecs.beverage.Coffe;
import com.intecs.beverage.Latte;
import com.intecs.beverage.Sugar;
import com.intecs.beverage.Te;
import com.intecs.machine.exception.*;


public class Machine {

	private Key key;
	private SugarLevel sugarLevel;
	private Configurator configurator;
	private BeverageFactory factory;
	private List<BeverageType> beverageTypes;


	public Machine() {
		key = null;
		sugarLevel = new SugarLevel();
		configurator=Configurator.getInstance();
		beverageTypes=configurator.initizlizeBeverageTypes();
	
		factory=new BeverageFactory();
      
	}

	public void insertKey(Key _key) {
		key = _key;
	}

	public Key removeKey() {
		Key result = key;
		key = null;
		System.out.println("Chiave rimossa.");
		return result;
	}

	public void setSugarLevel(int level) throws InvalidSugarLevel {
		if (!(sugarLevel.setLevel(level)))
			throw new InvalidSugarLevel();
	}

	public Beverage buy(BeverageName name) throws KeyNotPresent, OutOfAvailableCredit, InvalidBeverage {
		Beverage beverage = null;
		Sugar sugar=new Sugar(sugarLevel);

		if (!(checkKey()))
			throw new KeyNotPresent();	
		
		int index = getIndexByName(name);
		if(index==-1)
			throw new InvalidBeverage();
		
		BeverageType resultQuery = beverageTypes.get(index);
		Money cost = getBeverageCost(name);
		
		if (!(checkCredit(cost))) 
			throw new OutOfAvailableCredit();
		
		beverage = factory.createBeverage(resultQuery, sugar);
		return beverage;
	}
	
	public void chargeKey(Money credit) throws KeyNotPresent, CreditExceedsBound, FullCredit {

		if (!(checkKey()))
			throw new KeyNotPresent();

		Money total = credit.sum(key.getCredit());

 		if(total.compareTo(Key.KEY_BOUND)<=0) {
			key.setCredit(total);
		}
		else if (total.compareTo(Key.KEY_BOUND) == 1 && Key.KEY_BOUND.compareTo(key.getCredit()) == 1)
			throw new CreditExceedsBound();
		
		else
			throw new FullCredit();

	}

	private Boolean checkKey() {
		Boolean result = false;

		if (key != null)
			result = true;

		return result;
	}
	
	private Boolean checkCredit(Money cost) {
		Boolean result = false;

		if (cost.compareTo(key.getCredit())<=0)
			result = true;

		return result;
	}

	public SugarLevel getSugarLevel() {
		return this.sugarLevel;
	}

	public List<BeverageName> getBeverageNameList() {
		List<BeverageName> names = new ArrayList<>();
		for (BeverageType bname: beverageTypes) {
			
			names.add(bname.getName());
		}
		
		return names;
	}

	public Money getBeverageCost(BeverageName name) throws InvalidBeverage {
		
		Money cost = null;
		for (BeverageType btype: beverageTypes) {
			if (btype.equalsName(name))
				cost = btype.getCost();
		}
		
		if (cost==null)
			throw new InvalidBeverage();
		
		return cost;
	}
	
	private int getIndexByName(BeverageName name) {
		int idx = -1;
		for(int i=0; i < beverageTypes.size(); i++) {
			if (name.equals(beverageTypes.get(i).getName())) 
				return i;
		}
		return idx;
	}

	public boolean isValid(BeverageName name) {
		boolean result = false;
		int idx = getIndexByName(name);
		result = idx != -1;
		return result;
	}
}
