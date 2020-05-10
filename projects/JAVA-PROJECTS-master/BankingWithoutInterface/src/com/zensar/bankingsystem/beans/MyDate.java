package com.zensar.bankingsystem.beans;

public class MyDate {

	private int day;
	private int month;
	private int year;

	public MyDate() {
		super();
		day = 0;
		month = 0;
		year = 0;
	}

	public MyDate(int day, int month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "MyDate [day=" + day + ", month=" + month + ", year=" + year
				+ "]";
	}

	@Override
	public boolean equals(Object arg) {
		System.out.println(this);
		MyDate date = (MyDate) arg;
		if (this.day == date.day && this.month == date.month
				&& this.year == date.year) {
			return true;
		}

		return false;
	}

}
