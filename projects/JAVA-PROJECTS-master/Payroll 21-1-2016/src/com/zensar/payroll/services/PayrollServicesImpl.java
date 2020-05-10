package com.zensar.payroll.services;

import java.util.ArrayList;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.daoservices.PayrollDAOServices;
import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.InValidAssociateIDException;
import com.zensar.payroll.exceptions.PayRollServicesNotFoundException;
import com.zensar.payroll.provider.Provider;

public class PayrollServicesImpl implements PayrollServices {

	PayrollDAOServices dao = null;

	public PayrollServicesImpl() throws PayRollServicesNotFoundException {
		dao = Provider.getPayrollDAOServices();
	}

	@Override
	public int acceptCustomerDetails(String associateName, String emailId,
			String panCard, int basicSalary, int pf, int comapnyPf,
			int investment) throws PayRollServicesNotFoundException {

		if (dao == null) {
			throw new PayRollServicesNotFoundException();
		}
		int associateId = dao.insertAssociate(new Associate(associateName,
				emailId, panCard, basicSalary, pf, comapnyPf, investment));
		return associateId;
	}

	@Override
	public int calculateMonthlyNetSalary(int associateId)
			throws InValidAssociateIDException,
			AssociateDetailsNotFoundException, PayRollServicesNotFoundException {

		int basicSalary, hra, ta, da, grossSalary, pf, companyPf, monthlyTax, netSalary, investment, grossAnnualSalary;
		int tax = 0;

		if (associateId < 100) {
			throw new InValidAssociateIDException();
		} else {
			Associate associate = dao.getAssociate(associateId);
			if (associate == null) {
				throw new AssociateDetailsNotFoundException();
			} else {
				basicSalary = associate.getBasicSalary();
				hra = (int) (0.1 * basicSalary);
				ta = (int) (0.1 * basicSalary);
				da = (int) (0.1 * basicSalary);

				pf = associate.getPf();
				companyPf = associate.getCompanyPf();
				investment = associate.getInvestment();
				grossSalary = basicSalary + hra + ta + da + companyPf;

				System.out.println("Gross salary: " + grossSalary);

				grossAnnualSalary = grossSalary * 12;
				int annualInvestment = (pf * 12) + (companyPf * 12)
						+ investment;

				if (annualInvestment > 150000)
					annualInvestment = 150000;

				System.out.println("Annual Investment: " + annualInvestment);

				int taxableAmount = grossAnnualSalary;
				System.out.println("Gross Annual Salary " + grossAnnualSalary);

				int slabTwoTax = (int) ((250000 - annualInvestment) * 0.1);
				int slabThreeTax = (int) (300000 * 0.2);

				if (grossAnnualSalary < 250000) {

					System.out.println("SLAB 1");
					tax = 0;
				} else if (grossAnnualSalary >= 250000
						&& grossAnnualSalary < 500000) {

					System.out.println("SLAB 2");
					taxableAmount = taxableAmount - 250000;
					if ((taxableAmount - annualInvestment) < 0) {
						tax = 0;
					} else {
						tax = (int) ((taxableAmount - annualInvestment) * 0.1);

					}
				} else if (grossAnnualSalary >= 500000
						&& grossAnnualSalary < 800000) {

					System.out.println("SLAB 3");
					taxableAmount = taxableAmount - 500000;
					tax = (int) ((taxableAmount * 0.2) + ((250000 - annualInvestment) * 0.1));
				} else {

					System.out.println("SLAB 4");
					taxableAmount = taxableAmount - 800000;
					tax = (int) (taxableAmount * 0.3) + slabTwoTax
							+ slabThreeTax;
				}

				monthlyTax = tax / 12;
				System.out.println("Monthly Tax: " + monthlyTax);

				netSalary = grossSalary - pf - companyPf - monthlyTax;
				associate.setNetSalary(netSalary);
				associate.setMonthlyTax(monthlyTax);
				associate.setHra(hra);
				associate.setTa(ta);
				associate.setDa(da);
				associate.setGrossSalary(grossSalary);

				dao.updateAssociate(associate);

			}
		}

		return netSalary;
	}

	@Override
	public Associate getAssociateDetails(int associateId)
			throws InValidAssociateIDException,
			AssociateDetailsNotFoundException {

		if (associateId < 100) {
			throw new InValidAssociateIDException();
		} 
		else if (dao.getAssociate(associateId) == null) {
			throw new AssociateDetailsNotFoundException();
		} else {
			return dao.getAssociate(associateId);
		}
	}

	@Override
	public boolean closePayrollServices()
			throws PayRollServicesNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Associate> getAllAssociateDetails()
			throws PayRollServicesNotFoundException {

		if (dao.getAssociates() == null) {
			throw new PayRollServicesNotFoundException();
		} else {
			return dao.getAssociates();
		}

	}

}
