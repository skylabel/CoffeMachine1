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

import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.intecs.beverage.BeverageType;
import com.intecs.machine.exception.InvalidBeverageException;
import com.intecs.beverage.BeverageProperties;

public class FileConfig extends Config{
	
	private Map<BeverageType,BeverageProperties> beverageTypesMap;
	private String propFileName = "machine.properties";	
	
	FileConfig() {
		beverageTypesMap=new HashMap<>();
		try {
			setMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
		propFileName = "machine.properties";	
	}

	private InputStream getPropertyStream() {
		return getClass().getClassLoader().getResourceAsStream(propFileName);
	}
		
	private void  setMap() throws IOException {
		
		Properties prop = new Properties();
		InputStream inputStream = getPropertyStream();
		if (inputStream != null) {
			prop.load(inputStream);
			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				Float value = Float.parseFloat(prop.getProperty(key));
				BeverageType beverageName=new BeverageType(key);
				Money money=new Money(value);
				BeverageProperties beverageType=new BeverageProperties(beverageName,money);
				beverageTypesMap.put(beverageName,beverageType);
				}
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
			
	}
	
	@Override
	public Map<BeverageType,BeverageProperties> getBeverageTypesMap() {
		return beverageTypesMap;
	}
	
	@Override
	public List<BeverageType> getBeverageTypeList() {
		List<BeverageType> beveragetTypes = new ArrayList<>(
				this.beverageTypesMap.keySet());
		return beveragetTypes;
	}

		
	
}
