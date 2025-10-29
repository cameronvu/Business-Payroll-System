package tests;

// DO NOT ADD ANY JUNIT-RELATED IMPORTS TO THIS CLASS.  For your code to
// compile on the submit server you should use ONLY the features of JUnit
// that can be used with the imports below.
import arrayBackedList.ArrayBackedList;
import payrollSystem.BusinessPayrollSystem;
import payrollSystem.TerpCorp;

import org.junit.*;
import static org.junit.Assert.*;

public class StudentTests {

    // Tests the constructor in the ArrayBackedList class
	// by ensuring compilation after the declaration of a new ArrayBackedList
	// object.
	@Test 
	public void studentTest1() {
        ArrayBackedList arr = new ArrayBackedList(2);
        
        assertEquals(arr.getCapacity(), 2);
    }
	
	// Tests the getSize() method by ensuring that the method returns
	// 0 when no elements are being stored, and returns the correct number
	// when elements are being stored.
	@Test 
	public void studentTest2() {
        ArrayBackedList arr = new ArrayBackedList(3);
        
        assertEquals(arr.getSize(), 0);
        
        arr.add(1);
        arr.add(2);
        arr.add(3);
        
        assertEquals(arr.getSize(), 3);
   
    }
	
	// Tests the getSize() method by ensuring that the method returns
	// the correct number when more than the initial increment amount
	// of elements are being stored.
	@Test
	public void studentTest3() {
		ArrayBackedList arr = new ArrayBackedList(3);

		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);

		assertEquals(arr.getSize(), 4);

	}
	
	// Tests the getCapacity() method by ensuring that the correct capacity
	// is returned before an the array's size is modified by the add() method
	// and after the add() method.
	@Test
	public void studentTest4() {
		ArrayBackedList arr = new ArrayBackedList(3);

		assertEquals(arr.getCapacity(), 3);
		
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);

		assertEquals(arr.getCapacity(), 6);

	}
	
	// Tests the get() method by ensuring that the correct String reference
	// is retrieved by the method with the corresponding desired position.
	@Test
	public void studentTest5() {
		ArrayBackedList arr = new ArrayBackedList(6);

		arr.add("Cameron Vu");
		arr.add("Eats");
		arr.add(3);
		arr.add("Bananas");
		arr.add("Every");
		arr.add("Day");
		arr.add("!");
		
		assertEquals("Bananas", arr.get(3));
		assertEquals("Every", arr.get(4));
		assertEquals("!", arr.get(6));

	}
	
	// Tests the getCompanyName() method by ensuring that the correct String 
	// that represents the company name is utilized.
	@Test
	public void studentTest6() {
		
		assertEquals(TerpCorp.makeUserSystem("VuIT").getCompanyName(), "VuIT");

	}
	
	// Tests that adding a new commissioned employee to the current object
	// returns true.
	@Test
	public void studentTest7() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		assertTrue(myCompany.newCommissionEmp("Cameron Vu", 100));
		
	}
	
	// Tests that adding a new salaried employee to the current object
	// returns true.
	@Test
	public void studentTest8() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		assertTrue(myCompany.newSalariedEmp("Cameron Vu", 100000000));
		
	}
	
	// Tests that the correct number of employees associated with the current
	// object is returned by the employeeCount() method.
	@Test
	public void studentTest9() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newCommissionEmp("Cameron Vu", 100);
		myCompany.newCommissionEmp("Logan Vu", 1);
		
		assertEquals(2, myCompany.employeeCount());
		
		myCompany.newCommissionEmp("Cole Vu", 10);
		
		assertEquals(3, myCompany.employeeCount());
		
	}
	
	// Tests the unpaid user system by attempting to add more than the maximum
	// number of allowed employees to the current object.
	@Test
	public void studentTest10() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT", 2);
		
		myCompany.newCommissionEmp("Cameron Vu", 100);
		myCompany.newCommissionEmp("Logan Vu", 1);
		
		assertFalse(myCompany.newCommissionEmp("Cole Vu", 10));
				
	}
	
	// Ensures that the program disallows adding employees to the same object
	// if an employee with the specified name already exists.
	@Test
	public void studentTest11() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newCommissionEmp("Cameron Vu", 100);
		myCompany.newCommissionEmp("Logan Vu", 1);
		myCompany.newCommissionEmp("Cole Vu", 10);
		myCompany.newSalariedEmp("Loc Vu", 100000000);
		myCompany.newSalariedEmp("April Vu", 100000000);
		
		assertFalse(myCompany.newCommissionEmp("Cole Vu", 10));
				
	}
	
	// Ensures that the program disallows adding employees to the same object
	// if an employee with the specified name but a different paid employee
	// status already exists.
	@Test
	public void studentTest12() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");

		myCompany.newCommissionEmp("Cameron Vu", 100);
		myCompany.newCommissionEmp("Logan Vu", 1);
		myCompany.newCommissionEmp("Cole Vu", 10);
		myCompany.newSalariedEmp("Loc Vu", 100000000);
		myCompany.newSalariedEmp("April Vu", 100000000);

		assertFalse(myCompany.newSalariedEmp("Cole Vu", 10));

	}
	
	// Tests that employees that are commissioned may not receive commission
	// over 100 or less than 0.
	@Test
	public void studentTest13() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");

		assertFalse(myCompany.newCommissionEmp("Cameron Vu", 101));
		assertFalse(myCompany.newCommissionEmp("Logan Vu", -1));
		assertTrue(myCompany.newCommissionEmp("Cole Vu", 0));
		assertTrue(myCompany.newCommissionEmp("April Vu", .01));

	}
	
	// Tests that new salaried employees cannot be added to the current object
	// if their name is an empty string, null, or if their salary is zero or
	// negative.
	@Test
	public void studentTest14() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");

		assertFalse(myCompany.newSalariedEmp("Cameron Vu", 0));
		assertFalse(myCompany.newSalariedEmp("Cammy Vu", -100000));
		assertFalse(myCompany.newSalariedEmp("", 100000));
		assertFalse(myCompany.newSalariedEmp(null, 100000));
		assertTrue(myCompany.newSalariedEmp("Loc Vu", 1000000));
		assertTrue(myCompany.newSalariedEmp("April Vu", 1000000));

	}
	
	// Tests that the isEmployee() method is able to correctly determine
	// that an employee with a specified name exists with the current
	// object and is also case sensitive.
	@Test
	public void studentTest15() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");

		myCompany.newSalariedEmp("Cameron Vu", 10000000);
		myCompany.newSalariedEmp("Cammy Vu", 10000000);
		myCompany.newSalariedEmp("Cam Vu", 10000000);
		myCompany.newSalariedEmp("Camdawg Vu", 10000000);
		
		assertTrue(myCompany.isEmployee("Camdawg Vu"));
		assertFalse(myCompany.isEmployee("camdawg vu"));

	}
	
	// Ensures that the correct maximum number of employees able to be stored 
	// is reflected by a paid or unpaid user.
	@Test
	public void studentTest16() {
		BusinessPayrollSystem paid = TerpCorp.makeUserSystem("VuIT");
		BusinessPayrollSystem unpaid = TerpCorp.makeUserSystem("VuIT", 10);

		assertEquals(10, unpaid.maxEmployees());
		assertEquals(Integer.MAX_VALUE, paid.maxEmployees());

	}
	
	// Ensures that addHoursWorked() only operates under the correct conditions.
	@Test
	public void studentTest17() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newCommissionEmp("Camdawg Money", 100);
		
		assertFalse(myCompany.addHoursWorked("Camdawg Money", 0));
		assertFalse(myCompany.addHoursWorked("camdawg money", 1));
		assertFalse(myCompany.addHoursWorked(" ", 1));
		assertFalse(myCompany.addHoursWorked(null, 1));
		assertTrue(myCompany.addHoursWorked("Camdawg Money", 1));

	}
	
	// Tests that the correct number of hours are returned for a commissioned
	// employee.
	@Test
	public void studentTest18() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newCommissionEmp("Camdawg Money", 100);
		
		myCompany.addHoursWorked("Camdawg Money", 1);
		myCompany.addHoursWorked("Camdawg Money", 2);
		myCompany.addHoursWorked("Camdawg Money", 3);
		
		assertEquals(6, myCompany.hoursWorked("Camdawg Money"));

	}
	
	// Tests that the correct number of hours are returned for a commissioned
	// employee when they work the largest number of hours able to be stored
	// by the Java compiler.
	@Test
	public void studentTest19() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newCommissionEmp("Camdawg Money", 100);
		
		myCompany.addHoursWorked("Camdawg Money", Integer.MAX_VALUE);
		
		assertEquals(Integer.MAX_VALUE, myCompany.hoursWorked("Camdawg Money"));

	}
	
	// Tests that no more than 80 hours can be added during a single pay period
	// for salaried employees.
	@Test
	public void studentTest20() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newSalariedEmp("Cam Luvsmoney", 1000000000);
		myCompany.newSalariedEmp("CamLu Vsmoney", 1000000000);
		
		assertTrue(myCompany.addHoursWorked("Cam Luvsmoney", 80));
		assertFalse(myCompany.addHoursWorked("CamLu Vsmoney", 81));
		
	}
	
	// Ensures that hoursWorked() operates correctly with salaried employees
	@Test
	public void studentTest21() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newSalariedEmp("Cam Luvsmoney", 1000000000);
		
		assertTrue(myCompany.addHoursWorked("Cam Luvsmoney", 80));
		assertEquals(80, myCompany.hoursWorked("Cam Luvsmoney"));
		assertEquals(-1, myCompany.hoursWorked("CamLu Vsmoney"));
		assertEquals(-1, myCompany.hoursWorked(" "));
		assertEquals(-1, myCompany.hoursWorked(null));
		
	}
	
	// Tests that the saleMade() method returns the correct boolean
	// when respective values are added to the specified employee.
	@Test
	public void studentTest22() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newSalariedEmp("Cam Luvsmoney", 1000000000);
		
		assertTrue(myCompany.saleMade("Cam Luvsmoney", 1));
		assertFalse(myCompany.saleMade("Cam Luvsmoney", 0));
		assertFalse(myCompany.saleMade("Cam Luvsmoney", -100));
		assertFalse(myCompany.saleMade("Cammy Luvsmoney", 100));
		assertFalse(myCompany.saleMade(" ", 100));
		assertFalse(myCompany.saleMade(null, 100));
		
		
	}
	
	// Ensures that the correct double representing total sales for a 
	// specified employee is returned by the superclass method 
	// totalSalesMade() for salaried employees. 
	@Test
	public void studentTest23() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newSalariedEmp("Cameron Vu", 100);
		
		myCompany.saleMade("Cameron Vu", 100);
		myCompany.saleMade("Cameron Vu", 200);
		myCompany.saleMade("Cameron Vu", 50000);
		
		assertEquals(Double.valueOf(0), 
				    (Double) myCompany.totalSalesMade("Cameron Vu"));		
		
	}
	
	// Ensures that the correct double representing total sales for a 
	// specified employee is returned by the superclass method 
	// totalSalesMade() for commissioned employees. 
	@Test
	public void studentTest24() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newCommissionEmp("Cameron Vu", 100);
		
		myCompany.saleMade("Cameron Vu", 100);
		myCompany.saleMade("Cameron Vu", 200);
		myCompany.saleMade("Cameron Vu", 50000);
		
		assertEquals(Double.valueOf(50300), 
				    (Double) myCompany.totalSalesMade("Cameron Vu"));		
		
	}
	
	// Tests that the pay is correctly calculated for commissioned employees
	// and that the correct value is returned if conditions are not met.
	@Test
	public void studentTest25() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newCommissionEmp("Cameron Vu", 100);
		
		assertEquals(Double.valueOf(0), 
			    (Double) myCompany.issuePaycheck("Cameron Vu"));	
		
		myCompany.saleMade("Cameron Vu", 100);
		
		assertEquals(Double.valueOf(100), 
			    (Double) myCompany.issuePaycheck("Cameron Vu"));	
		assertEquals(Double.valueOf(-1), 
			    (Double) myCompany.issuePaycheck(null));	
		assertEquals(Double.valueOf(-1), 
			    (Double) myCompany.issuePaycheck(" "));	
		assertEquals(Double.valueOf(-1), 
			    (Double) myCompany.issuePaycheck("Cam Vu"));	
		
	}
	
	// Tests that the correct pay for a salaried employee is returned by
	// the issuePaycheck() method.
	@Test
	public void studentTest26() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newSalariedEmp("Cameron Vu", 26);
		myCompany.newSalariedEmp("Cammy Vu", 10000000);
		
		assertEquals(Double.valueOf(1), 
			    (Double) myCompany.issuePaycheck("Cameron Vu"));
		assertEquals(Double.valueOf(10000000 / 26), 
			    (Double) myCompany.issuePaycheck("Cammy Vu"), .5);
		
	}
	
	// Tests that the total for the expected pay due to employees by the company
	// associated with the current object is correct. 
	@Test
	public void studentTest27() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newSalariedEmp("Cameron Vu", 26);
		myCompany.newCommissionEmp("Cammy Vu", 100);
		
		myCompany.saleMade("Cammy Vu", 100);
		
		assertEquals(Double.valueOf(101), 
			    (Double) myCompany.payrollTotal());
		
		
	}
	
	// Tests that the changePayPeriod() method resets all sales and hours
	// by employees and that is does not affect the salaries of salaried
	// employees or the commission rates of commissioned employees. 
	@Test
	public void studentTest28() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newSalariedEmp("Cameron Vu", 26);
		myCompany.newCommissionEmp("Cammy Vu", 100);
		
		myCompany.saleMade("Cammy Vu", 100);
		
		myCompany.changePayPeriod();
		
		assertEquals(Double.valueOf(1), 
			    (Double) myCompany.payrollTotal());
		
		
	}
	
	// Tests changePayPeriod() using all commissioned employees, since
	// only the earnings of commissioned employees are affected by sales
	@Test
	public void studentTest30() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newCommissionEmp("Cameron Vu", 26);
		myCompany.newCommissionEmp("Cammy Vu", 100);
		
		myCompany.saleMade("Cameron Vu", 10000);
		myCompany.saleMade("Cammy Vu", 100);
		
		myCompany.changePayPeriod();
		
		assertEquals(Double.valueOf(0), 
			    (Double) myCompany.payrollTotal());
		
		
	}
	
	// Tests that all sales associated with the employees of the 
	// current object being tested are effectively reset. 
	@Test
	public void studentTest31() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newCommissionEmp("Cameron Vu", 100);
		myCompany.newCommissionEmp("Cammy Vu", 100);
		
		myCompany.saleMade("Cameron Vu", 10000);
		myCompany.saleMade("Cammy Vu", 100);
		
		assertEquals(Double.valueOf(10000), 
			    (Double) myCompany.totalSalesMade("Cameron Vu"));
		assertEquals(Double.valueOf(100), 
			    (Double) myCompany.totalSalesMade("Cammy Vu"));
		
		myCompany.changePayPeriod();
		
		assertEquals(Double.valueOf(0), 
			    (Double) myCompany.totalSalesMade("Cameron Vu"));
		assertEquals(Double.valueOf(0), 
			    (Double) myCompany.totalSalesMade("Cammy Vu"));
		
		
	}
	
	// Tests that all hours associated with the employees of the 
	// current object being tested are effectively reset. 
	@Test
	public void studentTest32() {
		BusinessPayrollSystem myCompany = TerpCorp.makeUserSystem("VuIT");
		
		myCompany.newCommissionEmp("Cameron Vu", 100);
		myCompany.newCommissionEmp("Cammy Vu", 100);
		
		myCompany.addHoursWorked("Cameron Vu", 10);
		myCompany.addHoursWorked("Cammy Vu", 100);
		
		assertEquals(10, myCompany.hoursWorked("Cameron Vu"));
		assertEquals(100, myCompany.hoursWorked("Cammy Vu"));
		
		myCompany.changePayPeriod();
		
		assertEquals(0, myCompany.hoursWorked("Cameron Vu"));
		assertEquals(0, myCompany.hoursWorked("Cammy Vu"));
		
	}
	
	// Tests that the getCompanyName(), employeeCount(), isEmployee(), and 
	// maxEmployees() retrieve the correct starting data from the test data.
	@Test
	public void studentTest33() {
		BusinessPayrollSystem myCompany = TestData.exampleBPSystem1();
		
		assertEquals("Gooble", myCompany.getCompanyName());
		assertEquals(4, myCompany.employeeCount());
		assertEquals(5, myCompany.maxEmployees());
		assertTrue(myCompany.isEmployee("Freddy Frog"));
		assertFalse(myCompany.isEmployee("freddy Frog"));
		
		
	}
	
	// Ensures that the previously tested data is updated to reflect the 
	// addition of a new employee.
	@Test
	public void studentTest34() {
		BusinessPayrollSystem myCompany = TestData.exampleBPSystem1();
		
		myCompany.newCommissionEmp("Moo Deng", 100);
		
		assertEquals(5, myCompany.employeeCount());
		assertTrue(myCompany.isEmployee("Moo Deng"));
		
	}
	
	// Tests that the correct number of added hours that a new employee has
	// worked is correctly reflected by the hoursWorked() method.
	@Test
	public void studentTest35() {
		BusinessPayrollSystem myCompany = TestData.exampleBPSystem1();
		
		myCompany.newCommissionEmp("Moo Deng", 100);
		
		myCompany.addHoursWorked("Moo Deng", 100);
		
		assertEquals(100, myCompany.hoursWorked("Moo Deng"));
		
	}
	
	// Tests that the sales are updated for the added employee in the test
	// data and checks that their paycheck amount is correct.
	@Test
	public void studentTest36() {
		BusinessPayrollSystem myCompany = TestData.exampleBPSystem1();
		
		myCompany.newCommissionEmp("Moo Deng", 100);
		
		myCompany.saleMade("Moo Deng", 100);
		
		assertEquals(Double.valueOf(100), 
				    (Double) myCompany.totalSalesMade("Moo Deng"));
		assertEquals(Double.valueOf(100), 
			    (Double) myCompany.issuePaycheck("Moo Deng"));
		
	}
	
	// Tests that after the pay period is reset, the total sales of the employee
	// is also reset as well.
	@Test
	public void studentTest37() {
		BusinessPayrollSystem myCompany = TestData.exampleBPSystem1();
		
		myCompany.newCommissionEmp("Moo Deng", 100);
		
		myCompany.saleMade("Moo Deng", 100);
		
		assertEquals(Double.valueOf(100), 
				    (Double) myCompany.totalSalesMade("Moo Deng"));
		assertEquals(Double.valueOf(100), 
			    	(Double) myCompany.issuePaycheck("Moo Deng"));
		
		myCompany.changePayPeriod();
		
		assertEquals(Double.valueOf(0), 
			    	(Double) myCompany.totalSalesMade("Moo Deng"));
		assertEquals(Double.valueOf(0), 
					(Double) myCompany.issuePaycheck("Moo Deng"));
		
	}

	// Ensures that no more than the determined maximum number of employees
	// may be added to the specified object.
	@Test
	public void studentTest38() {
		BusinessPayrollSystem myCompany = TestData.exampleBPSystem1();
		
		myCompany.newCommissionEmp("Moo Deng", 100);
		
		myCompany.saleMade("Moo Deng", 100);
		
		assertFalse(myCompany.newCommissionEmp("moo deng", 99));
		
	}
	
	// Ensures that the payrollTotal() method correctly calculates the total
	// that the company will have to pay all of their employees.
	@Test
	public void studentTest39() {
		BusinessPayrollSystem myCompany = TestData.exampleBPSystem2();
		
		myCompany.newSalariedEmp("Moo Deng", 26);
		
		assertEquals(Double.valueOf(1), (Double) myCompany.payrollTotal());
		
		myCompany.newSalariedEmp("Moo Dong", 26);
		
		assertEquals(Double.valueOf(2), (Double) myCompany.payrollTotal());
		
		myCompany.newSalariedEmp("Moo Ding", 26);
		
		assertEquals(Double.valueOf(3), (Double) myCompany.payrollTotal());
		
	}
	
	// Tests that the amountOwedToUs() method operates correctly for paid
	// users after users are added. 
	@Test
	public void studentTest40() {
		BusinessPayrollSystem myCompany = TestData.exampleBPSystem2();
		
		myCompany.newSalariedEmp("Moo Deng", 26);
		
		assertEquals(Double.valueOf(70), (Double) myCompany.amountOwedToUs());
		
		myCompany.newSalariedEmp("Moo Dong", 26);
		
		assertEquals(Double.valueOf(80), (Double) myCompany.amountOwedToUs());
		
		myCompany.newSalariedEmp("Moo Ding", 26);
		
		assertEquals(Double.valueOf(90), (Double) myCompany.amountOwedToUs());
		
	}
	
	@Test
	public void studentTest41() {
		BusinessPayrollSystem myCompany = TestData.exampleBPSystem2();
		
		assertEquals(Integer.MAX_VALUE, myCompany.maxEmployees());
		
	}

}
