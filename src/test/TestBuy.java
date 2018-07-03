package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.intecs.machine.ManualConfig;
import com.intecs.machine.Key;
import com.intecs.machine.Machine;
import com.intecs.machine.SugarLevel;
import com.intecs.machine.exception.InvalidBeverageException;
import com.intecs.machine.exception.InvalidSelectionException;
import com.intecs.machine.exception.InvalidSugarLevelException;
import com.intecs.machine.exception.KeyNotPresentException;
import com.intecs.machine.exception.MachineException;
import com.intecs.machine.exception.MachineIsOutOfServiceException;
import com.intecs.machine.exception.OutOfAvailableCreditException;

class TestBuy {

	private Machine machine;

	static class KeyStub extends Key {
		
		@Override
		public Money getCredit() {
			return new Money(ICreditValue.ENOUGH_CREDIT);
		}
		
	}
	
	@BeforeEach
	void initialize() {
		machine = new Machine();
	}

	@Test
	void testBuyWithOutKey() throws InvalidBeverageException, OutOfAvailableCreditException, MachineIsOutOfServiceException {
		try {
			machine.buy(IBevarageNames.COFFEE);
			fail("Exception expected.");
			
		} catch (KeyNotPresentException e) {
			// Do nothing
		} 
	}

	@Test
	void testInvalidBeverageSelection() throws KeyNotPresentException, OutOfAvailableCreditException, MachineIsOutOfServiceException {
		Key key = Key.empty();
		machine.insertKey(key);
		try {
			machine.buy(IBevarageNames.MOCHA);
			fail("Exception expected.");
		} catch (InvalidBeverageException e) {
			// Do nothing
		}
	}

	@Test
	void testOutOfCredit() throws InvalidBeverageException, KeyNotPresentException, MachineIsOutOfServiceException {
		Key key = Key.empty();
		machine.insertKey(key);
		try {
			machine.buy(IBevarageNames.COFFEE);
			fail("Exception expected.");
		} catch (OutOfAvailableCreditException e) {
			// Do nothing 
		}
	}

	@Test
	void testBuyCaffe() throws InvalidBeverageException, KeyNotPresentException, OutOfAvailableCreditException, MachineIsOutOfServiceException {
		Key key = new KeyStub();
		assertTrue(machine.isValid(IBevarageNames.COFFEE));
		
		machine.insertKey(key);
		Beverage beverage = machine.buy(IBevarageNames.COFFEE);
		BeverageProperties expectedtype = new BeverageProperties(IBevarageNames.COFFEE);
		assertTrue(expectedtype.equalsName(beverage.getType()));
	}
	
	@Test
	void testBuyCoffeSelectingSugarLevel() throws InvalidSelectionException, KeyNotPresentException, OutOfAvailableCreditException, MachineIsOutOfServiceException {
		Key key = new KeyStub();
		assertTrue(machine.isValid(IBevarageNames.COFFEE));
		machine.insertKey(key);
		
		machine.setSugarLevel(ISugarLevels.SWEET);
		Beverage beverage = machine.buy(IBevarageNames.COFFEE);
		Sugar expectedSugar = new Sugar(new SugarLevel(ISugarLevels.SWEET));
		assertEquals(expectedSugar, beverage.getSugar());
	}
	
	@Test
	void testBuyMoneyNegative() throws InvalidSelectionException, KeyNotPresentException, OutOfAvailableCreditException, MachineIsOutOfServiceException {
		assertThrows(IllegalArgumentException.class, () -> {
			Key key = new Key(new Money(-3.0f));
	        
			machine.insertKey(key);
			machine.buy(IBevarageNames.COFFEE);
	    });
		
	}
	
	@Test
	void testBeverageTypeList() {
		
		ManualConfig config = new ManualConfig();
		config.add("Caffè", 0.30f);
		config.add("Te", 0.20f);
		config.add("Cioccolato", 0.50f);
		
		Machine m = new Machine(config);
		
		assertEquals(3, m.getBeverageNameList().size());
		assertTrue(m.getBeverageNameList().contains(BeverageType.of("Caffè")));
		assertTrue(m.getBeverageNameList().contains(BeverageType.of("Te")));
		assertTrue(m.getBeverageNameList().contains(BeverageType.of("Cioccolato")));
		
	}
	
	@Test
	void testBeverageTypeList2() {
		
		ManualConfig config = new ManualConfig();
		config.add(IBevarageNames.COFFEE, 0.30f);
		config.add(IBevarageNames.CAPPUCCINO, 0.40f);
		config.add(IBevarageNames.CHOCOLATE, 0.50f);
		
		Machine m = new Machine(config);
		
		assertEquals(3, m.getBeverageNameList().size());
		assertTrue(m.getBeverageNameList().contains(IBevarageNames.COFFEE));
		assertTrue(m.getBeverageNameList().contains(IBevarageNames.CAPPUCCINO));
		assertTrue(m.getBeverageNameList().contains(IBevarageNames.CHOCOLATE));
	}
	
}
