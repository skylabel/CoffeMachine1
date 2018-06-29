package com.intecs.machine;

import com.intecs.machine.exception.InvalidSugarLevel;

public abstract class InService extends State {

	protected SugarLevel sugarLevel;
	
	public InService() {
		sugarLevel=new SugarLevel();
	}
	
	@Override
	public SugarLevel getSugarLevel() {
		return sugarLevel;
	}
	
	@Override
	public void setSugarLevel(int level) throws InvalidSugarLevel {
		if (!(sugarLevel.setLevel(level)))
			throw new InvalidSugarLevel();
	}

}
