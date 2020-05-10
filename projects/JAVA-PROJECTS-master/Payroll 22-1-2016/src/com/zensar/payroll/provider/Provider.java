package com.zensar.payroll.provider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import com.zensar.payroll.daoservices.PayrollDAOServices;
import com.zensar.payroll.exceptions.PayRollServicesNotFoundException;
import com.zensar.payroll.services.PayrollServices;

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
			// e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		} catch (InstantiationException e) {
			// e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		} catch (IllegalAccessException e) {
			// e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		} catch (IOException e) {
			// e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		} catch (Exception e) {
			// e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		}
	}

}
