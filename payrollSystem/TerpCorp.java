package payrollSystem;

/**
 * The TerpCorp class allows company users to associate with either of
 * the payroll plans below. Specifically, the TerpCorp class contains
 * two static methods which create BusinessPayrollSystem objects based 
 * on the status of the user as either a paid or an unpaid user.
 */

public class TerpCorp {

    /**
     * Uses the specified company name to construct a new BusinessPayrollSystem
     * object that is returned.
     * 
     * @param companyName Represents the name of the company to create the 
     * 					  subsequent BusinessPayrollSystem object for
     * @return			  Returns a new BusinessPayrollSystem object
     */
	public static BusinessPayrollSystem makeUserSystem(String companyName) {
        return new BusinessPayrollSystem(companyName);
    }

	/**
	 * Uses the specified company name and maximum number of employees able to 
	 * be supported in order to construct a new BusinessPayrollSystem
	 * object that is returned.
	 * 
	 * @param companyName  A String representing the name of the company to 
	 * 					   associate with the BusinessPayrollSystem object 
	 * @param maxEmployees A primitive integer representing the maximum number
	 *                     employees to associate with the new
	 *                     BussinessPayrollSystem object
	 * @return Returns a new BusinessPayrollSystem object
	 */
    public static BusinessPayrollSystem makeUserSystem(String companyName,
                                                       int maxEmployees) {
    	return new UnpaidUser(companyName, maxEmployees);
    }

}
