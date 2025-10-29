package tests;

// (c) Larry Herman, 2024.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

/* Some tests use a form of assertEquals() that is used to compare two
 * floating-point (real) numbers, which has three arguments:
 * assertEquals(double1, double2, delta).  It will say that the two doubles
 * are equal if their values are within delta of each other.  For instance,
 * a call like assertEquals(2.5, 2.501, 0.01) will be true, while
 * assertEquals(2.5, 2.55, 0.01) will fail.  Comparing real numbers this way
 * is needed due to the imprecision involved with doing arithmetic with
 * them.
 */

/* Although the project grading policy handout says not to use the form of
 * import using '*' (you should use explicit imports instead), we have to
 * use it in the next line here, because we don't know what classes
 * different students will write in the employeeTerpCorp package, so we
 * can't explicitly import them.
 */
import payrollSystem.*;
import arrayBackedList.ArrayBackedList;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

    // Tests the basic operation of the ArrayBackedList class.
    @Test public void testPublic1() {
        ArrayBackedList list= new ArrayBackedList(5);
        String[] words= {"banana", "mango", "grape", "pear", "orange",
                         "apple", "kiwi"};
        int i;

        for (String s : words)
            list.add(s);

        assertEquals(7, list.getSize());
        assertEquals(10, list.getCapacity());

        for (i= 0; i < list.getSize(); i++)
            // note casting the Object reference returned by get() to the
            // actual type of the object being returned, which we know here
            // is String
            assertEquals(words[i], (String) list.get(i));
    }

    // Just tests creating some BusinessPayrollSystem objects, of different
    // types, and calling getCompanyName() on them.
    @Test public void testPublic2() {
        BusinessPayrollSystem company1= TerpCorp.makeUserSystem("Gooble");
        BusinessPayrollSystem company2=
            TerpCorp.makeUserSystem("Chapsnat", 10);

        assertEquals("Gooble", company1.getCompanyName());
        assertEquals("Chapsnat", company2.getCompanyName());
    }

    // Tests hiring some employees for a company and calling employeeCount().
    @Test public void testPublic3() {
    	
    	assertEquals(4, TestData.exampleBPSystem1().employeeCount());

    }

    // Tests calling maxEmployees() for companies with free plans.
    @Test public void testPublic4() {
        BusinessPayrollSystem company1= TestData.exampleBPSystem1();
        BusinessPayrollSystem company2= TestData.exampleBPSystem3();

        assertEquals(5, company1.maxEmployees());
        assertEquals(50, company2.maxEmployees());
    }

    // Tests trying to try to hire employees who have the same names as
    // existing employees, which should fail.
    @Test public void testPublic5() {
        BusinessPayrollSystem company= TestData.exampleBPSystem3();
        
        assertFalse(company.newSalariedEmp("Kourtney Koala", 89530.0));
        assertFalse(company.newCommissionEmp("Sally Salamander", 16.0));
        assertEquals(10, company.employeeCount());
    
    }

    // Tests trying to hire more employees than a free plan company's
    // capacity.
    @Test public void testPublic6() {
        BusinessPayrollSystem company= TestData.exampleBPSystem1();

        assertTrue(company.newSalariedEmp("Leanne Lemur", 14.65));
        assertFalse(company.newSalariedEmp("Sheila Sheep", 34500.0));
        assertEquals(5, company.maxEmployees());
        assertEquals(5, company.employeeCount());
    }

    // Tests the basic operation of addHoursWorked() and hoursWorked().
    @Test public void testPublic7() {
        BusinessPayrollSystem company= TestData.exampleBPSystem4();

        assertTrue(company.addHoursWorked("Holly Dolphin", 8));
        assertTrue(company.addHoursWorked("Holly Dolphin", 9));
        assertTrue(company.addHoursWorked("Holly Dolphin", 7));
        assertTrue(company.addHoursWorked("Holly Dolphin", 9));
        assertTrue(company.addHoursWorked("Geri Giraffe", 11));
        assertTrue(company.addHoursWorked("Geri Giraffe", 10));
        assertTrue(company.addHoursWorked("Geri Giraffe", 9));
        assertTrue(company.addHoursWorked("Ryan Lion", 8));

        assertEquals(33, company.hoursWorked("Holly Dolphin"));
        assertEquals(0, company.hoursWorked("Freddy Frog"));
        assertEquals(30, company.hoursWorked("Geri Giraffe"));
        assertEquals(0, company.hoursWorked("Kourtney Koala"));
        assertEquals(8, company.hoursWorked("Ryan Lion"));
        assertEquals(0, company.hoursWorked("Bruce Moose"));
    }

    // Tests the basic operation of issuePaycheck() for a salaried employee.
    @Test public void testPublic8() {
        BusinessPayrollSystem company= TestData.exampleBPSystem4();

        assertEquals(3116.0, company.issuePaycheck("Holly Dolphin"), 0.001);
    }

    // Tests the basic operation of issuePaycheck() for commissioned
    // employees.
    @Test public void testPublic9() {
        BusinessPayrollSystem company= TestData.exampleBPSystem4();

        company.saleMade("Freddy Frog", 15000.0);
        company.saleMade("Freddy Frog", 30000.0);
        company.saleMade("Kourtney Koala", 27000.0);
        company.saleMade("Kourtney Koala", 32000.0);

        assertEquals(2250.0, company.issuePaycheck("Freddy Frog"), 0.001);
        assertEquals(3540.0, company.issuePaycheck("Kourtney Koala"), 0.001);
        // Bruce Moose did not make any sales
        assertEquals(0.0, company.issuePaycheck("Bruce Moose"), 0.001);
    }

    // Tests nonexistent employees trying to work hours and make sales.
    @Test public void testPublic10() {
        BusinessPayrollSystem company= TestData.exampleBPSystem4();

        assertFalse(company.addHoursWorked("Antonio Antelope", 1));
        assertFalse(company.saleMade("Quinn Quokka", 1000.0));
    }

    // Tests trying to call addHoursWorked() with a negative number of
    // hours.
    @Test public void testPublic11() {
        BusinessPayrollSystem company= TestData.exampleBPSystem3();

        assertTrue(company.addHoursWorked("Lizzie Lizard", 10));
        assertTrue(company.addHoursWorked("Timmy Termite", 11));
        assertFalse(company.addHoursWorked("Lizzie Lizard", -2));
        assertFalse(company.addHoursWorked("Timmy Termite", -2));
        assertEquals(10, company.hoursWorked("Lizzie Lizard"));
        assertEquals(11, company.hoursWorked("Timmy Termite"));
    }

    // Tests that employees of both types are able to work more than 40
    // hours in a pay period.
    @Test public void testPublic12() {
        BusinessPayrollSystem company= TestData.exampleBPSystem3();
        int i;

        for (i= 1; i <= 12; i++) {
            assertTrue(company.addHoursWorked("Kourtney Koala", 4));
            assertTrue(company.addHoursWorked("Jackie Jaguar", 4));
        }

        assertEquals(48, company.hoursWorked("Kourtney Koala"));
        assertEquals(48, company.hoursWorked("Jackie Jaguar"));
    }

    // Tests trying to call saleMade() with a negative sale amount.
    @Test public void testPublic13() {
        BusinessPayrollSystem company= TestData.exampleBPSystem2();

        assertTrue(company.saleMade("Chippy Chipmunk", 1000.0));
        assertFalse(company.saleMade("Chippy Chipmunk", -200.0));
        assertTrue(company.saleMade("Chippy Chipmunk", 2000.0));
        assertEquals(3000.0,
                     company.totalSalesMade("Chippy Chipmunk"), 0.001);
    }

    // Tests calling payrollTotal().
    @Test public void testPublic14() {
        BusinessPayrollSystem company= TestData.exampleBPSystem1();

        assertEquals(8818.0, company.payrollTotal(), 0.001);
    }

    // Tests calling amountOwedToUs() on companies that have free plans.
    @Test public void testPublic15() {
        BusinessPayrollSystem company1=
            TerpCorp.makeUserSystem("Applesauce", 10);
        BusinessPayrollSystem company2= TestData.exampleBPSystem1();

        assertEquals(0.0, company1.amountOwedToUs(), 0.001);
        assertEquals(0.0, company2.amountOwedToUs(), 0.001);
    }

    // Tests calling amountOwedToUs() on companies that have paid plans.
    @Test public void testPublic16() {
        BusinessPayrollSystem company1=
            TerpCorp.makeUserSystem("Instaounce");
        BusinessPayrollSystem company2= TerpCorp.makeUserSystem("Sungsam");

        company2.newSalariedEmp("Dolly Dolphin", 51012.0);
        company2.newSalariedEmp("Freddy Frog", 68900.0);
        company2.newSalariedEmp("Geri Giraffe", 49946.0);
        company2.newSalariedEmp("Kourtney Koala", 59410.0);

        assertEquals(0.0, company1.amountOwedToUs(), 0.001);
        assertEquals(40.0, company2.amountOwedToUs(), 0.001);
    }

}
