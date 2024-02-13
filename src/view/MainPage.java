package view;

import java.awt.Color;

/**
 * @author Elias Rønningen 
 * 
 * This class represents the main page for the application. 
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainPage extends JFrame{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for creating a main page frame.
	 */
    public MainPage() {
		// Create a new JFrame. 
        setResizable(false);
        setTitle("Transport Company");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        
		// Create a new JPanel for storing components. 
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 249, 250));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Label.
        JLabel lblMainPageGreeting = new JLabel("Transport Company Software");
        lblMainPageGreeting.setFont(new Font("SansSerif", Font.PLAIN, 24));
        lblMainPageGreeting.setBounds(130, 20, 400, 29);
        contentPane.add(lblMainPageGreeting);

        // Add menu buttons. 
        
        JButton buttonCustomers = new JButton("Customers");
		// Action listener. 
        buttonCustomers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new CustomerInterface();
            }
        });
        buttonCustomers.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonCustomers.setBounds(175, 93, 260, 50);
        contentPane.add(buttonCustomers);

        JButton buttonOrders = new JButton("Orders");
		// Action listener. 
        buttonOrders.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new OrderInterface();
            }
        });
        buttonOrders.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonOrders.setBounds(175, 150, 260, 50);
        contentPane.add(buttonOrders);
        
        JButton buttonPayments = new JButton("Payments");
        buttonPayments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openPaymentsFrame(); 
            }
        });
        buttonPayments.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonPayments.setBounds(175, 274, 260, 50);
        contentPane.add(buttonPayments);

        JButton buttonProducts = new JButton("Products");
		// Action listener. 
        buttonProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openProductsFrame();
            }
        });
        buttonProducts.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ProductsFrame();
            }
        });
        buttonProducts.setBounds(175, 212, 260, 50);
        contentPane.add(buttonProducts);
        
        
        JButton buttonEmployees = new JButton("Employees");
        buttonEmployees.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EmployeeInterface();
            }
        });
        buttonEmployees.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonEmployees.setBounds(175, 336, 260, 50);
        contentPane.add(buttonEmployees);
        
        JButton buttonFileManagement = new JButton("File Management");
		// Action listener. 
        buttonFileManagement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openFileManagerFrame();
            }
        });
        buttonFileManagement.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonFileManagement.setBounds(15, 410, 156, 40);
        contentPane.add(buttonFileManagement);
        
        JButton buttonLogOut = new JButton("Log Out");
        //Action Listener. 
        buttonLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openLoginPage();
                dispose();
                // Open the login page
                // openLoginPage();
            }
        });
        buttonLogOut.setBounds(500, 410, 83, 40);
        buttonLogOut.setFont(new Font("SansSerif", Font.PLAIN, 14));
        contentPane.add(buttonLogOut);
        
        setVisible(true);
    }


    private void openFileManagerFrame() {
        FileManagerFrame fileManagerFrame = new FileManagerFrame();
        fileManagerFrame.setVisible(true);
    }
    
    private void openProductsFrame() {
		ProductsFrame productsFrame = new ProductsFrame();
		productsFrame.setVisible(true);
		
	}
    private void openPaymentsFrame() {
		PaymentsFrame paymentsFrame = new PaymentsFrame();
		paymentsFrame.setVisible(true);
    }
    private void openLoginPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }
	
}
