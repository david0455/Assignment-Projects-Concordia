
// ----------------------------------------------------------
// Assignment #4
// Question: IV
// Written by: David Chen 40063285
// ----------------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class EnrolmentResults {
	//Read the input file
	public static void readFile(Scanner inFile, CourseList list) {
		String s = "";
		String courseID = "";
		String courseName = "";
		double credit = 0;
		String preReqID = "";
		String coReqID = "";
		
		while(inFile.hasNextLine()) {
			//Get only the necessary information about the courses in the file 
			s = inFile.nextLine();
			courseID = s.substring(0, s.indexOf("\t"));
			courseName = s.substring(s.indexOf("\t")+1, s.lastIndexOf("\t"));
			credit = Double.parseDouble(s.substring(s.lastIndexOf("\t")+1, s.length()));
			s = inFile.nextLine();
			if(s.length() == 1) { //If there is no pre-requisite
				preReqID = "";
			}else {
				preReqID = s.substring(s.indexOf("\t")+1,s.length());
			}
			s = inFile.nextLine();
			if(s.length() == 1){ //If there is no co-requisite
				coReqID = "";	
			}else {
				coReqID = s.substring(s.indexOf("\t")+1,s.length());
			}
			Course C = new Course(courseID, courseName, credit, preReqID, coReqID); //Creates Course object
			list.addToStart(C); //Add Course object to CourseList
			
			if(inFile.hasNextLine()) //Do not jump to next if its null
				inFile.nextLine();
		}
		list.removeDuplicates(); //Remove duplicate elements in the list
		list.displayList();
		System.out.println();
	}
	
	//Read Request(#).txt files
	public static void readRequestFile(Scanner inFile, CourseList list1) {
		
		String s = "";		
		//Create ArrayList to store the Strings in the file
		ArrayList<String> finished = new ArrayList<String>(5);	
		ArrayList<String> requested = new ArrayList<String>(5);
		
		inFile.nextLine();
		while(inFile.hasNextLine()) {
			s = inFile.nextLine();
			if(s.contains("Requested")) { //Read all the Finished course first, then it reads the requested courses
				while(inFile.hasNext()) {
					s = inFile.nextLine(); //Skip "Requested" line
					requested.add(s); //Add String (courseID to the ArrayList)
				}
			}else {
				finished.add(s);
			}	
		}
		if(requested.size() == 0)
			System.out.println("No enrollment courses found.");
		else {
			for(int i = 0; i < requested.size(); i++) {
				list1.enrollmentValidation(requested.get(i), finished);
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to Enrollment Results\n");
		//Create CourseList
		CourseList list1 = new CourseList();
		CourseList list2 = null; //CourseList to test methods
		String[] findCourseID = new String[3]; //User input courseID
		
		Scanner sc = null;
		Scanner myKeyboard = new Scanner(System.in);
		
		String fileInput = "";	//Name of the file to be read
		try {
			fileInput = "Syllabus.txt";
			sc = new Scanner(new FileInputStream(fileInput));	//Open stream to read file
			readFile(sc, list1);
			System.out.print("Please enter the name of the REQUEST file that needs to be processed. ");
			fileInput = myKeyboard.nextLine();
			sc = new Scanner(new FileInputStream(fileInput));	//Open stream to read file
			readRequestFile(sc, list1);
		}
		catch(FileNotFoundException e) {
			System.out.println("\nCould not open input file " + fileInput + " for reading." );
			System.out.println("\nPlease check if file exists! Program will terminate after closing any opened files");
			System.exit(0);
		}
		
		System.out.println("\nPlease enter a 3 courseID:");
		for(int i = 0; i < 3; i++) {
			findCourseID[i] = myKeyboard.nextLine();
			list1.find(findCourseID[i]);
		}
		
		
		//Testing methods
		System.out.println("\n==================== Testing methods from Course class ====================");
		
		Course c1 = new Course("ENGR213", "Calculus_I", 3.0, "COMP228", ""), c2 = new Course();
		Course c3 = new Course("ENGR214", "Calculus_I.5", 3.0, "COMP229", "");
		
		System.out.println("\n************ clone() Course class **********");
		c2 = c1.clone();
		System.out.println("[courseID]: " + c2.getCourseID() + "\n[courseName]: " + c2.getCourseName()
		+ "\n[credit]: " + c2.getCredit()+ "\n[preReqID]: " + c2.getPreReqID() + "\n[coReqID]: " + c2.getCoReqID());
	
		System.out.println("\n************ isDirectlyRelated() Course class **********");
		System.out.println("c2 directly related to c1: " + c2.isDirectlyRelated(c1));
		System.out.println("c1 directly related to c3: " + c1.isDirectlyRelated(c1));
		
		
		System.out.println("\n==================== Testing methods from CourseList class ====================");
		
		System.out.println("\n************ clone() CourseList class **********");
		list2 = list1.clone();
		list2.displayList();
		
		System.out.println("\n************ equals() CourseList class **********");
		System.out.println("list1 is equal to list2: " + list1.equals(list2));
		
		System.out.println("\n************ addToStart() CourseList class **********");
		Course c4 = new Course("ENGR251", "Thermodynamics", 3.0, "", "");
		list2.addToStart(c4);
		System.out.print("list1: ");
		list1.displayList();
		System.out.print("\nlist2: ");
		list2.displayList();
		System.out.println("\nlist1 is equal to list2: " + list1.equals(list2));
		
		System.out.println("\n************ insertAtIndex(), replaceAtIndex() CourseList class **********");
		list2.insertAtIndex(c4, 4);
		list2.replaceAtIndex(c4, 5);
		System.out.println("list2 display: ");
		list2.displayList();
		
		System.out.print("\nlist1 display: ");
		list1.displayList();
		
		System.out.println("\n************ deleteFromIndex(), deleteFromStart() CourseList **********");
		list2.deleteFromIndex(3);
		list2.deleteFromStart();
		list2.displayList();
		
		System.out.println("\n************ find(), contains() CourseList class **********");
		CourseList.CourseNode x;	
		x = list2.find("ENGR251");	//May result in privacy leak
		System.out.println("Contains: " + list2.contains("COMP233"));
		
		System.out.println("This program will terminate.");
		sc.close(); // Close Stream
		myKeyboard.close();
	}

}
