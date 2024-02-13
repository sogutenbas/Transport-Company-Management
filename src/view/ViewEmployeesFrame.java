package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import controller.DatabaseHelper;
import model.Employee;

/**
 * @author Migle Adomonyte
 * 
 * This class represents a table of all employees in the database. 
 */

public class ViewEmployeesFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DatabaseHelper databaseHelper = new DatabaseHelper();
	
	/**
	 * Constructor for creating a new frame that shows a table of all employees. 
	 */
	public ViewEmployeesFrame() {
		// Create a new JFrame. 
		setTitle("All Employees");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 500);
		
		// Create a JPanel. 
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(248,249,250));
		setContentPane(panel);
		
		String[] columnNames = {"Employee Number", "Last Name", "First Name", "Extension", "E-mail", 
				"Office Code", "Reports To", "Job Title"};
		
		// Fetch customers and store in list. 
		List<Employee> employees;
		
		try {
			employees = databaseHelper.getEmployees();
		} catch(SQLException e) {
            JOptionPane.showMessageDialog(this, "Error in retrieving data from the database. " + e.getMessage(), 
            		"Database Error", JOptionPane.ERROR_MESSAGE);
            return;
		}
		
		// Convert List<Employee> to a 2D Array. 
		Object[][] employeeData = new Object[employees.size()][columnNames.length];
	    for (int i = 0; i < employees.size(); i++) {
	    	// Get data from List<Employee>.
            Employee employee = employees.get(i);
            // Assign values to the 2D Array. 
            employeeData[i][0] = employee.getEmployeeNumber();
            employeeData[i][1] = employee.getLastName();
            employeeData[i][2] = employee.getFirstName();
            employeeData[i][3] = employee.getExtension();
            employeeData[i][4] = employee.getEmail();
            employeeData[i][5] = employee.getOfficeCode();
            employeeData[i][6] = employee.getReportsTo();
            employeeData[i][7] = employee.getJobTitle();
        }
		
		// Create JTable. 
        JTable table = new JTable(employeeData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JTableHeader header = table.getTableHeader();
        // Set table header. 
        header.setFont(header.getFont().deriveFont(Font.BOLD, 12));
        // Add the scrollpane. 
        add(scrollPane, BorderLayout.CENTER);
        // Automatic resizing
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        setVisible(true);
	}
}
