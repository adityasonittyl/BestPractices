package com.zensar.bankingsystem.beans;

import java.util.Arrays;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(account);
		result = prime * result + customerId;
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result
				+ ((haddress == null) ? 0 : haddress.hashCode());
		result = prime * result
				+ ((laddress == null) ? 0 : laddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (!Arrays.equals(account, other.account))
			return false;
		if (customerId != other.customerId)
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (haddress == null) {
			if (other.haddress != null)
				return false;
		} else if (!haddress.equals(other.haddress))
			return false;
		if (laddress == null) {
			if (other.laddress != null)
				return false;
		} else if (!laddress.equals(other.laddress))
			return false;
		return true;
	}

}
