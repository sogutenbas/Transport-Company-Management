package view;

/**
 * @author Migle Adomonyte
 * 
 * This class represents employee menu interface. 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author Migle Adomonyte 
 * 
 * This class represents employee menu interface. 
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class EmployeeInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for creating an employee frame.
	 */
	public EmployeeInterface() {
		// Create a new JFrame. 
		setTitle("Employee Menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 400);
		setResizable(false);
		
		// Create a new JPanel for storing components. 
        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));
        panel.setBackground(new Color(248, 249, 250));
        setContentPane(panel);
        
		JLabel employeeLabel = new JLabel("Employees", SwingConstants.CENTER);
		employeeLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		panel.add(employeeLabel);
        
        // Add padding around the content. 
        panel.setBorder(new EmptyBorder(42, 120, 80, 120)); 
        
		// Buttons for Customer Menu.
        
		JButton buttonViewEmployees = new JButton("View Employees");
		// Action listener. 
		buttonViewEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewEmployeesFrame();
			}
		});
		buttonViewEmployees.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel.add(buttonViewEmployees);
		
		JButton buttonAddEmployee = new JButton("Add Employee");
		// Action listener.
		buttonAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddEmployeeFrame();
			}
		});
		buttonAddEmployee.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel.add(buttonAddEmployee);
		
		JButton buttonUpdateEmployee = new JButton("Update Employee");
		// Action listener.
		buttonUpdateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateEmployeeFrame();
			}
		});
		buttonUpdateEmployee.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel.add(buttonUpdateEmployee);
		
		JButton buttonDeleteEmployee = new JButton("Delete Employee");
		// Action listener.
		buttonDeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeleteEmployeeFrame();
			}
		});
		buttonDeleteEmployee.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel.add(buttonDeleteEmployee);
		
		setVisible(true);
	}
}
