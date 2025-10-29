package payrollSystem;

/**
 * The SalariedEmp class is a subclass of the Employee class and denotes
 * employees of the company that are salaried as opposed to commissioned.
 * Salaried employees make earnings based on their yearly salary and 
 * number of pay periods--which is consistently 26. Salaried employees, 
 * unlike commissioned employees, may not work more than 80 hours in a single
 * pay period. Similarly, the total sales of a salaried employee serves 
 * void for SalariedEmp objects because the total sales do not impact 
 * the total pay that the employee receives. This class contains a field 
 * pertaining to the specified yearly salary of the current SalariedEmp
 * object and is used in order to calculate the total pay of the employee.
 * Many of the methods in this class override those in the Employee class. These
 * methods are denoted by @Override.
 */

public class SalariedEmp extends Employee {

	private double yearlySalary;
	
	/**
	 * Constructor that initializes fields pertaining to employee name, hours
	 * worked, and total sales through a call to the superclass constructor;
	 * the method also initializes the value of employee's yearly salary.
	 * 
	 * @param name 		   A String representing the desired name of the 
	 * 					   employee
	 * @param yearlySalary A double value representing the desired salary of 
	 * 				       the employee
	 */
	public SalariedEmp(String name, double yearlySalary) {
		/* calls to the Employee constructor */
		super(name);
		this.yearlySalary = yearlySalary;
	}
	
	/**
	 * Adds the specified number of hours to the total number of hours
	 * that the employee has worked and stores that value in the hoursWorked
	 * field. A salaried employee may not work over 80 hours in a single
	 * pay period.
	 * 
	 * @param hours A primitive integer representing the number of hours to be
	 * 				added to the current object
	 * @return true once the hours have been successfully added to the object
	 *         and false if the hours added will exceed 80
	 */
	@Override
	public boolean addHours(int hours) {
		if (hours + hoursWorked <= 80) {
			hoursWorked += hours;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the calculated pay of the current object by using the established
	 * yearly salary of the employee and dividing it by 26, the total number of
	 * pay periods in a given year.
	 * 
	 * @return A double value reflecting the calculations of the employee's
	 * 		   earnings per pay period.
	 */
	@Override
	public double calculatePay() {
		return yearlySalary / 26;
	}
	
}
