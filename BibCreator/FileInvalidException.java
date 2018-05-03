
// ----------------------------------------------------------
// Assignment #3
// Question: 1
// Written by: David Chen 40063285
// ----------------------------------------------------------

/**
 * David Chen 40063285
 * COMP249
 * Assignment 3
 * Due Date: March 19, 2018
 */

/**
 * FileInvalidException class is called when there is an invalid/empty field in the article
 * @author David Chen
 * @see BibCreator
 */

public class FileInvalidException extends Exception {
	
	/**
	 * Default constructor of class fileInvalidException.
	 */
	public FileInvalidException() {
		super("Error: Input file cannot be passed due to missing information (i.e. month={}, title={}, etc.)");
	}
	
	/**
	 * A constructor that takes a String as parameter
	 * @param s1 a String to display any other desired exception message
	 */
	public FileInvalidException(String s1) {
		super(s1);
	}
	
	/**
	 * This returns the message of this FileInvalidException class
	 * @return String message from Exception class
	 */
	public String getMessage() {
		return super.getMessage();
	}
}









