package com.zensar.payroll.services;

import java.util.ArrayList;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.InValidAssociateIDException;
import com.zensar.payroll.exceptions.PayRollServicesNotFoundException;

public interface PayrollServices {

	int acceptCustomerDetails(String associateName, String emailId,
			String panCard, int basicSalary, int pf, int comapnyPf,
			int investment) throws PayRollServicesNotFoundException;

	int calculateMonthlyNetSalary(int associateId)
			throws InValidAssociateIDException,
			AssociateDetailsNotFoundException, PayRollServicesNotFoundException;

	Associate getAssociateDetails(int associateId)
			throws InValidAssociateIDException,
			AssociateDetailsNotFoundException;

	ArrayList<Associate> getAllAssociateDetails(int associateId)
			throws PayRollServicesNotFoundException;

	boolean closePayrollServices() throws PayRollServicesNotFoundException;

}
