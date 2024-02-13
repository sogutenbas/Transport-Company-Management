package view;

/**
 * @author Migle Adomonyte
 * 
 * This class represents a frame for deleting a customer from the database. 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.DatabaseHelper;
import model.Customer;

public class DeleteCustomerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DatabaseHelper databaseHelper = new DatabaseHelper();
	private JTextField customerNumberTextField;
	WarningMessages warningMessage = new WarningMessages();
	
	/**
	 * Constructor for creating DeleteCustomerFrame. 
	 */
	public DeleteCustomerFrame() {
		// Create a new JFrame. 
		setTitle("Delete Customer");
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
        JLabel subtextLabel = new JLabel("Enter the customer number to delete");
        subtextLabel.setForeground(Color.GRAY);
        contentPanel.add(subtextLabel);
	    
		// Create form labels and text fields. 
		JLabel lblCustomerNumber = new JLabel("Customer Number");
		lblCustomerNumber.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblCustomerNumber);
		
		customerNumberTextField = new JTextField();
		customerNumberTextField.setColumns(10);
		contentPanel.add(customerNumberTextField);
		
        JButton searchCustomer = new JButton("Delete Customer");
        searchCustomer.setFont(new Font("SansSerif", Font.PLAIN, 14));
		// Action listener for the "Search" button.
        searchCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		// Create a Customer object for storing data about the customer.
        		try {
					Customer customer = databaseHelper.getCustomerByNumber(Integer.parseInt(customerNumberTextField.getText()));
					
					if (customer != null) {
						// Create a confirmation window. 
						int confirmation = JOptionPane.showConfirmDialog(
								null, 
								"Are you sure you want to delete this customer?", 
								"Confirmation", 
								JOptionPane.YES_NO_OPTION);

                        // If user confirms the action, a customer is deleted. 
                        if (confirmation == JOptionPane.YES_OPTION) {
                        	databaseHelper.deleteCustomer(customer.getCustomerNumber());
                        	// Display the message and close the frame. 
                        	warningMessage.displayMessage("Customer has been successfully deleted!");
                        }
                        dispose();
					}
					else {
						warningMessage.displayMessage("Customer not found!");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					warningMessage.displayMessage("Error: Unable to delete the customer.");
				}
            }
        });
        contentPanel.add(searchCustomer);
        
        add(contentPanel);
        
		setVisible(true);
	}
}
