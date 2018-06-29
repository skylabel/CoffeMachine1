package com.intecs.machine;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageType;
import com.intecs.machine.exception.CreditExceedsBound;
import com.intecs.machine.exception.FullCredit;
import com.intecs.machine.exception.InvalidBeverage;
import com.intecs.machine.exception.InvalidSugarLevel;
import com.intecs.machine.exception.KeyNotPresent;
import com.intecs.machine.exception.MachineIsOutOfService;
import com.intecs.machine.exception.OutOfAvailableCredit;

public abstract class State {

	public abstract Beverage buy(BeverageType type,Machine machine) throws MachineIsOutOfService, KeyNotPresent, InvalidBeverage, OutOfAvailableCredit;
	public abstract void chargeKey(Money credit) throws MachineIsOutOfService, KeyNotPresent, CreditExceedsBound, FullCredit;
	public abstract void setSugarLevel(int level) throws MachineIsOutOfService, InvalidSugarLevel;
	public abstract void removeKey(Machine machine) throws MachineIsOutOfService;
	public abstract void insertKey(Key key, Machine machine) throws MachineIsOutOfService;
	public abstract SugarLevel getSugarLevel() throws MachineIsOutOfService;

}
