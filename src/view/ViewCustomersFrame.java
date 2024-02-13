package view;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.util.List;

import controller.DatabaseHelper;

import model.Customer;

/**
 * @author Migle Adomonyte
 * 
 * This class represents a table of all customers in the database. 
 */

public class ViewCustomersFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private DatabaseHelper databaseHelper = new DatabaseHelper();
	
	/**
	 * Constructor for creating a new frame that shows a table of all customers. 
	 */
	public ViewCustomersFrame() {
		// Create a new JFrame. 
		setTitle("All Customers");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
		
		// Create a JPanel. 
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(248,249,250));
		setContentPane(panel);
		
		// Create a String Array for storing column names. 
		String[] columnNames = {"CustomerNumber", "CustomerName", "ContactLastName", "ContactFirstName", 
				"Phone", "AddressLine1", "AddressLine2", "City", "State", "PostalCode", "Country", 
				"SalesRepEmployeeNumber", "CreditLimit"}; 
		
		// Fetch customers and store in ArrayList. 
		List<Customer> customers;
		try {
			customers = databaseHelper.getCustomers();
		}catch(SQLException e) {
            JOptionPane.showMessageDialog(this, "Error retrieving data from the database." + e.getMessage(), 
            		"Database Error", JOptionPane.ERROR_MESSAGE);
            return;
		}
		
		// Convert List<Customer> to a 2D Array. 
		Object[][] customerData = new Object[customers.size()][columnNames.length];
	    for (int i = 0; i < customers.size(); i++) {
	    	// Get data from List<Customer>.
            Customer customer = customers.get(i);
            // Assign values to the 2D Array. 
            customerData[i][0] = customer.getCustomerNumber();
            customerData[i][1] = customer.getCustomerName();
            customerData[i][2] = customer.getContactLastName();
            customerData[i][3] = customer.getContactFirstName();
            customerData[i][4] = customer.getPhone();
            customerData[i][5] = customer.getAddressLine1();
            customerData[i][6] = customer.getAddressLine2();
            customerData[i][7] = customer.getCity();
            customerData[i][8] = customer.getState();
            customerData[i][9] = customer.getPostalCode();
            customerData[i][10] = customer.getCountry();
            customerData[i][11] = customer.getSalesRepEmployeeNumber();
            customerData[i][12] = customer.getCreditLimit();
        }
		
		// Create JTable. 
        JTable table = new JTable(customerData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JTableHeader header = table.getTableHeader();
        // Set table header. 
        header.setFont(header.getFont().deriveFont(Font.BOLD, 12));
        // Add the scrollpane. 
        add(scrollPane, BorderLayout.CENTER);
        
        setVisible(true);
	}
}
