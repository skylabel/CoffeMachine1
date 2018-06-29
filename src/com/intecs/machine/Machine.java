package com.intecs.machine;

import java.util.List;
import java.util.Map;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageProperties;
import com.intecs.beverage.BeverageType;
import com.intecs.machine.exception.CreditExceedsBound;
import com.intecs.machine.exception.FullCredit;
import com.intecs.machine.exception.InvalidBeverage;
import com.intecs.machine.exception.InvalidSugarLevel;
import com.intecs.machine.exception.KeyNotPresent;
import com.intecs.machine.exception.MachineIsOutOfService;
import com.intecs.machine.exception.OutOfAvailableCredit;


public class Machine {

	private Configurator configurator;
	private List<BeverageType> beverageNames;
	private Map<BeverageType, BeverageProperties> bTypes;
	private State state;
	
	void setState(State state) {
		this.state=state;		
	}

	public Machine() {
		this(new NoKey());
	}
		
	public Machine(State state) {
		this.state=state;
		configurator = Configurator.getInstance();
		beverageNames = configurator.initizlizeBeverageTypes();
		bTypes = configurator.initizlizeBTypes();
	}

	public void insertKey(Key key) throws MachineIsOutOfService {
		state.insertKey(key, this);
	}

	public void removeKey() throws MachineIsOutOfService {
		state.removeKey(this);
	}

	public void setSugarLevel(int level) throws MachineIsOutOfService, InvalidSugarLevel {
		state.setSugarLevel(level);
	}
	
	public Beverage buy(BeverageType type) throws OutOfAvailableCredit, InvalidBeverage, MachineIsOutOfService, KeyNotPresent {
		return state.buy(type,this);
	}
	
	public void chargeKey(Money credit) throws KeyNotPresent, CreditExceedsBound, FullCredit, MachineIsOutOfService {
		state.chargeKey(credit);
	}

	public SugarLevel getSugarLevel() throws MachineIsOutOfService {
		return state.getSugarLevel();
	}

	public List<BeverageType> getBeverageNameList() {
		return beverageNames;
	}

	public Money getBeverageCost(BeverageType name) throws InvalidBeverage {
		Money cost = null;
		BeverageProperties btype=bTypes.get(name);
		if (btype==null)
			throw new InvalidBeverage();
		
		cost = btype.getCost();
		
		return cost;
	}
	
	public boolean isValid(BeverageType name) {
		return bTypes.containsKey(name);
	}
	
	public Map<BeverageType, BeverageProperties> getBeverages() {
		
		return this.bTypes;
	}
	
}
