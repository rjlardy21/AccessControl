import java.util.ArrayList;
import java.util.Scanner;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Access Control
// Files: AccessControl.java, AccessControlTests.java User.java
// Course: CS300 Fall 2018
//
// Author: Reece Lardy
// Email: RLardy@wisc.edu
// Lecturer's Name: Alexander Brooks
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Nick Hayden
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
/**
 * AccessControl is a class that different users logging in and out of a session as well as adding
 * or removing users and changing their properties
 * 
 * @author Nick Hayden and Reece Lardy
 *
 */
public class AccessControl {
  private static ArrayList<User> users; // An ArrayList of valid users.
  private User currentUser; // Who is currently logged in, if anyone?
  private static final String DEFAULT_PASSWORD = "changeme"; // Default password given to
  // new users or when we reset a user's password.

  AccessControl() { // A no-parameter constructor
    users = new ArrayList<User>(); // Make users ArrayList be able to hold values
    User admin = new User("admin", "root", true); // Create new admin user for first login
    users.add(admin); // Add admin to users array
    currentUser = null;
  }

  /**
   * Launch an AccessControl instance and open login Screen
   */
  public static void main(String[] args) {
    AccessControl ac = new AccessControl(); // Create instance of AccessControl
    // If we have any persistent information to lead
    // this is where we load it.
    Scanner userIn = new Scanner(System.in);
    ac.loginScreen(userIn);
  }

  /**
   * isValid checks to make sure that the username and password pair are in the users ArrayList
   * 
   * @param username
   * @param password
   * @return ifValid
   */
  public static boolean isValidLogin(String username, String password) {// Report whether a
    // given username/password pair is a valid login
    User temp;
    for (int i = 0; i < users.size(); i++) {
      temp = users.get(i);
      if (temp.isValidLogin(password) && username.equals(temp.getUsername())) {
        return true;
      }
    }

    return false;
  }

  /**
   * logout sets the currentUser to null and prints that it logged out
   */
  public void logout() // Log out the current user
  {
    currentUser = null; // set currentUser to null
    System.out.println("==== Logged Out ====");
  }

  /**
   * sets the currentUser password to a new provided password
   * 
   * @param newPassword
   */
  public void changePassword(String newPassword) // Change the current user's password
  {
    currentUser.setPassword(newPassword);
  }

  /**
   * This method adds a new user with a given username to the users array giving it the default
   * password with admin automatically set to false
   * 
   * @param username
   * @return
   */
  public boolean addUser(String username) // Create a new user (non-admin)
  {
    if (currentUser == null || currentUser.getIsAdmin()) { // Checks to make sure the currentUser is
                                                           // an admin
      User user1 = new User(username, DEFAULT_PASSWORD, false); // Creates a new user with the
                                                                // username
      users.add(user1); // Add new user to users ArrayList
      return true;
    }
    return false; // if currentUser is not an admin return false
  }

  /**
   * This method adds a new user with a given username to the users array giving it the default
   * password with admin is set to the given admin value
   * 
   * @param username
   * @param isAdmin
   * @return
   */
  public boolean addUser(String username, boolean isAdmin) // Create a new user
  // and specify their admin status
  {
    if (currentUser == null || currentUser.getIsAdmin()) {// Checks to make sure the currentUser is
                                                          // an admin
      User user1 = new User(username, DEFAULT_PASSWORD, isAdmin);// Creates a new user with the
                                                                 // username and
                                                                 // the given admin value
      users.add(user1); // Add new user to users ArrayList
      return true;
    }
    return false; // if currentUser is not an admin return false
  }

  public boolean removeUser(String username) // Remove a user
  {
    if (currentUser == null || currentUser.getIsAdmin()) {
      users.remove(currentUser);
      return true;
    }
    return false;
  }

  /**
   * This method gives the admin authorization to a user
   * 
   * @param username
   * @return
   */
  public boolean giveAdmin(String username) // Give a user admin power
  {
    if (currentUser == null || currentUser.getIsAdmin()) { // Checks to make sure the currentUser is
                                                           // an admin
      User temp; // create temporary variable to use to go through users ArrayList
      for (int i = 0; i < users.size(); i++) { // loop through users ArrayList
        temp = users.get(i);
        if (username.equals(temp.getUsername())) { // Check to see if the username matches with any
                                                   // user
                                                   // in users ArrayList
          users.set(i, temp).setIsAdmin(true); // If a user matches set its admin to true
          return true;
        }
      }
    }
    return false; // If not an admin or the username is not in users return false
  }

  /**
   * this method takes away the admin authorization from a user
   * 
   * @param username
   * @return
   */
  public boolean takeAdmin(String username) // Remove a user's admin power
  {
    if (currentUser == null || currentUser.getIsAdmin()) { // Checks to make sure the currentUser is
                                                           // an admin
      User temp2; // create temporary variable to use to go through users ArrayList
      for (int i = 0; i < users.size(); i++) {// loop through users ArrayList
        temp2 = users.get(i);
        if (username.equals(temp2.getUsername())) {// Check to see if the username matches with any
                                                   // user
                                                   // in users ArrayList
          users.set(i, temp2).setIsAdmin(false); // If a user matches set its admin to false
          return true;
        }
      }
    }
    return false; // If not an admin or the username is not in users return false
  }

  /**
   * This method resets a users password to the default password
   * 
   * @param username
   * @return
   */
  public boolean resetPassword(String username) // Reset a user's password
  {
    if (currentUser == null || currentUser.getIsAdmin()) { // Checks to make sure the currentUser is
                                                           // an admin
      User temp2; // create temporary variable to use to go through users ArrayList
      for (int i = 0; i < users.size(); i++) { // loop through users ArrayList
        temp2 = users.get(i);
        if (username.equals(temp2.getUsername())) {// Check to see if the username matches with any
                                                   // user
          // in users ArrayList
          users.set(i, temp2).setPassword(DEFAULT_PASSWORD); // If a user matches set its password
                                                             // to
                                                             // DEFAULT_PASSWORD
          return true;
        }
      }
    }
    return false; // If not an admin or the username is not in users return false
  }

  /**
   * Prompts the user for a username and password then makes sure they are a valid login then calls
   * sessionScreen if it is valid else tell the user it is invalid and ask again
   * 
   * @param userInputScanner
   */
  public void loginScreen(Scanner userInputScanner) // Main driver loop.
  {
    while (true) {
      // Prompt the user for login information
      System.out.println("Username: ");
      String username = userInputScanner.nextLine();
      username = username.trim();
      System.out.println("Password: ");
      String password = userInputScanner.nextLine();
      password = password.trim();
      // calls the isValidLogin method
      if (isValidLogin(username, password) == true) {
        User temp;
        for (int i = 0; i < users.size(); i++) {
          temp = users.get(i);
          if (temp.isValidLogin(password)) {
            // If the login is valid, call the sessionScreen method
            sessionScreen(username, userInputScanner);
          }
        }
      } else {
        System.out.println("Invalid Login"); // If it is not valid tell the user and prompt the user
                                             // again
      }
    }
  }

  /**
   * prompts the user to take an action then calls different methods depending on what the user
   * inputs
   * 
   * @param username
   * @param userInputScanner
   */
  public void sessionScreen(String username, Scanner userInputScanner) {
    User temp; // A temporary variable used to go through the users ArrayList
    for (int i = 0; i < users.size(); i++) {
      temp = users.get(i);
      if (username.equals(temp.getUsername())) { // Check to see which user the username matches
                                                 // with
        currentUser = temp; // set currentUser to the user that matches the username
      }
    }
    while (true) { // Create an infinite loop that needs to broken out from
      // Give the users prompts to chose from
      System.out.println("What would you like to do?");
      System.out.println("logout");
      System.out.println("newpw [newpassword]");
      System.out.println("adduser [username]");
      System.out.println("adduser [username] [true or false]");
      System.out.println("rmuser [username]");
      System.out.println("giveadmin [username]");
      System.out.println("rmadmin [username]");
      System.out.println("resetpw [username]");
      String command = userInputScanner.nextLine(); // get the users input
      String[] commandHolder = command.split(" "); // split the users input into a string array so
                                                   // it can be
                                                   // more easily processed
      if (commandHolder[0].equals("logout")) { // If the first input mathces logout
        logout();
        break;// leave the forever while loop
      }
      if (commandHolder.length < 2) { // See if the user has input more than one strings
        command = userInputScanner.nextLine(); // If they havent make command into next line
      } else {
        command = commandHolder[1]; // If it is 2 or greater in size make command equal to
                                    // the second string in the array
      }
      if ((commandHolder[0].equals("adduser"))) { // If the first string equals addUser
        if (commandHolder.length > 2) { // If the user input more than 2 strings into the array list
          if (commandHolder[2].equals(true)) { // If the third value is true call addUser with true
            addUser(command, true);
          } else { // otherwise it must be false so call addUser with false
            addUser(command, false);
          }
        } else { // If the string array is not longer than 2 then there was no boolean value given
                 // so call
                 // addUser without the boolean value
          addUser(command);
        }
      }

      else if (command.equals("adduser")) { // if command equals addUser call addUser
        addUser(command);
      } else if (command.equals("newpw")) { // if command equals newpw call newpw
        changePassword(command);
      } else if (command.equals("rmuser")) {// if command equals rmuser call rmuser
        removeUser(command);
      } else if (command.equals("giveadmin")) {// if command equals giveadmin call giveAdmin
        giveAdmin(command);
      } else if (command.equals("rmadmin")) {// if command equals rmadmin call takeAdmin
        takeAdmin(command);
      } else if (command.equals("resetpw")) {// if command equals resetpw call resetpw
        resetPassword(command);
      }
    }
  }

  /**
   * a method used to create a currentUser that can be used to test other methods in the class
   * 
   * @param username
   */
  public void setCurrentUser(String username) {
    // A mutator you can use to write tests without simulating user input

    User temp; // A temporary variable used to go through the users ArrayList
    for (int i = 0; i < users.size(); i++) {// loop through users ArrayList
      temp = users.get(i);
      if (username.equals(temp.getUsername())) {// Check to see if the username matches with any
                                                // user
                                                // in users ArrayList
        currentUser = temp; // If one does set it to currentUser
      }
    }
  }
}
