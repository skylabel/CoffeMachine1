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

import com.intecs.beverage.BeverageType;
import com.intecs.beverage.BeverageProperties;

public class Configurator {
	
	private static Configurator instance;
	private Map<BeverageType,BeverageProperties> beverageTypes;
	private List<BeverageType> beverageNames;
	private InputStream inputStream;
	private Properties prop;
	private String propFileName = "machine.properties";	
	
	private Configurator() {
		beverageTypes = new HashMap<>();
		beverageNames = new ArrayList<>();
		prop = new Properties();
		propFileName = "machine.properties";	
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
	}
	
	public static Configurator getInstance() {
		
		if(instance==null) {instance = new Configurator();}
		return instance;
	}
	
	public Map<BeverageType,BeverageProperties> initizlizeBTypes() {
		try {
			if (inputStream != null) {
				prop.load(inputStream);
				Enumeration<?> e = prop.propertyNames();
				while (e.hasMoreElements()) {
					String key = (String) e.nextElement();
					Float value = Float.parseFloat(prop.getProperty(key));
					BeverageType beverageName=new BeverageType(key);
					Money money=new Money(value);
					BeverageProperties beverageType=new BeverageProperties(beverageName,money);
					beverageTypes.put(beverageName,beverageType);
					beverageNames.add(beverageName);
				}
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return beverageTypes;
	}
	
	public List<BeverageType> initizlizeBeverageTypes() {
		initizlizeBTypes();
		return beverageNames;
	}
	
}
