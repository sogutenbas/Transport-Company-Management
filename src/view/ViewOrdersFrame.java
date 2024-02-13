package view;

import java.awt.*;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.util.List;

import controller.DatabaseHelper;

import model.Order;
/**
 * @author serhatberg
 */
/**
 * This class represents a table of all orders in the database.
 */
public class ViewOrdersFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private DatabaseHelper databaseHelper = new DatabaseHelper();

    /**
     * Constructor for creating a new frame that shows a table of all orders.
     */
    public ViewOrdersFrame() {
    
        // Create a new JFrame.
        setTitle("All Orders");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 500);

        // Panel using BorderLayout function
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        getContentPane().add(panel);
        
     // Label for page title.
        JLabel lblOrders = new JLabel("Orders");
        lblOrders.setFont(new Font("Serif", Font.BOLD, 24));
        lblOrders.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Panel for title, placed in the middle of the screen, before the table
        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.add(lblOrders, BorderLayout.CENTER);

        panel.add(pnlHeader, BorderLayout.NORTH);

        // Create a String Array for storing column names.
        String[] columnNames = { "OrderNumber", "OrderDate", "RequiredDate", "ShippedDate",
                "Status", "Comments", "CustomerNumber" };

        // Fetch orders and store in list.
        List<Order> orders;
        try {
            orders = databaseHelper.getOrders(); // Assuming getOrders method is implemented in DatabaseHelper
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error retrieving data from the database: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert List<Order> to a 2D Array.
        Object[][] orderData = new Object[orders.size()][columnNames.length];
        for (int i = 0; i < orders.size(); i++) {
            // Get data from List<Order>.
            Order order = orders.get(i);
            // Assign values to the 2D Array.
            orderData[i][0] = order.getOrderNumber();
            orderData[i][1] = order.getOrderDate();
            orderData[i][2] = order.getRequiredDate();
            orderData[i][3] = order.getShippedDate();
            orderData[i][4] = order.getStatus();
            orderData[i][5] = order.getComments();
            orderData[i][6] = order.getCustomerNumber();
        }

        // Create JTable.
        JTable table = new JTable(orderData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JTableHeader header = table.getTableHeader();
        // Set table header.
        header.setFont(header.getFont().deriveFont(Font.BOLD, 12));
        // Add the scrollpane.
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
