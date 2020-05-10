package com.zensar.payroll.beans;

public class Associate {

	private int associateId, basicSalary, hra, ta, da, grossSalary, pf,
			companyPf, monthlyTax, netSalary, investment;
	private String associateName, emailID, panCard;

	public Associate() {

	}

	public Associate(int associateId, int basicSalary, int hra, int ta, int da,
			int grossSalary, int pf, int companyPf, int monthlyTax,
			int netSalary, int investment, String associateName,
			String emailID, String panCard) {
		super();
		this.associateId = associateId;
		this.basicSalary = basicSalary;
		this.hra = hra;
		this.ta = ta;
		this.da = da;
		this.grossSalary = grossSalary;
		this.pf = pf;
		this.companyPf = companyPf;
		this.monthlyTax = monthlyTax;
		this.netSalary = netSalary;
		this.investment = investment;
		this.associateName = associateName;
		this.emailID = emailID;
		this.panCard = panCard;
	}

	public Associate(String associateName, String emailId, String panCard,
			int basicSalary, int pf, int comapnyPf, int investment) {
		super();
		this.associateName = associateName;
		this.emailID = emailId;
		this.panCard = panCard;
		this.basicSalary = basicSalary;
		this.pf = pf;
		this.companyPf = comapnyPf;
		this.investment = investment;
	}

	public Associate(int hra, int ta, int da, int monthlyTax, int netSalary) {
		this.hra = hra;
		this.ta = ta;
		this.da = da;
		this.monthlyTax = monthlyTax;
		this.netSalary =  netSalary;
	}

	public int getAssociateId() {
		return associateId;
	}

	public void setAssociateId(int associateId) {
		this.associateId = associateId;
	}

	public int getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(int basicSalary) {
		this.basicSalary = basicSalary;
	}

	public int getHra() {
		return hra;
	}

	public void setHra(int hra) {
		this.hra = hra;
	}

	public int getTa() {
		return ta;
	}

	public void setTa(int ta) {
		this.ta = ta;
	}

	public int getDa() {
		return da;
	}

	public void setDa(int da) {
		this.da = da;
	}

	public int getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(int grossSalary) {
		this.grossSalary = grossSalary;
	}

	public int getPf() {
		return pf;
	}

	public void setPf(int pf) {
		this.pf = pf;
	}

	public int getCompanyPf() {
		return companyPf;
	}

	public void setCompanyPf(int companyPf) {
		this.companyPf = companyPf;
	}

	public int getMonthlyTax() {
		return monthlyTax;
	}

	public void setMonthlyTax(int monthlyTax) {
		this.monthlyTax = monthlyTax;
	}

	public int getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(int netSalary) {
		this.netSalary = netSalary;
	}

	public String getAssociateName() {
		return associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public int getInvestment() {
		return investment;
	}

	public void setInvestment(int investment) {
		this.investment = investment;
	}

	@Override
	public String toString() {
		return "Associate [associateId=" + associateId + ", basicSalary="
				+ basicSalary + ", hra=" + hra + ", ta=" + ta + ", da=" + da
				+ ", grossSalary=" + grossSalary + ", pf=" + pf
				+ ", companyPf=" + companyPf + ", monthlyTax=" + monthlyTax
				+ ", netSalary=" + netSalary + ", investment=" + investment
				+ ", associateName=" + associateName + ", emailID=" + emailID
				+ ", panCard=" + panCard + "]";
	}

}
