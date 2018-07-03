package com.intecs.machine;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageType;
import com.intecs.machine.exception.KeyNotPresentException;
import com.intecs.machine.exception.MachineIsOutOfServiceException;

public class OutOfService extends State{

	@Override
	public void chargeKey(Money credit) throws MachineIsOutOfServiceException {
		throw new MachineIsOutOfServiceException();
	}

	@Override
	public void setSugarLevel(int level) throws MachineIsOutOfServiceException {
		throw new MachineIsOutOfServiceException();
		
	}

	@Override
	public Beverage buy(BeverageType name, Machine machine) throws MachineIsOutOfServiceException, KeyNotPresentException {
		throw new MachineIsOutOfServiceException();
	}

	@Override
	public void removeKey(Machine machine) throws MachineIsOutOfServiceException {
		throw new MachineIsOutOfServiceException();		
	}

	@Override
	public void insertKey(Key key, Machine machine) throws MachineIsOutOfServiceException {
		throw new MachineIsOutOfServiceException();		
	}

	@Override
	public SugarLevel getSugarLevel() throws MachineIsOutOfServiceException {
		throw new MachineIsOutOfServiceException();		
	}

}
