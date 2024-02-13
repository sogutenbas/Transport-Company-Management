package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import controller.DatabaseHelper;
import model.Payments;

public class ViewCustomerPayments extends JFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboCustomerNumber = new JComboBox<>();
    private DatabaseHelper databaseHelper = new DatabaseHelper();

    private JTable paymentTable;
    private DefaultTableModel tableModel;

    /**
     * Create the frame for the view customer payments page
     */
    public ViewCustomerPayments() {
        setTitle("View Customer Payments");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        getContentPane().setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Select Customer:"));
        inputPanel.add(comboCustomerNumber);

        // Add an ActionListener to the combo box
        comboCustomerNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePaymentsForSelectedCustomer();
            }
        });

        getContentPane().add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"Check Number", "Payment Date", "Amount"};
        tableModel = new DefaultTableModel(columnNames, 0);
        paymentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(paymentTable);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        try {
            List<Integer> customerNumbers = databaseHelper.getCustomerNumbers();
            comboCustomerNumber.removeAllItems();

            // Sort customerNumbers in ascending order
            customerNumbers.sort(Integer::compareTo);

            for (Integer customerNumber : customerNumbers) {
                comboCustomerNumber.addItem(customerNumber.toString());
            }

            // Initially update payments for the selected customer 
            updatePaymentsForSelectedCustomer();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    private void updatePaymentsForSelectedCustomer() {
        String selectedCustomerNumber = (String) comboCustomerNumber.getSelectedItem();

        if (selectedCustomerNumber != null) {
            int customerNumber = Integer.parseInt(selectedCustomerNumber);
            displayPaymentsForCustomer(customerNumber);
        } else {
            JOptionPane.showMessageDialog(ViewCustomerPayments.this, "Please select a customer.");
        }
    }

    private void displayPaymentsForCustomer(int customerNumber) {
        try {
            List<Payments> payments = databaseHelper.getCustomerPayments(customerNumber);
            tableModel.setRowCount(0);

            for (Payments payment : payments) {
                Object[] rowData = {payment.getCheckNumber(), payment.getPaymentDate(), payment.getAmount()};
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
