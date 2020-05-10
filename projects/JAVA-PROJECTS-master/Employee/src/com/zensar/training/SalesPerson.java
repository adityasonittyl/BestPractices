package com.zensar.training;

public class SalesPerson extends WageEmployee {

	private double commission;

	public SalesPerson() {
		super();
	}

	public SalesPerson(int employeeId, String employeeName,
			double employeeSalary, int noOfHoursWorked, double ratePerHour,
			double commission) {
		super(employeeId, employeeName, employeeSalary, noOfHoursWorked,
				ratePerHour);
		this.commission = commission;
	}

	public double calculateSalary() {
		return getNoOfHoursWorked() * getRatePerHour() * commission;
	}

	@Override
	public String toString() {
		return super.toString() + " commission=" + commission;
	}

}
