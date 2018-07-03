package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.beverage.BeverageProperties;
import com.intecs.machine.Money;
import com.intecs.machine.Key;
import com.intecs.machine.Machine;
import com.intecs.machine.exception.CreditExceedsBoundException;
import com.intecs.machine.exception.FullCreditException;
import com.intecs.machine.exception.KeyNotPresentException;
import com.intecs.machine.exception.MachineException;
import com.intecs.machine.exception.MachineIsOutOfServiceException;

class TestCharge {

	private Machine machine;
	

	@BeforeEach
	void initialize() {
		machine = new Machine();
	}

	@Test
	void testChargeEmptyKey() throws KeyNotPresentException, CreditExceedsBoundException, FullCreditException, MachineIsOutOfServiceException {
		Key key = Key.empty();
		machine.insertKey(key);

		Money amount = new Money(ICreditValue.OFIFTY_CREDIT);
		machine.chargeKey(amount);
		assertEquals(amount, key.getCredit());
	}

	@Test
	void testChargeWithoutKey() throws CreditExceedsBoundException, FullCreditException, MachineIsOutOfServiceException {

		try {
			Money amount = new Money(ICreditValue.OFIFTY_CREDIT);
			machine.chargeKey(amount);
			fail("exception expected");
		} catch (KeyNotPresentException e) {
			// Do nothing
		}
	}

	@Test
	void testChargeTooMuchCredit() throws KeyNotPresentException, FullCreditException, MachineIsOutOfServiceException {
		Money credit = new Money(ICreditValue.CLOSE_TO_THE_BUONDARY);
		Key key = new Key(credit);
		machine.insertKey(key);

		try {
			Money amount = new Money(ICreditValue.ENOUGH_CREDIT);
			machine.chargeKey(amount);
			fail("Exception expected.");
		} catch (CreditExceedsBoundException e) {
			// Do nothing
		}
	}

	@Test
	void testChargeFullCredit() throws KeyNotPresentException, CreditExceedsBoundException, MachineIsOutOfServiceException {
		Key key = Key.full();
		machine.insertKey(key);

		try {
			Money amount = new Money(ICreditValue.OFIFTY_CREDIT);
			machine.chargeKey(amount);
			fail("Exception expected.");
		} catch (FullCreditException e) {
			// Do nothing
		}
	}

	@Test
	void testFullCreditLimit1() throws KeyNotPresentException, CreditExceedsBoundException, FullCreditException, MachineIsOutOfServiceException {
		Key key = Key.full();
		machine.insertKey(key);
		
		Money oldCredit = key.getCredit();
		machine.chargeKey(Money.zero());
		assertEquals(oldCredit, key.getCredit());
	}
	
	@Test
	void testIncreaseCredit() throws KeyNotPresentException, CreditExceedsBoundException, FullCreditException, MachineIsOutOfServiceException {
		Key key = new Key(new Money(2.0f));
		machine.insertKey(key);
		
		machine.chargeKey(new Money(2.0f));
		assertEquals(new Money(4.0f), key.getCredit());
	}
	
	@Test
	void testIncreaseWithNegativeCredit() throws KeyNotPresentException, CreditExceedsBoundException, FullCreditException, MachineIsOutOfServiceException {
		Key key = new Key(new Money(2.0f));
		machine.insertKey(key);
		
		
		assertThrows(IllegalArgumentException.class, () -> {
			machine.chargeKey(new Money(-2.0f));
	    });
		
		
	}
	
}
