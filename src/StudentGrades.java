/**
 * 
 * @author Glen Feldkamp
 * Written January 30, 2014.
 * None of this code can be reused or copied without permission.
 * Class for doing file IO operations on student records for IT341 Assignment 1.
 * 
 */
import java.io.*;
import java.util.*;

public class StudentGrades 
{

	/**
	 * Main method for managing student grades, will create necessary objects and perform functions.
	 * @param args Requires two arguments, input file and an output file in that order.
	 */
	public static void main(String[] args) 
	{
		// Error for invalid arguments.
		if(args.length != 2)
		{
			System.out.println("Usage: StudentGrades <input.txt> <output.txt>");
		}
		// Perform IO operations
		else
		{
			// Create scanner and printwriter
			Scanner in = null;
			PrintWriter out = null;
			try 
			{
				in = new Scanner(new File(args[0]));
				out = new PrintWriter(args[1]);
				
				// Create StudentProcessor object and necessary variables
				StudentProcessor students = new StudentProcessor();
				int numAssignments = in.nextInt();
				double[] weights = new double[numAssignments];
				double[] grades = new double[numAssignments];
				String first, last = "";
				
				// IO Operations require the file to be formatted correctly and have correct data.
				
				// Set the number of Assignments from the input file.
				students.setNoOfAssignments(numAssignments);
				
				// Populate and set weights array.
				for(int i = 0; i < numAssignments; i++)
				{
					weights[i] = in.nextDouble();
				}
				students.setWeights(weights);
				
				// While another line remains in the file, we have a student record
				while(in.hasNextLine())
				{
					// Populate name which is always the first two strings
					first = in.next();
					last = in.next();
					
					// Populate scores for grades out of 100, this should always match the number 
					// of assignments.  Casting is done because they are listed as integers in the given file
					for(int i = 0; i < numAssignments; i++)
					{
						grades[i] = (double)in.nextInt();
					}
					
					// Add the student and advance the file to the next line.
					students.addStudent(first, last, grades);
					in.nextLine();
				}
				// Write the output file.
				out.write(students.toString());
				// Close IO operations.
				in.close();
				out.close();
			
			} catch (FileNotFoundException e) {
				// Error message for file not found.
				System.out.println("File " + args[0] + " not found.");
			}	
		}
	}
}
