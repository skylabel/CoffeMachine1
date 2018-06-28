package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageName;
import com.intecs.beverage.BeverageType;
import com.intecs.beverage.Sugar;
import com.intecs.machine.Money;
import com.intecs.machine.Key;
import com.intecs.machine.Machine;
import com.intecs.machine.SugarLevel;
import com.intecs.machine.exception.InvalidBeverage;
import com.intecs.machine.exception.InvalidSugarLevel;
import com.intecs.machine.exception.KeyNotPresent;
import com.intecs.machine.exception.MachineException;
import com.intecs.machine.exception.OutOfAvailableCredit;

class TestBuy {

	private Machine machine;

	static class KeyStub extends Key {
		
//		@Override
		
	}
	
	@BeforeEach
	void initialize() {
		machine = new Machine();
	}

	@Test
	void testBuyWithOutKey() throws InvalidBeverage, OutOfAvailableCredit {
		BeverageName name = new BeverageName("Caffe");

		try {
			machine.buy(name);
			fail("Exception expected.");
			
		} catch (KeyNotPresent e) {
			// Do nothing
		} 
	}

	@Test
	void testInvalidBeverageSelection() {
		BeverageName name = new BeverageName("Mocha");
		
		Key key = Key.empty();
		machine.insertKey(key);

		try {
			machine.buy(name);
			fail("Exception expected.");
		} catch (InvalidBeverage e) {
			// Do nothing
		} catch (MachineException e) {
			fail(e);
		}
	}

	@Test
	void testOutOfCredit() {
		BeverageName name = new BeverageName("Caffe");
		
		Key key = Key.empty();
		machine.insertKey(key);

		try {
			machine.buy(name);
		} catch (OutOfAvailableCredit e) {
			// Do nothing
		} catch (MachineException e) {
			fail(e);
		}
	}

	@Test
	void testBuyCoffeSelectingSugarLevel() throws InvalidBeverage, KeyNotPresent, OutOfAvailableCredit, InvalidSugarLevel {
		BeverageName coffee = new BeverageName("Caffe");
		assertTrue(machine.isValid(coffee));
		
		Money coffeeCost = machine.getBeverageCost(coffee);
		
		Key key = new KeyStub();
		machine.insertKey(key);
		
		machine.setSugarLevel(ISugarLevels.SWEET);

		Beverage beverage = machine.buy(coffee);
		Sugar expectedSugar = new Sugar(new SugarLevel(ISugarLevels.SWEET));
		assertEquals(expectedSugar, beverage.getSugar());
	}
	
//	void testBuyCoffeSelectingSugarLevel() throws InvalidBeverage, KeyNotPresent, OutOfAvailableCredit, InvalidSugarLevel {
//		BeverageName coffee = new BeverageName("Caffe");
//		assertTrue(machine.isValid(coffee));
//		
//		Money coffeeCost = machine.getBeverageCost(coffee);
//		
//		Key key = new Key(coffeeCost);
//		machine.insertKey(key);
//		
//		machine.setSugarLevel(ISugarLevels.SWEET);
//		
//		Beverage beverage = machine.buy(coffee);
//		Sugar expectedSugar = new Sugar(new SugarLevel(ISugarLevels.SWEET));
//		assertEquals(expectedSugar, beverage.getSugar());
//	}

	@Test
	void testBuyCaffe() throws InvalidBeverage, KeyNotPresent, OutOfAvailableCredit {
		List<BeverageName> names = machine.getBeverageNameList();
		BeverageName name = new BeverageName("Caffe");
		assertTrue(names.contains(name));
		
		Money beveragecost = machine.getBeverageCost(name);
		Key key = new Key(beveragecost);
		machine.insertKey(key);
		
		Beverage beverage = machine.buy(name);
		BeverageType expectedtype = new BeverageType(name, beveragecost);
		assertEquals(expectedtype, beverage.getType());
	}
}
