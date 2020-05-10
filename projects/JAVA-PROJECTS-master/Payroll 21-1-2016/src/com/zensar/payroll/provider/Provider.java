package com.zensar.payroll.provider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.zensar.payroll.daoservices.PayrollDAOServices;
import com.zensar.payroll.exceptions.PayRollServicesNotFoundException;
import com.zensar.payroll.services.PayrollServices;

public class Provider {

	static Properties properties = null;

	public static PayrollServices getPayrollServices()
			throws PayRollServicesNotFoundException {

		try {
			properties = new Properties();
			properties
					.load(new FileReader(".\\src\\resources\\data.properties"));
			String provider = (String) properties.get("serviceProvider");
			Class<?> c = Class.forName(provider);
			return (PayrollServices) c.newInstance();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		} catch (InstantiationException e) {
			//e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		} catch (IllegalAccessException e) {
			//e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		} catch (IOException e) {
			//e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		}

	}

	public static PayrollDAOServices getPayrollDAOServices() throws PayRollServicesNotFoundException {

		try {
			properties = new Properties();
			properties
					.load(new FileReader(".\\src\\resources\\data.properties"));
			String provider = (String) properties.get("repoProvider");
			Class<?> c = Class.forName(provider);
			return (PayrollDAOServices) c.newInstance();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		}

	}
}
