package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageType;
import com.intecs.beverage.Sugar;
import com.intecs.machine.Credit;
import com.intecs.machine.Key;
import com.intecs.machine.Machine;
import com.intecs.machine.exception.InvalidBeverage;
import com.intecs.machine.exception.KeyNotPresent;
import com.intecs.machine.exception.MachineException;
import com.intecs.machine.exception.OutOfAvailableCredit;


class TestBuy {

	private Machine machine;
	private BeverageType type;
	
	@BeforeEach
	void initialize() {
		
		machine = new Machine();
		type= new BeverageType("Caffe");
		
	}
		
	@Test
	void testBuyWithOutKey() {
		
		try {
			
			machine.buy(type);
			
		} catch (KeyNotPresent e) {
			
			//Do nothing
			
		} catch (Exception e) {			
		
			fail(e);
		
		}
		
	}
	
	@Test
	void testInvalidBeverageSelection() {
	
		BeverageType type_1 = new BeverageType("Mocha");
		Key key= Key.empty();
		
		machine.insertKey(key);
		
		try {
			
			machine.buy(type_1);
		
		} catch (InvalidBeverage e) {
		
			//Do nothing
			
		} catch (MachineException e) {
			
			fail(e);
			
		} 
		
	}

	@Test
	void testOutOfCredit() {
	
		Key key = Key.empty();
		
		machine.insertKey(key);
		
		try {
			
			machine.buy(type);
		
		} catch (OutOfAvailableCredit e) {
			
			//Do nothing
			
		} catch (MachineException e) {
			
			fail(e);
			
		} 
	   
	}   
	
	
	
	@Test
	void testBuySelectingSugarLevel() {
			  
		Key key = new Key(new Credit(5f), "");
			
		machine.insertKey(key);
			
			
		try {
				
			machine.setSugarLevel(4);
				
			Beverage beverage = machine.buy(type);
			Beverage beverage2=new Beverage(type, new Sugar(4));
			assertEquals(beverage,beverage2);

				
		} catch (MachineException e) {
		
				assertEquals(true, false);
			
		} 
		
		
	}

	@Test
	void testBuyOK() {
	
		Key key = new Key(new Credit(5f), "");
		
		machine.insertKey(key);
		
		try {
			
			Beverage beverage = machine.buy(type);
			Beverage beverage2 = new Beverage(type, new Sugar(3));
			assertEquals(beverage,beverage2);

			
		} catch (MachineException e) {
		
			assertEquals(true, false);
		
		} 
	
	}
	  
}
