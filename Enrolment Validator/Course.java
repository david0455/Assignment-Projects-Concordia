import java.util.Scanner;

// ----------------------------------------------------------
// Assignment #4
// Question: II
// Written by: David Chen 40063285
// ----------------------------------------------------------

/**
 * David Chen 40063285
 * COMP249
 * Assignment 4
 * Due Date: April 13, 2018
 */


/** 
* This class implements a Course which holds informations about
* its courseID, courseName, credir
* t, preReqID, coReqID
*@author David Chen
*/
public class Course implements DirectlyRelatable { //This class creates a Course object
	private String courseID;
	private String courseName;
	private double credit;
	private String preReqID;
	private String coReqID;
	
	/**
	 * This constructs a Course object with the default values of the attributes
	 */
	public Course() {
		this.courseID = "";
		this.courseName = "";
		this.credit = 0;
		this.preReqID = "";
		this.coReqID = "";
	}
	/**
	 * This constructs a Course object with specific values of the attributes
	 * @param courseID the course id of the Course
	 * @param courseName the course name of the Course
	 * @param credit the number of credit of the Course
	 * @param preReqID the pre-requisite of the Course
	 * @param coReqID the co-requisite of the Course
	 */
	public Course(String courseID, String courseName, double credit, String preReqID, String coReqID) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.credit = credit;
		this.preReqID = preReqID;
		this.coReqID = coReqID;
	}
	
	/**
	 * This is the copy constructor of A Course Object 
	 * @param c1 another course object to be copied
	 * @param s1 courseId courseID parameter to be modified
	 */
	public Course(Course c1, String s1) {
//		System.out.println("=============== Using copy constructor from Course class ===============");
		this.courseID = s1;
		this.courseName = c1.courseName;
		this.credit = c1.credit;
		this.preReqID = c1.preReqID;
		this.coReqID = c1.coReqID;
	}
	
	/**
	 * This clones the object to give a deep copy of the object
	 */
	public Course clone() {
		System.out.println("=============== Using clone() method from Course class ===============");
		Scanner myKeyboard = new Scanner(System.in);
		System.out.print("Enter new courseID: ");
		String s1 = myKeyboard.nextLine();
		return new Course(this, s1);
	}
	
	/**
	 * This returns the courseID of the Course object
	 * @return this Course's courseID
	 */
	public String getCourseID() {
		return courseID;
	}
	
	/**
	 * This sets the courseID of the object
	 * @param courseID the courseID of the Course object
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	/**
	 * This returns the courseName of the Course object
	 * @return this Course's courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * This sets the courseName of the object
	 * @param courseName the courseName of the Course object
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * This returns the credit of the Course object
	 * @return this Course's credit
	 */
	public double getCredit() {
		return credit;
	}
	
	/**
	 * This sets the credit of the object
	 * @param credit the credit of the Course object
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}
	
	/**
	 * This returns the preReqID of the Course object
	 * @return this Course's preReqID
	 */
	public String getPreReqID() {
		return preReqID;
	}
	/**
	 * This sets the preReqID  of the object
	 * @param preReqID the preReqID of the Course object
	 */
	public void setPreReqID(String preReqID) {
		this.preReqID = preReqID;
	}
	
	/**
	 * This returns the coReqID of the Course object
	 * @return this Course's coReqID
	 */
	public String getCoReqID() {
		return coReqID;
	}
	
	/**
	 * This sets the coReqID  of the object
	 * @param coReqID the coReqID of the Course object
	 */
	public void setCoReqID(String coReqID) {
		this.coReqID = coReqID;
	}
	
	/**
	 * @return the equality of two Course objects based on all their attributes except courseID
	 */
	public boolean equals(Object obj) {
		//System.out.println("=============== Using equals() method from Course class ===============");
		if (this == obj)
			return true;
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		Course c1 = (Course)obj;
		if (this.coReqID == null) {
			if (c1.coReqID != null)
				return false;
		} else if (!this.coReqID.equals(c1.coReqID))
			return false;
		if (this.courseName == null) {
			if (c1.courseName != null)
				return false;
		} else if (!this.courseName.equals(c1.courseName))
			return false;
		if (this.credit != c1.credit)
			return false;
		if (this.preReqID == null) {
			if (c1.preReqID != null)
				return false;
		} else if (!this.preReqID.equals(c1.preReqID))
			return false;
		return true;
	}
	
	/**
	 * @return the courseID, courseName, credir, preReqID, coReqID of this Course object
	 */
	public String toString() {
		return this.courseID;
//			+"[courseID]: " +  "\n[courseName]: " + this.courseName
//			+ "\n[credit]: " + this.credit
//			+ "\n[preReqID]: " +this.preReqID
//			+ "\n[coReqID]: " +this.coReqID; 
	}
	
	/**
	 * This test if two Course object are directly relatable by their preReqID or coReqID
	 * @return the equality of the Course objects
	 */
	public boolean isDirectlyRelated(Course C) {
	//	System.out.println("=============== Using isDirectlyRelated() method from Course class ===============");
		if(this.preReqID.equals(C.getPreReqID()) || this.coReqID.equals(C.getCoReqID())) 
			return true;
		return false;
	}

	
	
}
