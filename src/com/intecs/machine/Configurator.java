package com.intecs.machine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configurator {

	
	private static Configurator instance=null;
	
	private Configurator() {};
	
	public static Configurator getInstance() {
		
		if(instance==null) {instance=new Configurator();}
		return instance;
	}
	
	
	
	public Map<String, Float> initializeBeverageType() {
		Map result=new HashMap<String, Float>();
		
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
					String value = prop.getProperty(key);
					result.put(key, value);
				}
			
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//result.forEach((key,value)-> System.out.println("Key : " + key + ", Value : " + value));
		
		return result;
	}
	
	
	
	
	public static void main(String args[]) {
		Configurator conf=Configurator.getInstance();
		Map<String, ?> res = (Map<String, ?>)conf.initializeBeverageType();
		res.forEach((k,v)->System.out.println(k+"  "+v));
	}
	
	
}
