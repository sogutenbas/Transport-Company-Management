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
import model.Employee;

/**
 * @author Migle Adomonyte
 * 
 *         This class represents a new frame when a user searches for a
 *         customer. The frame consists of a filled out form the user can edit.
 */

public class UpdateEmployeeDetailsFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DatabaseHelper databaseHelper = new DatabaseHelper();
	private JComboBox<String> officeCodeComboBox = new JComboBox<>();
	private JComboBox<Integer> reportsToComboBox = new JComboBox<Integer>();
	WarningMessages warningMessage = new WarningMessages();

	/**
	 * Constructor for creating a frame for updating the employee.
	 * 
	 * @param employee The employee information to be displayed.
	 */
	public UpdateEmployeeDetailsFrame(Employee employee) {
		// Create a new JFrame.
		setTitle("Update Employee");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 400);

		// Create a new panel for updating customer information.
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(248, 249, 250));
		contentPanel.setLayout(new GridLayout(0, 2, 10, 0));

		// Add padding around the content.
		contentPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

		// Add a header.
		JLabel headerLabel = new JLabel("Update Employee");
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
		contentPanel.add(new JLabel("Last Name"));
		JTextField lastNameTextField = new JTextField(employee.getLastName());
		contentPanel.add(lastNameTextField);

		contentPanel.add(new JLabel("First Name"));
		JTextField firstNameTextField = new JTextField(employee.getFirstName());
		contentPanel.add(firstNameTextField);

		contentPanel.add(new JLabel("Extension"));
		JTextField extensionTextField = new JTextField(employee.getExtension());
		contentPanel.add(extensionTextField);

		contentPanel.add(new JLabel("Email"));
		JTextField emailTextField = new JTextField(employee.getEmail());
		contentPanel.add(emailTextField);

		JLabel lblOfficeCode = new JLabel("Office Code");
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

		contentPanel.add(new JLabel("Job Title"));
		JTextField jobTitleTextField = new JTextField(employee.getJobTitle());
		contentPanel.add(jobTitleTextField);

		// Empty space.
		contentPanel.add(new JLabel(""));

		// Button for updating employee information.
		JButton updateEmployeeButton = new JButton("Update Employee");
		updateEmployeeButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
		// Action listener for the "Update Employee" button.
		updateEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Retrieve input from the fields.
					String newLastname = lastNameTextField.getText();
					String newFirstname = firstNameTextField.getText();
					String newExtension = extensionTextField.getText();
					String newEmail = emailTextField.getText();
					String newOfficeCode = officeCodeComboBox.getSelectedItem().toString();
					int newReportsTo = Integer.parseInt(reportsToComboBox.getSelectedItem().toString());
					String newjobTitle = jobTitleTextField.getText();

					// Update customer with the provided user input.
					databaseHelper.updateEmployee(employee.getEmployeeNumber(), newLastname, newFirstname, newExtension,
							newEmail, newOfficeCode, newReportsTo, newjobTitle);
					// Display the message and close the frame.
					warningMessage.displayMessage("The employee has been successfully updated!");

					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
					warningMessage.displayMessage("Something went wrong!");
				}
			}
		});
		contentPanel.add(updateEmployeeButton);

		// Add a scroll bar on the right.
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(scrollPane);

		setVisible(true);
	}
}
