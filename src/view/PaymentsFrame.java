package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DatabaseHelper;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * @author eliasronningen
 */

/*
 * Page for handling Payments
 */

public class PaymentsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> dateComboBox;
	private DatabaseHelper databaseHelper;

	/*
	 * Create the frame.
	 */
	public PaymentsFrame() {
		setResizable(false);
		setTitle("Payments");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 338, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		databaseHelper = new DatabaseHelper();

		/*
		 * Greeting message
		 */
		JLabel lblPayment = new JLabel("Payments");
		lblPayment.setBounds(122, 17, 136, 38);
		lblPayment.setFont(new Font("SansSerif", Font.PLAIN, 18));
		contentPane.add(lblPayment);

		/*
		 * Button for opening the View All Payments table
		 */
		JButton btnViewAllPayments = new JButton("View All Payments");
		btnViewAllPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPaymentsTableFrame();
			}
		});
		btnViewAllPayments.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnViewAllPayments.setBounds(63, 66, 218, 29);
		contentPane.add(btnViewAllPayments);

		/*
		 * Button to see all orders by a customer, will function similarly to payments
		 * on date - WILL BE ADDED LATER / NEED TO SET UP CUSTOMER FIRST
		 */
		JButton btnViewCustomerPayments = new JButton("View Customer Payments");
		btnViewCustomerPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCustomerPayments();
			}
		});
		btnViewCustomerPayments.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnViewCustomerPayments.setBounds(63, 107, 218, 29);
		contentPane.add(btnViewCustomerPayments);

		/*
		 * Button to open Date Selction and querying database for all orders on given
		 * date.
		 */
		JButton btnViewPaymentsOnDate = new JButton("View Payments on Date");
		btnViewPaymentsOnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDateSelectionFrame();
			}
		});
		btnViewPaymentsOnDate.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnViewPaymentsOnDate.setBounds(63, 145, 218, 29);
		contentPane.add(btnViewPaymentsOnDate);

		/*
		 * Button for adding new payments 
		 * 
		 */
		JButton btnNewPayment = new JButton("New Payment");
		btnNewPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openNewPaymentFrame(); // Open the NewPaymentFrame
			}
		});
		btnNewPayment.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnNewPayment.setBounds(63, 186, 218, 29);
		contentPane.add(btnNewPayment);
	}

	/*
	 * Simple window for selecting the desired date for viewing payments
	 */
	private void openDateSelectionFrame() {
		JFrame dateSelectionFrame = new JFrame("Date Selection");
		dateSelectionFrame.setBounds(200, 200, 300, 150);

		JPanel dateSelectionPanel = new JPanel();
		dateSelectionPanel.setLayout(null);

		JLabel lblSelectDate = new JLabel("Select Date:");
		lblSelectDate.setBounds(10, 20, 80, 25);
		dateSelectionPanel.add(lblSelectDate);

		// Populate the JComboBox with payment dates
		List<String> paymentDatesList;
		try {
			databaseHelper.open();
			paymentDatesList = databaseHelper.getPaymentDates();
			databaseHelper.close();

			String[] paymentDatesArray = paymentDatesList.toArray(new String[0]);

			JComboBox<String> comboBox = new JComboBox<>(paymentDatesArray);
			comboBox.setBounds(100, 20, 150, 25);
			dateSelectionPanel.add(comboBox);
			dateComboBox = comboBox; // Assign to the class-level variable
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle SQLException appropriately (show an error message, log, etc.)
		}

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String selectedDate = dateComboBox.getSelectedItem().toString();

					// SQL query to fetch payments for the selected date
					String sqlQuery = "SELECT * FROM payments WHERE paymentDate = '" + selectedDate + "'";

					// Execute the SQL query and display the results
					databaseHelper.executeSQLCommand(sqlQuery, resultSet -> {
						databaseHelper.displayResultSet(resultSet);
					});
				} catch (SQLException ex) {
					ex.printStackTrace();
					// Handle SQLException appropriately (show an error message, log, etc.)
				}

				dateSelectionFrame.dispose(); // Close the date selection frame
			}
		});
		btnOK.setBounds(120, 80, 60, 25);
		dateSelectionPanel.add(btnOK);

		dateSelectionFrame.setContentPane(dateSelectionPanel);
		dateSelectionFrame.setVisible(true);
	}

	/*
	 * Method for opening the PaymentsTableFrame and setting it visible
	 */
	private void openPaymentsTableFrame() {
		PaymentsTableFrame paymentsTableFrame = new PaymentsTableFrame();
		paymentsTableFrame.setVisible(true);

	}

	private void ViewCustomerPayments() {
		ViewCustomerPayments viewCustomerPayments = new ViewCustomerPayments();
		viewCustomerPayments.setVisible(true);
	}

	// Method to open the New Payment Frame
	private void openNewPaymentFrame() {
		NewPaymentFrame newPaymentFrame = new NewPaymentFrame();
		newPaymentFrame.setVisible(true);
	}
}
