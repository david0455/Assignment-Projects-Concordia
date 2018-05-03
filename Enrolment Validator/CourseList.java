
// ----------------------------------------------------------
// Assignment #4
// Question: III
// Written by: David Chen 40063285
// ----------------------------------------------------------

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * David Chen 40063285
 * COMP249
 * Assignment 4
 * Due Date: April 13, 2018
 */


/** 
*This class creates a linkedlist
*@author David Chen
*/
public class CourseList {
	/**
	 * This inner class creates the Node for the CourseList LinkedList
	 * @author David
	 */
	public class CourseNode{	//This inner class may result in privacy leak because the find() method
								//returns a reference to the Node which is very dangerous.
		private Course C;
		private CourseNode next;
		
		/**
		 * Default Constructor
		 */
		public CourseNode(){
			C = null;
			next = null;
		}
		/**
		 * Parametrize construcotr
		 * @param C Course object
		 * @param next CourseNode
		 */
		public CourseNode(Course C, CourseNode next) {
			this.C = C;
			this.next = next;
		}
		
		/**
		 * copy constructor
		 * @param course CourseNode
		 */
		public CourseNode(CourseNode course) {//Copy constructor
			System.out.println("=============== Using copy constructor from CourseNode inner class ===============");
			this.C = course.C.clone();
			this.next = course.next;
		}
		
		/**
		 * clone method
		 */
		public CourseNode clone() {
			System.out.println("=============== Using clone() method from CourseNode inner class ===============");
			return new CourseNode(this);
		}
		
		/**
		 * Return Course value
		 * @return Course value
		 */
		public Course getC() {
			return C;
		}
		
		/**
		 * set Course
		 * @param c Course object
		 */
		public void setC(Course c) {
			C = c;
		}
		
		/**
		 * @return CourseNode next
		 */
		public CourseNode getNext() {
			return next;
		}
		
		/**
		 * set next
		 * @param next CourseNode
		 */
		public void setNext(CourseNode next) {
			this.next = next;
		}
	}//End of CourseNode inner class
	
	private CourseNode head;
	private int size;
	
	/**
	 * Default constructor
	 */
	public CourseList() {
		head = null;
		size = 0;
	}
	
	/**
	 * copy constructor
	 * @param list Courselist
	 */
	public CourseList(CourseList list) {//Copy constructor
	//	System.out.println("=============== Using copy constructor from CourseList class ===============");
		if(list.head == null) {
			head = null;
		}
		else {
			head = null;
			
			CourseNode t1, t2, t3;
			
			t1 = list.head;
			t2 = t3 = null;
			
			while(t1 != null) {
				if(head == null) {
					t2 = new CourseNode(t1.C, null);
					head = t2;
				}else {
					t3 = new CourseNode(t1.C, null);
					t2.next = t3;
					t2 = t3;
				}
				t1 = t1.next;
			}
			t2 = t2 = null;
		}
	}
	
	/**
	 * clone method()
	 */
	public CourseList clone() {
		//System.out.println("=============== Using clone() method from CourseList class ===============");
		return new CourseList(this);
	}
	
	/**
	 * addtostart
	 * @param C Course object 
	 */
	public void addToStart(Course C) {
		head = new CourseNode(C, head);
		size++;
	}
	
	/**
	 * inserAtIndex
	 * @param C Course object
	 * @param x index
	 * @return boolean
	 */
	public boolean insertAtIndex(Course C, int x) {
		CourseNode t = head;
		int index = 0;
		
		if( x < 0 && x > size-1) {
			throw new NoSuchElementException();
		}
		else if(x == 0) {
			addToStart(C);
		}else {
			while(t.next != null && index != x){
				t = t.next;
				index++;
			}
			t.next = new CourseNode(C, t.next);
			size++;	
		}
		return true;
	}
	
	/**
	 * deletefromindex
	 * @param x index
	 * @return boolean
	 */
	public boolean deleteFromIndex(int x) {
		CourseNode t = head;
		int index = 0;
		if(head == null) {
			return false;
		}
		if(x == 0) {
			head= head.next.next;
			return true;
		}
		if( x < 0 && x > size-1) {
			throw new NoSuchElementException();
		}
		while(t.next != null & index != x) {
			t = t.next;
			index++;
		}
		t.next = t.next.next;
		t = null;
		size--;
		return true;
	}
	
	/**
	 * deleteFromStart
	 * @return boolean
	 */
	public boolean deleteFromStart() {
		if(head == null) {
			return false;
		}
		head = head.next;
		size--;
		return true;
	}
	
	/**
	 * replaceAtIndex
	 * @param C1 Course object
	 * @param x index
	 * @return boolean
	 */
	public boolean replaceAtIndex(Course C1, int x) {
		if( x < 0 && x > size-1) {
			throw new NoSuchElementException();
		}
		
		CourseNode t = head;
		int index = 0;
		
		while(t != null && index != x) {
			t = t.next;
			index++;
		}
		t.C = C1; 
		return true;
	}
	
	/**
	 * find
	 * @param courseID  String of courseID
	 * @return reference to CourseNode
	 */
	public CourseNode find(String courseID) {
		if(head == null) {
			return null;
		}
		CourseNode t = head;
		int iteration = 1; 
		while(t != null && t.next != null && !t.C.getCourseID().equals(courseID)) {
			t = t.next;
			iteration++;
		}
		if(t.next != null) {
			System.out.println(courseID + " found after: " + iteration + " iterations");
			return t;
		}else
			System.out.println(courseID + " is not found in list.");
		return null;
	}
	
	/**
	 * contains
	 * @param courseID the courseID of the class
	 * @return boolean
	 */
	public boolean contains(String courseID) {
		if(find(courseID) != null) {
			return true;
		}else
			return false;
	}
	
	/**
	 * eqals() method
	 * @param list LinkedList
	 * @return	boolean
	 */
	public boolean equals(CourseList list) {
		CourseNode t1 = this.head, t2 = list.head;
		while(t1 != null && t2 != null){
			if(t1.C != t2.C) {
				return false;
			}	
				t1 = t1.next;
				t2 = t2.next;
			}
			return (t1 == null && t2 == null);
	}
	
	
//	=============================================
//	===============	Added methods ===============
//	=============================================
	
	/**
	 *Show content of linkedList
	 */
	public void displayList() {
		CourseNode t = head;
		if(t == null) {
			System.out.println("\nThere is nothing to display; list is empty.");
		}else
			System.out.println("Here are the contents of the list." );
		while(t != null){
			System.out.print(t.C + " ---> ");
			t = t.next;
		}
		System.out.println("X");
	}
	
	/**
	 * find courseID
	 * @param courseID the courseID of the class
	 * @return boolean 
	 */
	public CourseNode find2(String courseID) {
		if(head == null) {
			return null;
		}
		CourseNode t = head;
		while(t != null && t.next !=null && !t.C.getCourseID().equals(courseID)) {
			t = t.next;
		}
		return t;
	}
	
	/**
	 * Remove duplicate elements in the list
	 */
	public void removeDuplicates() {
		//t1 stays fixed at a node while t2 move across the LinkedList
        CourseNode t1 = head, t2 = null, duplicate = null;

        while (t1 != null && t1.next != null) {
            t2 = t1;	
            while (t2.next != null) {
                if (t1.C.equals(t2.next.C)) {	//node of t1 is equal to node of t2
                    duplicate = t2.next;
                    t2.next = t2.next.next;
                } else{
                    t2 = t2.next;	//Move to next node to compare with t1
                }
            }
            t1 = t1.next;
        }
    }
	private static ArrayList<String> enrolled = new ArrayList<String>(5);	//Store enrolled courses into ArrayList
	/**
	 * Validates if enrollment is acceptable
	 * @param requestedCourse name of the requested course
	 * @param finished	ArrayList of the finished course
	 * @return boolean
	 */
	public boolean enrollmentValidation(String requestedCourse, ArrayList<String> finished) {
		CourseNode t = null;
		t = find2(requestedCourse);
		
		if(t.C.getCourseID().equals(requestedCourse) || finished.isEmpty() ) {//Check to see if the requested course is in the Syllabus.txt
		
			if(t.C.getPreReqID().equals("") && t.C.getCoReqID().equals("")) { //No pre-requisite or co-requisite required
				System.out.println("Student can enrol in " + requestedCourse + " as it requires no pre-requite or co-requisite.");
				enrolled.add(requestedCourse);
				return true;
			}
			else if(finished.contains(t.C.getPreReqID()) && finished.contains(t.C.getCoReqID())) { //Completed both pre-requisite and co-requisite
				System.out.println("Student can enrol in " + requestedCourse + " as he/she has completed the pre-requisite " + t.C.getPreReqID() + " and the co-requisite " + t.C.getCoReqID() + ".");
				enrolled.add(requestedCourse);
				return true;
			}
			else if(finished.contains(t.C.getPreReqID()) && enrolled.contains(t.C.getCoReqID())) {	//Completed the pre-requisite and is enrolling in the co-requisite
				System.out.println("Student can enrol in " + requestedCourse + " as he/she has completed the pre-requisite " + t.C.getPreReqID() + " and is enrolled the co-requisite " + t.C.getCoReqID() + ".");
				enrolled.add(requestedCourse);
				return true;
			}
			else if(finished.contains(t.C.getPreReqID())) {	//Completed the pre-requisite
				System.out.println("Student can enrol in " + requestedCourse + " as he/she has completed the pre-requisite " + t.C.getPreReqID() + ".");
				enrolled.add(requestedCourse);
				return true;
			}
			else if(finished.contains(t.C.getCoReqID())) {	//Completed the co-requisite
				System.out.println("Student can enrol in " + requestedCourse + " as he/she has completed the co-requisite " + t.C.getCoReqID() + ".");
				enrolled.add(requestedCourse);
				return true;
			}
			else if(enrolled.contains(t.C.getCoReqID())) {	//Enrolled in the co-requisite
				System.out.println("Student can enrol in " + requestedCourse + " as he/she is enrolled the co-requisite " + t.C.getCoReqID() + ".");
				enrolled.add(requestedCourse);
				return true;
			}
			else{	//Has neither completed the pre-requisite nor co-requisite, or enrolled in the co-requisite
				System.out.println("Student cannot enrol in " + requestedCourse + " as he/she doesn't have sufficient background needed.");
				return false;
			}
		}
		else {
			if(finished.isEmpty())
				System.out.println("Student cannot enrol in " + requestedCourse + " as he/she doesn't have sufficient background needed.");
			else
				System.out.println("The course the student requested is not among the list of courses");
		}
		return false;
	}
}
