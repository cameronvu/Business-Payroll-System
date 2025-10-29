package payrollSystem;

/**
 * The CommissionedEmp class is a subclass of the Employee class and denotes
 * employees of the company that are commissioned as opposed to salaried.
 * Commissioned employees make earnings based on their total sales and 
 * commission rates, determined at the instantiation of the CommissionedEmp 
 * object, and are exempt from limitations on maximum number of hours
 * worked within a pay period, unlike a salaried employee. This class contains
 * a private field pertaining to the specified commission rate of the current
 * Employee object and is used to calculate the pay of the current employee.
 * Many of the methods in this class override those in the Employee class. These
 * methods are denoted by @Override.
 */

public class CommissionedEmp extends Employee {
	
	private double commissionRate;
	
	/**
	 * Constructor that initializes fields pertaining to employee name, hours
	 * worked, and total sales through a call to the superclass constructor;
	 * the method also initializes the value of employee's commission rate.
	 * 
	 * @param name 		     A String representing the desired name of the 
	 * 					     employee
	 * @param commissionRate A double value representing the desired commission  
	 * 				         rate of the employee
	 */
	public CommissionedEmp(String name, double commissionRate) {
		/* calls to the Employee constructor */
		super(name);
		/* instantiates commissionRate as a percentage for later calculation */
		this.commissionRate = commissionRate * .01;
	}
	
	/**
	 * Returns true to verify that the sale was successful and updates the 
	 * field which stores the total sales of the employee to reflect this.
	 * 
	 * @param saleAmt A double value representing the amount of money that 
	 * 				  the current employee made from a sale
	 * @return true after the amount was successfully added to the current
	 * 		   object
	 */
	@Override
	public boolean addSale(double saleAmt) {
		totalSales += saleAmt;
		return true;
	}
	
	/**
	 * Returns the calculated pay of the current object by multiplying the 
	 * total sales of the commissioned employee by their set commission rate.
	 * 
	 * @return A double value reflected by the calculation of the employee's
	 * 		   earnings from the current pay period
	 */
	@Override
	public double calculatePay() {
		return totalSales * commissionRate;
	}
	

}
