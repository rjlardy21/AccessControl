//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: User.java
// Files: AccessControl.java, AccessControl.java
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
import java.util.Scanner; // import scanner class
/*
 * This class contains methods to alter and retrieve information from user objects in AccessControl
 * class
 * 
 * @author Nick Hayden and Reece Lardy
 */

public class User {
  private final String USERNAME; // The user's name
  private String password; // The user's password
  private boolean isAdmin; // Whether or not the user has Admin powers
  /*
   * This method creates a user object in AccessControl class
   * 
   * @param username
   * 
   * @param password
   * 
   * @param isAdmin
   */

  public User(String username, String password, boolean isAdmin) { // Creates a new user with the
                                                                   // given
                                                                   // password and admin status
    USERNAME = username; // set user object username equal to input username
    this.password = password; // set user object password equal to input password
    this.isAdmin = isAdmin; // set user object admin status equal to input admin status
  }

  /*
   * This method compares input password to user password to see if it is a valid login
   * 
   * @param password
   * 
   * @return boolean
   */
  public boolean isValidLogin(String password) {
    if (password.equals(this.password)) { // if input password is equal to user password
      return true;
    } else {
      return false;
    }
  }

  /*
   * This method gets the username from user object
   * 
   * @return string with user's username
   */
  public String getUsername() {
    return USERNAME;
  } // Return the user's name
  /*
   * This method gets the admin status from user object
   * 
   * @return boolean containing user's admin status
   */

  public boolean getIsAdmin() {
    return isAdmin;
  } // Report whether the user is an admin
  /*
   * This method sets user's password to input password
   * 
   * @param password
   */

  public void setPassword(String password) {
    this.password = password;
  } // Set the new password
  /*
   * This method sets user's admin status to input boolean
   * 
   * @param admin status
   */

  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  } // Set the new admin status

  /*
   * Launch an AccessControl instance
   */
  public static void main(String[] args) {
    AccessControl ac = new AccessControl();
    // If we have any persistent information to lead
    // this is where we load it.
    Scanner userIn = new Scanner(System.in); // creates scanner instance
    ac.loginScreen(userIn); // calls loginScreen in AccessControl
  }
}
