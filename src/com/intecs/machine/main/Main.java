package com.intecs.machine.main;

import com.intecs.beverage.Beverage;
import com.intecs.beverage.BeverageType;
import com.intecs.machine.Credit;
import com.intecs.machine.Key;
import com.intecs.machine.Machine;
import com.intecs.machine.exception.CreditExceedsBound;
import com.intecs.machine.exception.FullCredit;
import com.intecs.machine.exception.InvalidBeverage;
import com.intecs.machine.exception.KeyNotPresent;
import com.intecs.machine.exception.OutOfAvailableCredit;

public class Main {

	private void testCase001() {
		
		Machine machine_001 = new Machine();
		BeverageType type_001 = new BeverageType("Caffe", (float) 0.40);
		
		try {
		
			machine_001.buy(type_001);
		
		} catch (KeyNotPresent e) {
			
			if (e.getMessage().equals("Chiavetta non inserita.")) System.out.println("Test 001 superato.");
			e.printStackTrace();
		
		} catch (OutOfAvailableCredit e) {
	
			e.printStackTrace();
			
		} catch (InvalidBeverage e) {
			
			e.printStackTrace();
		}		
		
	}
		
	public static void main(String[] args) {

		Main main = new Main();
		main.testCase001();	
	
	}

}
