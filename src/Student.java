/**
 * 
 * @author Glen Feldkamp
 * Written January 28, 2014.
 * None of this code can be reused or copied without permission.
 * Class for storing data in regards to a student for IT341 Assignment 1.
 * 
 */
public class Student 
{
	// Variables for storing necessary information
	// Strings store first and last name of student
	private String firstName, lastName;
	// Double to store final grade value.
	private double finalGrade;
	// Double array for storing grades received
	private double[] studentGrades;
	
	/**
	 * Constructor to build student object that stores name and grades.
	 * @param first String value for first name.
	 * @param last String value for last name.
	 * @param grades Integer value to store expected grades.
	 */
	public Student(String first, String last, int grades)
	{
		super();
		this.firstName = first;
		this.lastName = last;
		this.finalGrade = 0.0;
		this.studentGrades = new double[grades];
	}
	
	/**
	 * Method will initialize the grade array to given values.
	 * @param grades Double array containing grades, must match expected size.
	 */
	public void addGrades(double[] grades)
	{
		// Sizes do not match, error is printed.
		if(this.studentGrades.length != grades.length)
		{
			System.out.println("Input number of Grades does not match the Student's Record.");
			System.out.println("Expected: " + this.studentGrades.length + ", Input: " + grades.length);
		}
		// Sizes do match so process the grades.
		else
		{
			for(int i = 0; i < this.studentGrades.length; i++)
			{
				this.studentGrades[i] = grades[i];
			}
		}
	}
	
	/**
	 * Method will compute the final grades using the given weights.
	 * @param weights Array of doubles for use in computing final grades, amount of grades must match weights.
	 */
	public void computeFinalGrades(double[] weights)
	{
		if(this.studentGrades.length != weights.length)
		{
			System.out.println("Number of weights does not match the number of grades.");
			System.out.println("Expected: " + this.studentGrades.length + ", Input: " + weights.length);
		}
		else
		{
			for(int i = 0; i < this.studentGrades.length; i++)
			{
				this.finalGrade = this.finalGrade + (this.studentGrades[i] * weights[i]);
			}
		}
	}
	
	/**
	 * Method to get the students first name.
	 * @return String value that is the Students first name.
	 */
	public String getFirstName() 
	{
		return firstName;
	}

	/**
	 * Method to get the students last name.
	 * @return String value that is the Students last name.
	 */
	public String getLastName() 
	{
		return lastName;
	}

	/**
	 * Method to get the number of grades the student record holds.
	 * @return Double value that is the final grade in the record.
	 */
	public double getFinalGrade() 
	{
		return finalGrade;
	}
	
	/**
	 * Method for obtaining an assignment grade.
	 * @param index Assignment index value, starts at 0 and goes to n-1.  N is total assignments.
	 * @return Double representing the assignment grade, returns -1.0 if index is out of bounds.
	 */
	public double getAsgGrade(int index)
	{
		if(index >= 0 && index < this.studentGrades.length)
			return this.studentGrades[index];
		else
		{
			System.out.println("Index Value is out of bounds, expected value between 0 and "
								+ (this.studentGrades.length-1));
			return -1.0;
		}
	}

	// Main for testing purposes
	public static void main(String[] args)
	{
		Student test = new Student("Thomas","Johnson",8);
		System.out.println(test.getFirstName());
		System.out.println(test.getLastName());
		System.out.println("final:" + test.getFinalGrade());
		double[] temp = new double[8];
		temp[0] = 72.0;
		temp[1] = 100.0;
		temp[2] = 92.0;
		temp[3] = 83.0;
		temp[4] = 64.0;
		temp[5] = 91.0;
		temp[6] = 88.3;
		temp[7] = 77.4;
		test.addGrades(temp);
		for(int i = 0; i<test.studentGrades.length;i++)
		{
			System.out.println(test.studentGrades[i]);
		}
		double[] temp2 = new double[8];
		for(int i = 0; i<temp2.length;i++)
		{
			temp2[i] = .125;
		}
		System.out.println("Asg2: " + test.getAsgGrade(1));
		test.computeFinalGrades(temp2);
		System.out.println("final:" + test.getFinalGrade());
	}

}
