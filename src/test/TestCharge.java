package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.beverage.BeverageType;
import com.intecs.machine.Credit;
import com.intecs.machine.Key;
import com.intecs.machine.Machine;
import com.intecs.machine.exception.CreditExceedsBound;
import com.intecs.machine.exception.FullCredit;
import com.intecs.machine.exception.KeyNotPresent;
import com.intecs.machine.exception.MachineException;

class TestCharge {

	

	
	private Machine machine;

	
	@BeforeEach
	void initialize() {
		
		machine = new Machine();
				
	}
	
	
	
	@Test
	void testChargeEmptyKey1() {

		Key key = Key.empty();
		
		machine.insertKey(key);
		
		try {
	
			machine.charge(new Credit(CreditValue.FIFTY_COIN));
			assertEquals(new Credit(CreditValue.FIFTY_COIN), key.getCredit());
		
		} catch (MachineException e) {
		
		
			assertEquals(true, false);
			
		} 
		
	}

		@Test
		void testChargeWithoutKey() {	
			
			try {
		
				machine.charge(new Credit(CreditValue.FIFTY_COIN));
				assertEquals(false,true);
			
			}catch (KeyNotPresent e) {
				
				//Do nothing
				
			} catch (MachineException e) {			
			
				fail(e);
			
			}
			
		}
		
		@Test
		void testChargeTooMuchCredit() {


			Key key = new Key(new Credit(CreditValue.CLOSE_TO_THE_BUONDARY), "");
			
			machine.insertKey(key);
			
			try {
		
				machine.charge(new Credit(2));
				assertEquals(false,true);
			
			}catch (CreditExceedsBound e) {
				
				//Do nothing
				
			} catch (MachineException e) {			
			
				fail(e);
			
			}
			
		}
	
		@Test
		void testChargeFullCredit() {

			Key key = new Key(new Credit(CreditValue.FULL_CREDIT), "");
			
			machine.insertKey(key);
			
			try {
		
				machine.charge(new Credit(2));
				assertEquals(false,true);
			
			}catch (FullCredit e) {
				
				//Do nothing
				
			} catch (MachineException e) {			
			
				fail(e);
			
			} 
			
		}

}
