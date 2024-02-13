package view;

/**
 * @author Migle Adomonyte 
 * 
 * This class represents customer menu interface. 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CustomerInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for creating a customer frame.
	 */
	public CustomerInterface() {
		// Create a new JFrame. 
		setTitle("Customer Menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 400);
		setResizable(false);
		
		// Create a new JPanel for storing components. 
        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));
        panel.setBackground(new Color(248, 249, 250));
        setContentPane(panel);
        
		JLabel customerLabel = new JLabel("Customers", SwingConstants.CENTER);
		customerLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		panel.add(customerLabel);
        
        // Add padding around the content. 
        panel.setBorder(new EmptyBorder(42, 120, 80, 120)); 
        
		// Buttons for Customer Menu.
		
		JButton buttonViewCustomers = new JButton("View Customers");
		// Action listener. 
		buttonViewCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewCustomersFrame();
			}
		});
		buttonViewCustomers.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel.add(buttonViewCustomers);
		
		JButton buttonAddCustomer = new JButton("Add Customer");
		// Action listener.
		buttonAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddCustomerFrame();
			}
		});
		buttonAddCustomer.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel.add(buttonAddCustomer);
		
		JButton buttonUpdateCustomer = new JButton("Update Customer");
		// Action listener.
		buttonUpdateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateCustomerFrame();
			}
		});
		buttonUpdateCustomer.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel.add(buttonUpdateCustomer);
		
		JButton buttonDeleteCustomer = new JButton("Delete Customer");
		// Action listener.
		buttonDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeleteCustomerFrame();
			}
		});
		buttonDeleteCustomer.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel.add(buttonDeleteCustomer);
		
		setVisible(true);
	}
}
