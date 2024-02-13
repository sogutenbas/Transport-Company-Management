package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

/**
 * @author Migle Adomonyte
 * 
 * This class represents a frame for deleting an employee from the database. 
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.DatabaseHelper;
import model.Employee;

public class DeleteEmployeeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private DatabaseHelper databaseHelper = new DatabaseHelper();
	private JTextField employeeNumberTextField;
	WarningMessages warningMessage = new WarningMessages();

	/**
	 * Constructor for creating DeleteEmployeeFrame. 
	 */
	public DeleteEmployeeFrame() {
		// Create a new JFrame. 
		setTitle("Delete Employee");
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
        JLabel subtextLabel = new JLabel("Enter the employee number to delete");
        subtextLabel.setForeground(Color.GRAY);
        contentPanel.add(subtextLabel);
        
		// Create form labels and text fields. 
		JLabel lblEmployeeNumber = new JLabel("Employee Number");
		lblEmployeeNumber.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblEmployeeNumber);
		
		employeeNumberTextField = new JTextField();
		employeeNumberTextField.setColumns(10);
		contentPanel.add(employeeNumberTextField);
		
        JButton searchCustomer = new JButton("Delete Employee");
        searchCustomer.setFont(new Font("SansSerif", Font.PLAIN, 14));
		// Action listener for the "Search" button.
        searchCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		// Create an Employee object for storing data about the customer.
        		try {
					Employee employee = databaseHelper.getEmployeeByNumber(Integer.parseInt(employeeNumberTextField.getText()));
					
					if (employee != null) {
						// Create a confirmation window. 
						int confirmation = JOptionPane.showConfirmDialog(
								null, 
								"Are you sure you want to delete this employee?", 
								"Confirmation", 
								JOptionPane.YES_NO_OPTION);

                        // If user confirms the action, an employee is deleted. 
                        if (confirmation == JOptionPane.YES_OPTION) {
                        	databaseHelper.deleteEmployee(employee.getEmployeeNumber());
                        	// Display the message and close the frame. 
                        	warningMessage.displayMessage("Employee has been successfully deleted!");
                        }
                        dispose();
					}
					else {
						warningMessage.displayMessage("Employee not found!");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					warningMessage.displayMessage("Error: Unable to delete the Employee.");
				}
            }
        });
        contentPanel.add(searchCustomer);
		
		
        add(contentPanel);
        
		setVisible(true);
	}
}
