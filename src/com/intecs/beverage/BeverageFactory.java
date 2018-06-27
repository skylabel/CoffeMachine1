package com.intecs.beverage;

public class BeverageFactory {
	
	public BeverageFactory() {
		
	}
		
	public Beverage createBeverage(BeverageType type,Sugar sugar) {
		Beverage beverage=null;
		
		if(type.equals(new BeverageType("Caffe"))) {
			beverage = new Coffe(type, sugar);
		} else if(type.equals(new BeverageType("Cappuccino"))) {
			beverage = new Cappuccino(type, sugar);
		} else if(type.equals(new BeverageType("Caffelatte"))) {
			beverage = new Latte(type, sugar);
		} else if(type.equals(new BeverageType("Cioccolata"))) {
			beverage = new Chocolate(type, sugar);
		} else if(type.equals(new BeverageType("Tè"))) {
			beverage = new Te(type, sugar);
		}
		
		return beverage;
	}
}
