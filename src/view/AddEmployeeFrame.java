package view;

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

/**
 * @author Migle Adomonyte
 * 
 * This class represents a frame for adding a new employee to the database. 
 */

public class AddEmployeeFrame extends JFrame{
	private static final long serialVersionUID = 1L;	
	private DatabaseHelper databaseHelper = new DatabaseHelper();
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField extensionTextField;
	private JTextField emailTextField;
	private JComboBox<String> officeCodeComboBox = new JComboBox<>();
	private JComboBox<Integer> reportsToComboBox = new JComboBox<Integer>();
	private JTextField jobTitleTextField;
	WarningMessages warningMessage = new WarningMessages();
	
	/**
	 * Constructor for creating AddEmployeeFrame. 
	 */
	public AddEmployeeFrame() {
		// Create a new JFrame. 
		setTitle("Add Employee");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 400);
		
		// Create a new JPanel for storing components. 
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(248,249,250));
	    contentPanel.setLayout(new GridLayout(0, 2, 10, 10)); 
	    
        // Add padding around the content. 
        contentPanel.setBorder(new EmptyBorder(20, 40, 20, 40)); 
	    
	    // Add a header. 
        JLabel headerLabel = new JLabel("Add a New Employee");
        headerLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        contentPanel.add(headerLabel);
        
		// Empty space. 
        contentPanel.add(new JLabel(""));
        
        // Add subtext. 
        JLabel subtextLabel = new JLabel("* Indicates a required field");
        subtextLabel.setForeground(Color.GRAY);
        contentPanel.add(subtextLabel);
        subtextLabel.setBorder(new EmptyBorder(0, 0, 20, 0)); 
        
		// Empty space. 
        contentPanel.add(new JLabel(""));
        
		// Create form labels and text fields. 
        
		JLabel lblLastName = new JLabel("Last Name *");
		lblLastName.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblLastName);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setColumns(10);
		contentPanel.add(lastNameTextField);
		
		JLabel lblFirstName = new JLabel("First Name *");
		lblFirstName.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblFirstName);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setColumns(10);
		contentPanel.add(firstNameTextField);
		
		JLabel lblExtension = new JLabel("Extension *");
		lblExtension.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblExtension);
		
		extensionTextField = new JTextField();
		extensionTextField.setColumns(10);
		contentPanel.add(extensionTextField);
		
		JLabel lblEmail = new JLabel("Email * ");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblEmail);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		contentPanel.add(emailTextField);
		
		JLabel lblOfficeCode = new JLabel("Office Code *");
		lblOfficeCode.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblOfficeCode);
		// A drop down box for selecting office code. 
        try {
            List<String> officeCodes = databaseHelper.getOfficeCode();

            // Assuming comboBoxProductNames is declared as JComboBox<String>
            for (String officeCode : officeCodes) {
            	officeCodeComboBox.addItem(officeCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        contentPanel.add(officeCodeComboBox);
		
		JLabel lblReportsTo = new JLabel("Reports To");
		lblReportsTo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblReportsTo);
		// A drop down box for selecting employee number. 
        try {
            List<Integer> employeeNumbers = databaseHelper.getEmployeeNumber();
            for (Integer employeeNumber : employeeNumbers) {
            	reportsToComboBox.addItem(employeeNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        contentPanel.add(reportsToComboBox);
		
		
		JLabel lblJobTitle = new JLabel("Job Title *");
		lblJobTitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblJobTitle);
		
		jobTitleTextField = new JTextField();
		jobTitleTextField.setColumns(10);
		contentPanel.add(jobTitleTextField);
		
		// Empty space. 
		contentPanel.add(new JLabel(""));
        
        // Button for saving a new customer. 
		JButton saveCustomerButton = new JButton("Save Employee");
		saveCustomerButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
		// Action listener for the "Save Customer" button.
		saveCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(areRequiredFieldsFilled()) {
						// Add the customer to the database by using user input as data. 
						databaseHelper.addEmployee(
								databaseHelper.getLastEmployeeNumber() + 1, 
								firstNameTextField.getText(),
								lastNameTextField.getText(),
								extensionTextField.getText(),
								emailTextField.getText(),
								officeCodeComboBox.getSelectedItem().toString(),
								Integer.parseInt(reportsToComboBox.getSelectedItem().toString()),
								jobTitleTextField.getText()
								);
						// If successful display the message and close the frame. 
						warningMessage.displayMessage("New employee has been added successfully!");
	        			dispose();
					}
					else {
						warningMessage.displayMessage("Please fill in all required fields.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					warningMessage.displayMessage("Something went wrong!");
				}
			}
		});
		contentPanel.add(saveCustomerButton);
        
		// Add a scroll bar on the right. 
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
		setVisible(true);
	}
	
	/**
	 * A method for checking if all the required fields are filled. 
	 * 
	 * @return true if all the required fields are filled, otherwise false. 
	 */
	private boolean areRequiredFieldsFilled() {
	    return !lastNameTextField.getText().isEmpty() &&
	            !firstNameTextField.getText().isEmpty() &&
	            !extensionTextField.getText().isEmpty() &&
	            !emailTextField.getText().isEmpty() &&
	            !jobTitleTextField.getText().isEmpty();

	}
}
