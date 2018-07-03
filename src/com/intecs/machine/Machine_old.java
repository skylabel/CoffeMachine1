package com.intecs.machine;

import java.util.List;
import java.util.Map;

import javax.xml.crypto.dsig.keyinfo.KeyName;

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


public class Machine_old {

	private List<BeverageType> beverageNames;
	private Map<BeverageType, BeverageProperties> bTypes;
	private State state;
	
	private ManualConfig config;
	
	void setState(State state) {
		this.state=state;		
	}

	public Machine_old() {
		this(new NoKey());
	}
		
	public Machine_old(State state) {
		this.state=state;
		FileConfig configurator = new FileConfig();
		beverageNames = configurator.getBeverageTypeList();
		bTypes = configurator.getBeverageTypesMap();
	}

	public Machine_old(ManualConfig config) {
		this(new NoKey());
		this.config = config;
	}

//	public void insertKey(Key key) throws MachineIsOutOfService {
//		state.insertKey(key, this);
//	}
//
//	public void removeKey() throws MachineIsOutOfService {
//		state.removeKey(this);
//	}

	public void setSugarLevel(int level) throws MachineIsOutOfServiceException, InvalidSugarLevelException {
		state.setSugarLevel(level);
	}
	
//	public Beverage buy(BeverageType type) throws OutOfAvailableCredit, InvalidBeverage, MachineIsOutOfService, KeyNotPresent {
//		return state.buy(type,this);
//	}
	
	public void chargeKey(Money credit) throws KeyNotPresentException, CreditExceedsBoundException, FullCreditException, MachineIsOutOfServiceException {
		state.chargeKey(credit);
	}

	public SugarLevel getSugarLevel() throws MachineIsOutOfServiceException {
		return state.getSugarLevel();
	}

	public List<BeverageType> getBeverageNameList() {
		return this.config.getBeverageTypeList();
	}
	
	public Money getBeverageCost(BeverageType name) throws InvalidBeverageException {
		Money cost = null;
		BeverageProperties btype=bTypes.get(name);
		if (btype==null)
			throw new InvalidBeverageException();
		
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
