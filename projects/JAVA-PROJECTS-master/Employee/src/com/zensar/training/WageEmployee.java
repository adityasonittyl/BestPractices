package com.zensar.training;

public class WageEmployee extends Employee {

	private int noOfHoursWorked;
	private double ratePerHour;

	public WageEmployee() {
		super();
	}

	public WageEmployee(int employeeId, String employeeName,
			double employeeSalary, int noOfHoursWorked, double ratePerHour) {
		super(employeeId, employeeName, employeeSalary);
		this.noOfHoursWorked = noOfHoursWorked;
		this.ratePerHour = ratePerHour;
	}

	
	public int getNoOfHoursWorked() {
		return noOfHoursWorked;
	}

	public void setNoOfHoursWorked(int noOfHoursWorked) {
		this.noOfHoursWorked = noOfHoursWorked;
	}

	public double getRatePerHour() {
		return ratePerHour;
	}

	public void setRatePerHour(double ratePerHour) {
		this.ratePerHour = ratePerHour;
	}

	public double calculateSalary() {
		return noOfHoursWorked * ratePerHour;
	}

	@Override
	public String toString() {
		return super.toString() + " noOfHoursWorked= " + noOfHoursWorked
				+ ", ratePerHour=" + ratePerHour + "";
	}
	
	public void display()
	{
		System.out.println(this);
		System.out.println("Wage Employee method");
	}

}
