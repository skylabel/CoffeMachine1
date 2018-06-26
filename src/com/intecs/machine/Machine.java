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
	
	private Map costTable;

	public Machine() {

		key = null;
		sugarLevel = new SugarLevel();
		costTable = new HashMap<String, Float>();
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

	public void setSugarLevel(int _level) throws InvalidSugarLevel {

		if (!(sugarLevel.setLevel(_level)))
			throw new InvalidSugarLevel();

	}

	public Beverage buy(BeverageType _type) throws KeyNotPresent, OutOfAvailableCredit, InvalidBeverage {

		Beverage beverage = null;

		if (!(checkKey()))
			throw new KeyNotPresent();
		else {

			switch (_type.getName()) {

			case ("Caffe"):

				if (!(checkCredit((float) costTable.get("Caffe"))))
					throw new OutOfAvailableCredit();
				else
					beverage = new Coffe(_type, new Sugar(this.sugarLevel.getLevel()));
				break;

			case ("Cappuccino"):

				if (!(checkCredit((float) costTable.get("Cappucino"))))
					throw new OutOfAvailableCredit();
				else
					beverage = new Cappuccino(_type, new Sugar(this.sugarLevel.getLevel()));
				break;

			case ("Caffelatte"):

				if (!(checkCredit((float) costTable.get("Caffelatte"))))
					throw new OutOfAvailableCredit();
				else
					beverage = new Latte(_type, new Sugar(this.sugarLevel.getLevel()));
				break;

			case ("Cioccolata"):

				if (!(checkCredit((float) costTable.get("Cioccolata"))))
					throw new OutOfAvailableCredit();
				else
					beverage = new Chocolate(_type, new Sugar(this.sugarLevel.getLevel()));
				break;

			case ("Tè"):

				if (!(checkCredit((float) costTable.get("Tè"))))
					throw new OutOfAvailableCredit();
				else
					beverage = new Te(_type, new Sugar(this.sugarLevel.getLevel()));
				break;

			default:
				throw new InvalidBeverage();

			}

		}

		return beverage;

	}

	public void chargeKey(Credit credit) throws KeyNotPresent, CreditExceedsBound, FullCredit {

		if (!(checkKey()))
			throw new KeyNotPresent();

		Credit total = credit.sum(key.getCredit());

		
		if(total.compareTo(keyboundCredit)<=0) {

			key.setCredit(total);

		} else if (total.getValue() > keyBound && key.getCredit().getValue() < keyBound)
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

	private Boolean checkCredit(float cost) {

		Boolean result = false;

		if (key.getCredit().getValue() >= cost)
			result = true;

		return result;

	}

	public SugarLevel getSugarLevel() {

		return this.sugarLevel;

	}

}
