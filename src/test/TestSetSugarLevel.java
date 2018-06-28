package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.machine.Machine;
import com.intecs.machine.SugarLevel;
import com.intecs.machine.exception.InvalidSugarLevel;
import com.intecs.machine.exception.MachineException;

class TestSetSugarLevel {
	
	private Machine machine;
	
	@BeforeEach
	public void initialize() {
		machine = new Machine();
	}
	
	@Test
	void testSetSugarLevelToNONE() throws InvalidSugarLevel {
		machine.setSugarLevel(ISugarLevels.NONE);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.NONE);
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testSetSugarLevelToMILD() throws InvalidSugarLevel {
		machine.setSugarLevel(ISugarLevels.MILD);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.MILD);
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testSetSugarLevelToMEDIUM() throws InvalidSugarLevel {
		machine.setSugarLevel(ISugarLevels.MEDIUM);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.MEDIUM);
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testSetSugarLevelToSWEET() throws InvalidSugarLevel {
		machine.setSugarLevel(ISugarLevels.SWEET);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.SWEET);
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testSetSugarLevelToVERYSWEET() throws InvalidSugarLevel {
		machine.setSugarLevel(ISugarLevels.VERY_SWEET);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.VERY_SWEET);
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testInvalidSelection1() {
		try {
			machine.setSugarLevel(ISugarLevels.INVALID);
			fail("Exception expected.");
		} catch (InvalidSugarLevel e) {
			//Do nothing
		} catch (MachineException e) {			
			fail(e);
		}
	}
}
