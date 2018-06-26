package com.intecs.machine;

import java.util.HashMap;
import java.util.Map;

import com.intecs.beverage.Beverage;
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
	private static float keyBound = 10;
	private static Credit keyboundCredit=new Credit(keyBound);
	private Configurator configurator = Configurator.getInstance();
	private Map costTable;

	public Machine() {
		key = null;
		sugarLevel = new SugarLevel();
//        Map<String, ?> result = configurator.initializeBeverageType();
       
		costTable = new HashMap<BeverageType, Float>();
		costTable.put("Caffe", 0.4f);
		costTable.put("Cappucino", 0.5f);
		costTable.put("Caffelatte", 0.5f);
		costTable.put("Cioccolata", 0.4f);
		costTable.put("Te", 0.4f);
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
		
		if (type.equals(new BeverageType("Caffe"))) {
				if (!(checkCredit((Float) costTable.get("Caffe"))))
					throw new OutOfAvailableCredit();
				beverage = new Coffe(type, sugar);
		} else if (type.equals(new BeverageType("Cappuccino"))) {
				if (!(checkCredit((Float) costTable.get("Cappucino"))))
					throw new OutOfAvailableCredit();
				beverage = new Cappuccino(type, sugar);
		} else if (type.equals(new BeverageType("Caffelatte"))) {
				if (!(checkCredit((Float) costTable.get("Caffelatte"))))
					throw new OutOfAvailableCredit();
				beverage = new Latte(type, sugar);
		} else if (type.equals(new BeverageType("Cioccolata"))) {
				if (!(checkCredit((Float) costTable.get("Cioccolata"))))
					throw new OutOfAvailableCredit();
				beverage = new Chocolate(type, sugar);
		} else if (type.equals(new BeverageType("Tè"))) {
				if (!(checkCredit((Float) costTable.get("Tè"))))
					throw new OutOfAvailableCredit();
				beverage = new Te(type, sugar);
		} else
			throw new InvalidBeverage();
		
		return beverage;
	}

	public void chargeKey(Credit credit) throws KeyNotPresent, CreditExceedsBound, FullCredit {

		if (!(checkKey()))
			throw new KeyNotPresent();

		Credit total = credit.sum(key.getCredit());

		if(total.compareTo(keyboundCredit)<=0) {

			key.setCredit(total);

		} else if (total.compareTo(keyboundCredit) == 1 && keyboundCredit.compareTo(key.getCredit()) == 1)
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
