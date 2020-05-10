package com.zensar.bankingsystem.beans;

public class Customer {

	private int customerId;
	private String customerName;
	private Address laddress;
	private Address haddress;
	private MyDate dob;
	private Account[] account = new Account[3];

	public Customer() {
		super();
	}

	public Customer(int customerId, String customerName, Address laddress,
			Address haddress) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.laddress = laddress;
		this.haddress = haddress;
	}

	public Customer(String customerName, Address laddress, Address haddress) {
		super();
		this.customerName = customerName;
		this.laddress = laddress;
		this.haddress = haddress;
	}

	public Customer(String customerName, Address laddress, Address haddress,
			MyDate dob) {
		super();
		this.customerName = customerName;
		this.laddress = laddress;
		this.haddress = haddress;
		this.dob = dob;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Address getLaddress() {
		return laddress;
	}

	public void setLaddress(Address laddress) {
		this.laddress = laddress;
	}

	public Address getHaddress() {
		return haddress;
	}

	public void setHaddress(Address haddress) {
		this.haddress = haddress;
	}

	public Account[] getAccount() {
		return account;
	}

	public void setAccount(Account[] account) {
		this.account = account;
	}

	public MyDate getDob() {
		return dob;
	}

	public void setDob(MyDate dob) {
		this.dob = dob;
	}

}
