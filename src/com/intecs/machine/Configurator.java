package com.intecs.machine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.intecs.beverage.BeverageName;
import com.intecs.beverage.BeverageType;

public class Configurator {

	
	private static Configurator instance;
	
	private Configurator() {};
	
	public static Configurator getInstance() {
		
		if(instance==null) {instance = new Configurator();}
		return instance;
	}
	
	
	
	
//	public Map<BeverageType, Float> initializeBeverageType2() {
//		Map<BeverageType, Float> result=new HashMap<>();
//		
//		InputStream inputStream;
//		
//		Properties prop = new Properties();
//		String propFileName = "machine.properties";	
//		
//		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
//		try {
//			
//			if (inputStream != null) {
//				
//				prop.load(inputStream);
//				Enumeration<?> e = prop.propertyNames();
//				while (e.hasMoreElements()) {
//					String key = (String) e.nextElement();
//					Float value = Float.parseFloat(prop.getProperty(key));
//					result.put(new BeverageType(key), value);
//				}
//				
//			} else {
//				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//result.forEach((key,value)-> System.out.println("Key : " + key + ", Value : " + value));
//		
//		return result;
//	}
	
	
	
	
	public static void main(String args[]) {
//		Configurator conf=Configurator.getInstance();
//		Map<String, Float> res = (Map<String, Float>)conf.initializeBeverageType();
//		res.forEach((k,v)->System.out.println(k+"  "+v));
	}

	public List<BeverageType> initizlizeBeverageTypes() {
		
		List<BeverageType> beverageTypes=new ArrayList<>();
       InputStream inputStream;
		
		Properties prop = new Properties();
		String propFileName = "machine.properties";	
		
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		try {
			
			if (inputStream != null) {
				
				prop.load(inputStream);
				Enumeration<?> e = prop.propertyNames();
				while (e.hasMoreElements()) {
					String key = (String) e.nextElement();
					Float value = Float.parseFloat(prop.getProperty(key));
					BeverageName beverageName=new BeverageName(key);
					Money money=new Money(value);
					BeverageType beverageType=new BeverageType(beverageName,money);
					
					beverageTypes.add(beverageType);
				}
				
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return beverageTypes;
	}
	
	
}
