package view;

/**
 * @author Migle Adomonyte
 * 
 * This class represents a new frame when a user searches for a customer. 
 * The frame consists of a filled out form the user can edit.  
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.DatabaseHelper;
import model.Customer;

public class UpdateCustomerDetailsFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private DatabaseHelper databaseHelper = new DatabaseHelper();
	private JComboBox<Integer> salesRepEmployeeComboBox = new JComboBox<Integer>();
	WarningMessages warningMessage = new WarningMessages();

	/**
	 * Constructor for creating a frame for updating the customer information. 
	 * 
	 * @param customer The customer information to be displayed. 
	 */
	public UpdateCustomerDetailsFrame(Customer customer) {
			// Create a new JFrame. 
		setTitle("Update Customer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 400);
		
		// Create a new panel for updating customer information. 
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(248,249,250));
		contentPanel.setLayout(new GridLayout(0, 2, 10, 0)); 
		
        // Add padding around the content. 
        contentPanel.setBorder(new EmptyBorder(20, 40, 20, 40)); 
		
	    // Add a header. 
        JLabel headerLabel = new JLabel("Update Customer");
        headerLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        contentPanel.add(headerLabel);
        
		// Empty space. 
        contentPanel.add(new JLabel(""));
        
        // Add subtext. 
        JLabel subtextLabel = new JLabel("Modify the desired fields");
        subtextLabel.setForeground(Color.GRAY);
        contentPanel.add(subtextLabel);
        subtextLabel.setBorder(new EmptyBorder(0, 0, 20, 0)); 
        
		// Empty space. 
        contentPanel.add(new JLabel(""));
		
		// Add labels and text fields. 
		contentPanel.add(new JLabel("Customer Name"));
        JTextField customerNameTextField = new JTextField(customer.getCustomerName());
        contentPanel.add(customerNameTextField);
        
		contentPanel.add(new JLabel("Contact Last Name"));
        JTextField lastNameTextField = new JTextField(customer.getContactLastName());
        contentPanel.add(lastNameTextField);
        
		contentPanel.add(new JLabel("Contact First Name"));
        JTextField firstNameTextField = new JTextField(customer.getContactFirstName());
        contentPanel.add(firstNameTextField);
        
        contentPanel.add(new JLabel("Phone"));
        JTextField phoneTextField = new JTextField(customer.getPhone());
        contentPanel.add(phoneTextField);
        
        contentPanel.add(new JLabel("Address Line 1"));
        JTextField address1TextField = new JTextField(customer.getAddressLine1());
        contentPanel.add(address1TextField);
        
        contentPanel.add(new JLabel("Address Line 2"));
        JTextField address2TextField = new JTextField(customer.getAddressLine2());
        contentPanel.add(address2TextField);
        
        contentPanel.add(new JLabel("City"));
        JTextField cityTextField = new JTextField(customer.getCity());
        contentPanel.add(cityTextField);
        
        contentPanel.add(new JLabel("State"));
        JTextField stateTextField = new JTextField(customer.getState());
        contentPanel.add(stateTextField);
        
        contentPanel.add(new JLabel("Postal Code"));
        JTextField postalCodeTextField = new JTextField(customer.getPostalCode());
        contentPanel.add(postalCodeTextField);
        
        contentPanel.add(new JLabel("Country"));
        JTextField countryTextField = new JTextField(customer.getCountry());
        contentPanel.add(countryTextField);
        
        contentPanel.add(new JLabel("Sales Rep Employee Number"));
        try {
            List<Integer> employeeNumbers = databaseHelper.getEmployeeNumber();
            for (Integer employeeNumber : employeeNumbers) {
            	salesRepEmployeeComboBox.addItem(employeeNumber);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }  
        contentPanel.add(salesRepEmployeeComboBox);
        
        contentPanel.add(new JLabel("Credit Limit"));
        JTextField creditLimitTextField = new JTextField(String.valueOf(customer.getCreditLimit()));
        contentPanel.add(creditLimitTextField);
        
		// Empty space. 
		contentPanel.add(new JLabel(""));
        
        // Button for updating customer information. 
		JButton updateCustomerButton = new JButton("Update Customer");
		updateCustomerButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
		// Action listener for the "Update Customer" button.
		updateCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Retrieve input from the fields. 
					String newCustomerName = customerNameTextField.getText();
					String newLastName = lastNameTextField.getText();
					String newFirstName = firstNameTextField.getText();
					String newPhone = phoneTextField.getText();
					String newAddress1 = address1TextField.getText();
					String newAddress2 = address2TextField.getText();
					String newCity = cityTextField.getText();
					String newState = stateTextField.getText();
					String newPostalCode = postalCodeTextField.getText();
					String newCountry = countryTextField.getText();
					int newEmployeeNumber = Integer.parseInt(salesRepEmployeeComboBox.getSelectedItem().toString());
					double newCreditLimit = Double.parseDouble(creditLimitTextField.getText());
					
					// Update customer with the provided user input. 
					databaseHelper.updateCustomer(
							customer.getCustomerNumber(),
							newCustomerName, 
							newLastName, 
							newFirstName, 
							newPhone, 
							newAddress1, 
							newAddress2, 
							newCity, 
							newState, 
							newPostalCode, 
							newCountry, 
							newEmployeeNumber, 
							newCreditLimit);
					// Display the message and close the frame. 
					warningMessage.displayMessage("The customer has been successfully updated!");
					
        			dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
					warningMessage.displayMessage("Something went wrong!");
				}
			}
		});
		contentPanel.add(updateCustomerButton);
		
		// Add a scroll bar on the right. 
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);
        
        setVisible(true);
        
	}
}
