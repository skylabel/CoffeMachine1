package com.intecs.machine;

import java.util.HashMap;
import java.util.Map;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageFactory;
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
	private Map<String, Float> costTable;
	private Map <String , BeverageType> beverageMap;
	private BeverageFactory factory;
	private Map<BeverageType,Float> costTable1;

	public Machine() {
		key = null;
		sugarLevel = new SugarLevel();
		configurator=Configurator.getInstance();
		costTable1=configurator.initializeBeverageType2();
		factory=new BeverageFactory();
        costTable = configurator.initializeBeverageType();
        beverageMap=new HashMap<>();
        costTable.forEach((k,v)->beverageMap.put(k, new BeverageType(k)));
        
        
       
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

	public Beverage buy(BeverageType type) throws KeyNotPresent, OutOfAvailableCredit, InvalidBeverage {
		Beverage beverage = null;
		Sugar sugar=new Sugar(sugarLevel);

		if (!(checkKey()))
			throw new KeyNotPresent();
		
		
		Float resultQuery = costTable1.get(type);
		
		if(resultQuery==null)
			throw new InvalidBeverage();
		
		if (!(checkCredit(resultQuery))) 
			throw new OutOfAvailableCredit();
		
		beverage = factory.createBeverage(type, sugar);
		return beverage;
	}
	
//	public Beverage buy(BeverageType type) throws KeyNotPresent, OutOfAvailableCredit, InvalidBeverage {
//		Beverage beverage = null;
//		Sugar sugar=new Sugar(sugarLevel);
//		
//		if (!(checkKey()))
//			throw new KeyNotPresent();
//		
//		if (type.equals(beverageMap.get("Caffe"))) {
//			if (!(checkCredit(costTable.get("Caffe"))))
//				throw new OutOfAvailableCredit();
//			beverage = new Coffe(type, sugar);
//		} else if (type.equals(beverageMap.get("Cappuccino"))) {
//			if (!(checkCredit(costTable.get("Cappucino"))))
//				throw new OutOfAvailableCredit();
//			beverage = new Cappuccino(type, sugar);
//		} else if (type.equals(beverageMap.get("Caffelatte"))) {
//			if (!(checkCredit(costTable.get("Caffelatte"))))
//				throw new OutOfAvailableCredit();
//			beverage = new Latte(type, sugar);
//		} else if (type.equals(beverageMap.get("Cioccolata"))) {
//			if (!(checkCredit(costTable.get("Cioccolata"))))
//				throw new OutOfAvailableCredit();
//			beverage = new Chocolate(type, sugar);
//		} else if (type.equals(beverageMap.get("Tè"))) {
//			if (!(checkCredit(costTable.get("Tè"))))
//				throw new OutOfAvailableCredit();
//			beverage = new Te(type, sugar);
//		} else
//			throw new InvalidBeverage();
//		
//		return beverage;
//	}

	public void chargeKey(Credit credit) throws KeyNotPresent, CreditExceedsBound, FullCredit {

		if (!(checkKey()))
			throw new KeyNotPresent();

		Credit total = credit.sum(key.getCredit());

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
	
	private Boolean checkCredit(Float cost) {
		Boolean result = false;
		Credit c = new Credit(cost);

		if (c.compareTo(key.getCredit())<=0)
			result = true;

		return result;
	}
//	private Boolean checkCredit(Credit credit) {
//		
//		Boolean result = false;
//		
//		if (credit.compareTo(key.getCredit())<=0)
//			result = true;
//		
//		return result;
//		
//	}

	public SugarLevel getSugarLevel() {

		return this.sugarLevel;

	}

}
