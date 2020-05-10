package com.zensar.payroll.services;

import java.util.ArrayList;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.daoservices.PayrollDAOServices;
import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.InValidAssociateIDException;
import com.zensar.payroll.exceptions.PayRollServicesNotFoundException;
import com.zensar.payroll.provider.Provider;

public class PayrollServicesImpl implements PayrollServices {

	private PayrollDAOServices dao = null;
	private static final int SLAB_ONE_UPPER_LIMIT = 250000;
	private static final int SLAB_TWO_UPPER_LIMIT = 500000;
	private static final int SLAB_THREE_UPPER_LIMIT = 800000;
	private static final int INVESTMENT_EXEMPTION_LIMIT = 150000;
	private static final int SLAB_TWO_AMOUNT = 250000;
	private static final int SLAB_THREE_AMOUNT = 300000;
	private static final float SLAB_TWO_TAX_RATE = 0.1f;
	private static final float SLAB_THREE_TAX_RATE = 0.2f;
	private static final float SLAB_THREE_ABOVE_TAX_RATE = 0.3f;
	private static final int MONTHS = 12;
	private static final float RATE = 0.1f;

	public PayrollServicesImpl() throws PayRollServicesNotFoundException {
		try {
			dao = Provider.getPayrollServices("repoProvider");
		} catch (Exception e) {
			// e.printStackTrace();
			throw new PayRollServicesNotFoundException();
		}
	}

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
		Associate associate = null;
		int grossAnnualSalary;
		int annualTax = 0, taxableAmount, annualInvestment, slabTwoTax, slabThreeTax;

		if (associateId < 100) {
			throw new InValidAssociateIDException();
		} else {
			associate = dao.getAssociate(associateId);
			if (associate == null) {
				throw new AssociateDetailsNotFoundException();
			} else {
				associate.setHra((int) (RATE * associate.getBasicSalary()));
				associate.setTa((int) (RATE * associate.getBasicSalary()));
				associate.setDa((int) (RATE * associate.getBasicSalary()));
				associate.setGrossSalary(associate.getBasicSalary()
						+ associate.getHra() + associate.getTa()
						+ associate.getDa() + associate.getCompanyPf());

				annualInvestment = (associate.getPf() * MONTHS)
						+ (associate.getCompanyPf() * MONTHS)
						+ associate.getInvestment();

				if (annualInvestment > INVESTMENT_EXEMPTION_LIMIT)
					annualInvestment = INVESTMENT_EXEMPTION_LIMIT;

				grossAnnualSalary = associate.getGrossSalary() * MONTHS;
				taxableAmount = grossAnnualSalary;
				slabTwoTax = (int) ((SLAB_TWO_AMOUNT - annualInvestment) * SLAB_TWO_TAX_RATE);
				slabThreeTax = (int) (SLAB_THREE_AMOUNT * SLAB_THREE_TAX_RATE);

				if (grossAnnualSalary < SLAB_ONE_UPPER_LIMIT) {
					annualTax = 0;
				} else if (grossAnnualSalary >= SLAB_ONE_UPPER_LIMIT
						&& grossAnnualSalary < SLAB_TWO_UPPER_LIMIT) {
					taxableAmount = taxableAmount - SLAB_ONE_UPPER_LIMIT;
					if ((taxableAmount - annualInvestment) < 0) {
						annualTax = 0;
					} else {
						annualTax = (int) ((taxableAmount - annualInvestment) * SLAB_TWO_TAX_RATE);
					}
				} else if (grossAnnualSalary >= SLAB_TWO_UPPER_LIMIT
						&& grossAnnualSalary < SLAB_THREE_UPPER_LIMIT) {
					taxableAmount = taxableAmount - SLAB_TWO_UPPER_LIMIT;
					annualTax = (int) (taxableAmount * SLAB_THREE_TAX_RATE)
							+ slabTwoTax;
				} else {
					taxableAmount = taxableAmount - SLAB_THREE_UPPER_LIMIT;
					annualTax = (int) (taxableAmount * SLAB_THREE_ABOVE_TAX_RATE)
							+ slabTwoTax + slabThreeTax;
				}
				associate.setMonthlyTax(annualTax / MONTHS);
				associate.setNetSalary(associate.getGrossSalary()
						- associate.getPf() - associate.getCompanyPf()
						- associate.getMonthlyTax());
				dao.updateAssociate(associate);
			}
		}
		return associate.getNetSalary();
	}

	@Override
	public Associate getAssociateDetails(int associateId)
			throws InValidAssociateIDException,
			AssociateDetailsNotFoundException {
		if (associateId < 100) {
			throw new InValidAssociateIDException();
		} else if (dao.getAssociate(associateId) == null) {
			throw new AssociateDetailsNotFoundException();
		} else {
			return dao.getAssociate(associateId);
		}
	}

	@Override
	public boolean closePayrollServices()
			throws PayRollServicesNotFoundException {
		if (dao.closePayrollDAOServices() == true)
			return true;
		else
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
