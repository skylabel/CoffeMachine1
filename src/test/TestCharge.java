package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.intecs.machine.Credit;
import com.intecs.machine.Key;
import com.intecs.machine.Machine;
import com.intecs.machine.exception.CreditExceedsBound;
import com.intecs.machine.exception.FullCredit;
import com.intecs.machine.exception.KeyNotPresent;
import com.intecs.machine.exception.MachineException;

class TestCharge {

	private static final float FIFTY_COIN = (float) 0.50;
	private static final float ZERO_CREDIT = 0;

	@Test
	void testChargeEmptyKey1() {

		Machine machine = new Machine();
		Credit credit = new Credit(ZERO_CREDIT);
		Key key = new Key(credit, "testChargeEmptyKey1");
		
		machine.insertKey(key);
		
		try {
	
			assertEquals(new Credit(ZERO_CREDIT), key.getCredit());
			machine.charge(new Credit(FIFTY_COIN));
			assertEquals(new Credit(FIFTY_COIN), key.getCredit());
		
		} catch (MachineException e) {
		
		
			assertEquals(true, false);
			
		} 
		
	}

		@Test
		void testChargeWithoutKey() {

			Machine machine = new Machine();
			Credit credit = new Credit(ZERO_CREDIT);
			Key key = new Key(credit, "testChargeWithoutKey");
			
			//machine.insertKey(key);
			
			try {
		
				machine.charge(new Credit(FIFTY_COIN));
			
			} catch (MachineException e) {
				
				System.out.println("Eccezzione: "+e.getMessage());		
				MachineException assertException = new KeyNotPresent();
				assertEquals(assertException, e);
				
			} 
			
		}
		
		@Test
		void testChargeTooMuchCredit() {

			Machine machine = new Machine();
			Credit credit = new Credit(9);
			Key key = new Key(credit, "testChargeTooMuchCredit");
			
			machine.insertKey(key);
			
			try {
		
				machine.charge(new Credit(2));
			
			} catch (MachineException e) {
				
				System.out.println("Eccezione: "+e.getMessage());
				MachineException assertException = new CreditExceedsBound();
				assertEquals(assertException, e);
				
			} 
			
		}
	
		@Test
		void testChargeFullCredit() {

			Machine machine = new Machine();
			Credit credit = new Credit(10);
			Key key = new Key(credit, "testChargeFullCredit");
			
			machine.insertKey(key);
			
			try {
		
				machine.charge(new Credit(2));
			
			} catch (MachineException e) {
				
				System.out.println("Eccezione: "+e.getMessage());
				MachineException assertException = new FullCredit();
				assertEquals(assertException, e);
				
			} 
			
		}

}
