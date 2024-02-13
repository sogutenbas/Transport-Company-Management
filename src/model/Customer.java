package model;
/**
 * @author Migle Adomonyte
 * 
 * This class represents a customer in the system. 
 * It provides methods for customer management. 
 */

public class Customer {
	private int customerNumber, salesRepEmployeeNumber;
	private String customerName, contactLastName, contactFirstName, phone, addressLine1, 
	addressLine2, city, state, postalCode, country;
	private double creditLimit;
	
	/**
	 * Constructor for creating a new customer. 
	 * 
	 * @param customerNumber          Customer number.
	 * @param customerName            Customer's name. 
	 * @param contactLastName         Last name of the contact person. 
	 * @param contactFirstName        First name of the contact person. 
	 * @param phone                   Phone number. 
	 * @param addressLine1            Address line 1. 
	 * @param addressLine2            Address line 2. 
	 * @param city                    City.
	 * @param postalCode              Postal code. 
	 * @param country                 Country.
	 * @param salesRepEmployeeNumber  Sales representative employee number. 
	 * @param creditLimit             Credit limit. 
	 * 
	 */
	public Customer(int customerNumber, String customerName, String contactLastName, String contactFirstName, 
			String phone, String addressLine1, String addressLine2, String city, String state, String postalCode, 
			String country, int salesRepEmployeeNumber, double creditLimit) {
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.contactLastName = contactLastName;
		this.contactFirstName = contactFirstName;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
		this.creditLimit = creditLimit;
	}

	/**
	 * Getter for customer number. 
	 * @return Customer number.
	 */
	public int getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * Setter for customer number. 
	 * @param customerNumber Customer number to set. 
	 */
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
	 * Getter for customer's name. 
	 * @return Customer's name. 
	 */
	public String getCustomerName() {
		return customerName; 
	}

	/**
	 * Setter for customer's name. 
	 * @param customerName Customer's name to set.
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * Getter for last name of the contact person. 
	 * @return Last name of the contact person. 
	 */
	public String getContactLastName() {
		return contactLastName;
	}

	/**
	 * Setter for last name of the contact person. 
	 * @param contactLastName Last name of the contact person to set. 
	 */
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	/**
	 * Getter for first name of the contact person. 
	 * @return First name of the contact person. 
	 */
	public String getContactFirstName() {
		return contactFirstName;
	}
	
	/**
	 * Setter for first name of the contact person to set.
	 * @param contactFirstName First name of the contact person to set.
	 */
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	/**
	 * Getter for phone number. 
	 * @return Phone number. 
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Setter for phone number.  
	 * @param phone Phone number to set. 
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Getter for address line 1. 
	 * @return Address line 1. 
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * Setter for address line 1. 
	 * @param addressLine1 Address line 1 to set. 
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	/**
	 * Getter for address line 2. 
	 * @return Address line 2. 
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * Setter for address line 2. 
	 * @param addressLine2 Address line 2 to set. 
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * Getter for city. 
	 * @return City. 
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Setter for city. 
	 * @param city City to set. 
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Getter for state. 
	 * @return State. 
	 */
	public String getState() {
		return state;
	}

	/**
	 * Setter for state. 
	 * @param state State to set. 
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Getter for postal code. 
	 * @return Postal code. 
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Setter for postal code. 
	 * @param postalCode Postal code to set. 
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Getter for country
	 * @return Country. 
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Setter for country. 
	 * @param country Country to set. 
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Getter for sales representative employee number. 
	 * @return Sales representative employee number. 
	 */
	public int getSalesRepEmployeeNumber() {
		return salesRepEmployeeNumber;
	}

	/**
	 * Setter for sales representative employee number. 
	 * @param salesRepEmployeeNumber The sales representative employee number to set. 
	 */
	public void setSalesRepEmployeeNumber(int salesRepEmployeeNumber) {
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
	}

	/**
	 * Getter for credit limit. 
	 * @return Credit limit. 
	 */
	public double getCreditLimit() {
		return creditLimit;
	}

	/**
	 * Setter for credit limit. 
	 * @param creditLimit Credit limit to set. 
	 */
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
}
