package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageType;
import com.intecs.beverage.BeverageProperties;
import com.intecs.beverage.Sugar;
import com.intecs.machine.Money;
import com.intecs.machine.Key;
import com.intecs.machine.Machine;
import com.intecs.machine.SugarLevel;
import com.intecs.machine.exception.InvalidBeverage;
import com.intecs.machine.exception.InvalidSelection;
import com.intecs.machine.exception.InvalidSugarLevel;
import com.intecs.machine.exception.KeyNotPresent;
import com.intecs.machine.exception.MachineException;
import com.intecs.machine.exception.MachineIsOutOfService;
import com.intecs.machine.exception.OutOfAvailableCredit;

class TestBuy {

	private Machine machine;

	static class KeyStub extends Key {
		
		@Override
		public Money getCredit() {
			return new Money(CreditValue.ENOUGH_CREDIT);
		}
		
	}
	
	@BeforeEach
	void initialize() {
		machine = new Machine();
	}

	@Test
	void testBuyWithOutKey() throws InvalidBeverage, OutOfAvailableCredit, MachineIsOutOfService {
		try {
			machine.buy(IBevarageNames.COFFEE);
			fail("Exception expected.");
			
		} catch (KeyNotPresent e) {
			// Do nothing
		} 
	}

	@Test
	void testInvalidBeverageSelection() throws KeyNotPresent, OutOfAvailableCredit, MachineIsOutOfService {
		Key key = Key.empty();
		machine.insertKey(key);
		try {
			machine.buy(IBevarageNames.MOCHA);
			fail("Exception expected.");
		} catch (InvalidBeverage e) {
			// Do nothing
		}
	}

	@Test
	void testOutOfCredit() throws InvalidBeverage, KeyNotPresent, MachineIsOutOfService {
		Key key = Key.empty();
		machine.insertKey(key);
		try {
			machine.buy(IBevarageNames.COFFEE);
			fail("Exception expected.");
		} catch (OutOfAvailableCredit e) {
			// Do nothing 
		}
	}

	@Test
	void testBuyCaffe() throws InvalidBeverage, KeyNotPresent, OutOfAvailableCredit, MachineIsOutOfService {
		Key key = new KeyStub();
		assertTrue(machine.isValid(IBevarageNames.COFFEE));
		
		machine.insertKey(key);
		Beverage beverage = machine.buy(IBevarageNames.COFFEE);
		BeverageProperties expectedtype = new BeverageProperties(IBevarageNames.COFFEE);
		assertTrue(expectedtype.equalsName(beverage.getType()));
	}
	
	@Test
	void testBuyCoffeSelectingSugarLevel() throws InvalidSelection, KeyNotPresent, OutOfAvailableCredit, MachineIsOutOfService {
		Key key = new KeyStub();
		assertTrue(machine.isValid(IBevarageNames.COFFEE));
		machine.insertKey(key);
		
		machine.setSugarLevel(ISugarLevels.SWEET);
		Beverage beverage = machine.buy(IBevarageNames.COFFEE);
		Sugar expectedSugar = new Sugar(new SugarLevel(ISugarLevels.SWEET));
		assertEquals(expectedSugar, beverage.getSugar());
	}
	
	@Test
	void testBuyMoneyNegative() throws InvalidSelection, KeyNotPresent, OutOfAvailableCredit, MachineIsOutOfService {
		Key key = new Key(new Money(-3.0f));
        
		machine.insertKey(key);
		machine.buy(IBevarageNames.COFFEE);
	}
	
	
}
