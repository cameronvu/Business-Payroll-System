package tests;

// (c) Larry Herman, 2024.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

/* Although the project grading policies say not to use this form of import
 * (to use explicit imports instead), we have to use it here, because we
 * don't know what classes you will write in the payrollSystem package.
 */
import payrollSystem.*;
import arrayBackedList.ArrayBackedList;

/* This class contains utility methods that create and return example
 * BusinessPayrollSystem objects that the public (and secret) tests can use,
 * to reduce the amount of code needed in different tests to create objects
 * to test the methods with.
 *
 * Your student tests themselves must be your own individual work- you can
 * use ideas from the public the public tests, but you cannot just copy the
 * public tests to create your student tests.  However, you CAN use the
 * methods in THIS class in writing your own student tests, without any
 * restrictions, meaning your student tests can call any of the methods
 * below to create BusinessPayrollSystem objects for testing purposes.
 * However, don't modify this TestData class, because our version is going
 * to be used on the submit server.  (If you want to have a modified version
 * of any of the methods here note that you can write your own helper
 * methods in your StudentTests class, and you can also add your own classes
 * to the tests package, so instead of changing the methods here, you can
 * make a copy of them in one of these two places and modify that copy.)
 */

public class TestData {

  // Returns a BusinessPayrollSystem with a free plan that only has salaried
  // employees, who haven't (yet) worked any hours or made any sales.
  public static BusinessPayrollSystem exampleBPSystem1() {
    BusinessPayrollSystem company= TerpCorp.makeUserSystem("Gooble", 5);

    company.newSalariedEmp("Dolly Dolphin", 51012.0);
    company.newSalariedEmp("Freddy Frog", 68900.0);
    company.newSalariedEmp("Geri Giraffe", 49946.0);
    company.newSalariedEmp("Kourtney Koala", 59410.0);

    return company;
  }

  // Returns a BusinessPayrollSystem with a paid plan that only has
  // commissioned employees, who haven't (yet) worked any hours or made any
  // sales.
  public static BusinessPayrollSystem exampleBPSystem2() {
    BusinessPayrollSystem company= TerpCorp.makeUserSystem("Microsloth");

    company.newCommissionEmp("Paul Platypus", 15.0);
    company.newCommissionEmp("Steve Starfish", 15.25);
    company.newCommissionEmp("Timmy Termite", 16.0);
    company.newCommissionEmp("Jackie Jaguar", 15.75);
    company.newCommissionEmp("Sally Salamander", 16.5);
    company.newCommissionEmp("Chippy Chipmunk", 10.0);

    return company;
  }

  // Returns a BusinessPayrollSystem with a free plan that has a mix of
  // salaried and commissioned employees, who haven't (yet) worked any hours
  // or made any sales.
  public static BusinessPayrollSystem exampleBPSystem3() {
    BusinessPayrollSystem company= TerpCorp.makeUserSystem("Nvidiot", 50);

    company.newSalariedEmp("Lizzie Lizard", 48900.0);
    company.newCommissionEmp("Paul Platypus", 5.0);
    company.newCommissionEmp("Chippy Chipmunk", 10.0);
    company.newSalariedEmp("Kourtney Koala", 59425.0);
    company.newCommissionEmp("Jackie Jaguar", 7.5);
    company.newCommissionEmp("Steve Starfish", 8.0);
    company.newSalariedEmp("Ginny Giraffe", 79950.0);
    company.newCommissionEmp("Sally Salamander", 6.5);
    company.newCommissionEmp("Timmy Termite", 4.0);
    company.newSalariedEmp("Wally Walrus", 61000.0);

    return company;
  }

  // Returns a BusinessPayrollSystem with a paid plan that has a mix of
  // salaried and commissioned employees, who haven't (yet) worked any hours
  // or made any sales.
  public static BusinessPayrollSystem exampleBPSystem4() {
    BusinessPayrollSystem company= TerpCorp.makeUserSystem("Auricle");

    company.newSalariedEmp("Holly Dolphin", 81016.0);
    company.newCommissionEmp("Freddy Frog", 5.0);
    company.newSalariedEmp("Geri Giraffe", 49959.0);
    company.newCommissionEmp("Kourtney Koala", 6.0);
    company.newSalariedEmp("Ryan Lion", 79950.0);
    company.newCommissionEmp("Bruce Moose", 7.5);

    return company;
  }

}
