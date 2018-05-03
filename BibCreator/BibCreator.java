
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

//This program reads and process .bib files and create 3 different files with the correct reference formats for IEEE, ACM and NJ.
public class BibCreator {
	/**
	 * This validates the input files and writes to the 3 different created files
	 * @param inFile the input file to be validated
	 * @param outFileIEEE the output file to be written on
	 * @param outFileACM the output file to be written on
	 * @param outFileNJ the output file to be written on
	 * @throws FileInvalidException This validates the input file
	 */
	//Validates input files, and writes to 3 different created files
	public static void processFilesForValidation(Scanner inFile, PrintWriter outFileIEEE, 
					PrintWriter outFileACM, PrintWriter outFileNJ) throws FileInvalidException{
		
		String s = ""; // Read line by line from input file and copy it to output file
		int numArticles = 0; //Count for number of articles in a file
		
		StringTokenizer tokenizer = null;
		String[] token;
		int numToken = 0;
		
		String author = "";
		String ACM_author = "";
		String journal  = "";
		String title = "";
		String year = "";
		String volume = "";
		String number = "";
		String pages = "";
		String doi = "";
		String month = "";
		
		while(inFile.hasNextLine()) {
			s = inFile.nextLine();
			if(s.contains("={}")) {
				inFile.close();
				outFileIEEE.close(); outFileACM.close(); outFileNJ.close();
				throw new FileInvalidException("File is Invalid. Field \"" + s.substring(0, s.indexOf("=")) 
				+ "\" is Empty. Processing stopped at this point. Other empty fields may be present as well!");
			}
			else {
				if(s.contains("@ARTICLE{")) {
					numArticles++;
				}
				if(s.contains("author={")) {
					author = s.substring(s.indexOf("{")+1, s.indexOf("}"));
					
					tokenizer = new StringTokenizer(author, " "); //Find tokens in the string separated by " ";
					numToken = tokenizer.countTokens(); //Number of tokens
					token = new String[numToken];
					for(int i = 0; i < numToken; i++){
						token[i] = tokenizer.nextToken(); //Store the tokens in an array
					}
					ACM_author = token[0] + " " + token[1] + " et al. ";
				}
				if(s.contains("journal={")) {
					journal = s.substring(s.indexOf("{")+1, s.indexOf("}"));
				}
				if(s.contains("title={")) {
					title = s.substring(s.indexOf("{")+1, s.indexOf("}"));
				}
				if(s.contains("year={")) {
					year = s.substring(s.indexOf("{")+1, s.indexOf("}"));
				}
				if(s.contains("volume={")) {
					volume = s.substring(s.indexOf("{")+1, s.indexOf("}"));
				}
				if(s.contains("number={")) {
					number = s.substring(s.indexOf("{")+1, s.indexOf("}"));
				}
				if(s.contains("pages={")) {
					pages = s.substring(s.indexOf("{")+1, s.indexOf("}"));
				}
				if(s.contains("doi={")) {
					doi = s.substring(s.indexOf("{")+1, s.indexOf("}"));
				}
				if(s.contains("month={")) {
					month = s.substring(s.indexOf("{")+1, s.indexOf("}"));
				}
				//Writes to the 3 different output files
				if(s.equals("}")) {
					//IEEE format
					outFileIEEE.println(author.replace(" and", ",") +". \"" + title + "\", "
							+ journal + ", vol. " + volume + ", no. " + number + ", p. " + pages
							+ ", " + month + " " + year + ".");
					outFileIEEE.println();
					//ACM format
					outFileACM.println("["+ numArticles + "]\t"+ ACM_author + year
							+ ". " + title + ". " + journal + ". " + volume + ", " + number 
							+ " (" + year + "), " + pages + ". DOI:https://doi.org/" + doi + ".");
					outFileACM.println();
					//NJ format
					outFileNJ.println(author.replace("and", "&") + ". " + title + ". " 
							+ journal + ". " + volume + ", " + pages + "(" + year +").");
					outFileNJ.println();
				}
				
			}//End of if-else statement
		}//End of while loop
		inFile.close();
		outFileIEEE.close(); outFileACM.close(); outFileNJ.close(); //Flush the buffers
	}//End of processFilesForValidation method
	
	/**
	 * This displays the content of a file
	 * @param inFile the input file to be read
	 * @throws IOException This validates the file
	 */
	// A method that takes a stream file name and display the contents of this file 
	public static void displayFileContents(BufferedReader inFile) throws IOException{
		int x; 
		x = inFile.read();
		while(x != -1) {
			System.out.print((char)x); //Cast to char since x is an integer
			x = inFile.read();
		}
		inFile.close();
	}

	public static void main(String[] args){
		System.out.println("Welcome to David's BibCreator!");
		
		Scanner myKeyboard = new Scanner(System.in);
		String fileToRead = "";
		int numOfAttempts = 0;	//Number of attempts for reading a file
		int fileCounter = 0;	//Number of files
		int invalidCounter = 0; //Number of invalid files
		
		Scanner sc = null;
		PrintWriter pw1 = null;	//PrintWriter for IEEE files
		PrintWriter pw2 = null;	//PrintWriter for ACM files
		PrintWriter pw3 = null;	//PrintWriter for NJ files
		BufferedReader br = null;
		
		File IEEE = null;
		File ACM = null;
		File NJ = null;
		
		String inputFile ="";
		String IEEE_file = "";
		String ACM_file = "";
		String NJ_file = "";
		
		for(int i = 0; i < 10; i++) {
			//Name of input and output files
			inputFile = "Latex" + (i+1) + ".bib";
			IEEE_file = "IEEE" + (i+1) + ".json";
			ACM_file = "ACM" + (i+1) + ".json";
			NJ_file = "NJ" + (i+1) + ".json";
			//Try to open all files
			try {
					sc = new Scanner(new FileInputStream(inputFile));
					fileCounter++;
			}
			catch(FileNotFoundException e) {
				System.out.println("\nCould not open input file " + inputFile + " for reading." );
				System.out.println("\nPlease check if file exists! Program will terminate after closing any opened files");
				System.exit(0);
			}
			//All input files must be opened in order to attempt to open/create output files
			try {
				pw1 = new PrintWriter(new FileOutputStream(IEEE_file));
				pw2 = new PrintWriter(new FileOutputStream(ACM_file));
				pw3 = new PrintWriter(new FileOutputStream(NJ_file));
			
				IEEE = new File(IEEE_file);
				ACM = new File(ACM_file);
				NJ = new File(NJ_file);
			
				processFilesForValidation(sc, pw1, pw2, pw3);	
			}
			catch(FileNotFoundException e) {
				System.out.println("\nCould not open input file " + inputFile + " for reading." );
				System.out.println("\nPlease check if file exists! Program will terminate after closing any opened files");
				sc.close();
				System.exit(0);
			}
			catch(FileInvalidException e) {
				invalidCounter++;
				IEEE.delete(); ACM.delete(); NJ.delete();
				String s = e.getMessage();
				System.out.println("\nERROR: Detected Empty Field!" 
						+ "\n=====================================\n"
						+ "\nProblem detected with input file: " + inputFile);
				System.out.println(s); 
			}
		} // End of for loop
		
		System.out.println("\nA total of " + invalidCounter + " files were invalid, and could not be processed. All other " 
				+ (fileCounter-invalidCounter) + " \"Valid\" files have been created.\n");
		
		// Try to re-open the copied file
		while(numOfAttempts < 2) {
			System.out.print("Please enter the name of one of the files that you need to review:");
			fileToRead = myKeyboard.next();
			
			try {
				br = new BufferedReader(new FileReader(fileToRead));
				System.out.println("Here are the contents of the successfully created Json File:" + fileToRead );
				numOfAttempts = 2;
			}
			catch(FileNotFoundException e) {
				numOfAttempts++;
				if(numOfAttempts < 2) {
				System.out.println("Could not open input file. File does not exist; possibly it could not be created!");
				System.out.println("\nHowever, you will be allowed another chance to enter another file name.");
				}
				if(numOfAttempts >= 2) {
					System.out.println("\nCould not open input file again! Either file does not exist or could not be created");
					System.out.println("Sorry! I am unable to display your desired files! Program will exit!");
					System.exit(0);
				}
			}
		}
		//Try to display file content
		try {
			displayFileContents(br);
			System.out.println("\nGoodBye! Hope you have enjoyed creating the needed files using BibCreator.");
		}
		catch(IOException e) {
			System.out.println("Error: An error has occured while reading from the " + fileToRead + " file.");
			System.out.println("Program will terminate");
			System.exit(0);
		}
	}
}
