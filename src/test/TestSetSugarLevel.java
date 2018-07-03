package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.machine.Machine;
import com.intecs.machine.OutOfService;
import com.intecs.machine.SugarLevel;
import com.intecs.machine.exception.InvalidSugarLevelException;
import com.intecs.machine.exception.MachineIsOutOfServiceException;

class TestSetSugarLevel {
	
	private Machine machine;
	
	@BeforeEach
	public void initialize() {
		machine = new Machine();
	}
	
	@Test
	void testSetSugarLevelToNONE() throws InvalidSugarLevelException, MachineIsOutOfServiceException {
		machine.setSugarLevel(ISugarLevels.NONE);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.NONE);
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testSetSugarLevelToMILD() throws InvalidSugarLevelException, MachineIsOutOfServiceException {
		machine.setSugarLevel(ISugarLevels.MILD);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.MILD);
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testSetSugarLevelToMEDIUM() throws InvalidSugarLevelException, MachineIsOutOfServiceException {
		machine.setSugarLevel(ISugarLevels.MEDIUM);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.MEDIUM);
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testSetSugarLevelToSWEET() throws InvalidSugarLevelException, MachineIsOutOfServiceException {
		machine.setSugarLevel(ISugarLevels.SWEET);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.SWEET);
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testSetSugarLevelToVERYSWEET() throws InvalidSugarLevelException, MachineIsOutOfServiceException {
		machine.setSugarLevel(ISugarLevels.VERY_SWEET);
		SugarLevel expectedlevel = new SugarLevel(ISugarLevels.VERY_SWEET);		
		assertEquals(expectedlevel, machine.getSugarLevel());
	}
	
	@Test
	void testInvalidSelection() throws MachineIsOutOfServiceException {
		try {
			machine.setSugarLevel(ISugarLevels.INVALID);
			fail("Exception expected.");
		} catch (InvalidSugarLevelException e) {
			//Do nothing
		} 
	}
	
	@Test
	void testSetSugarOutOfService() throws InvalidSugarLevelException {
		Machine machineOutOfService = new Machine(new OutOfService());
		try {
			machineOutOfService.setSugarLevel(ISugarLevels.MEDIUM);
			fail("Exception expected.");
		} catch (MachineIsOutOfServiceException e) {
			//Do nothing
		}
	}
	
}
