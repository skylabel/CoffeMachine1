package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.machine.Credit;
import com.intecs.machine.Key;
import com.intecs.machine.Machine;
import com.intecs.machine.SugarLevel;
import com.intecs.machine.exception.InvalidSugarLevel;
import com.intecs.machine.exception.KeyNotPresent;
import com.intecs.machine.exception.MachineException;

class TestSetSugarLevel {
	


	private static final int SUGAR_LEVEL1 = 1;

	private Machine machine;
	private Credit credit;
	private Key key;
	
	@BeforeEach
	public void initialize() {
		
		machine = new Machine();
		credit = new Credit(CreditValue.ENOUGH_CREDIT);
		key = new Key(credit, "");
		machine.insertKey(key);
		
	}
	
	
	@Test
	void testValidSelection() {

		
		try {
	
			machine.setSugarLevel(SUGAR_LEVEL1);
			
			assertEquals(new SugarLevel(SUGAR_LEVEL1), machine.getSugarLevel());
				
		} catch (MachineException e) {
		
			fail(e);
			
		} 
		
	}
	
	
	
	@Test
	void testInvalidSelection1() {

		
		try {
	
			machine.setSugarLevel(7);
			assertEquals(false,true);
				
		} catch (InvalidSugarLevel e) {
			
			//Do nothing
			
		} catch (MachineException e) {			
		
			fail(e);
		
		}
		
	}

}
