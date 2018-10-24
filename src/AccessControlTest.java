//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AccessControlTest.java
// Files: User.java, AccessControl.java
// Course: CS300 Fall 2018
//
// Author: Reece Lardy
// Email: RLardy@wisc.edu
// Lecturer's Name: Alexander Brooks
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Nick Hayden
// Partner Email: NHayden@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner; // import Scanner class
/*
 * This class contains test methods to test different methods from AccessControl.java
 * 
 * @author Nick Hayden and Reece Lardy
 */

public class AccessControlTest {
  /*
   * Testing main. Runs each test and prints which (if any) failed.
   */
  public static void main(String[] args) {
    int fails = 0;
    if (!testLogin1()) { // if test fails
      System.out.println("testLogin1 [bad username] failed");
      fails++; // increment fails count
    }
    if (!testLogin2()) { // if test fails
      System.out.println("testLogin2 [good login] failed");
      fails++; // increment fails count
    }
    if (!testLogin3()) { // if test fails
      System.out.println("testLogin3 [bad username with default password] failed");
      fails++; // increment fails count
    }
    if (!testLogin4()) { // if test fails
      System.out.println("testLogin4 [default username username with default password] failed");
      fails++; // increment fails count
    }
    if (!testAddUser1()) { // if test fails
      System.out.println("testAddUser1 failed");
      fails++; // increment fails count
    }
    if (!testSessionScreen()) { // if test fails
      System.out.println("testSessionScreen [uses input to logout, login, and add user] failed");
      fails++; // increment fails count
    }
    if (!testTakeAdmin()) { // if test fails
      System.out
          .println("testTakeAdmin [returns output of takeAdmin method in AccessControl] failed");
      fails++; // increment fails count
    }
    if (!testResetPassword()) { // if test fails
      System.out.println(
          "testResetPassword [returns output of resetPassword method in AccessControl] failed");
      fails++; // increment fails count
    }
    if (fails == 0) { // if all tests pass
      System.out.println("All tests passed!");
    }
  }

  /*
   * Tests login with a bad username and default password
   * 
   * @return boolean test passed
   */
  private static boolean testLogin3() {
    AccessControl ac = new AccessControl(); // It doesn't look like we use ac, but we need users
                                            // initialized
    String user = "probablyNotInTheSystem1234";
    String pw = "changeme";
    return !AccessControl.isValidLogin(user, pw); // isValidLogin should return false
  }

  /*
   * Tests login with default username and default password
   * 
   * @return boolean test passed
   */
  private static boolean testLogin2() {
    AccessControl ac = new AccessControl(); // Create new AccessControl object
    if (ac.isValidLogin("admin", "root")) { // Checks to see if username admin and password root is
                                            // a valid login
      return true; // isValid login should return true
    } else {
      return false;
    }
  }

  /*
   * Tests SessionScreen by logging out, logging in again, and adding a user
   * 
   * @return boolean test passed
   */
  public static boolean testSessionScreen() {
    AccessControl ac = new AccessControl(); // Create new AccessControl object
    Scanner userIn = new Scanner("logout" + "yeet \n" + "changeme \n" + "adduser nok"); // input for
                                                                                        // sessionscreen
    ac.addUser("yeet", true); // creates new user in user array
    try { // catches exception allowing us to put in a finite amount of input
      ac.sessionScreen("yeet", userIn); // calls sessionScreen method in AccessControl
    } catch (Exception e) {
      if (ac.isValidLogin("nok", "changeme")) { // if added user from input is a valid login
        return true; // isValidlogin should return true
      } else {
        return false;
      }
    }
    return false;
  }

  /*
   * This test tries to log in a user that doesn't exist
   * 
   * @return boolean test passed
   */
  public static boolean testLogin1() {
    AccessControl ac = new AccessControl(); // It doesn't look like we use ac, but we need users //
                                            // initialized
    String user = "probablyNotInTheSystem1234";
    String pw = "password";
    return !AccessControl.isValidLogin(user, pw); // isValidLogin should return false
  }

  /*
   * This test tries to log in the default user
   * 
   * @return boolean test passed
   */
  public static boolean testLogin4() {
    AccessControl ac = new AccessControl(); // It doesn't look like we use ac, but we need users
                                            // initialized
    String user = "admin";
    String pw = "root";
    return AccessControl.isValidLogin(user, pw); // isValidLogin should return true
  }

  /*
   * This test tries to remove admin priveleges from a user with admin priveleges
   * 
   * @return boolean test passed
   */
  public static boolean testTakeAdmin() {
    AccessControl ac = new AccessControl(); // It doesn't look like we use ac, but we need users
                                            // initialized
    if (ac.takeAdmin("admin")) { // if AccessControl can remove admin from admin user
      return true; // test is passed
    }
    return false; // test is failed
  }

  /*
   * This test tries to reset a user's password
   * 
   * @return boolean test passed
   */
  public static boolean testResetPassword() {
    AccessControl ac = new AccessControl(); // It doesn't look like we use ac, but we need users
                                            // initialized
    ac.setCurrentUser("admin");
    if (ac.resetPassword("admin")) { // if AccessControl can reset admin password to "changeme" from
                                     // "root"
      return true; // test is passed
    }
    return false; // test is failed
  }

  /*
   * Create a new AccessControl and do not log in an admin. Verify that addUser(String username)
   * returns false and that the new user is not added.
   * 
   * @return boolean test passed
   */
  public static boolean testAddUser1() {
    // A mutator you can use to write tests without simulating user input
    AccessControl ac = new AccessControl(); // It doesn't look like we use ac, but we need users
                                            // initialized
    ac.addUser("yeet", true); // add user to user array
    if (ac.isValidLogin("yeet", "changeme")) { // if username "yeet" and password "changeme" is a
                                               // valid login
      return true; // test is passed
    } else {
      return false;
    }
  }
}
