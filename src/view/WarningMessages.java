package view;

import javax.swing.JOptionPane;

/**
 * @author Migle Adomonyte
 *  
 * Class for displaying warning messages. 
 * 
 * The structure of the code is based on ExampleApp. 
 * 
 */

public class WarningMessages {
	/**
	 * Constructor for creating an instance of WarningMessage. 
	 */
	public WarningMessages() {
	}
	
	/**
	 * Method for displaying a message on the screen. 
	 * 
	 * @param message The message to be displayed. 
	 */
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.PLAIN_MESSAGE);
	}
}
