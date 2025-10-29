package payrollSystem;

import arrayBackedList.ArrayBackedList;

/**
 * The UnpaidUser class contains the means to create objects that store various 
 * data about employees associated with a specific company. More specifically, 
 * this class pertains to company users who have an unpaid plan. This class uses 
 * a non-static field which denotes the maximum number of employees able to
 * be supported by the current object based on the company status as an 
 * unpaid user. As a subclass of the BusinessPayrollSystem class, the UnpaidUser
 * class shares the non-static fields of the superclass, which are used in
 * multiple instances throughout proceeding methods. Finally, several methods
 * in this class override methods in it's superclass and is denoted 
 * respectively by @Override. 
 */

public class UnpaidUser extends BusinessPayrollSystem {

	private int maxEmp;
	
	/**
	 * Constructor that initializes the non-static field storing the name of the 
	 * company associated with the current object. The constructor also
	 * initializes the associated array of employees and names based on
	 * a pre-determined number of maximum employees. 
	 * 
	 * @param companyName A String that represents the name of the company
	 * @param maxEmp      A primitive integer that represents the maximum
	 * 					  number of employees that the associated company is 
	 * 				      able to store data for
	 */
	public UnpaidUser(String companyName, int maxEmp) {
		super(companyName, maxEmp);
		this.maxEmp = maxEmp;
		/* initializes the emps data structure for a max number of employees */
		emps = new ArrayBackedList(maxEmp); 
		/* initializes the names data structure for a max number of names */
		names = new ArrayBackedList(maxEmp);
	}
	
	/**
	 * Adds a commissioned employee with the desired name and commission rate
	 * to the non-static array associated with the current object. Unpaid users 
	 * must not exceed a  previously determined maximum number of employees. 
	 * 
	 * @param empName        A String that represents the name of the employee 
	 * 						 that is to be added to the current object
	 * @param commissionRate A double representing the commission rate of the 
	 * 						 new employee
	 * @return true if the employee is successfully added to the object and 
	 * 		   false otherwise
	 */
	@Override
	public boolean newCommissionEmp(String empName, double commissionRate) {
		if (empName != null && !empName.isEmpty() && commissionRate <= 100 
			&& commissionRate >= 0 && !isEmployee(empName)) {
			if (emps.getSize() < maxEmp) {
				/* adds a new CommissionedEmp object to emp */
				emps.add(new CommissionedEmp(empName, commissionRate));
				/* adds the employee name to the names data structure */
				names.add(empName);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds a salaried employee with the desired name and yearly salary
	 * to the non-static array associated with the current object. Unpaid users 
	 * must not exceed a previously determined maximum number of employees. 
	 * 
	 * @param empName        A String that represents the name of the employee 
	 * 						 that is to be added to the current object
	 * @param yearlySalary   A double representing the yearly salary of the 
	 * 						 new employee
	 * @return true if the employee is successfully added to the object and 
	 * 		   false otherwise
	 */
	@Override
	public boolean newSalariedEmp(String empName, double yearlySalary) {
		if (empName != null && !empName.isEmpty() && yearlySalary > 0.0 
			&& !isEmployee(empName)) {
			if (emps.getSize() < maxEmp) {
				/* adds a new CommissionedEmp object to emp */
				emps.add(new SalariedEmp(empName, yearlySalary));
				/* adds the employee name to the names data structure */
				names.add(empName);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retrieves the number of maximum employees associated with the current 
	 * object, which is a different maximum than it's superclass object,
	 * BusinessPayrollSystem which is for paid users.
	 * 
	 * @return A primitive integer value representing the total number of 
	 * 		   employees allowed to be stored by the current object
	 */
	@Override
	public int maxEmployees() {
        return maxEmp;
    }
	
	/**
     * Retrieves the total owed by the current company. The method returns 
     * differ for unpaid users, who pay nothing.
     * 
     * @return A double representing the total owed by the company
     */
	@Override
	public double amountOwedToUs() {
		return 0.0;
    }

}
