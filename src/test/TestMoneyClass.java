package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.intecs.machine.Money;

class TestMoneyClass {

	
	
	

	@Test
	void testMoneyCostructor() {
		
	}
	
	@Test
	void testMoneyCostructorWithArgument() {
		
	}
	
	@Test
	void testZero() {
		
	}
	
	
	@Test
	void testSum() {
		
	}
	
	
	@Test
	void testEquals() {
		
	}
	
	
	@Test
	void testCompareTo() {
	}
	
	//----------------------
	
	@Test
	void testNegativeMoney() {
		Money money=new Money(-3.0f);
   
	}
	
	@Test
	void testNullMoney() {
		assertThrows(IllegalArgumentException.class, () -> {
			Money money=new Money(null);
	    });
	}
	
	
	@Test
	void testMoney() {
		
	}
	
	
	
	
}
