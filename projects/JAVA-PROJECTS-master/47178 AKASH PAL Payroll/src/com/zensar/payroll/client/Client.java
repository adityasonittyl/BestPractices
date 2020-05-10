package com.zensar.payroll.client;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.InValidAssociateIDException;
import com.zensar.payroll.exceptions.PayRollServicesNotFoundException;
import com.zensar.payroll.provider.Provider;
import com.zensar.payroll.services.PayrollServices;

public class Client {

	public static void main(String[] args) {

		PayrollServices services = null;
		Scanner scanner = null;
		String decision;
		String associateName, emailId, panCard;
		int basicSalary, pf, comapnyPf, investment, associateId, choice = 0;
		scanner = new Scanner(System.in);

		try {
			services = Provider.getPayrollServices("serviceProvider");
		} catch (PayRollServicesNotFoundException e1) {
			// e1.printStackTrace();
			System.out.println("Sorry service is unavailable currently");
			System.exit(0);
		}

		do {
			String menuAlignFormat = "| %-101s |%n";
			printLine2();
			System.out.format(menuAlignFormat,
					"Welcome to Zensar Payroll System");
			printLine2();
			System.out.format(menuAlignFormat, "1. Registration");
			System.out.format(menuAlignFormat,
					"2. Calculate Monthly Net Salary");
			System.out.format(menuAlignFormat, "3. Get Associate Details");
			System.out.format(menuAlignFormat, "4. Get All Associate Details");
			System.out.format(menuAlignFormat, "5. Close payroll services");
			printLine2();
			System.out.format(menuAlignFormat, "Please enter your choice");
			printLine2();

			try {

				choice = scanner.nextInt();
				switch (choice) {
				case 1:
					scanner.nextLine();
					System.out.println("Enter your name");
					associateName = scanner.nextLine();
					System.out.println("Enter your email Id");
					emailId = scanner.next();
					System.out.println("Enter your pan card");
					panCard = scanner.next();
					System.out.println("Enter your basic salary");
					basicSalary = scanner.nextInt();
					System.out.println("Enter your pf");
					pf = scanner.nextInt();
					System.out.println("Enter your Company pf");
					comapnyPf = scanner.nextInt();
					System.out.println("Enter your investment");
					investment = scanner.nextInt();

					System.out
							.println("Successfully registered with associate Id "
									+ services.acceptCustomerDetails(
											associateName, emailId, panCard,
											basicSalary, pf, comapnyPf,
											investment));
					break;
				case 2:
					System.out.println("Enter your associate Id");

					associateId = scanner.nextInt();

					int monthlyNetSalary = services
							.calculateMonthlyNetSalary(associateId);
					System.out.println("Monthly Net Salary: "
							+ monthlyNetSalary);
					break;

				case 3:

					System.out.println("Enter your associate Id");
					associateId = scanner.nextInt();

					Associate associate = services
							.getAssociateDetails(associateId);

					printLine2();
					System.out.format("| %50s %-50s |%n", "PAY SLIP", " ");
					printLine2();
					printPayRoll(associate);

					break;
				case 4:

					ArrayList<Associate> associates = services
							.getAllAssociateDetails();

					printLine2();
					System.out
							.format("| %60s %-40s |%n", "ZENSAR PAYROLL", " ");
					printLine2();

					for (Associate a : associates) {

						String leftAlignFormat = "|%-50s |%-50s |%n";

						System.out.format(leftAlignFormat, "Associate Id",
								a.getAssociateId());
						System.out.format(leftAlignFormat, "Associate Name",
								a.getAssociateName());
						System.out.format(leftAlignFormat,
								"Associate Email Id", a.getEmailID());
						System.out.format(leftAlignFormat,
								"Associate PAN Card", a.getPanCard());

						printLine2();

						System.out.format(leftAlignFormat, "Basic Salary",
								a.getBasicSalary());
						System.out.format(leftAlignFormat, "HRA", a.getHra());
						System.out.format(leftAlignFormat, "TA", a.getTa());
						System.out.format(leftAlignFormat, "DA", a.getDa());
						System.out.format(leftAlignFormat, "PF", a.getPf());
						System.out.format(leftAlignFormat, "Company PF",
								a.getCompanyPf());
						System.out.format(leftAlignFormat, "Monthly Tax",
								a.getMonthlyTax());
						System.out.format(leftAlignFormat, "Net Salary",
								a.getNetSalary());

						printLine2();
						printLine2();
					}

					break;
				case 5:

					if (services.closePayrollServices() == true) {
						System.out
								.println("Thank you for using the payroll system");
						System.exit(0);
					}
					break;
				default:
					System.out.println("Invalid choice");
					break;
				}
			} catch (PayRollServicesNotFoundException e) {
				// e.printStackTrace();
				System.out.println("Sorry service is unavailable currently");
				System.exit(0);
			} catch (InValidAssociateIDException e) {
				 e.printStackTrace();
				 System.out.println("Please enter a valid assoicate ID");
			} catch (AssociateDetailsNotFoundException e) {
				// e.printStackTrace();
				System.out.println("Sorry you have not registered");
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid input");
				scanner.next();
			} catch (Exception e) {
				System.out.println("Something went wrong, Please try again");
			}

			System.out.println("Do you wish to continue?(Y/N)");
			do {
				decision = scanner.next();
				if (decision.equalsIgnoreCase("n")) {
					System.out
							.println("Thank you for using the payroll system");
					scanner.close();
					System.exit(0);
				} else if (!decision.equalsIgnoreCase("y")) {
					System.out.println("Please enter a valid input (Y/N)");
				}
			} while (!decision.equalsIgnoreCase("y"));

		} while (decision.equalsIgnoreCase("y"));

	}

	public static void printPayRoll(Associate associate) {
		String leftAlignFormat = "|%-50s |%-50s |%n";
		String leftAlignFormat2 = "|%-25s|%25s|%-25s|%25s|%n";
		String leftAlignFormat3 = "|%25s|%25s|%25s|%25s|%n";

		System.out.format(leftAlignFormat, "Associate Id",
				associate.getAssociateId());
		System.out.format(leftAlignFormat, "Associate Name",
				associate.getAssociateName());
		System.out.format(leftAlignFormat, "Associate Email Id",
				associate.getEmailID());
		System.out.format(leftAlignFormat, "Associate PAN Card",
				associate.getPanCard());
		printLine2();
		printLine2();
		System.out.format(leftAlignFormat3, "Earnings", "Amount", "Deductions",
				"Amount");
		printLine();
		System.out.format(leftAlignFormat2, "Basic Salary",
				associate.getBasicSalary(), "Company PF",
				associate.getCompanyPf());
		System.out.format(leftAlignFormat2, "HRA", associate.getHra(), "PF",
				associate.getPf());
		System.out.format(leftAlignFormat2, "DA", associate.getDa(),
				"Monthly Tax", associate.getMonthlyTax());
		System.out.format(leftAlignFormat2, "TA", associate.getTa(), " ", " ");
		System.out.format(leftAlignFormat2, "Company PF",
				associate.getCompanyPf(), " ", " ");
		printLine();
		System.out.format(leftAlignFormat2, "Gross Salary",
				associate.getGrossSalary(), "Total Deductions",
				associate.getMonthlyTax() + associate.getCompanyPf()
						+ associate.getPf());
		printLine();
		System.out.format(leftAlignFormat2, " ", " ", "Net Salary",
				associate.getNetSalary());
		printLine2();
	}

	public static void printLine() {
		System.out.print("|");
		for (int i = 1; i <= 104; i++) {
			if (i == 26 || i == 52 || i == 78 || i == 104)
				System.out.print("|");
			else
				System.out.print("-");
		}
		System.out.println();
	}

	public static void printLine2() {
		System.out.print("|");
		for (int i = 1; i <= 103; i++) {
			System.out.print("-");
		}
		System.out.println("|");
	}

}
