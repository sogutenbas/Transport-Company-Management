package model;

import java.sql.Date;

/**
 * @author serhatberg
 *         This class represents an order in a system.
 */

public class Order {
	private int orderNumber, customerNumber;
	java.util.Date orderDate;
	java.util.Date requiredDate;
	private java.util.Date shippedDate;
	private String status, comments;

	/**
	 * Constructor for creating an order.
	 * 
	 * @param orderNumber    Order number.
	 * @param orderDate2      Order date.
	 * @param requiredDate2   Required date.
	 * @param shippedDate2    Shipped date.
	 * @param status         Status of the order.
	 * @param comments       Comments for the order.
	 * @param customerNumber Customer number.
	 */
	public Order(int orderNumber, java.util.Date orderDate, java.util.Date requiredDate, java.util.Date shippedDate,
			String status, String comments, int customerNumber) {
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.status = status;
		this.comments = comments;
		this.customerNumber = customerNumber;
	}

	/**
	 * Getter for order number.
	 * 
	 * @return Order number.
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * Setter for order number.
	 * 
	 * @param orderNumber Order number to set.
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * Getter for order date.
	 * 
	 * @return Order date.
	 */
	public java.util.Date getOrderDate() {
		return orderDate;
	}

	/**
	 * Setter for order date.
	 * 
	 * @param orderDate Order date to set.
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * Getter for required date.
	 * 
	 * @return Required date.
	 */
	public java.util.Date getRequiredDate() {
		return requiredDate;
	}

	/**
	 * Setter for required date.
	 * 
	 * @param requiredDate Required date to set.
	 */
	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	/**
	 * Getter for shipped date.
	 * 
	 * @return Shipped date.
	 */
	public java.util.Date getShippedDate() {
		return shippedDate;
	}

	/**
	 * Setter for shipped date.
	 * 
	 * @param shippedDate Shipped date to set.
	 */
	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	/**
	 * Getter for status of the order.
	 * 
	 * @return Status of the order.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Setter for status of the order.
	 * 
	 * @param status Status of the order to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Getter for comments for the order.
	 * 
	 * @return Comments for the order.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Setter for comments for the order.
	 * 
	 * @param comments Comments for the order to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Getter for customer number.
	 * 
	 * @return Customer number.
	 */
	public int getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * Setter for customer number.
	 * 
	 * @param customerNumber Customer number to set.
	 */
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}
}
