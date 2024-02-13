// LoginPage.java
package view;

import authentication.IDandPasswords;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField userText;
    private JPasswordField passwordText;

    public LoginPage() {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(283, 183);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(180, 80, 80, 25);
        panel.add(btnReset);

        // Add ActionListener to the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = userText.getText();
                String enteredPassword = new String(passwordText.getPassword());

                // Check if the entered credentials are valid
                if (isValidCredentials(enteredUsername, enteredPassword)) {
                    // If valid, close the login page and open the main page
                    dispose();
                    openMainPage();
                } else {
                    // If invalid, show an error message
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add ActionListener to the Reset button
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset the text fields
                userText.setText("");
                passwordText.setText("");
            }
        });

        // Add KeyListener to userText for Enter key
        userText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    passwordText.requestFocus();
                }
            }
        });

        // Add KeyListener to passwordText for Enter key
        passwordText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });
    }

    // Method for opening the main page
    private void openMainPage() {
        MainPage mainPage = new MainPage();
        mainPage.setVisible(true);
    }

    // Method to check if the entered credentials are valid
    private boolean isValidCredentials(String enteredUsername, String enteredPassword) {
        IDandPasswords idAndPasswords = IDandPasswords.getInstance();
        return idAndPasswords.getLoginInfo().containsKey(enteredUsername) &&
                idAndPasswords.getLoginInfo().get(enteredUsername).equals(enteredPassword);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage());
    }
}
