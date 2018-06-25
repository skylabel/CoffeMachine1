package com.intecs.machine;

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
	static float keyBound = 10;
	
	public Machine() {
		
		key = null;
		sugarLevel = new SugarLevel();
	
	}
	
	public void insertKey(Key _key) {
	
		key = _key; 
		//System.out.println("Chiave inserita. ID: " + key.getId() + " Credito: " + key.getCredit());

	}
	
	public Key removeKey() {
		
		Key result = key;
		key = null;
		System.out.println("Chiave rimossa.");
		return result;
		
	}
	
	public void setSugarLevel(int _level) throws InvalidSugarLevel {
				
		if (!(sugarLevel.setLevel(_level))) throw new InvalidSugarLevel();
		
	}
	
	public Beverage buy(BeverageType _type) throws KeyNotPresent, OutOfAvailableCredit, InvalidBeverage {
		
		Beverage beverage = null;

		if (!(checkKey())) throw new KeyNotPresent();
		else {
			
			switch (_type.getName()) {
				
				case ("Caffe"): 
					
					if (!(checkCredit(_type.getCost()))) throw new OutOfAvailableCredit();
					else beverage = new Coffe(_type, new Sugar(this.sugarLevel.getLevel()));
					break;
					
				case ("Cappuccino"): 
					
					if (!(checkCredit(_type.getCost()))) throw new OutOfAvailableCredit();
					else beverage = new Cappuccino(_type, new Sugar(this.sugarLevel.getLevel()));
					break;
				
				case ("Caffelatte"): 
					
					if (!(checkCredit(_type.getCost()))) throw new OutOfAvailableCredit();
					else beverage = new Latte(_type, new Sugar(this.sugarLevel.getLevel()));
					break;
					
				case ("Cioccolata"): 
					
					if (!(checkCredit(_type.getCost()))) throw new OutOfAvailableCredit();
					else beverage = new Chocolate(_type, new Sugar(this.sugarLevel.getLevel()));
					break;
					
				case ("Tè"): 
					
					if (!(checkCredit(_type.getCost()))) throw new OutOfAvailableCredit();
					else beverage = new Te(_type, new Sugar(this.sugarLevel.getLevel()));
					break;
					
				default: throw new InvalidBeverage();
				
				}	
			
		}
		
		return beverage;
			
	}
	
	public void charge(Credit _credit) throws KeyNotPresent, CreditExceedsBound, FullCredit {

		if (!(checkKey())) throw new KeyNotPresent();
		else {
					
			float total = key.getCredit().getValue()+_credit.getValue();
				
			if (total <= keyBound) {
					
				key.setCredit(new Credit(total));
				
			} else if (total > keyBound && key.getCredit().getValue() < keyBound) throw new CreditExceedsBound();
			
			else throw new FullCredit();
					
		}
		
	}
	
	private Boolean checkKey() {
		
		Boolean result = false;
		
		if (key != null) result = true;
		
		return result;
		
	}
	
	private Boolean checkCredit(float cost) {
		
		Boolean result = false;
		
		if (key.getCredit().getValue() >= cost) result = true;
		
		return result;
		
	}

	public SugarLevel getSugarLevel() {
	
		return this.sugarLevel;
		
	}
	
}
