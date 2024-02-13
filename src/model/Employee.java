package model;


/**
 * @author Migle Adomonyte
 * This class represents an employee in a system. 
 */

public class Employee {
	private int employeeNumber, reportsTo;
	private String lastName, firstName, extension, email, officeCode, jobTitle;
	
	/**
	 * Constructor for creating a new employee.
	 * 
	 * @param employeeNumber    Employee number. 
	 * @param lastName          Last name. 
	 * @param firstName         First name. 
	 * @param extension         Extension number of the employee. 
	 * @param email             Email address. 
	 * @param officeCode        Office code. 
	 * @param reportsTo         Employee number the employee reports to. 
	 * @param jobTitle          Job title. 
	 */
	public Employee(int employeeNumber, String lastName, String firstName, String extension, String email, String officeCode, 
			int reportsTo, String jobTitle) {
		this.employeeNumber = employeeNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.extension = extension;
		this.email = email;
		this.officeCode = officeCode;
		this.reportsTo = reportsTo;
		this.jobTitle = jobTitle;
	}

	/**
	 * Getter for employee number. 
	 * @return Employee number. 
	 */
	public int getEmployeeNumber() {
		return employeeNumber;
	}

	/**
	 * Setter for employee number. 
	 * @param employeeNumber Employee number to set.
	 */
	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	/**
	 * Getter for last name. 
	 * @return Last name. 
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for last name. 
	 * @param lastName Last name to set. 
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for first name. 
	 * @return First name. 
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for for first name. 
	 * @param firstName Fist name to set. 
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for extension number of the employee. 
	 * @return Extension number of the employee. 
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * Setter for extension number of the employee. 
	 * @param extension Extension number of the employee to set. 
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * Getter for email address. 
	 * @return Email address. 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for email address. 
	 * @param email Email address to set. 
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for office code. 
	 * @return Office code. 
	 */
	public String getOfficeCode() {
		return officeCode;
	}

	/**
	 * Setter for office code. 
	 * @param officeCode Office code to set. 
	 */
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	
	/**
	 * Getter for employee number the employee reports to. 
	 * @return Employee number the employee reports to. 
	 */
	public int getReportsTo() {
		return reportsTo;
	}

	/**
	 * Setter for employee number the employee reports to. 
	 * @param reportsTo Employee number the employee reports to to set. 
	 */
	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}

	/**
	 * Getter for job title. 
	 * @return Job title. 
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * Setter for job title. 
	 * @param jobTitle Job title to set. 
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	
	
}
