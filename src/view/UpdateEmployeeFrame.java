package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.DatabaseHelper;
import model.Employee;

/**
 * @author Migle Adomonyte
 * 
 * This class represents a frame for updating an existing employee in the database. 
 */

public class UpdateEmployeeFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private DatabaseHelper databaseHelper = new DatabaseHelper();
	private JTextField employeeNumberTextField;
	WarningMessages warningMessage = new WarningMessages();
	
	/**
	 * Constructor for creating UpdateEmployeeFrame. 
	 */
	public UpdateEmployeeFrame() {
		// Create a new JFrame. 
		setTitle("Update Employee");
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
        JLabel subtextLabel = new JLabel("Enter the employee number for update");
        subtextLabel.setForeground(Color.GRAY);
        contentPanel.add(subtextLabel);
	    
		// Create form labels and text fields. 
		JLabel lblEmployeeNumber = new JLabel("Employee Number");
		lblEmployeeNumber.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblEmployeeNumber);
		
		employeeNumberTextField = new JTextField();
		employeeNumberTextField.setColumns(10);
		contentPanel.add(employeeNumberTextField);
		
        JButton searchEmployee = new JButton("Search");
        searchEmployee.setFont(new Font("SansSerif", Font.PLAIN, 14));
		// Action listener for the "Search" button.
        searchEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		// Create an Employee object for storing data about the employee.
            		Employee employee = databaseHelper.getEmployeeByNumber(Integer.parseInt(employeeNumberTextField.getText()));
            		
            		// If statement for checking that the employee exists in the database. 
            		if (employee != null) {
            			new UpdateEmployeeDetailsFrame(employee);
            		}
            		else {
            			warningMessage.displayMessage("Employee not found!");
            		}
            	}catch(SQLException e1) {
					e1.printStackTrace();
					warningMessage.displayMessage("Something went wrong!");
            	}
            }
        });
        contentPanel.add(searchEmployee);
        
        add(contentPanel);
        
		setVisible(true);
	}
}
