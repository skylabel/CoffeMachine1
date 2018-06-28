package com.intecs.beverage;

public class BeverageFactory {
	
	public BeverageFactory() {
		
	}
		
	public Beverage createBeverage(BeverageType type,Sugar sugar) {
		Beverage beverage=null;
		BeverageName name = type.getName();
		
		if(name.equals(new BeverageName("Caffe"))) {
			beverage = new Coffe(type, sugar);
		} else if(name.equals(new BeverageName("Cappuccino"))) {
			beverage = new Cappuccino(type, sugar);
		} else if(name.equals(new BeverageName("Caffelatte"))) {
			beverage = new Latte(type, sugar);
		} else if(name.equals(new BeverageName("Cioccolata"))) {
			beverage = new Chocolate(type, sugar);
		} else if(name.equals(new BeverageName("Tè"))) {
			beverage = new Te(type, sugar);
		}
		
		return beverage;
	}
}
