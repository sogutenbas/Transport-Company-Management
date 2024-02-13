package model;

import java.sql.Date;
/**
 * @author eliasronningen
 */

public class Payments {
	private int customerNumber;
	private String checkNumber;
	private Date paymentDate;
	private float amount;
	
	/**
     * Payment object is made through the parameters stored in the database. 
     *
     * @param custumberNumber    Number of the customer the payment belongs to as an int
     * @param checkNumber        Unique identifying String given to each payment
     * @param paymentDate        Date of the payment as yyyy-mm-dd.
     * @param amount       	     Amount paid as a float
     */
	
	public Payments (int custumerNumber, String checkNumber, java.util.Date paymentDate, float amount) {
		
		this.customerNumber = custumerNumber;
		this.checkNumber = checkNumber;
		this.paymentDate = (Date) paymentDate;
		this.amount = amount;
	}
    /**
     * Getter for customerNumber int
     *
     * @return custumerNumber as an int
     */
    public int getCustomerNumber() {
        return customerNumber;
    }

    /**
     *  Setter for customerNumber int
     *
     * @param Desired custoerNumber.
     */
    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }
    
    /**
     * Getter for checkNumber String
     *
     * @return custumerNumber as an int
     */
    public String getCheckNumber() {
        return checkNumber;
    }

    /**
     *  Setter for checkNumber String
     *
     * @param Desired checkNumber
     */
    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }
    
    /**
     * Getter for paymentDate Date as yyyy-mm-dd
     *
     * @return paymentDate yyyy-mm-dd
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     *  Setter for paymentDate Date yyyy-mm-dd
     *
     * @param Desired paymentDate yyyy-mm-dd
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    /**
     * Getter for amount as a float
     *
     * @return amount float
     */
    public float getAmount() {
        return amount;
    }

    /**
     *  Setter for amount float
     *
     * @param Desired amount 
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    
    
    
    
}
