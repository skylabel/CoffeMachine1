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
			final Credit CREDIT = new Credit(CreditValue.FIFTY_COIN);
			machine.chargeKey(CREDIT);
			assertEquals(CREDIT, key.getCredit());

		} catch (MachineException e) {
			fail(e);
		}
	}

	@Test
	void testChargeWithoutKey() throws CreditExceedsBound, FullCredit {

		try {
			final Credit CREDIT = new Credit(CreditValue.FIFTY_COIN);
			machine.chargeKey(CREDIT);
			fail("exception expected");

		} catch (KeyNotPresent e) {
			// Do nothing
		}
	}

	@Test
	void testChargeTooMuchCredit() {

		Key key = new Key(new Credit(CreditValue.CLOSE_TO_THE_BUONDARY), "");

		machine.insertKey(key);

		try {

			machine.chargeKey(new Credit(2f));
			assertEquals(false, true);

		} catch (CreditExceedsBound e) {

			// Do nothing

		} catch (MachineException e) {

			fail(e);

		}

	}

	@Test
	void testChargeFullCredit() {

		Key key = new Key(new Credit(CreditValue.FULL_CREDIT), "");

		machine.insertKey(key);

		try {

			machine.chargeKey(new Credit(2f));
			assertEquals(false, true);

		} catch (FullCredit e) {

			// Do nothing

		} catch (MachineException e) {

			fail(e);

		}

	}

	@Test
	void testChargeFullCredit2() throws KeyNotPresent, CreditExceedsBound, FullCredit {
		
		Key key = new Key(new Credit(CreditValue.FULL_CREDIT), "");
		machine.insertKey(key);
		
		Credit oldCredit = key.getCredit();
		machine.chargeKey(Credit.zero());
		assertEquals(oldCredit, key.getCredit());
	}
	
}
