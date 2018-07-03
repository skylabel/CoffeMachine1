package com.intecs.machine;

import com.intecs.machine.exception.InvalidSugarLevelException;

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
	public void setSugarLevel(int level) throws InvalidSugarLevelException {
		if (!(sugarLevel.setLevel(level)))
			throw new InvalidSugarLevelException();
	}

}
