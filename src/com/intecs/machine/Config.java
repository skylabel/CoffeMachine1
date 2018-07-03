package com.intecs.machine;

import java.util.List;
import java.util.Map;

import com.intecs.beverage.BeverageProperties;
import com.intecs.beverage.BeverageType;
import com.intecs.machine.exception.InvalidBeverageException;

public abstract class Config {

	abstract public Map<BeverageType,BeverageProperties> getBeverageTypesMap();
	abstract public List<BeverageType> getBeverageTypeList();
	
	
	
	public Money getBeverageCost(BeverageType name) throws InvalidBeverageException {
		Money cost = null;
		BeverageProperties btype = getBeverageTypesMap().get(name);
		if (btype==null)
			throw new InvalidBeverageException();
		cost = btype.getCost();
		return cost;
	}
	
	
}
