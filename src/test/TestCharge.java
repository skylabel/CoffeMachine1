package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.beverage.BeverageType;
import com.intecs.machine.Money;
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
	void testChargeEmptyKey() throws KeyNotPresent, CreditExceedsBound, FullCredit {
		Key key = Key.empty();
		machine.insertKey(key);

		Money amount = new Money(CreditValue.FIFTY_COIN);
		machine.chargeKey(amount);
		assertEquals(amount, key.getCredit());
	}

	@Test
	void testChargeWithoutKey() {

		try {
			Money amount = new Money(CreditValue.FIFTY_COIN);
			machine.chargeKey(amount);
			fail("exception expected");
		} catch (KeyNotPresent e) {
			// Do nothing
		} catch (MachineException e) {
			fail(e);
		} 
	}

	@Test
	void testChargeTooMuchCredit() {
		Money credit = new Money(CreditValue.CLOSE_TO_THE_BUONDARY);
		Key key = new Key(credit);
		machine.insertKey(key);

		try {
			Money amount = new Money(CreditValue.ENOUGH_CREDIT);
			machine.chargeKey(amount);
			fail("Exception expected.");
		} catch (CreditExceedsBound e) {
			// Do nothing
		} catch (MachineException e) {
			fail(e);
		}
	}

	@Test
	void testChargeFullCredit() {
		Money credit = new Money(CreditValue.FULL_CREDIT);
		Key key = new Key(credit);
		machine.insertKey(key);

		try {
			Money amount = new Money(CreditValue.FIFTY_COIN);
			machine.chargeKey(amount);
			fail("Exception expected.");
		} catch (FullCredit e) {
			// Do nothing
		} catch (MachineException e) {
			fail(e);
		}
	}

	@Test
	void testFullCreditLimit1() throws KeyNotPresent, CreditExceedsBound, FullCredit {
		Key key = new Key(new Money(CreditValue.FULL_CREDIT), "");
		machine.insertKey(key);
		
		Money oldCredit = key.getCredit();
		machine.chargeKey(Money.zero());
		assertEquals(oldCredit, key.getCredit());
	}
	
	@Test
	void testIncreaseCredit() throws KeyNotPresent, CreditExceedsBound, FullCredit {
		Key key = new Key(new Money(2.0f));
		machine.insertKey(key);
		
		machine.chargeKey(new Money(2.0f));
		assertEquals(new Money(4.0f), key.getCredit());
	}
	
}
