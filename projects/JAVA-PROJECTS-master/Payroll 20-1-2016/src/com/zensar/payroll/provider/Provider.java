package com.zensar.payroll.provider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.zensar.payroll.daoservices.PayrollDAOServices;
import com.zensar.payroll.services.PayrollServices;

public class Provider {

	static Properties properties = null;

	public static PayrollServices getPayrollServices() {

		try {
			properties = new Properties();
			properties
					.load(new FileReader(".\\src\\resources\\data.properties"));
			String provider = (String) properties.get("serviceProvider");
			Class c = Class.forName(provider);
			return (PayrollServices) c.newInstance();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static PayrollDAOServices getPayrollDAOServices() {

		try {
			properties = new Properties();
			properties
					.load(new FileReader(".\\src\\resources\\data.properties"));
			String provider = (String) properties.get("repoProvider");
			Class c = Class.forName(provider);
			return (PayrollDAOServices) c.newInstance();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
