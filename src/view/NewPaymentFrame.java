package view;

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

/**
 * This class represents a frame for adding a new payment to the database.
 * 
 * @author serhatberg
 */
public class NewPaymentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private DatabaseHelper databaseHelper = new DatabaseHelper();
	private JComboBox<Integer> customerNumberComboBox;
	private JTextField checkNumberTextField;
	private JTextField paymentDateTextField;
	private JTextField amountTextField;

	/**
	 * Constructor for creating NewPaymentFrame.
	 */
	public NewPaymentFrame() {
		setTitle("Add Payment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 400);

		// Create a new JPanel for storing components.
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(248, 249, 250));
		contentPanel.setLayout(new GridLayout(0, 2, 10, 10));

		// Add a header.
		JLabel headerLabel = new JLabel("Add a New Payment");
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
		contentPanel.add(new JLabel("Customer Number *"));
		customerNumberComboBox = new JComboBox<>();
		contentPanel.add(customerNumberComboBox);
		try {
			List<Integer> customerNumbers = databaseHelper.getCustomerNumbers();
			for (Integer number : customerNumbers) {
				customerNumberComboBox.addItem(number);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		contentPanel.add(new JLabel("Check Number *"));
		checkNumberTextField = new JTextField();
		contentPanel.add(checkNumberTextField);

		contentPanel.add(new JLabel("Payment Date (yyyy-mm-dd) *"));
		paymentDateTextField = new JTextField();
		contentPanel.add(paymentDateTextField);

		contentPanel.add(new JLabel("Amount *"));
		amountTextField = new JTextField();
		contentPanel.add(amountTextField);

		// Empty space.
		contentPanel.add(new JLabel(""));

		// Button for saving a new payment.
		JButton savePaymentButton = new JButton("Save Payment");
		savePaymentButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
		savePaymentButton.setForeground(Color.WHITE);
		savePaymentButton.setBackground(new Color(46, 164, 78));
		contentPanel.add(savePaymentButton);
		savePaymentButton.setUI(new javax.swing.plaf.basic.BasicButtonUI());
		// Action listener for the save payment button.
		savePaymentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int customerNumber = (int) customerNumberComboBox.getSelectedItem();
					String checkNumber = checkNumberTextField.getText();
					String paymentDate = paymentDateTextField.getText();
					float amount = Float.parseFloat(amountTextField.getText());
					databaseHelper.addPayment(customerNumber, checkNumber, paymentDate, amount);
					displayMessage("New payment has been added successfully!");
					dispose();
				} catch (SQLException e1) {
					displayMessage("Something went wrong!");
					e1.printStackTrace();
				} catch (NumberFormatException e2) {
					displayMessage("Please enter a valid amount!");
					e2.printStackTrace();
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
	}

	// Main method for testing the frame
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				NewPaymentFrame frame = new NewPaymentFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}