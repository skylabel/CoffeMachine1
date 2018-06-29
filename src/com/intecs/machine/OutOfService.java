package com.intecs.machine;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageType;
import com.intecs.machine.exception.KeyNotPresent;
import com.intecs.machine.exception.MachineIsOutOfService;

public class OutOfService extends State{

	@Override
	public void chargeKey(Money credit) throws MachineIsOutOfService {
		throw new MachineIsOutOfService();
	}

	@Override
	public void setSugarLevel(int level) throws MachineIsOutOfService {
		throw new MachineIsOutOfService();
		
	}

	@Override
	public Beverage buy(BeverageType name, Machine machine) throws MachineIsOutOfService, KeyNotPresent {
		throw new MachineIsOutOfService();
	}

	@Override
	public void removeKey(Machine machine) throws MachineIsOutOfService {
		throw new MachineIsOutOfService();		
	}

	@Override
	public void insertKey(Key key, Machine machine) throws MachineIsOutOfService {
		throw new MachineIsOutOfService();		
	}

	@Override
	public SugarLevel getSugarLevel() throws MachineIsOutOfService {
		throw new MachineIsOutOfService();		
	}

}
