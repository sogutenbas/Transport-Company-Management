package view;

/**
 * @author Migle Adomonyte
 * 
 * This class represents a frame for updating an existing customer in the database. 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.DatabaseHelper;
import model.Customer;

public class UpdateCustomerFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private DatabaseHelper databaseHelper = new DatabaseHelper();
	private JTextField customerNumberTextField;
	WarningMessages warningMessage = new WarningMessages();

	/**
	 * Constructor for creating UpdateCustomerFrame. 
	 */
	public UpdateCustomerFrame() {
		// Create a new JFrame. 
		setTitle("Update Customer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 400);
		setResizable(false);
		
		// Create a new JPanel for storing components. 
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(248,249,250));
	    contentPanel.setLayout(new GridLayout(0, 1)); 
	    
        // Add padding around the content. 
        contentPanel.setBorder(new EmptyBorder(100, 120, 100, 120)); 
        
        // Add subtext. 
        JLabel subtextLabel = new JLabel("Enter the customer number for update");
        subtextLabel.setForeground(Color.GRAY);
        contentPanel.add(subtextLabel);
	    
		// Create form labels and text fields. 
		JLabel lblCustomerNumber = new JLabel("Customer Number");
		lblCustomerNumber.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblCustomerNumber);
		
		customerNumberTextField = new JTextField();
		customerNumberTextField.setColumns(10);
		contentPanel.add(customerNumberTextField);
		
        JButton searchCustomer = new JButton("Search");
        searchCustomer.setFont(new Font("SansSerif", Font.PLAIN, 14));
		// Action listener for the "Search" button.
        searchCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		// Create a Customer object for storing data about the customer.
            		Customer customer = databaseHelper.getCustomerByNumber(Integer.parseInt(customerNumberTextField.getText()));
            		
            		// If statement for checking that the customer exists in the database. 
            		if (customer != null) {
            			new UpdateCustomerDetailsFrame(customer);
            		}
            		else {
            			warningMessage.displayMessage("Customer not found!");
            		}
            	}catch(SQLException e1) {
					e1.printStackTrace();
					warningMessage.displayMessage("Something went wrong!");
            	}
            }
        });
        contentPanel.add(searchCustomer);
        
        add(contentPanel);
        
		setVisible(true);
	}
}

