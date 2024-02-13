package view;


import javax.swing.*;

import controller.DatabaseHelper;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateStockFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    JComboBox<String> comboBoxProductNames = new JComboBox<>();
    private JLabel lblCurrentStockTally = new JLabel();
    private DatabaseHelper databaseHelper = new DatabaseHelper();
    private JTextField updateStockField;
    private JRadioButton rdbtnAddition;
    private JRadioButton rdbtnSubtraction;

    /**
     * Create the frame for the update stock page
     */
    public UpdateStockFrame() {
        setTitle("Update Product Stock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        JLabel lblUpdateStockTitle = new JLabel("Update Product Stock");
        lblUpdateStockTitle.setBounds(0, 35, 450, 29);
        lblUpdateStockTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblUpdateStockTitle.setFont(new Font("SansSerif", Font.PLAIN, 24));
        getContentPane().add(lblUpdateStockTitle);

        JLabel lblUpdateItem = new JLabel("Item Name");
        lblUpdateItem.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblUpdateItem.setHorizontalAlignment(SwingConstants.CENTER);
        lblUpdateItem.setBounds(18, 98, 96, 40);
        getContentPane().add(lblUpdateItem);

        comboBoxProductNames.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCurrentStockTally();
            }
        });

        comboBoxProductNames.setBounds(142, 106, 282, 27);
        getContentPane().add(comboBoxProductNames);

        JLabel lblUpdatedStock = new JLabel("Update Stock");
        lblUpdatedStock.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblUpdatedStock.setHorizontalAlignment(SwingConstants.CENTER);
        lblUpdatedStock.setBounds(18, 192, 108, 40);
        getContentPane().add(lblUpdatedStock);

        // Initialize lblCurrentStockTally here...
        lblCurrentStockTally.setBounds(225, 164, 61, 16);
        getContentPane().add(lblCurrentStockTally);

        JLabel lblCurrentstock = new JLabel("Current Stock");
        lblCurrentstock.setFont(new Font("SansSerif", Font.PLAIN, 14));

        lblCurrentstock.setHorizontalAlignment(SwingConstants.CENTER);
        lblCurrentstock.setBounds(20, 140, 108, 40);
        getContentPane().add(lblCurrentstock);

        updateStockField = new JTextField();
        updateStockField.setBounds(190, 199, 130, 26);
        getContentPane().add(updateStockField);
        updateStockField.setColumns(10);

        JButton btnUpdateStock = new JButton("Confirm");
        btnUpdateStock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the selected product name from the combo box
                    String selectedProductName = (String) comboBoxProductNames.getSelectedItem();

                    // Check if a product is selected
                    if (selectedProductName == null || selectedProductName.isEmpty()) {
                        // Optionally, show an error message to the user
                        showErrorMessage("Please select a product");
                        return;
                    }

                    // Get the quantity to update from the text field
                    String updateQuantityText = updateStockField.getText();
                    if (updateQuantityText.isEmpty()) {
                        // Optionally, show an error message to the user
                        showErrorMessage("Please enter a quantity to update");
                        return;
                    }

                    // Parse the quantity to update
                    int quantityToUpdate;
                    try {
                        quantityToUpdate = Integer.parseInt(updateQuantityText);
                    } catch (NumberFormatException ex) {
                        // Show a warning for non-integer input
                        showErrorMessage("Please enter a valid integer for quantity");
                        return;
                    }

                    // Check if addition or subtraction is selected
                    if (rdbtnAddition.isSelected() || rdbtnSubtraction.isSelected()) {
                        boolean isAddition = rdbtnAddition.isSelected();
                        databaseHelper.updateQuantityInStock(selectedProductName, quantityToUpdate, isAddition);
                    } else {
                        // Optionally, show an error message to the user
                        showErrorMessage("Please select an operation (+ or -)");
                        return;
                    }

                    // Optionally, update the UI or show a confirmation message
                    updateCurrentStockTally();
                    showConfirmationMessage("Stock updated successfully!");
                } catch (SQLException ex) {
                    handleException(ex);
                }
            }
        });
        btnUpdateStock.setBounds(307, 237, 117, 29);
        btnUpdateStock.setFont(new Font("SansSerif", Font.PLAIN, 14));
        getContentPane().add(btnUpdateStock);

        // Radio buttons for addition and subtraction
        rdbtnAddition = new JRadioButton("+");
        rdbtnAddition.setBounds(339, 176, 61, 23);
        getContentPane().add(rdbtnAddition);

        rdbtnSubtraction = new JRadioButton("-");
        rdbtnSubtraction.setBounds(339, 200, 61, 23);
        getContentPane().add(rdbtnSubtraction);

        // Group the radio buttons so that only one can be selected at a time
        ButtonGroup operationGroup = new ButtonGroup();
        operationGroup.add(rdbtnAddition);
        operationGroup.add(rdbtnSubtraction);

        // Populating the comboBoxProductNames
        try {
            List<String> productNames = databaseHelper.getProductName();

            // Assuming comboBoxProductNames is declared as JComboBox<String>
            for (String productName : productNames) {
                comboBoxProductNames.addItem(productName);
            }
        } catch (SQLException e) {
            handleException(e);
        }
    }

    private void updateCurrentStockTally() {
        try {
            // Get the selected product name from the combo box
            String selectedProductName = (String) comboBoxProductNames.getSelectedItem();

            // Check if a product is selected
            if (selectedProductName == null || selectedProductName.isEmpty()) {
                // Optionally, show an error message to the user
                showErrorMessage("Please select a product");
                return;
            }

            // Query the database to get the quantity in stock based on the selected name
            int currentStock = databaseHelper.getQuantityByName(selectedProductName);

            // Update the lblCurrentStockTally label with only the number
            lblCurrentStockTally.setText(String.valueOf(currentStock));
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
	