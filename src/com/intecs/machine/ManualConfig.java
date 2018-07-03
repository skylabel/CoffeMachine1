package com.intecs.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intecs.beverage.BeverageProperties;
import com.intecs.beverage.BeverageType;
import com.intecs.machine.exception.InvalidBeverageException;

public class ManualConfig extends Config {

	private Map<BeverageType,BeverageProperties> beverageTypesMap;
	
	public ManualConfig() {
		this.beverageTypesMap = new HashMap<>();
	}
	

	@Override
	public Map<BeverageType,BeverageProperties> getBeverageTypesMap() {
		if(beverageTypesMap==null)
			throw new NullPointerException("Beverage types Map is null");
		return this.beverageTypesMap;
	}
	
	
	@Override
	public List<BeverageType> getBeverageTypeList() {
		if(beverageTypesMap==null)
			throw new NullPointerException("Beverage types Map is null");
		List<BeverageType> result = new ArrayList<>(this.beverageTypesMap.keySet());
		return result;
	}

	public void add(BeverageType type, float cost) {
		Money bevCost = new Money (cost);
		BeverageProperties props = new BeverageProperties(type, bevCost);
		this.beverageTypesMap.put(type, props);
	}

	public void add(String bevName, float cost) {
		add(BeverageType.of(bevName), cost);
	}

}
