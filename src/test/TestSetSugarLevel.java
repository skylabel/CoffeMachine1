package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.intecs.machine.Credit;
import com.intecs.machine.Key;
import com.intecs.machine.Machine;
import com.intecs.machine.SugarLevel;
import com.intecs.machine.exception.InvalidSugarLevel;
import com.intecs.machine.exception.KeyNotPresent;
import com.intecs.machine.exception.MachineException;

class TestSetSugarLevel {
	

	private static final float ENOUGH_CREDIT = 5;
	private static final int SUGAR_LEVEL1 = 1;

	@Test
	void testValidSelection() {

		Machine machine = new Machine();
		Credit credit = new Credit(ENOUGH_CREDIT);
		Key key = new Key(credit, "testChargeEmptyKey1");
		
		machine.insertKey(key);
		
		try {
	
			machine.setSugarLevel(SUGAR_LEVEL1);
			
			assertEquals(new SugarLevel(SUGAR_LEVEL1), machine.getSugarLevel());
				
		} catch (MachineException e) {
		
			assertEquals(true, false);
			
		} 
		
	}
	
	
	
	@Test
	void testInvalidSelection1() {

		Machine machine = new Machine();
		Credit credit = new Credit(ENOUGH_CREDIT);
		Key key = new Key(credit, "testChargeEmptyKey1");
		
		machine.insertKey(key);
		
		try {
	
			machine.setSugarLevel(7);
				
		} catch (MachineException e) {
		
			System.out.println("Eccezione: "+e.getMessage());		
			MachineException assertException = new InvalidSugarLevel();
			assertEquals(assertException, e);
			
		} 
		
	}

}
