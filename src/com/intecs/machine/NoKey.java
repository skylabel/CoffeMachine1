package com.intecs.machine;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageType;
import com.intecs.machine.exception.InvalidBeverageException;
import com.intecs.machine.exception.KeyNotPresentException;
import com.intecs.machine.exception.MachineIsOutOfServiceException;
import com.intecs.machine.exception.OutOfAvailableCreditException;

public class NoKey extends InService {

	@Override
	public Beverage buy(BeverageType name, Machine machine)
			throws MachineIsOutOfServiceException, KeyNotPresentException, InvalidBeverageException, OutOfAvailableCreditException {
		throw new KeyNotPresentException();
	}

	@Override
	public void chargeKey(Money credit) throws MachineIsOutOfServiceException, KeyNotPresentException {
		throw new KeyNotPresentException();
	}

	@Override
	public void removeKey(Machine machine) {
		//Do nothing		
	}

	@Override
	public void insertKey(Key key, Machine machine) {
		machine.setState(new KeyPresent(key));
	}

}
