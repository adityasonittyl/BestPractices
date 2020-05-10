package com.zensar.payroll.client;

import java.util.ArrayList;
import java.util.Scanner;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.InValidAssociateIDException;
import com.zensar.payroll.exceptions.PayRollServicesNotFoundException;
import com.zensar.payroll.provider.Provider;
import com.zensar.payroll.services.PayrollServices;

public class Client {

	public static void main(String[] args) {

		PayrollServices services=null;
		try {
			services = Provider.getPayrollServices();
		} catch (PayRollServicesNotFoundException e1) {
			System.out.println(e1.getMessage());
			System.exit(0);
		}

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
				try {
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

					System.out
							.println("Successfully registered with associatedId "
									+ services.acceptCustomerDetails(
											associateName, emailId, panCard,
											basicSalary, pf, comapnyPf,
											investment));
				} catch (PayRollServicesNotFoundException e) {
					//e.printStackTrace();
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println("Something went wrong. Please try again");
				}

				break;
			case 2: // Calculate Monthly Net Salary
				System.out.println("Enter your associate Id");
				try {
					associateId = scanner.nextInt();
					
					int monthlyNetSalary = services
							.calculateMonthlyNetSalary(associateId);
					System.out.println("Monthly Net Salary: "
							+ monthlyNetSalary);
				} catch (InValidAssociateIDException e) {
					//e.printStackTrace();
					System.out.println(e.getMessage());
				} catch (AssociateDetailsNotFoundException e) {
					//e.printStackTrace();
					System.out.println(e.getMessage());
				} catch (PayRollServicesNotFoundException e) {
					//e.printStackTrace();
					System.out.println(e.getMessage());
				}catch (Exception e) {
					System.out.println("Something went wrong. Please try again");
				}
				break;
			case 3: // Get Associate Details
				try {
					System.out.println("Enter your associate Id");
					associateId = scanner.nextInt();
					
					Associate associate = services
							.getAssociateDetails(associateId);
					
					System.out.println(" ------------------------------------------------------------------------------------- 	");
					System.out.println(" |                                          											");
					System.out.println(" |              			ZENSAR PAYROLL              								");
					System.out.println(" |                                          											");	
					System.out.println(" ---------------------------------------------------------------------------------------");
					System.out.println(" |  		Associate Id           				|	" + associate.getAssociateId());
					System.out.println(" |  		Associate Name         				|	" + associate.getAssociateName());
					System.out.println(" |  		Associate Email Id     				|	" + associate.getEmailID());
					System.out.println(" |  		Associate PAN Card     				|	" + associate.getPanCard());	
					System.out.println(" ---------------------------------------------------------------------------------------");
					System.out.println(" |  		Associate Basic Salary 				| 	" + associate.getBasicSalary());
					System.out.println(" |  		Associate HRA          				| 	" + associate.getHra());
					System.out.println(" |  		Associate DA           				| 	" + associate.getDa());
					System.out.println(" |  		Associate TA           				| 	" + associate.getTa());
					System.out.println(" |  		Associate Gross Salary 				| 	" + associate.getGrossSalary());
					System.out.println(" |  		Associate PF           				| 	" + associate.getPf());
					System.out.println(" |  		Associate Company PF               		| 	" + associate.getCompanyPf());
					System.out.println(" |  		Associate Monthly Tax	 			| 	" + associate.getMonthlyTax());
					System.out.println(" |  		Associate Net Salary     			| 	" + associate.getNetSalary());
					System.out.println(" |  		Associate Investment     			| 	" + associate.getInvestment());
					System.out.println(" -------------------------------------------------------------------------------------");
					
					
				} catch (InValidAssociateIDException e) {
					//e.printStackTrace();
					System.out.println(e.getMessage());
				} catch (AssociateDetailsNotFoundException e) {
					//e.printStackTrace();
					System.out.println(e.getMessage());
				}catch (Exception e) {
					System.out.println("Something went wrong. Please try again");
				}

				break;
			case 4:// Get All Associate Details
					try {
						
					ArrayList<Associate> associates = services.getAllAssociateDetails();
					for (Associate associate : associates) {
						
						System.out.println("Id: " + associate.getAssociateId());
						System.out.println("Name: "+ associate.getAssociateName());
						System.out.println("EmailId: "+associate.getEmailID());
						System.out.println("Pan Card: "+ associate.getPanCard());
						System.out.println("Basic Salary: " + associate.getBasicSalary());
						System.out.println("HRA: "+associate.getHra());
						System.out.println("TA: "+associate.getTa());
						System.out.println("DA: "+associate.getDa());
						System.out.println("Gross Salary: "+associate.getGrossSalary());
						System.out.println("PF: "+associate.getPf());
						System.out.println("Company PF: "+associate.getCompanyPf());
						System.out.println("Monthy Salary: " +associate.getMonthlyTax());
						System.out.println("Net Salary: "+associate.getNetSalary());
						System.out.println("Investment: "+ associate.getInvestment());
						System.out.println("--------------------------------------------");
					}
				} catch (PayRollServicesNotFoundException e) {
					//e.printStackTrace();
					System.out.println(e.getMessage());
				}catch (Exception e) {
					System.out.println("Something went wrong. Please try again");
				}
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
