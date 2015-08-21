/**
 * 
 * @author Glen Feldkamp
 * Written January 28, 2014.
 * None of this code can be reused or copied without permission.
 * Class for processing data in regards to a student for IT341 Assignment 1.
 * 
 */
import java.text.DecimalFormat;
import java.util.*;

public class StudentProcessor 
{
	// Variables to hold required data
	// ArrayList of students, grows as required
	private ArrayList<Student> students;
	// Integer value holds the total assignments
	private int numAssignments;
	// Double array to hold the assignments weights
	private double[] asgWeights;

	/**
	 * Constructor for a StudentProcessor object, takes no arguments
	 * Initializes a new ArrayList of students and sets number of assignments to 0.
	 */
	public StudentProcessor()
	{
		super();
		this.students = new ArrayList<Student>();
		this.numAssignments = 0;
	}
	
	/**
	 * Sets the current number of assignments.
	 * @param a Integer value representing the number of assignments, must be greater than or equal to 0.
	 */
	public void setNoOfAssignments(int a)
	{
		if(a > 0)
			this.numAssignments = a;
		else
		{
			System.out.println("Invalid value for number of assignments, must " +
					"be greater than 0. Input value: " +  a);
		}
	}
	
	/**
	 * Sets the required weights using provided array of doubles.
	 * @param weights Double values representing the given assignment weights as a percentage.
	 */
	public void setWeights(double[] weights)
	{
		this.asgWeights = new double[weights.length];
		for(int i = 0; i < weights.length;i++)
		{
			this.asgWeights[i] = weights[i];
		}
	}
	
	/**
	 * Adds a student to the Students ArrayList.
	 * @param first First name of the student as a String.
	 * @param last Last name of the student as a String.
	 * @param grades Array of grades the student received as doubles.
	 */
	public void addStudent(String first, String last, double[] grades)
	{
		Student temp = new Student(first, last, grades.length);
		temp.addGrades(grades);
		temp.computeFinalGrades(asgWeights);
		this.students.add(temp);
	}
	
	/**
	 * Returns a string containing assignment records as required in Assignment description.
	 */
	public String toString()
	{
		String out = "";
		int numStu = this.students.size();
		double minScore, maxScore, avgScore;
		// Formats the data to 2 decimal places.
		DecimalFormat df = new DecimalFormat("#.00");
		// Need to cycle through each assignment and get each students grade for it to calculate min,max,avg
		for(int i = 0; i < this.numAssignments; i++)
		{
			minScore = 100.0;
			maxScore = avgScore = 0.0;
			for(int j = 0; j < numStu; j++)
			{
				// minimum is initially 100 and gets set to each successive lower score
				if(this.students.get(j).getAsgGrade(i) <= minScore)
				{
					minScore = this.students.get(j).getAsgGrade(i);
				}
				// maximum is initially 0 and gets set to each successive higher score
				if(this.students.get(j).getAsgGrade(i) >= maxScore)
				{
					maxScore = this.students.get(j).getAsgGrade(i);
				}
				// average is accumulated for each student and then calculated at the end.
				avgScore = avgScore + this.students.get(j).getAsgGrade(i);
			}
			// Check for division by 0 although this should never occur.
			if(numStu != 0)
			{
				avgScore = avgScore/numStu;
			}
			// In case division by 0 does occur, the average will be set to 0.0
			else
			{
				avgScore = 0.0;
			}
			out += "Assignment " + (i+1) + " statistics:\n";
			out += "Min: " + df.format(minScore) + "\n";
			out += "Max: " + df.format(maxScore) + "\n";
			out += "Average: " + df.format(avgScore) + "\n";
			out += "_________________________________________________________________________\n";
		}
		for(int i = 0; i < numStu ; i++)
		{
			out += students.get(i).getFirstName() + " " + students.get(i).getLastName() + " ";
			if(students.get(i).getFinalGrade() >= 90.0)
			{
				out += "A\n";
			}
			else if(students.get(i).getFinalGrade() >= 80.0)
			{
				out += "B\n";
			}
			else if(students.get(i).getFinalGrade() >= 70.0)
			{
				out += "C\n";
			}
			else if(students.get(i).getFinalGrade() >= 60.0)
			{
				out += "D\n";
			}
			else
			{
				out += "F\n";
			}
		}
		return out;
	}
	
	// Main for testing purposes
	public static void main(String[] args) 
	{
		int testVal = 8;
		StudentProcessor test = new StudentProcessor();
		double[] temp = new double[testVal];
		double[] temp2 = new double[testVal];
		for(int i = 0; i < testVal;i++)
		{
			temp[i] = 0.125;
			temp2[i] = 79.9625;
		}
		test.setNoOfAssignments(testVal);
		test.setWeights(temp);
		
		test.addStudent("Bob", "Johnson", temp2);
		for(int i = 0; i < testVal; i++)
		{
			temp2[i] = 90.000000001;
		}
		test.addStudent("Jeff", "Williams", temp2);
		System.out.println(test.toString());
		System.out.println(test.students.size());
	}
}
