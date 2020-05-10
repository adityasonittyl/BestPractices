package com.zensar.payroll.provider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.zensar.payroll.exceptions.PayRollServicesNotFoundException;

public class Provider {
	static Properties properties = null;

	public static <T> T getPayrollServices(String key)
			throws PayRollServicesNotFoundException {
		try {
			properties = new Properties();
			properties
					.load(new FileReader(".\\src\\resources\\data.properties"));
			String provider = (String) properties.get(key);
			Class<?> c = Class.forName(provider);
			return (T) c.newInstance();
		} catch (FileNotFoundException e) {
			throw new PayRollServicesNotFoundException(e);
		} catch (ClassNotFoundException e) {
			throw new PayRollServicesNotFoundException(e);
		} catch (InstantiationException e) {
			throw new PayRollServicesNotFoundException(e);
		} catch (IllegalAccessException e) {
			throw new PayRollServicesNotFoundException(e);
		} catch (IOException e) {
			throw new PayRollServicesNotFoundException(e);
		} catch (Exception e) {
			throw new PayRollServicesNotFoundException(e);
		}
	}

}
