package com.zensar.training;

public class Client {

	public static void main(String[] args) {
		Employee employee = new Employee(47178, "Akash Pal", 0);
		//System.out.println(employee);
		employee.display();

		WageEmployee wageEmployee = new WageEmployee(2, "BBB", 0, 9, 100);
		//System.out.println(wageEmployee);
		
		SalesPerson salesPerson = new SalesPerson(1, "AAA", 0, 10, 50, 20);
		//System.out.println(salesPerson);
		
		WageEmployee e = new WageEmployee(2, "BBB", 0, 9, 100);
		Employee emp = e;
		emp.display();
		//this.display(); 
		
		//System.out.println('a'=='b');
		
		
	}

}
   