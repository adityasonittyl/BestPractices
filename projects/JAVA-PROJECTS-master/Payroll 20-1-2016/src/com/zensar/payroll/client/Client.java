package com.zensar.payroll.client;

import java.util.Scanner;

import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.InValidAssociateIDException;
import com.zensar.payroll.exceptions.PayRollServicesNotFoundException;
import com.zensar.payroll.provider.Provider;
import com.zensar.payroll.services.PayrollServices;

public class Client {

	public static void main(String[] args) {

		PayrollServices services = Provider.getPayrollServices();

		char decision = 'n';
		int choice;
		String associateName, emailId, panCard;
		int basicSalary, pf, comapnyPf, investment, associateId;
		Scanner scanner = new Scanner(System.in);
		do {

			System.out.println("1. Registration");
			System.out.println("2. Calculate Monthly Net Salary");
			System.out.println("3. Get Associate Details");
			System.out.println("4. Get All Associate Details");
			System.out.println("5. Close payroll services");
			choice = scanner.nextInt();

			switch (choice) {
			case 1: // registration
				System.out.println("Enter your name");
				associateName = scanner.next();
				System.out.println("Enter your email Id");
				emailId = scanner.next();
				System.out.println("Enter your pan card");
				panCard = scanner.next();
				System.out.println("Enter your basic salary");
				basicSalary = scanner.nextInt();
				System.out.println("Enter your pf");
				pf = scanner.nextInt();
				System.out.println("Enter your company pf");
				comapnyPf = scanner.nextInt();
				System.out.println("Enter your investment");
				investment = scanner.nextInt();

				try {
					System.out
							.println("Successfully registered with associatedId "
									+ services.acceptCustomerDetails(
											associateName, emailId, panCard,
											basicSalary, pf, comapnyPf,
											investment));
				} catch (PayRollServicesNotFoundException e) {
					e.printStackTrace();
				}

				break;
			case 2: // Calculate Monthly Net Salary
				System.out.println("Enter your associate Id");
				associateId = scanner.nextInt();
				try {
					int monthlyNetSalary = services
							.calculateMonthlyNetSalary(associateId);
					System.out.println("Monthly Net Salary: "
							+ monthlyNetSalary);
				} catch (InValidAssociateIDException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AssociateDetailsNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PayRollServicesNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3: // Get Associate Details

				break;
			case 4:// Get All Associate Details

				break;
			case 5: // Close payroll services

				break;

			default:
				System.out.println("Invalid choice");
				break;
			}

			System.out.println("Do you wish to continue?(Y/N)");
			decision = scanner.next().charAt(0);
			if (decision == 'n' || decision == 'N') {
				System.out.println("Thank you for using the banking system");
				System.exit(0);
			}
		} while (decision == 'y' || decision == 'Y');

		scanner.close();

	}

}
