package test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.intecs.machine.Money;


class TestMoneyClass {

	@Test
	void testMoneyCostructor() {
		Money money = new Money();
		Money expectedMoney = IMoneyValue.ZERO_MONEY;
		assertEquals(expectedMoney, money);
	}
		
	@Test
	void testZero() {
		Money money=Money.zero();
		Money expectedMoney=IMoneyValue.ZERO_MONEY;
		assertEquals(expectedMoney, money);
	}
	
	@Test
	void testSumE1() {
		Money money=Money.zero();
		Money expectedMoneySum=Money.zero();
		assertEquals(expectedMoneySum, money.sum(Money.zero()));
	}
	
	@Test
	void testSumE2() {
		Money money=IMoneyValue.OFIFTY_MONEY;
		Money expectedMoneySum=IMoneyValue.ONE_MONEY;
		assertEquals(expectedMoneySum, money.sum(IMoneyValue.OFIFTY_MONEY));
	}
	
	
	@Test
	void testSumE3() {
		Money money=IMoneyValue.FIVE_MONEY;
		Money expectedMoneySum=new Money(15f);
		assertEquals(expectedMoneySum, money.sum(IMoneyValue.TEN_MONEY));
	}
	
	
	@Test
	void testEquals() {
		Money moneyA=IMoneyValue.OFIFTY_MONEY;
		Money moneyB=IMoneyValue.OFIFTY_MONEY;
		assertEquals(moneyB, moneyA);	
	}
	
	
	@Test
	void testCompareToBigger() {
		Money money = IMoneyValue.ZERO_MONEY;
		Money biggerMoney = IMoneyValue.FIVE_MONEY;
		assertEquals(-1, money.compareTo(biggerMoney));
	}

	@Test
	void testCompareToLesser() {
		Money money = IMoneyValue.TEN_MONEY;
		Money lesserMoney = IMoneyValue.FIVE_MONEY;
		assertEquals(1, money.compareTo(lesserMoney));
	}

	@Test
	void testCompareToEqual() {
		Money money = IMoneyValue.TEN_MONEY;
		Money equalMoney = IMoneyValue.TEN_MONEY;
		assertEquals(0, money.compareTo(equalMoney));
	}
	
	@Test
	void testNegativeMoney() {
		assertThrows(IllegalArgumentException.class, () -> {new Money(-3.0f);});
	}
	
	@Test
	void testNullMoney() {
		assertThrows(NullPointerException.class, () -> {
			Money money=new Money(null);
	    });
	}
}
