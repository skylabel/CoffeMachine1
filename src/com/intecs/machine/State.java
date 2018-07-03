package com.intecs.machine;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageType;
import com.intecs.machine.exception.CreditExceedsBoundException;
import com.intecs.machine.exception.FullCreditException;
import com.intecs.machine.exception.InvalidBeverageException;
import com.intecs.machine.exception.InvalidSugarLevelException;
import com.intecs.machine.exception.KeyNotPresentException;
import com.intecs.machine.exception.MachineIsOutOfServiceException;
import com.intecs.machine.exception.OutOfAvailableCreditException;

public abstract class State {

	public abstract Beverage buy(BeverageType type,Machine machine) throws MachineIsOutOfServiceException, KeyNotPresentException, InvalidBeverageException, OutOfAvailableCreditException;
	public abstract void chargeKey(Money credit) throws MachineIsOutOfServiceException, KeyNotPresentException, CreditExceedsBoundException, FullCreditException;
	public abstract void setSugarLevel(int level) throws MachineIsOutOfServiceException, InvalidSugarLevelException;
	public abstract void removeKey(Machine machine) throws MachineIsOutOfServiceException;
	public abstract void insertKey(Key key, Machine machine) throws MachineIsOutOfServiceException;
	public abstract SugarLevel getSugarLevel() throws MachineIsOutOfServiceException;

}
