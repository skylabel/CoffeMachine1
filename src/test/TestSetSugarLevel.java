package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.machine.Machine;
import com.intecs.machine.OutOfService;
import com.intecs.machine.SugarLevel;
import com.intecs.machine.exception.InvalidSugarLevel;
import com.intecs.machine.exception.MachineIsOutOfService;

class TestSetSugarLevel {
	
	private Machine machine;
	
	@BeforeEach
	public void initialize() {
		machine = new Machine();
	}
	
	@Test
	void testSetSugarLevelToNONE() throws InvalidSugarLevel, MachineIsOutOfService {
		machine.setSugarLevel(ISugarLevels.NONE);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.NONE);
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testSetSugarLevelToMILD() throws InvalidSugarLevel, MachineIsOutOfService {
		machine.setSugarLevel(ISugarLevels.MILD);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.MILD);
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testSetSugarLevelToMEDIUM() throws InvalidSugarLevel, MachineIsOutOfService {
		machine.setSugarLevel(ISugarLevels.MEDIUM);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.MEDIUM);
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testSetSugarLevelToSWEET() throws InvalidSugarLevel, MachineIsOutOfService {
		machine.setSugarLevel(ISugarLevels.SWEET);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.SWEET);
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testSetSugarLevelToVERYSWEET() throws InvalidSugarLevel, MachineIsOutOfService {
		machine.setSugarLevel(ISugarLevels.VERY_SWEET);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.VERY_SWEET);		
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testInvalidSelection() throws MachineIsOutOfService {
		try {
			machine.setSugarLevel(ISugarLevels.INVALID);
			fail("Exception expected.");
		} catch (InvalidSugarLevel e) {
			//Do nothing
		} 
	}
	
	@Test
	void testSetSugarOutOfService() throws InvalidSugarLevel {
		Machine machineOutOfService = new Machine(new OutOfService());
		try {
			machineOutOfService.setSugarLevel(ISugarLevels.MEDIUM);
			fail("Exception expected.");
		} catch (MachineIsOutOfService e) {
			//Do nothing
		}
	}
	
}
