package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class OrderInterface extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for creating an order frame.
     */
    public OrderInterface() {
        // Create a new JFrame.
        setTitle("Order Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setResizable(false);

        // Create a new JPanel for storing components.
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBackground(new Color(248, 249, 250));
        setContentPane(panel);

        // Add padding around the content.
        panel.setBorder(new EmptyBorder(80, 120, 80, 120));

        // Buttons for Order Menu.

        JButton buttonViewOrders = new JButton("View Orders");
        buttonViewOrders.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openViewOrdersFrame();
            }
        });
        buttonViewOrders.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel.add(buttonViewOrders);

        JButton buttonAddOrder = new JButton("Add Order");
        buttonAddOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddOrderFrame(); // Implement this frame class
            }
        });
        buttonAddOrder.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel.add(buttonAddOrder);

        JButton buttonUpdateOrder = new JButton("Update Order Status");
        buttonUpdateOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openUpdateOrderStatusFrame();
            }
        });
        buttonUpdateOrder.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel.add(buttonUpdateOrder);

        setVisible(true);
    }
    
    private void openViewOrdersFrame() {
        ViewOrdersFrame viewOrdersFrame = new ViewOrdersFrame();
        viewOrdersFrame.setVisible(true);
    }
    
    private void openUpdateOrderStatusFrame() {
        UpdateOrderStatusFrame updateOrderStatusFrame = new UpdateOrderStatusFrame();
        updateOrderStatusFrame.setVisible(true);
    }
}

