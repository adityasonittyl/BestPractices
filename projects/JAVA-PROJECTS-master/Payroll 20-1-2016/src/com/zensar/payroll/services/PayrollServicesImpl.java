package com.zensar.payroll.services;

import java.util.ArrayList;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.daoservices.PayrollDAOServices;
import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.InValidAssociateIDException;
import com.zensar.payroll.exceptions.PayRollServicesNotFoundException;
import com.zensar.payroll.provider.Provider;

public class PayrollServicesImpl implements PayrollServices {

	PayrollDAOServices dao = Provider.getPayrollDAOServices();

	@Override
	public int acceptCustomerDetails(String associateName, String emailId,
			String panCard, int basicSalary, int pf, int comapnyPf,
			int investment) throws PayRollServicesNotFoundException {

		int associateId = dao.insertAssociate(new Associate(associateName,
				emailId, panCard, basicSalary, pf, comapnyPf, investment));
		return associateId;
	}

	@Override
	public int calculateMonthlyNetSalary(int associateId)
			throws InValidAssociateIDException,
			AssociateDetailsNotFoundException, PayRollServicesNotFoundException {

		int basicSalary, hra, ta, da, grossSalary, pf, companyPf, monthlyTax, netSalary, investment, grossAnnualSalary;
		int tax;
		Associate associate = dao.getAssociate(associateId);
		basicSalary = associate.getBasicSalary();
		hra = (int) (0.1 * basicSalary);
		ta = (int) (0.1 * basicSalary);
		da = (int) (0.1 * basicSalary);
		

		pf = associate.getPf();
		companyPf = associate.getCompanyPf();
		investment = associate.getInvestment();
		grossSalary = basicSalary + hra + ta + da + companyPf;

		grossAnnualSalary = grossSalary * 12;
		int annualInvestment = pf * 12 + companyPf * 12 + investment;

		if (annualInvestment > 150000)
			annualInvestment = 150000;

		int taxableAmount = grossAnnualSalary;
		int slabOneTax = 0;
		int slabTwoTax = (int) ((250000 - investment) * 0.1);
		int slabThreeTax = (int) (300000 * 0.2);

		if (grossAnnualSalary < 250000) {
			// no tax
			associate.setMonthlyTax(0);
			return 0;
		} else if (grossAnnualSalary >= 250000 && grossAnnualSalary < 500000) {
			taxableAmount = taxableAmount - 250000;
			tax = (int) (taxableAmount * 0.1);
		} else if (grossAnnualSalary >= 500000 && grossAnnualSalary < 800000) {
			taxableAmount = taxableAmount - 500000;
			tax = (int) (taxableAmount * 0.2) + slabTwoTax;
		} else {
			taxableAmount = taxableAmount - 800000;
			tax = (int) (taxableAmount * 0.3) + slabTwoTax + slabThreeTax;
		}

		monthlyTax = tax / 12;
		dao.updateAssociate(new Associate(hra,ta,da,monthlyTax));
		return monthlyTax;
	}

	@Override
	public Associate getAssociateDetails(int associateId)
			throws InValidAssociateIDException,
			AssociateDetailsNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Associate> getAllAssociateDetails(int associateId)
			throws PayRollServicesNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean closePayrollServices()
			throws PayRollServicesNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

}
