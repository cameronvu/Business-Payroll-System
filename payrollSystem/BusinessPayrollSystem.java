package payrollSystem;

import arrayBackedList.ArrayBackedList;

/**
 * The BusinessPayrollSystem class contains the means to create objects 
 * that store various data about employees associated with a specific
 * company. More specifically, this class pertains to company users who have
 * a paid plan. This class uses non-static fields pertaining to the name of
 * the company associated with the current object, the total payroll or the 
 * total owed by the company to all of their employees regardless of 
 * commissioned or salaried status, an ArrayBackedList object storing the 
 * employee objects, an ArrayBackedList object storing the names of each
 * employee associated with the current object, and finally, an Employee object
 * which keeps track of the current Employee object used in many methods
 * within this class. The fields that are directly accessed by the subclass of 
 * the BusinessPayrollSystem class, the UnpaidUser class, have protected 
 * access modifiers. Fields that are not directly accessed by the UnpaidUser
 * class are denoted as private.
 */

public class BusinessPayrollSystem {
	
	private String companyName; 
	private double totalPayroll;
	protected ArrayBackedList emps; /* an ArrayBackedList for employees */
	protected ArrayBackedList names; /* an ArrayBackedList for employee names */
	protected Employee current; /* the current Employee object */
	
	/**
	 * Constructor that initializes the non-static field storing the name 
	 * of the company associated with the current object. The constructor also
	 * initializes the arrays associated with Employee objects and their
	 * respective names.
	 * 
	 * @param companyName A String that represents the name of the company
	 */
	public BusinessPayrollSystem(String companyName) {
		this.companyName = companyName;
		emps = new ArrayBackedList(1);
		names = new ArrayBackedList(1);
	}
	
	/**
	 * Constructor that initializes the non-static field storing the name of the 
	 * company associated with the current object. 
	 * 
	 * @param companyName A String that represents the name of the company
	 * @param maxEmp      A primitive integer that represents the maximum
	 * 					  number of employees that the associated company is 
	 * 				      able to store data for
	 */
	public BusinessPayrollSystem(String companyName, int maxEmp) {
		this.companyName = companyName;
	}

    /**
     * Getter method for the company name associated with the current object.
     * 
     * @return A String representing the field, companyName, which is
     * 		   also equivalent to the argument passed into the initial TerpCorp 
     * 		   makeUserSystem() method
     */
	public String getCompanyName() {
        return companyName;
    }

	/**
	 * Adds a commissioned employee with the desired name and commission rate
	 * to the non-static array associated with the current object. Paid users 
	 * are always able to add another employee. 
	 * 
	 * @param empName        A String that represents the name of the employee 
	 * 						 that is to be added to the current object
	 * @param commissionRate A double representing the commission rate of the 
	 * 						 new employee
	 * @return true if the employee is successfully added to the object and 
	 * 		   false otherwise
	 */
	public boolean newCommissionEmp(String empName, double commissionRate) {
		if (empName != null && !empName.isEmpty() && commissionRate <= 100 
			&& commissionRate >= 0 && !isEmployee(empName)) {
			/* adds a new CommissionedEmp object to the emp data structure */
			emps.add(new CommissionedEmp(empName, commissionRate));
			/* adds the specified employee name to the names data structure */
			names.add(empName);
			return true;

		}
		return false;
	}

    /**
	 * Adds a salaried employee with the desired name and yearly salary
	 * to the non-static array associated with the current object. Paid users 
	 * are able to always add another employee. 
	 * 
	 * @param empName        A String that represents the name of the employee 
	 * 						 that is to be added to the current object
	 * @param yearlySalary   A double representing the yearly salary of the 
	 * 						 new employee
	 * @return true if the employee is successfully added to the object and 
	 * 		   false otherwise
	 */
	public boolean newSalariedEmp(String empName, double yearlySalary) {
		if (empName != null && !empName.isEmpty() && yearlySalary > 0.0 
			&& !isEmployee(empName)) {
			/* adds a new SalariedEmp object to the emp data structure */
			emps.add(new SalariedEmp(empName, yearlySalary));
			/* adds the specified employee name to the names data structure */
			names.add(empName);
			return true;
		}
		return false;
	}

	/**
	 * Searches non-static field, emps, for the specified name of an employee 
	 * and returns either true or false depending on if the name was found.
	 * 
	 * @param empName A String representing the name of the employee being 
	 *                searched
	 *                for in the current object
	 * @return true if the name was found in the non-static array storing 
	 *         employees that is associated with the current object and false 
	 *         otherwise
	 */
    public boolean isEmployee(String empName) {
        /* iterates through the emps data structure for the length of emps */
    	for (int i = 0; i < emps.getCapacity(); i++) {
        	/* creates and initializes an Employee object representing 
        	 * the current object at the respective index of emps */
    		Employee e = (Employee) emps.get(i);
        	if (e != null) {
        		if (e.getName().equals(empName)) {
            		/* changes the value of the current field to the found 
            		 * Employee object */
        			current = e;
            		return true;
            	}
        	}
        }
        return false;
    }

    /**
     * Retrieves the total number of employees being stored in the current 
     * object.
     * 
     * @return A primitive integer representing the number of employees
     * associated with the current object; alternately, the number of 
     * employees being stored in the non-static array, emps.
     */
    public int employeeCount() {
    	/* calls to the getSize() method of the ArrayBackedList class */
    	return emps.getSize();
    }

	/**
	 * Retrieves the number of maximum employees associated with the current 
	 * object, which for users of this class is a maximum integer value.
	 * 
	 * @return A primitive integer value representing the total number of 
	 * 		   employees allowed to be stored by the current object
	 */
    public int maxEmployees() {
        return Integer.MAX_VALUE;
    }
    
	/**
	 * Adds the specified amount of hours worked to the employee desired. The
	 * outcome differs for salaried employees, who cannot exceed 80 hours of 
	 * work per pay period, and commissioned employees, who can work an 
	 * unlimited amount of hours per pay period.
	 * 
	 * @param empName     A String representing the name of the employee in 
	 * 					  which the specified hours should be added to
	 * @param hoursWorked A primitive integer representing the number of hours 
	 * 					  to add to the specified employee
	 * @return true if the addition was successful and false otherwise
	 */
    public boolean addHoursWorked(String empName, int hoursWorked) {
    	if (empName != null && !empName.isEmpty() && hoursWorked > 0) {
			if (isEmployee(empName)) {
				/* returns the true or false value that is a result of a call 
				 * to the addHours() method in the Employee or SalariedEmp
				 * classes using the updated value of the field current */
				return current.addHours(hoursWorked);
			}
		}
		return false;
    }
    
	/**
	 * Retrieves the number of hours that a specific employee has worked during 
	 * a current pay period.
	 * 
	 * @param empName A String representing the name of the desired employee
	 * @return A primitive integer representing the number of hours that the 	
	 * 		   given employee has worked in a pay period
	 */
	public int hoursWorked(String empName) {
		if (empName != null && !empName.isEmpty() && isEmployee(empName)) {
			/* returns the true or false value that is a result of a call 
			 * to the getHours() method in the Employee class
			 * using the updated value of the field current */
			return current.getHours();
		}
		return -1;
	}

    /**
     * Adds the specified sale amount to the desired employee. The effect
     * of this method differs depending on whether the employee is salaried,
     * in which the sales are not actually stored in memory, or commissioned,
     * in which the sales are actually stored. 
     * 
     * @param empName A String representing the desired employee for the sale
     * 		          to be added to
     * @param saleAmt A double representing the sale amount that the specified
     * 				  employee made
     * @return true if the addition of the sale was successful and false 
     * 	       otherwise
     */
	public boolean saleMade(String empName, double saleAmt) {
    	if (empName != null && !empName.isEmpty() 
    		&& isEmployee(empName) && saleAmt > 0) {
    		/* returns the true or false value that is a result of a call 
			 * to the addSale() method in the Employee or CommissionedEmp
			 * classes using the updated value of the field current */
    		return current.addSale(saleAmt);
		}
		return false;
    }

	/**
	 * Retrieves the total number of sales made for the given employee. The 
	 * method returns differ for salaried employees, where 0.0 is returned
	 * for their total sales, and commissioned employees, where the stored
	 * number of their sales is returned.
	 * 
	 * @param empName A String representing the specified employee to retrieve
	 * 				  the total sales of
	 * @return A double value representing the total sales made by the specified
	 * 		   employee; -1 is returned otherwise
	 */
    public double totalSalesMade(String empName) {
        if (empName != null && !empName.isEmpty() 
        	&& isEmployee(empName)) {
        	/* returns the attribute specific to the current Employee object */
        	return current.totalSales;
        }
        return -1;
    }

    /**
     * Calculates the returns the amount that a specified employee makes
     * based on their status as either a salaried employee, where pay is 
     * calculated in regards to a yearly salary and payroll periods, or a 
     * commissioned employee, where pay is calculated using a rate of commission
     * and total hours worked.
     * 
     * @param empName A String representing the name of the employee that the
     * 				  paycheck is being issued to
     * @return A double representing the decimal amount that the employee will
     * 		   be paid based on their employee status
     */
    public double issuePaycheck(String empName) {
    	if (empName != null && !empName.isEmpty() 
            && isEmployee(empName)) {
    			/* calls to the calculatePay() method from the Employee class 
    			 * or its subclasses */
    			return current.calculatePay();
            }
            return -1;
    }

    /**
     * Calculates the total amount that the company associated with the current
     * object will have to pay all of their employees.
     * 
     * @return A double representing the total payroll
     */
    public double payrollTotal() {
        totalPayroll = 0;
    	/* iterates through the occupied indices in the emp data structure */
    	for (int i = 0; i < emps.getSize(); i++) {
        	/* instantiates a new Employee object by casting the object at
        	 * the current index to an Employee object in order to calculate
        	 * the pay */
    		Employee e = (Employee) emps.get(i);
        	totalPayroll += e.calculatePay();
        }
        return totalPayroll;
    }

    /**
     * Resets the hours worked and the total sales made by all employees being
     * stored in the current object in order to denote the start of a new
     * pay period.
     */
    public void changePayPeriod() {
    	for (int i = 0; i < emps.getSize(); i++) {
    		/* instantiates a new Employee object by casting the object at
        	 * the current index to an Employee object */
    		Employee e = (Employee) emps.get(i);
    		e.reset(); /* calls to the Employee reset() method */
        }
    }

    /**
     * Calculates the total owed by the current company. The method returns 
     * differ for paid users, who pay per-user.
     * 
     * @return A double representing the total owed by the company
     */
    public double amountOwedToUs() {
    	return emps.getSize() * 10;
    }

}
