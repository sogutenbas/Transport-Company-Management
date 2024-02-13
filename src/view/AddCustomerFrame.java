package view;

/**
 * @author Migle Adomonyte
 * 
 * This class represents a frame for adding a new customer to the database. 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.DatabaseHelper;

public class AddCustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private DatabaseHelper databaseHelper = new DatabaseHelper();
	private JTextField customerNameTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField phoneTextField;
	private JTextField address1TextField;
	private JTextField address2TextField;
	private JTextField cityTextField;
	private JTextField stateTextField;
	private JTextField postalCodeTextField;
	private JTextField countryTextField;
	private JTextField creditLimitTextField;
	private JComboBox<Integer> salesRepEmployeeComboBox = new JComboBox<Integer>();

	/**
	 * Constructor for creating AddCustomerFrame.
	 */
	public AddCustomerFrame() {
		// Create a new JFrame.
		setTitle("Add Customer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 400);

		// Create a new JPanel for storing components.
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(248, 249, 250));
		contentPanel.setLayout(new GridLayout(0, 2, 10, 10));

		// Add a header.
		JLabel headerLabel = new JLabel("Add a New Customer");
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
		JLabel lblCustomerName = new JLabel("Customer Name *");
		lblCustomerName.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblCustomerName);

		customerNameTextField = new JTextField();
		customerNameTextField.setColumns(10);
		contentPanel.add(customerNameTextField);

		JLabel lblContactFirstName = new JLabel("Contact First Name *");
		lblContactFirstName.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblContactFirstName);

		firstNameTextField = new JTextField();
		firstNameTextField.setColumns(10);
		contentPanel.add(firstNameTextField);

		JLabel lblContactLastName = new JLabel("Contact Last Name *");
		lblContactLastName.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblContactLastName);

		lastNameTextField = new JTextField();
		lastNameTextField.setColumns(10);
		contentPanel.add(lastNameTextField);

		JLabel lblPhone = new JLabel("Phone *");
		lblPhone.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblPhone);

		phoneTextField = new JTextField();
		phoneTextField.setColumns(10);
		contentPanel.add(phoneTextField);

		JLabel lblAddress = new JLabel("Address Line 1 *");
		lblAddress.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblAddress);

		address1TextField = new JTextField();
		address1TextField.setColumns(10);
		contentPanel.add(address1TextField);

		JLabel lblAddressLine = new JLabel("Address Line 2");
		lblAddressLine.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblAddressLine);

		address2TextField = new JTextField();
		address2TextField.setColumns(10);
		contentPanel.add(address2TextField);

		JLabel lblCity = new JLabel("City *");
		lblCity.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblCity);

		cityTextField = new JTextField();
		cityTextField.setColumns(10);
		contentPanel.add(cityTextField);

		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblState);

		stateTextField = new JTextField();
		stateTextField.setColumns(10);
		contentPanel.add(stateTextField);

		JLabel lblPostalCode = new JLabel("Postal Code");
		lblPostalCode.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblPostalCode);

		postalCodeTextField = new JTextField();
		postalCodeTextField.setColumns(10);
		contentPanel.add(postalCodeTextField);

		JLabel lblCountry = new JLabel("Country *");
		lblCountry.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblCountry);

		countryTextField = new JTextField();
		countryTextField.setColumns(10);
		contentPanel.add(countryTextField);

		JLabel lblSalesRepEmployee = new JLabel("Sales Rep Employee Number");
		lblSalesRepEmployee.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblSalesRepEmployee);

		try {
			List<Integer> employeeNumbers = databaseHelper.getEmployeeNumber();
			for (Integer employeeNumber : employeeNumbers) {
				salesRepEmployeeComboBox.addItem(employeeNumber);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		contentPanel.add(salesRepEmployeeComboBox);

		JLabel lblCreditLimit = new JLabel("Credit Limit");
		lblCreditLimit.setFont(new Font("SansSerif", Font.PLAIN, 14));
		contentPanel.add(lblCreditLimit);

		creditLimitTextField = new JTextField("0");
		creditLimitTextField.setColumns(10);
		contentPanel.add(creditLimitTextField);

		// Empty space.
		contentPanel.add(new JLabel(""));

		// Button for saving a new customer.
		JButton saveCustomerButton = new JButton("Save Customer");
		saveCustomerButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
		saveCustomerButton.setForeground(Color.WHITE);
		saveCustomerButton.setBackground(new Color(46, 164, 78));
		contentPanel.add(saveCustomerButton);
		saveCustomerButton.setUI(new javax.swing.plaf.basic.BasicButtonUI());
		// Action listener.
		saveCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					databaseHelper.addCustomer(databaseHelper.getLastCustomerNumber() + 2,
							customerNameTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(),
							phoneTextField.getText(), address1TextField.getText(), address2TextField.getText(),
							cityTextField.getText(), stateTextField.getText(), postalCodeTextField.getText(),
							countryTextField.getText(), (int) salesRepEmployeeComboBox.getSelectedItem(),
							Double.parseDouble(creditLimitTextField.getText()));
					displayMessage("New customer has been added successfully!");
					dispose();
				} catch (SQLException e1) {
					displayMessage("Something went wrong!");
					e1.printStackTrace();
				}
			}
		});

		// Add a scroll bar on the right.
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		// Add padding around the content.
		contentPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

		setVisible(true);
	}

	private void displayMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	};
}
