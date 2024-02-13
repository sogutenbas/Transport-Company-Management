package view;


import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.DatabaseHelper;
import model.Payments;

/**
 * Frame for displaying a table of all payments stored on database.
 */

public class PaymentsTableFrame extends JFrame {

	private static final long serialVersionUID = 1L;
    private DatabaseHelper databaseHelper = new DatabaseHelper();



	/**
	 * Frame for the GUI created
	 */
	public PaymentsTableFrame() {
		 setTitle("All Payments");
	     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	     setSize(1000, 500);
	     
	  // Panel created using the Borderlayout method.
	        JPanel panel = new JPanel();
	        panel.setLayout(new BorderLayout());
	        getContentPane().add(panel);
	        
	     // Label for page title 
	        JLabel lblPayments = new JLabel("Payments");
	        lblPayments.setFont(new Font("SansSerif", Font.PLAIN, 24));
	        lblPayments.setHorizontalAlignment(SwingConstants.CENTER);
		
	        // Panel for title, placed in the middle of the screen, before the table
	        JPanel pnlHeader = new JPanel(new BorderLayout());
	        pnlHeader.add(lblPayments, BorderLayout.CENTER);
	        panel.add(pnlHeader, BorderLayout.NORTH);
	        
	     // Table columns
	        String[] columnTitles = {
	                "Customer Number", "Check Number", "Payment Date", "Amount"	               

	};
	     // Fetches data through the database helper
	        List<Payments> payment;
	        try {
	            payment = databaseHelper.getPayments();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, "Error in fetching payments: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        // Create data array for the table
	        Object[][] data = new Object[payment.size()][columnTitles.length];
	        
	        // Populate the data array using enhanced for loop
	        int rowIndex = 0;
	        for (Payments payments : payment) {
	            data[rowIndex][0] = payments.getCustomerNumber();
	            data[rowIndex][1] = payments.getCheckNumber();
	            data[rowIndex][2] = payments.getPaymentDate();
	            data[rowIndex][3] = payments.getAmount();
	          

	            rowIndex++;
	        }
	        
	     // Create the DefaultTableModel with data and column titles
	        DefaultTableModel model = new DefaultTableModel(data, columnTitles) {
	            private static final long serialVersionUID = 1L;

				@Override   // Makes sure the cells of the tables aren't tamperable
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };
	        
	        // Create the JTable using the DefaultTableModel
	        JTable paymentsTable = new JTable(model);

	        // Automatic resizing
	        paymentsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

	        // Make the list scrollable
	        JScrollPane scrollPane = new JScrollPane(paymentsTable);
	        panel.add(scrollPane, BorderLayout.CENTER);
	    }

	}

