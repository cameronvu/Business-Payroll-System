package payrollSystem;

/**
 * The Employee class serves as a superclass for both the CommissionedEmp and 
 * SalariedEmp classes. This class contains fields and methods applicable to
 * both types of employees--commissioned and salaried. Specifically, this class
 * contains fields that represent the name of the current Employee object, 
 * the total of hours that the associated employee has worked, as well as
 * the total sales that they have made. 
 */

public class Employee {
	
	private String name;
	protected int hoursWorked;
	protected double totalSales;
	
	/**
	 * Constructor that initializes the name of the Employee object based on the
	 * specified parameter; the method also sets the total hours worked and the
	 * total sales to zero due to the fact that the employee is new.
	 * 
	 * @param name A String representing the desired name of the employee
	 */
	public Employee(String name) {
		this.name = name;
		hoursWorked = 0;
		totalSales = 0.0;
	}
	
	/**
	 * A getter method that retrieves the name of the employee.
	 * 
	 * @return A String representing the associated name of the current object 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A getter method that retrieves the total number of hours that the 
	 * employee has worked.
	 * 
	 * @return A primitive integer representing the associated number of hours
	 * 		   worked of the current object 
	 */
	public int getHours() {
		return hoursWorked;
	}
	
	/**
	 * Adds the specified number of hours to the total number of hours
	 * that the employee has worked and stores that value in the hoursWorked
	 * field. This method is overridden in the SalariedEmp class.
	 * 
	 * @param hours A primitive integer representing the number of hours to be
	 * 				added to the current object
	 * @return true once the hours have been successfully added to the object
	 */
	public boolean addHours(int hours) {
		hoursWorked += hours;
		return true;
	}
	
	/**
	 * Returns true to verify that the sale was successful; no value is 
	 * updated in this superclass method, which is overridden in the 
	 * CommissionedEmp class.
	 * 
	 * @param saleAmt A double value representing the amount of money that 
	 * 				  the current employee made from a sale
	 * @return true in all cases where the method is successfully called
	 */
	public boolean addSale(double saleAmt) {
		return true;
	}
	
	/**
	 * Returns the calculated pay of the current object and is overridden in
	 * both the SalariedEmp and CommissionedEmp classes. 
	 * 
	 * @return A double value of 0.0
	 */
	public double calculatePay() {
		return 0.0;
	}
	
	/**
	 * Sets the hours and totalSales stored by all Employee objects equal to
	 * zero. The method is used in order to aid the process of resetting the
	 * pay period, which requires the hours worked and sales to subsequently
	 * reset.
	 */
	public void reset() {
		hoursWorked = 0;
		totalSales = 0.0;
	}

	
}
