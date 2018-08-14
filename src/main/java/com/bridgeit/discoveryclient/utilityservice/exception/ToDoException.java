/**
 * 
 */
package com.bridgeit.discoveryclient.utilityservice.exception;

/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>Custom Exception Class.</b>
 *        </p>
 */
@SuppressWarnings("serial")
public class ToDoException extends Exception {
	private String message;

	/**
	 * @param message
	 */
	public ToDoException(String message) {
		super();
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
