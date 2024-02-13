package view;

import javax.swing.*;
import controller.DatabaseHelper;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class UpdateOrderStatusFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<Integer> comboBoxOrderNumbers = new JComboBox<>();
    private JLabel lblCurrentStatus = new JLabel();
    private DatabaseHelper databaseHelper = new DatabaseHelper();
    private JTextField updateStatusField;

    /**
     * Create the frame for updating order status.
     */
    public UpdateOrderStatusFrame() {
        setTitle("Update Order Status");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        JLabel lblUpdateOrderStatusTitle = new JLabel("Update Order Status");
        lblUpdateOrderStatusTitle.setBounds(0, 35, 450, 29);
        lblUpdateOrderStatusTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblUpdateOrderStatusTitle.setFont(new Font("Serif", Font.BOLD, 24));
        getContentPane().add(lblUpdateOrderStatusTitle);

        JLabel lblUpdateOrder = new JLabel("Order Number");
        lblUpdateOrder.setHorizontalAlignment(SwingConstants.CENTER);
        lblUpdateOrder.setBounds(18, 98, 96, 40);
        getContentPane().add(lblUpdateOrder);

        comboBoxOrderNumbers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCurrentStatus();
            }
        });

        comboBoxOrderNumbers.setBounds(142, 106, 282, 27);
        getContentPane().add(comboBoxOrderNumbers);

        JLabel lblUpdatedStatus = new JLabel("Update Status");
        lblUpdatedStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblUpdatedStatus.setBounds(18, 192, 108, 40);
        getContentPane().add(lblUpdatedStatus);

        // Initialize lblCurrentStatus here...
        lblCurrentStatus.setBounds(225, 164, 61, 16);
        getContentPane().add(lblCurrentStatus);

        JLabel lblCurrentStatus = new JLabel("Current Status");
        lblCurrentStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblCurrentStatus.setBounds(20, 140, 108, 40);
        getContentPane().add(lblCurrentStatus);

        updateStatusField = new JTextField();
        updateStatusField.setBounds(190, 199, 130, 26);
        getContentPane().add(updateStatusField);
        updateStatusField.setColumns(10);

        JButton btnUpdateStatus = new JButton("Confirm");
        btnUpdateStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the selected order number from the combo box
                    Integer selectedOrderNumber = (Integer) comboBoxOrderNumbers.getSelectedItem();

                    // Check if an order is selected
                    if (selectedOrderNumber == 0) {
                        // Optionally, show an error message to the user
                        showErrorMessage("Please select an order");
                        return;
                    }

                    // Get the new status from the text field
                    String newStatus = updateStatusField.getText();
                    if (newStatus.isEmpty()) {
                        // Optionally, show an error message to the user
                        showErrorMessage("Please enter a new status");
                        return;
                    }

                    // Update the order status in the database
                    databaseHelper.updateOrderStatus(selectedOrderNumber, newStatus);

                    // Optionally, update the UI or show a confirmation message
                    updateCurrentStatus();
                    showConfirmationMessage("Order status updated successfully!");
                } catch (SQLException ex) {
                    handleException(ex);
                }
            }
        });

        btnUpdateStatus.setBounds(307, 237, 117, 29);
        getContentPane().add(btnUpdateStatus);

        // Populating the comboBoxProductNames
        try {
            List<Integer> orderNumbers = databaseHelper.getOrderNumbers();

            // Assuming comboBoxOrderNumbers is declared as JComboBox<Integer>
            for (Integer orderNumber : orderNumbers) {
                comboBoxOrderNumbers.addItem(orderNumber);
            }
        } catch (SQLException e) {
            handleException(e);
        }

    }


    private void updateCurrentStatus() {
        try {
            // Get the selected order number from the combo box
            Integer selectedOrderNumber = (Integer) comboBoxOrderNumbers.getSelectedItem();

            // Check if an order is selected
            if (selectedOrderNumber == 0) {
                // Optionally, show an error message to the user
                showErrorMessage("Please select an order");
                return;
            }

            // Query the database to get the current status based on the selected order number
            String currentStatus = databaseHelper.getOrderStatusByOrderNumber(selectedOrderNumber);

            // Update the lblCurrentStatus label with the current status
            lblCurrentStatus.setText(currentStatus);
        } catch (SQLException ex) {
            handleException(ex);
        }
    }


    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showConfirmationMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleException(Exception ex) {
        ex.printStackTrace();
        // Handle the exception, e.g., show an error message to the user
        showErrorMessage("An error occurred: " + ex.getMessage());
    }
}
