package com.intecs.machine;

import java.util.List;
import java.util.Map;
import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageProperties;
import com.intecs.beverage.BeverageType;
import com.intecs.machine.exception.CreditExceedsBoundException;
import com.intecs.machine.exception.FullCreditException;
import com.intecs.machine.exception.InvalidBeverageException;
import com.intecs.machine.exception.InvalidSugarLevelException;
import com.intecs.machine.exception.KeyNotPresentException;
import com.intecs.machine.exception.MachineIsOutOfServiceException;
import com.intecs.machine.exception.OutOfAvailableCreditException;


public class Machine {


	private State state;
	private Config config;

	public Machine() {
		this(new NoKey());
	}
		
	public Machine(State state) {
		if(state==null) 
			throw new NullPointerException("State is null");
		this.state=state;
		this.config = new FileConfig();
	}

	public Machine(ManualConfig config) {
		this.state = new NoKey();
		if(config==null)
			throw new NullPointerException("Config is null");
		this.config = config;
	}

	void setState(State state) {
		this.state=state;		
	}
	
	public void insertKey(Key key) throws MachineIsOutOfServiceException {
		state.insertKey(key, this);
	}

	public void removeKey() throws MachineIsOutOfServiceException {
		state.removeKey(this);
	}

	public void setSugarLevel(int level) throws MachineIsOutOfServiceException, InvalidSugarLevelException {
		state.setSugarLevel(level);
	}
	
	public Beverage buy(BeverageType type) throws OutOfAvailableCreditException, InvalidBeverageException, MachineIsOutOfServiceException, KeyNotPresentException {
		return state.buy(type,this);
	}
	
	public void chargeKey(Money credit) throws KeyNotPresentException, CreditExceedsBoundException, FullCreditException, MachineIsOutOfServiceException {
		state.chargeKey(credit);
	}

	public SugarLevel getSugarLevel() throws MachineIsOutOfServiceException {
		return state.getSugarLevel();
	}

	public Map<BeverageType, BeverageProperties> getBeveragesMap() {
		return this.config.getBeverageTypesMap();
	}
	
	public List<BeverageType> getBeverageNameList() {
		return this.config.getBeverageTypeList();
	}
	
	public Money getBeverageCost(BeverageType name) throws InvalidBeverageException {
		return config.getBeverageCost(name);
	}

	
	public boolean isValid(BeverageType name) {
		return getBeveragesMap().containsKey(name);
	}
	
}
