package test.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.intecs.machine.Key;
import com.intecs.machine.Money;
import com.intecs.machine.exception.CreditExceedsBoundException;
import com.intecs.machine.exception.FullCreditException;
import com.intecs.machine.exception.OutOfAvailableCreditException;

class TestKeyClass {

	@Test
	void testKeyConstructorWithArguments() {
		Key key = new Key(IMoneyValue.FIVE_MONEY);
		Key expectedKey = new Key(IMoneyValue.FIVE_MONEY, "");
		assertEquals(expectedKey, key);
	}
	
	@Test
	void testKeyConstructorWithNullArgument() {
		assertThrows(NullPointerException.class, () -> {new Key(null);});
	}

	@Test
	void testIncreaseCredit1() throws CreditExceedsBoundException, FullCreditException {
		Key key=new Key(IMoneyValue.FIVE_MONEY);
		key.increaseCredit(IMoneyValue.FIVE_MONEY);
		Key expectedKey=new Key(IMoneyValue.TEN_MONEY);
		assertEquals(expectedKey, key);
	}

	@Test
	void testIncreaseCredit2() {
		Key key=new Key(IMoneyValue.TEN_MONEY);
		assertThrows(FullCreditException.class, () -> {key.increaseCredit(IMoneyValue.OFIFTY_MONEY);});
	}

	
	@Test
	void testIncreaseCreditWithNegativeMoney() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			Key key=new Key(new Money(-3f));
			key.increaseCredit(IMoneyValue.OFIFTY_MONEY);});
	}
	
	
	@Test
	void testDecreaseCredit1() throws OutOfAvailableCreditException {
		Key key=new Key(IMoneyValue.TEN_MONEY);
		key.decreaseCredit(IMoneyValue.FIVE_MONEY);
		Key expectedKey=new Key(IMoneyValue.FIVE_MONEY);
		assertEquals(expectedKey, key);
	}

	@Test
	void testDecreaseCredit2() {
		Key key=new Key(IMoneyValue.FIVE_MONEY);
		assertThrows(OutOfAvailableCreditException.class, () -> {key.decreaseCredit(IMoneyValue.TEN_MONEY);});
	}
	
}
