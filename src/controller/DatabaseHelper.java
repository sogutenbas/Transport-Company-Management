package controller;

import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.*;

public class DatabaseHelper implements DatabaseInterface {

	// JDBC driver name and database URL.
	private final String DB_URL = "jdbc:mysql://localhost/classicmodels?autoReconnect=true&useSSL=false";

	// Database credentials.
	private static final String USERNAME = "student";
	private static final String PASSWORD = "student";

	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	/**
	 * Based on provided ExampleApp Establishes a connection to database and permits
	 * execution of SQL queries.
	 *
	 * @throws to SQLException for any errors.
	 */
	@Override
	public void open() throws SQLException {
		try {
			// Establish connection.
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			// Create statement that will be used for executing SQL queries.
			statement = connection.createStatement();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void close() throws SQLException {
		try {
			statement.close();
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void test() throws SQLException {
		try {
			String sql = "SELECT * FROM customers";
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				System.out.println(resultSet.getInt("customerNumber") + resultSet.getString("customerName"));
			}

			resultSet.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	/**********************
	 * * CUSTOMER FUNCTIONS * *
	 **********************/

	@Override
	public List<Customer> getCustomers() throws SQLException {
		// Create a list of Customers.
		ArrayList<Customer> customers = new ArrayList<Customer>();

		// Open the database connection.
		this.open();

		try {
			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("SELECT * FROM customers");

			// Execute the SQL statement.
			resultSet = preparedStatement.executeQuery();

			// A while loop for collecting results and creating Customer objects.
			while (resultSet.next()) {
				int customerNumber = resultSet.getInt("customerNumber");
				String customerName = resultSet.getString("customerName");
				String contactLastName = resultSet.getString("contactLastName");
				String contactFirstName = resultSet.getString("contactFirstName");
				String phone = resultSet.getString("phone");
				String addressLine1 = resultSet.getString("addressLine1");
				String addressLine2 = resultSet.getString("addressLine2");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String postalCode = resultSet.getString("postalCode");
				String country = resultSet.getString("country");
				int salesRepEmployeeNumber = resultSet.getInt("salesRepEmployeeNumber");
				double creditLimit = resultSet.getDouble("creditLimit");

				Customer current = new Customer(customerNumber, customerName, contactLastName, contactFirstName, phone,
						addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber,
						creditLimit);

				// Add the Customer object to the Customer ArrayList.
				customers.add(current);
			}
			// Close the database connection.
			close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return customers;
	}

	@Override
	public Customer getCustomerByNumber(int customerNumber) throws SQLException {
		// Variable for storing the Customer object.
		Customer customer = null;

		// Open the database connection.
		this.open();

		try {
			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE customerNumber=?");

			// Set the parameter values for the SQL statement.
			preparedStatement.setInt(1, customerNumber);

			// Execute the SQL statement.
			resultSet = preparedStatement.executeQuery();

			// Check that the results exist.
			if (resultSet.next()) {
				String customerName = resultSet.getString("customerName");
				String contactLastName = resultSet.getString("contactLastName");
				String contactFirstName = resultSet.getString("contactFirstName");
				String phone = resultSet.getString("phone");
				String addressLine1 = resultSet.getString("addressLine1");
				String addressLine2 = resultSet.getString("addressLine2");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String postalCode = resultSet.getString("postalCode");
				String country = resultSet.getString("country");
				int salesRepEmployeeNumber = resultSet.getInt("salesRepEmployeeNumber");
				double creditLimit = resultSet.getDouble("creditLimit");

				// Create a Customer object based on the data.
				customer = new Customer(customerNumber, customerName, contactLastName, contactFirstName, phone,
						addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber,
						creditLimit);
			}

			// Close the database connection.
			close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return customer;
	}

	@Override
	public int getLastCustomerNumber() throws SQLException {
		int lastCustomerNumber = 0;

		// Open the database connection.
		open();

		try {
			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("SELECT MAX(customerNumber) FROM customers");

			// Execute the SQL statement.
			resultSet = preparedStatement.executeQuery();

			// Check that the results exist.
			if (resultSet.next()) {
				lastCustomerNumber = resultSet.getInt(1);
			}
			// Close the database connection.
			close();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return lastCustomerNumber;
	}

	@Override
	public void addCustomer(int customerNumber, String customerName, String contactLastName, String contactFirstName,
			String phone, String addressLine1, String addressLine2, String city, String state, String postalCode,
			String country, int salesRepEmployeeNumber, double creditLimit) throws SQLException {
		try {
			// Open the database connection.
			open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("INSERT INTO customers "
					+ "(customerNumber, customerName, contactLastName, contactFirstName, phone, "
					+ "addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			// Set the parameter values for the SQL statement.
			preparedStatement.setInt(1, customerNumber);
			preparedStatement.setString(2, customerName);
			preparedStatement.setString(3, contactLastName);
			preparedStatement.setString(4, contactFirstName);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, addressLine1);
			preparedStatement.setString(7, addressLine2);
			preparedStatement.setString(8, city);
			preparedStatement.setString(9, state);
			preparedStatement.setString(10, postalCode);
			preparedStatement.setString(11, country);
			preparedStatement.setInt(12, salesRepEmployeeNumber);
			preparedStatement.setDouble(13, creditLimit);

			// Execute the SQL statement.
			preparedStatement.executeUpdate();

			// Close the database connection.
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCustomer(int customerNumber, String customerName, String contactLastName, String contactFirstName,
			String phone, String addressLine1, String addressLine2, String city, String state, String postalCode,
			String country, int salesRepEmployeeNumber, double creditLimit) throws SQLException {
		try {
			// Open database connection.
			open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("UPDATE customers SET customerName=?, contactLastName=?, "
					+ "contactFirstName=?, phone=?, addressLine1=?, addressLine2=?, city=?, state=?, postalCode=?, "
					+ "country=?, salesRepEmployeeNumber=?, creditLimit=? WHERE customerNumber=?");

			// Set the parameter values for the SQL statement.
			preparedStatement.setString(1, customerName);
			preparedStatement.setString(2, contactLastName);
			preparedStatement.setString(3, contactFirstName);
			preparedStatement.setString(4, phone);
			preparedStatement.setString(5, addressLine1);
			preparedStatement.setString(6, addressLine2);
			preparedStatement.setString(7, city);
			preparedStatement.setString(8, state);
			preparedStatement.setString(9, postalCode);
			preparedStatement.setString(10, country);
			preparedStatement.setInt(11, salesRepEmployeeNumber);
			preparedStatement.setDouble(12, creditLimit);
			preparedStatement.setInt(13, customerNumber);

			// Execute the SQL statement.
			preparedStatement.executeUpdate();

			// Close the database connection.
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCustomer(int customerNumber) throws SQLException {
		try {
			// Open database connection.
			open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE customerNumber=?");

			// Set the parameter values for the SQL statement.
			preparedStatement.setInt(1, customerNumber);

			// Execute the SQL statement.
			preparedStatement.executeUpdate();

			// Close the database connection.
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Makes a list of all customerNumbers in the database. Primarely used to
	 * populate a Combobox for use in the AddOrderFrame.
	 *
	 * @return a list of customers in the database, represented by their
	 *         customerNumber as objects in this list.
	 * @throws to SQLException for any errors.
	 */

	@Override
	public List<Integer> getCustomerNumbers() throws SQLException {
		ArrayList<Integer> customerNumbers = new ArrayList<Integer>();
		this.open();
		try {
			preparedStatement = connection.prepareStatement("SELECT DISTINCT customerNumber FROM customers");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Integer fetchedCustomerNumbers = resultSet.getInt("customerNumber");
				if (fetchedCustomerNumbers != null) {

					customerNumbers.add(fetchedCustomerNumbers);
				}

			}
			return customerNumbers;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	/**********************
	 * * EMPLOYEE FUNCTIONS * *
	 **********************/

	@Override
	public List<Employee> getEmployees() throws SQLException {
		// Create a list of Customers.
		ArrayList<Employee> employees = new ArrayList<Employee>();

		try {
			// Open the database connection.
			this.open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("SELECT * FROM employees");

			// Execute the SQL statement.
			resultSet = preparedStatement.executeQuery();

			// A while loop for collecting results and creating Employee objects.
			while (resultSet.next()) {
				int employeeNumber = resultSet.getInt("employeeNumber");
				String lastName = resultSet.getString("lastName");
				String firstName = resultSet.getString("firstName");
				String extensions = resultSet.getString("extension");
				String email = resultSet.getString("email");
				String officeCode = resultSet.getString("officeCode");
				int reportsTo = resultSet.getInt("reportsTo");
				String jobTitle = resultSet.getString("jobTitle");

				Employee current = new Employee(employeeNumber, lastName, firstName, extensions, email, officeCode,
						reportsTo, jobTitle);

				// Add the Employee object to the Customer ArrayList.
				employees.add(current);
			}

			// Close the database connection.
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public List<Integer> getEmployeeNumber() throws SQLException {
		// Create list for storing employee numbers.
		List<Integer> employeeNumbers = new ArrayList<>();

		try {
			// Open database connection.
			open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("SELECT employeeNumber FROM employees");

			// Execute the SQL statement.
			resultSet = preparedStatement.executeQuery();

			// Iterate through the results and add the employee number to the list.
			while (resultSet.next()) {
				int employeeNumber = resultSet.getInt("employeeNumber");
				employeeNumbers.add(employeeNumber);
			}

			// Close the database connection.
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeNumbers;
	}

	@Override
	public Employee getEmployeeByNumber(int employeeNumber) throws SQLException {
		// Variable for storing the Customer object.
		Employee employee = null;

		try {
			// Open the database connection.
			this.open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE employeeNumber=?");

			// Set the parameter values for the SQL statement.
			preparedStatement.setInt(1, employeeNumber);

			// Execute the SQL statement.
			resultSet = preparedStatement.executeQuery();

			// Check that the results exist.
			if (resultSet.next()) {
				String lastName = resultSet.getString("lastName");
				String firstName = resultSet.getString("firstName");
				String extensions = resultSet.getString("extension");
				String email = resultSet.getString("email");
				String officeCode = resultSet.getString("officeCode");
				int reportsTo = resultSet.getInt("reportsTo");
				String jobTitle = resultSet.getString("jobTitle");

				// Create an Employee object based on the data.
				employee = new Employee(employeeNumber, lastName, firstName, extensions, email, officeCode, reportsTo,
						jobTitle);
			}

			// Close the database connection.
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public int getLastEmployeeNumber() throws SQLException {
		int lastEmployeeNumber = 0;

		try {
			// Open the database connection.
			open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("SELECT MAX(employeeNumber) FROM employees");

			// Execute the SQL statement.
			resultSet = preparedStatement.executeQuery();

			// Check that the results are not empty.
			if (resultSet.next()) {
				lastEmployeeNumber = resultSet.getInt(1);
			}
			// Close the database connection.
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastEmployeeNumber;
	}

	@Override
	public void addEmployee(int employeeNumber, String lastName, String firstName, String extension, String email,
			String officeCode, int reportsTo, String jobTitle) throws SQLException {
		try {
			// Open the database connection.
			open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("INSERT INTO employees "
					+ "(employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

			// Set the parameter values for the SQL statement.
			preparedStatement.setInt(1, employeeNumber);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, extension);
			preparedStatement.setString(5, email);
			preparedStatement.setString(6, officeCode);
			preparedStatement.setInt(7, reportsTo);
			preparedStatement.setString(8, jobTitle);

			// Execute the SQL statement.
			preparedStatement.executeUpdate();

			// Close the database connection.
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateEmployee(int employeeNumber, String lastName, String firstName, String extension, String email,
			String officeCode, int reportsTo, String jobTitle) throws SQLException {
		try {
			// Open database connection.
			open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("UPDATE employees SET lastName=?, "
					+ "firstName=?, extension=?, email=?, officeCode=?, reportsTo=?, jobTitle=? WHERE employeeNumber=?");

			// Set the parameter values for the SQL statement.
			preparedStatement.setString(1, lastName);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, extension);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, officeCode);
			preparedStatement.setInt(6, reportsTo);
			preparedStatement.setString(7, jobTitle);
			preparedStatement.setInt(8, employeeNumber);

			// Execute the SQL statement.
			preparedStatement.executeUpdate();

			// Close the database connection.
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteEmployee(int employeeNumber) throws SQLException {
		try {
			// Open database connection.
			open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE employeeNumber=?");

			// Set the parameter values for the SQL statement.
			preparedStatement.setInt(1, employeeNumber);

			// Execute the SQL statement.
			preparedStatement.executeUpdate();

			// Close the database connection.
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**********************
	 * * ORDER FUNCTIONS * *
	 **********************/

	/**
	 * Makes a list of all Orders from the database.
	 * 
	 * @return list populated with the information retrieved from database.
	 *         Primarely used to display the products in a table in ViewOrdersFrame
	 * @throws to SQLException for any errors.
	 */

	@Override
	public List<Order> getOrders() throws SQLException {
		ArrayList<Order> orders = new ArrayList<Order>();
		this.open();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM orders");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int orderNumber = resultSet.getInt("orderNumber");
				Date orderDate = resultSet.getDate("orderDate");
				Date requiredDate = resultSet.getDate("requiredDate");
				Date shippedDate = resultSet.getDate("shippedDate");
				String status = resultSet.getString("status");
				String comments = resultSet.getString("comments");
				int customerNumber = resultSet.getInt("customerNumber");

				Order current = new Order(orderNumber, orderDate, requiredDate, shippedDate, status, comments,
						customerNumber);
				orders.add(current);
			}
			return orders;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	@Override
	public void addOrder(int orderNumber, String orderDate, String requiredDate, String shippedDate, String status,
			String comments, int customerNumber) throws SQLException {
		try {
			// Open the database connection.
			open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement(
					"INSERT INTO orders (orderNumber, orderDate, requiredDate, shippedDate, status, comments, customerNumber) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?)");

			// Set the parameter values for the SQL statement.
			preparedStatement.setInt(1, orderNumber);
			preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDate.parse(orderDate)));
			preparedStatement.setDate(3, java.sql.Date.valueOf(LocalDate.parse(requiredDate)));
			preparedStatement.setDate(4,
					(shippedDate != null && !shippedDate.isEmpty())
							? java.sql.Date.valueOf(LocalDate.parse(shippedDate))
							: null);
			preparedStatement.setString(5, status);
			preparedStatement.setString(6, comments);
			preparedStatement.setInt(7, customerNumber);

			// Execute the SQL statement.
			preparedStatement.executeUpdate();

			// Close the database connection.
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addOrder(int orderNumber, java.sql.Date orderDate, java.sql.Date requiredDate,
			java.sql.Date shippedDate, String status, String comments, int customerNumber) throws SQLException {
		try {
			// Open the database connection.
			open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement(
					"INSERT INTO orders (orderNumber, orderDate, requiredDate, shippedDate, status, comments, customerNumber) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?)");

			// Set the parameter values for the SQL statement.
			preparedStatement.setInt(1, orderNumber);
			preparedStatement.setDate(2, orderDate);
			preparedStatement.setDate(3, requiredDate);
			preparedStatement.setDate(4, shippedDate);
			preparedStatement.setString(5, status);
			preparedStatement.setString(6, comments);
			preparedStatement.setInt(7, customerNumber);

			// Execute the SQL statement.
			preparedStatement.executeUpdate();

			// Close the database connection.
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Order> getOrdersByCustomerNumber(int customerNumber) throws SQLException {
		List<Order> orders = new ArrayList<>();
		// Open the database connection.
		this.open();

		try {
			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE customerNumber=?");

			// Set the parameter values for the SQL statement.
			preparedStatement.setInt(1, customerNumber);

			// Execute the SQL statement.
			resultSet = preparedStatement.executeQuery();

			// A while loop for collecting results and creating Order objects.
			if (resultSet.next()) {
				int orderNumber = resultSet.getInt("orderNumber");
				Date orderDate = resultSet.getDate("orderDate");
				Date requiredDate = resultSet.getDate("requiredDate");
				Date shippedDate = resultSet.getDate("shippedDate");
				String status = resultSet.getString("status");
				String comments = resultSet.getString("comments");

				// Create an Order object based on the data.
				Order order = new Order(orderNumber, orderDate, requiredDate, shippedDate, status, comments,
						customerNumber);

				// Add the Order object to the orders ArrayList.
				orders.add(order);
			}
			// Close the database connection.
			close();

		} catch (SQLException exc) {
			exc.printStackTrace();
		}

		return orders;
	}

	@Override
	public void updateOrderStatus(int orderNumber, String newStatus) throws SQLException {
		try {
			// Open database connection.
			open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("UPDATE orders SET status = ? WHERE orderNumber = ?");

			// Set the parameter values for the SQL statement.
			preparedStatement.setString(1, newStatus);
			preparedStatement.setInt(2, orderNumber);

			// Execute the SQL statement.
			preparedStatement.executeUpdate();

			// Close the database connection.
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Makes a list of all OrderNumbers in the database. Primarely used to populate
	 * a Combobox for use in the UpdateOrderStatusFrame.
	 *
	 * @return a list of orderNumbers in the database, represented by their
	 *         orderNumbers as objects in this list.
	 * @throws to SQLException for any errors.
	 */

	@Override
	public List<Integer> getOrderNumbers() throws SQLException {
		ArrayList<Integer> orderNumbers = new ArrayList<Integer>();
		this.open();
		try {
			preparedStatement = connection.prepareStatement("SELECT DISTINCT orderNumber FROM orders");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Integer fetchedOrderNumber = resultSet.getInt("orderNumber");
				if (fetchedOrderNumber != null) {

					orderNumbers.add(fetchedOrderNumber);
				}

			}
			return orderNumbers;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	public String getOrderStatusByOrderNumber(int selectedOrderNumber) throws SQLException {
		String status = null;
		try {
			open();
			preparedStatement = connection.prepareStatement("SELECT status FROM orders WHERE orderNumber = ?");
			preparedStatement.setInt(1, selectedOrderNumber);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				status = resultSet.getString("status");
			}

			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	/**********************
	 * * PRODUCT FUNCTIONS * *
	 **********************/

	/**
	 * Makes a list of all products from the database.
	 * 
	 * @return list populated with the information retrieved from database.
	 *         Primarely used to display the products in a table in ProductsFrame
	 * @throws to SQLException for any errors.
	 */

	@Override
	public List<Products> getProducts() throws SQLException {
		ArrayList<Products> products = new ArrayList<Products>();
		this.open();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM products");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String productCode = resultSet.getString("productCode");
				String productName = resultSet.getString("productName");
				String productLine = resultSet.getString("productLine");
				String productScale = resultSet.getString("productScale");
				String productVendor = resultSet.getString("productVendor");
				String productDescription = resultSet.getString("productDescription");
				int quantityInStock = resultSet.getInt("quantityInStock");
				float buyPrice = resultSet.getFloat("buyPrice");
				float MSRP = resultSet.getFloat("MSRP");

				Products current = new Products(productCode, productName, productLine, productScale, productVendor,
						productDescription, quantityInStock, buyPrice, MSRP);
				products.add(current);
			}
			return products;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	/**
	 * Makes a list of all ProductNames in the database. Primarely used to populate
	 * a Combobox for use in the UpdateStockFrame.
	 *
	 * @return a list of Products in the database, represented by their productNames
	 *         as objects in this list.
	 * @throws to SQLException for any errors.
	 */

	@Override
	public List<String> getProductName() throws SQLException {
		ArrayList<String> productNames = new ArrayList<String>();
		this.open();
		try {
			preparedStatement = connection.prepareStatement("SELECT DISTINCT productName FROM products");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String fetchedProductName = resultSet.getString("productName");
				if (fetchedProductName != null) {

					productNames.add(fetchedProductName);
				}

			}
			return productNames;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	/**
	 * Finds the quantity of a product by finding the value in the column of the
	 * corresponding name
	 * 
	 * @param selectedProductName
	 * @return int quantity - value of quantityInStock
	 * @throws SQLException for any errors
	 */

	public int getQuantityByName(String selectedProductName) throws SQLException {
		int quantity = 0;
		try {
			open();
			preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE productName = ?");
			preparedStatement.setString(1, selectedProductName);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				quantity = resultSet.getInt("quantityInStock");
			}

			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quantity;
	}

	/**
	 * Method that updates the quantityInStock value of a desired product, based on
	 * user inputted integer and math operator
	 * 
	 * @param productName      of the product you want to update
	 * @param quantityToUpdate Value to add/subtract to existing quantity
	 * @param isAddition       Checks if you want to add to total, if not it
	 *                         subtracts
	 * @throws SQLException for any errors
	 */
	public void updateQuantityInStock(String productName, int quantityToUpdate, boolean isAddition)
			throws SQLException {
		try {
			open(); // Open connection

			// Determine the operation (addition or subtraction)
			String operator = (isAddition) ? "+" : "-";

			// Prepare the SQL statement to update the quantity in stock by adding or
			// subtracting
			String updateQuery = "UPDATE products SET quantityInStock = quantityInStock " + operator
					+ " ? WHERE productName = ?";
			preparedStatement = connection.prepareStatement(updateQuery);

			// Set the parameters in the prepared statement
			preparedStatement.setInt(1, quantityToUpdate);
			preparedStatement.setString(2, productName);

			// Execute the update statement
			preparedStatement.executeUpdate();
		} finally {
			close(); // Close the connection.
		}
	}

	/**
	 * Makes a list of all products from the database.
	 * 
	 * @return list populated with the information retrieved from database.
	 *         Primarely used to display the products in a table in ProductsFrame
	 * @throws to SQLException for any errors.
	 */

	@Override
	public List<Payments> getPayments() throws SQLException {
		ArrayList<Payments> payments = new ArrayList<Payments>();
		this.open();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM payments");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int customerNumber = resultSet.getInt("customerNumber");
				String checkNumber = resultSet.getString("checkNumber");
				Date paymentDate = resultSet.getDate("paymentDate");
				float amount = resultSet.getFloat("amount");

				Payments current = new Payments(customerNumber, checkNumber, paymentDate, amount);
				payments.add(current);
			}
			return payments;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	/**
	 * Makes a list of all ProductNames in the database. Primarely used to populate
	 * a Combobox for use in the UpdateStockFrame.
	 *
	 * @return a list of Products in the database, represented by their productNames
	 *         as objects in this list.
	 * @throws to SQLException for any errors.
	 */

	@Override
	public List<String> getPaymentDates() throws SQLException {
		ArrayList<String> paymentDates = new ArrayList<String>();
		this.open();
		try {
			preparedStatement = connection.prepareStatement("SELECT DISTINCT paymentDate FROM payments");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String fetchedPaymentDate = resultSet.getString("paymentDate");
				if (fetchedPaymentDate != null) {

					paymentDates.add(fetchedPaymentDate);
				}

			}
			return paymentDates;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	/**********************
	 * * OFFICE FUNCTIONS * *
	 **********************/
	@Override
	public List<String> getOfficeCode() throws SQLException {
		// Create list for storing employee numbers.
		List<String> officeCodes = new ArrayList<>();

		try {
			// Open database connection.
			open();

			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("SELECT officeCode FROM offices");

			// Execute the SQL statement.
			resultSet = preparedStatement.executeQuery();

			// Iterate through the results and add the officeCode to the list.
			while (resultSet.next()) {
				String officeCode = resultSet.getString("officeCode");
				officeCodes.add(officeCode);
			}

			// Close the database connection.
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return officeCodes;
	}

	/**********************
	 * * PAYMENTS FUNCTIONS * *
	 **********************/

	/**
	 * Retrieves all payments made by a specific customer from the database.
	 * 
	 * @param customerNumber The number of the customer.
	 * @return ResultSet containing the customer's payment details.
	 * @throws SQLException If a database error occurs.
	 */
	public ResultSet getPaymentsForCustomer(int customerNumber) throws SQLException {
		// Open the database connection.
		open();

		try {
			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement("SELECT * FROM payments WHERE customerNumber=?");

			// Set the parameter values for the SQL statement.
			preparedStatement.setInt(1, customerNumber);

			// Execute the SQL statement.
			return preparedStatement.executeQuery();

		} catch (Exception exc) {
			exc.printStackTrace(); // Replace with proper error handling
		} finally {
			// Close the database connection.
			close();
		}

		return null;
	}

	/**
	 * Retrieves a list of payment objects for a specific customer.
	 * 
	 * @param customerNumber The number of the customer.
	 * @return A list of Payments objects.
	 * @throws SQLException If a database error occurs.
	 */
	public List<Payments> getCustomerPayments(int customerNumber) throws SQLException {
		List<Payments> payments = new ArrayList<>();

		// SQL query to retrieve payments for the given customerNumber
		String query = "SELECT * FROM payments WHERE customerNumber = ?";

		try {
			this.open();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, customerNumber);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String checkNumber = resultSet.getString("checkNumber");
				Date paymentDate = resultSet.getDate("paymentDate");
				float amount = resultSet.getFloat("amount");

				Payments payment = new Payments(customerNumber, checkNumber, paymentDate, amount);
				payments.add(payment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return payments;
	}

	/**
	 * Method for adding a new payment to the database.
	 * 
	 * @param customerNumber Customer number the payment belongs to.
	 * @param checkNumber    Unique check number for the payment.
	 * @param paymentDate    Date of the payment.
	 * @param amount         Amount paid.
	 * @throws SQLException If a database error occurs.
	 */
	public void addPayment(int customerNumber, String checkNumber, String paymentDate, float amount)
			throws SQLException {
		// Convert the paymentDate from String to java.sql.Date
		java.sql.Date sqlPaymentDate = java.sql.Date.valueOf(paymentDate);

		// Open the database connection.
		open();

		try {
			// Prepare the SQL statement.
			preparedStatement = connection.prepareStatement(
					"INSERT INTO payments (customerNumber, checkNumber, paymentDate, amount) VALUES (?, ?, ?, ?)");

			// Set the parameter values for the SQL statement.
			preparedStatement.setInt(1, customerNumber);
			preparedStatement.setString(2, checkNumber);
			preparedStatement.setDate(3, sqlPaymentDate);
			preparedStatement.setFloat(4, amount);

			// Execute the SQL statement.
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			// Close the database connection.
			close();
		}
	}

	/**********************
	 * * FILEMANAGER FUNCTIONS * *
	 **********************/

	/**
	 * Makes a list of all tables in the database. Primarely used to populate a
	 * Combobox for use with FileManagerFrame, where it lets users pick - whichever
	 * table they want to save data from.
	 *
	 * @return a list of tables in the database, represented by their names as
	 *         objects in this list.
	 * @throws to SQLException for any errors.
	 */
	@Override
	public List<String> getTableNames() throws SQLException {
		List<String> tableNames = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement("SHOW TABLES");
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String tableName = resultSet.getString(1);
				tableNames.add(tableName);
			}
		}

		return tableNames;
	}

	/**
	 * Used to save entire tables to local files on users computer
	 * 
	 * @param tableName - Name of the desired table to save
	 * @param filePath  - The picked location to save the file
	 * @throws SQLException for any errors
	 */
	public void saveTableToFile(String tableName, String filePath) throws SQLException {
		try {
			open(); // Open connection

			// SELECT all rows from the desired table to use in storing.
			String selectQuery = "SELECT * FROM " + tableName;
			preparedStatement = connection.prepareStatement(selectQuery);
			resultSet = preparedStatement.executeQuery();

			// Results are then written to the file
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
				// Write column names as headers
				for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
					writer.write(resultSet.getMetaData().getColumnName(i) + "\t");
				}
				writer.write("\n");

				// Each row of the table gets a new line in the document.
				while (resultSet.next()) {
					for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
						writer.write(resultSet.getString(i) + "\t");
					}
					writer.write("\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(); // Close connection
		}
	}

	/**
	 * Execute SQL commands through the program, interracting directly with the
	 * database.
	 *
	 * @param sqlCommand the SQL command to execute
	 * @return Resultset containing the results of query, or null if the command
	 *         wasn't a SELECT statement.
	 * @throws SQLException for any errors.
	 */
	public boolean executeSQLCommand(String sqlCommand, Consumer<ResultSet> resultHandler) throws SQLException {
		try {
			open();

			boolean isSelect = sqlCommand.trim().toUpperCase().startsWith("SELECT");

			if (isSelect) {
				preparedStatement = connection.prepareStatement(sqlCommand);
				ResultSet resultSet = preparedStatement.executeQuery();

				// Resultset retrieved and ready for use in the FileManagerFrame.
				resultHandler.accept(resultSet);

				return true; // Return true for SELECT statements
			} else {
				statement = connection.createStatement();
				statement.executeUpdate(sqlCommand);
				return true; // Return true for non-SELECT statements
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false; // Return false in both instances if any errors occur.
		} finally {
			close(); // Close connection
		}
	}

	/**
	 * Used to display results of SQL SELECT statements
	 * 
	 * @param resultSet from query
	 */

	public void displayResultSet(ResultSet resultSet) {
		// Create a new frame to display the results
		JFrame resultFrame = new JFrame("SQL Query Result");
		resultFrame.setSize(1000, 600);

		// Use DefaultTableModel to populate the JTable
		DefaultTableModel tableModel = new DefaultTableModel();

		// Store any metaData for use in the textfield
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			// Adds the column info retrieved from the query to the table model
			for (int column = 1; column <= columnCount; column++) {
				tableModel.addColumn(metaData.getColumnName(column));
			}

			// Adds the data retrieved from the query to the table model
			while (resultSet.next()) {
				Object[] rowData = new Object[columnCount];
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
				}
				tableModel.addRow(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Create JTable with the DefaultTableModel
		JTable resultTable = new JTable(tableModel);

		// Add the table to a JScrollPane for scrolling if necessary
		JScrollPane scrollPane = new JScrollPane(resultTable);
		resultFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Make the frame visible
		resultFrame.setVisible(true);
	}

	/**
	 * Display the result set data in a new frame.
	 *
	 * @param rowDataList The list of Object arrays representing the result set
	 *                    data.
	 */
	public void displayResultSet(List<Object[]> rowDataList) {
		// Create a new frame to display the results
		JFrame resultFrame = new JFrame("SQL Query Result");
		resultFrame.setSize(1000, 600);

		// Use DefaultTableModel to populate the JTable
		DefaultTableModel tableModel = new DefaultTableModel();

		// Use the first row of data to add columns to the table model
		if (!rowDataList.isEmpty()) {
			Object[] firstRow = rowDataList.get(0);
			for (int columnIndex = 1; columnIndex <= firstRow.length; columnIndex++) {
				tableModel.addColumn("Column " + columnIndex);
			}
		}

		// Adds the data retrieved from the query to the table model
		for (Object[] rowData : rowDataList) {
			tableModel.addRow(rowData);
		}

		// Create JTable with the DefaultTableModel
		JTable resultTable = new JTable(tableModel);

		// Add the table to a JScrollPane for scrolling if necessary
		JScrollPane scrollPane = new JScrollPane(resultTable);
		resultFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Make the frame visible
		resultFrame.setVisible(true);
	}

}
