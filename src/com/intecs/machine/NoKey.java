package com.intecs.machine;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageType;
import com.intecs.machine.exception.InvalidBeverage;
import com.intecs.machine.exception.KeyNotPresent;
import com.intecs.machine.exception.MachineIsOutOfService;
import com.intecs.machine.exception.OutOfAvailableCredit;

public class NoKey extends InService {

	@Override
	public Beverage buy(BeverageType name, Machine machine)
			throws MachineIsOutOfService, KeyNotPresent, InvalidBeverage, OutOfAvailableCredit {
		throw new KeyNotPresent();
	}

	@Override
	public void chargeKey(Money credit) throws MachineIsOutOfService, KeyNotPresent {
		throw new KeyNotPresent();
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
