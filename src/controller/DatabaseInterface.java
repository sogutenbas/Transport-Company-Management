package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import model.Customer;
import model.Employee;
import model.Order;
import model.Payments;
import model.Products;

/**
 * @author Migle Adomonyte
 * 
 *         This class represents database interface. The code is based on
 *         ExampleApp.
 */

public interface DatabaseInterface {
	/**
	 * Open connection.
	 * 
	 * @throws SQLException
	 */
	void open() throws SQLException;

	/**
	 * Close connection.
	 * 
	 * @throws SQLException
	 */
	void close() throws SQLException;

	/**
	 * Test connection.
	 * 
	 * @throws SQLException
	 */
	void test() throws SQLException;

	/**********************
	 * * CUSTOMER FUNCTIONS * *
	 **********************/

	/**
	 * Method for retrieving a list of customers from database it selects all
	 * customers without any applied filter.
	 * 
	 * @return List of Customer objects.
	 * @throws SQLException
	 */
	List<Customer> getCustomers() throws SQLException;

	/**
	 * Method for retrieving a list of customers from database that are from the
	 * specified city.
	 * 
	 * @param city The city of the customer.
	 * @return List of Customer objects filtered by city.
	 * @throws SQLException
	 */
	Customer getCustomerByNumber(int customerNumber) throws SQLException;

	/**
	 * Method for retrieving the last customer number.
	 * 
	 * @return Customer number.
	 * @throws SQLException
	 */
	int getLastCustomerNumber() throws SQLException;

	/**
	 * Method for adding a new employee to the database.
	 * 
	 * @param customerNumber         Customer number.
	 * @param customerName           Customer's name.
	 * @param contactFirstName       First name of the contact person.
	 * @param contactLastName        Last name of the contact person.
	 * @param phone                  Phone number.
	 * @param addressLine1           Address line 1.
	 * @param addressLine2           Address line 2.
	 * @param city                   City.
	 * @param postalCode             Postal code.
	 * @param country                Country.
	 * @param salesRepEmployeeNumber Sales representative employee number.
	 * @param creditLimit            Credit limit.
	 * @throws SQLException
	 */
	void addCustomer(int customerNumber, String customerName, String contactLastName, String contactFirstName,
			String phone, String addressLine1, String addressLine2, String city, String state, String postalCode,
			String country, int salesRepEmployeeNumber, double creditLimit) throws SQLException;

	/**
	 * Method for updating a customer information in the database.
	 * 
	 * @param customerName           Customer's name.
	 * @param contactFirstName       First name of the contact person.
	 * @param contactLastName        Last name of the contact person.
	 * @param phone                  Phone number.
	 * @param addressLine1           Address line 1.
	 * @param addressLine2           Address line 2.
	 * @param city                   City.
	 * @param postalCode             Postal code.
	 * @param country                Country.
	 * @param salesRepEmployeeNumber Sales representative employee number.
	 * @param creditLimit            Credit limit.
	 * @throws SQLException
	 */
	void updateCustomer(int customerNumber, String customerName, String contactLastName, String contactFirstName,
			String phone, String addressLine1, String addressLine2, String city, String state, String postalCode,
			String country, int salesRepEmployeeNumber, double creditLimit) throws SQLException;

	/**
	 * Method for deleting a customer based on the customer number.
	 * 
	 * @param customerNumber Customer number.
	 * @throws SQLException
	 */
	void deleteCustomer(int customerNumber) throws SQLException;

	/**
	 * Makes a list of all customerNumbers in the database. Primarely used to
	 * populate a Combobox for use in the AddOrderFrame.
	 *
	 * @return a list of customers in the database, represented by their
	 *         customerNumber as objects in this list.
	 * @throws to SQLException for any errors.
	 */
	List<Integer> getCustomerNumbers() throws SQLException;

	/**********************
	 * * EMPLOYEE FUNCTIONS * *
	 **********************/
	/**
	 * Method for retrieving a list of employees from database it selects all
	 * employees without any applied filter.
	 * 
	 * @return List of Customer objects.
	 * @throws SQLException
	 */
	List<Employee> getEmployees() throws SQLException;

	/**
	 * Method for retrieving all employee numbers.
	 * 
	 * @return List of employee numbers.
	 * @throws SQLException
	 */
	List<Integer> getEmployeeNumber() throws SQLException;

	/**
	 * Method for retrieving an employee from the database filtered by employee
	 * number.
	 * 
	 * @param employeeNumber Employee number.
	 * @return Employee Employee object.
	 * @throws SQLException
	 */
	Employee getEmployeeByNumber(int employeeNumber) throws SQLException;

	/**
	 * Method for retrieving the last employee number.
	 * 
	 * @return lastEmployeeNumber Employee number.
	 * @throws SQLException
	 */
	int getLastEmployeeNumber() throws SQLException;

	/**
	 * Method for adding a new employee to the database.
	 * 
	 * @param employeeNumber Employee number.
	 * @param lastName       Last name.
	 * @param firstName      First name.
	 * @param extension      Extension number of the employee.
	 * @param email          Email address.
	 * @param officeCode     Office code.
	 * @param reportsTo      Employee number the employee reports to.
	 * @param jobTitle       Job title.
	 * @throws SQLException
	 */
	void addEmployee(int employeeNumber, String lastName, String firstName, String extension, String email,
			String officeCode, int reportsTo, String jobTitle) throws SQLException;

	/**
	 * Method for updating an existing employee in the database.
	 * 
	 * @param employeeNumber Employee number.
	 * @param lastName       Last name.
	 * @param firstName      First name.
	 * @param extension      Extension number of the employee.
	 * @param email          Email address.
	 * @param officeCode     Office code.
	 * @param reportsTo      Employee number the employee reports to.
	 * @param jobTitle       Job title.
	 * @throws SQLException
	 */
	void updateEmployee(int employeeNumber, String lastName, String firstName, String extension, String email,
			String officeCode, int reportsTo, String jobTitle) throws SQLException;

	/**
	 * Method for deleting an employee in the database based on the employee number.
	 * 
	 * @param employeeNumber Employee number.
	 * @throws SQLException
	 */
	void deleteEmployee(int employeeNumber) throws SQLException;

	/**********************
	 * * ORDER FUNCTIONS * *
	 **********************/

	/**
	 * @author serhatberg
	 */

	/**
	 * Adds order to database
	 * 
	 * @param orderNumber
	 * @param orderDate
	 * @param requiredDate
	 * @param shippedDate
	 * @param status
	 * @param comments
	 * @param customerNumber
	 * @throws SQLException
	 */

	// DatabaseInterface
	void addOrder(int orderNumber, Date orderDate, Date requiredDate, Date shippedDate, String status, String comments,
			int customerNumber) throws SQLException;

	void addOrder(int orderNumber, String orderDate, String requiredDate, String shippedDate, String status,
			String comments, int customerNumber) throws SQLException;

	/**
	 * Makes a list of all orders from the database.
	 * 
	 * @return list populated with the information retrieved from database.
	 *         Primarely used to display the products in a table in ViewOrdersFrame
	 * @throws to SQLException for any errors.
	 */
	List<Order> getOrders() throws SQLException;

	/**
	 * 
	 * /** Method for retrieving a list of orders from the database associated with
	 * a specific customer number.
	 * 
	 * @param customerNumber The customer number to filter orders by.
	 * @return list of Order objects filtered by customer number.
	 * @throws SQLException
	 */
	List<Order> getOrdersByCustomerNumber(int customerNumber) throws SQLException;
	// Update Order Status

	/**
	 * Makes a list of all OrderNumbers in the database. Primarely used to populate
	 * a Combobox for use in the UpdateOrderStatusFrame.
	 *
	 * @return a list of orderNumbers in the database, represented by their
	 *         orderNumbers as objects in this list.
	 * @throws to SQLException for any errors.
	 */
	List<Integer> getOrderNumbers() throws SQLException;

	/**
	 * Method for updating the status of an existing order in the database.
	 * 
	 * @param orderNumber The order number of the order to be updated.
	 * @param newStatus   The new status to set for the order.
	 * @throws SQLException
	 */
	void updateOrderStatus(int orderNumber, String newStatus) throws SQLException;

	/**********************
	 * * PRODUCT FUNCTIONS * *
	 **********************/

	/**
	 * @author eliasronningen
	 */

	/**
	 * Makes a list of all products from the database.
	 * 
	 * @return list populated with the information retrieved from database.
	 *         Primarely used to display the products in a table in
	 *         ViewProductsFrame
	 * @throws to SQLException for any errors.
	 */
	List<Products> getProducts() throws SQLException;

	/**
	 * Makes a list of all ProductNames in the database. Primarely used to populate
	 * a Combobox for use in the UpdateStockFrame.
	 *
	 * @return a list of Products in the database, represented by their productNames
	 *         as objects in this list.
	 * @throws to SQLException for any errors.
	 */
	List<String> getProductName() throws SQLException;

	/**********************
	 * * OFFICE FUNCTIONS * *
	 **********************/

	/**
	 * Method for retrieving office codes from the database.
	 * 
	 * @return List of office codes.
	 * @throws SQLException
	 */
	List<String> getOfficeCode() throws SQLException;

	/**********************
	 * * PAYMENTS FUNCTIONS * *
	 **********************/

	/**
	 * @author serhatberg
	 */

	/**
	 * Makes a list of all payments from the database.
	 * 
	 * @return A list populated with the information retrieved from the database,
	 *         primarily used to display the payments in a table in PaymentsFrame.
	 * @throws SQLException If any database errors occur.
	 */
	List<Payments> getPayments() throws SQLException;

	/**
	 * Makes a list of all payment dates in the database. Primarily used to populate
	 * a Combobox for use in viewing payments by date.
	 *
	 * @return A list of payment dates in the database, represented by their dates
	 *         as objects in this list.
	 * @throws SQLException If any database errors occur.
	 */
	List<String> getPaymentDates() throws SQLException;

	/**
	 * Method for adding a new payment to the database.
	 * 
	 * @param customerNumber The customer number to whom the payment belongs.
	 * @param checkNumber    A unique check number for the payment.
	 * @param paymentDate    The date of the payment.
	 * @param amount         The amount paid.
	 * @throws SQLException If any database errors occur.
	 */
	void addPayment(int customerNumber, String checkNumber, String paymentDate, float amount) throws SQLException;

	/**********************
	 * * FILEMANAGER FUNCTIONS * *
	 **********************/

	/**
	 * @author eliasronningen
	 */

	/**
	 * Makes a list of all tables in the database. Primarely used to populate a
	 * Combobox for use with FileManagerFrame, where it lets users pick - whichever
	 * table they want to save data from.
	 *
	 * @return a list of tables in the database, represented by their names as
	 *         objects in this list.
	 * @throws to SQLException for any errors.
	 */
	List<String> getTableNames() throws SQLException;

}
