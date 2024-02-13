package view;

/**
 * 
 * This class represents a frame for adding a new order to the database. 
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

public class AddOrderFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    DatabaseHelper databaseHelper = new DatabaseHelper();
    private JTextField orderDateTextField;
    private JTextField requiredDateTextField;
    private JTextField shippedDateTextField;
    private JTextField statusTextField;
    private JTextField commentsTextField;
    private JTextField orderNumberTextField;
    private JComboBox<Integer> customerNumberComboBox = new JComboBox<>();

    /**
     * Constructor for creating AddOrderFrame.
     */
    public AddOrderFrame() {
        // Create a new JFrame.
        setTitle("Add Order");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);

        // Create a new JPanel for storing components.
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(248, 249, 250));
        contentPanel.setLayout(new GridLayout(0, 2, 10, 10));

        // Add padding around the content.
        contentPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        // Add a header.
        JLabel headerLabel = new JLabel("Add a New Order");
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

        // Create form labels and text fields for order details.
        orderDateTextField = new JTextField();
        orderDateTextField.setColumns(10);
        contentPanel.add(orderDateTextField);

        JLabel lblOrderDate = new JLabel("Order Date (yyyy-mm-dd) *");
        lblOrderDate.setFont(new Font("SansSerif", Font.PLAIN, 14));
        contentPanel.add(lblOrderDate);

        requiredDateTextField = new JTextField();
        requiredDateTextField.setColumns(10);
        contentPanel.add(requiredDateTextField);

        JLabel lblRequiredDate = new JLabel("Required Date (yyyy-mm-dd) *");
        lblRequiredDate.setFont(new Font("SansSerif", Font.PLAIN, 14));
        contentPanel.add(lblRequiredDate);

        shippedDateTextField = new JTextField();
        shippedDateTextField.setColumns(10);
        contentPanel.add(shippedDateTextField);

        JLabel lblShippedDate = new JLabel("Shipped Date (yyyy-mm-dd)");
        lblShippedDate.setFont(new Font("SansSerif", Font.PLAIN, 14));
        contentPanel.add(lblShippedDate);

        statusTextField = new JTextField();
        statusTextField.setColumns(10);
        contentPanel.add(statusTextField);

        JLabel lblStatus = new JLabel("Status *");
        lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 14));
        contentPanel.add(lblStatus);

        commentsTextField = new JTextField();
        commentsTextField.setColumns(10);
        contentPanel.add(commentsTextField);

        JLabel lblComments = new JLabel("Comments");
        lblComments.setFont(new Font("SansSerif", Font.PLAIN, 14));
        contentPanel.add(lblComments);

        orderNumberTextField = new JTextField();
        orderNumberTextField.setColumns(10);
        contentPanel.add(orderNumberTextField);

        JLabel lblOrderNumber = new JLabel("Order Number *");
        lblOrderNumber.setFont(new Font("SansSerif", Font.PLAIN, 14));
        contentPanel.add(lblOrderNumber);

     // Populating the comboBoxProductNames
        try {
            List<Integer> customerNumbers = databaseHelper.getCustomerNumbers();

            // Assuming customerNumberComboBox is declared as JComboBox<Integer>
            for (Integer customerNumber : customerNumbers) {
                customerNumberComboBox.addItem(customerNumber);
            }
        } catch (SQLException e) {
            
        }
        contentPanel.add(customerNumberComboBox);
        JLabel lblCustomerNumber = new JLabel("Customer Number *");
        lblCustomerNumber.setFont(new Font("SansSerif", Font.PLAIN, 14));
        contentPanel.add(lblCustomerNumber);

        // Empty space.
        contentPanel.add(new JLabel(""));

        // Button for saving a new order
        JButton saveOrderButton = new JButton("Save Order");
        saveOrderButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        // Action listener for the "Save Order" button.
        saveOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (areRequiredFieldsFilled()) {
                        // Add the order to the database by using user input as data.
                        // Assuming a method 'addOrder' exists in DatabaseHelper

                        System.out.println("Attempting to add order...");
                        databaseHelper.addOrder(
                        		 Integer.parseInt(orderNumberTextField.getText()),
                        	        orderDateTextField.getText(),
                        	        requiredDateTextField.getText(),
                        	        shippedDateTextField.getText(),
                        	        statusTextField.getText(),
                        	        commentsTextField.getText(),
                        	        Integer.parseInt(customerNumberComboBox.getSelectedItem().toString()));
                        System.out.println("Order added successfully!");

                        // If successful, display the message and close the frame.
                        displayMessage("New order has been added successfully!");
                        dispose();
                    } else {
                        displayMessage("Please fill in all required fields.");
                    }
                } catch (SQLException | NumberFormatException e1) {
                    e1.printStackTrace();
                    displayMessage("Error: " + e1.getMessage());
                }
            }
        });


        contentPanel.add(saveOrderButton);

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
        return !orderDateTextField.getText().isEmpty() &&
                !requiredDateTextField.getText().isEmpty() &&
                !shippedDateTextField.getText().isEmpty() &&
                !commentsTextField.getText().isEmpty();
    }
    private void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}
