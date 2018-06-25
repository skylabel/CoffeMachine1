package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
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

	@Before
	void setup() {
		System.out.println("before");
	}
		
	@Test
	void testBuyWithOutKey() {
		
		Machine machine = new Machine();
		BeverageType type= new BeverageType("Caffe", (float) 0.40);
		
		try {
			machine.buy(type);
			
		} catch (KeyNotPresent e) {
			//
			
		} catch (Exception e) {			
			fail(e);
		} 	
	}
	
	@Test
	void testInvalidBeverageSelection() {
	
		BeverageType type = new BeverageType("Mocha", (float) 0.40);
		Credit credit = Credit.zero();
		Key key= new Key(credit, "AndreaLuca");
		Machine machine = new Machine();
		
		machine.insertKey(key);
		
		try {
			
			machine.buy(type);
		
		} catch (MachineException e) {
			
			System.out.println("Eccezione: "+e.getMessage());
			MachineException assertException = new InvalidBeverage();
			assertEquals(assertException, e);
			
		} 
		
	}

	@Test
	void testOutOfCredit() {
	
		BeverageType type = new BeverageType("Caffe", (float) 0.40);
		Credit credit = Credit.zero();
		Key key = new Key(credit, "AndreaLuca");
		Machine machine = new Machine();
		
		machine.insertKey(key);
		
		try {
			
			machine.buy(type);
		
		} catch (MachineException e) {
			
			System.out.println("Eccezione: "+e.getMessage());
			MachineException assertException = new OutOfAvailableCredit();
			assertEquals(assertException, e);
			
		} 
	   
	}   

	@Test
	void testBuyOK() {
	
		BeverageType type = new BeverageType("Caffe", (float) 0.40);
		Credit credit = new Credit((float) 5);
		Key key = new Key(credit, "AndreaLuca");
		Machine machine = new Machine();
		
		machine.insertKey(key);
		
		try {
			
			Beverage beverage = machine.buy(type);
			Beverage beverage2=new Beverage(type, new Sugar(3));
			assertEquals(beverage,beverage2);

			
		} catch (MachineException e) {
		
			assertEquals(true, false);
		
		} 
	
	  }
	  
	@Test
	void testBuySelectingSugarLevel() {
		  
		BeverageType type = new BeverageType("Caffe", (float) 0.40);
		Credit credit = new Credit((float) 5);
		Key key = new Key(credit, "AndreaLuca");
		
		Machine machine = new Machine();
		
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
	  
	  
	  
	

}
