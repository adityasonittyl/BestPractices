package com.zensar.training;

public class Employee {

	private int employeeId;
	private String employeeName;
	private double employeeSalary;

	 Employee() {

	}

	public Employee(int employeeId, String employeeName, double employeeSalary) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public double getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	@Override
	public String toString() {
		return "employeeId=" + employeeId + ", employeeName=" + employeeName
				+ " " + " Salary=" + this.calculateSalary();
	}

	public double calculateSalary() {
		return 0;
	}
	
	public void display()
	{
		System.out.println("Employee method");
	}
	
}
