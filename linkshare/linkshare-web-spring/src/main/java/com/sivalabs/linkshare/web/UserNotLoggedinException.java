/**
 * 
 */
package com.sivalabs.linkshare.web;

/**
 * @author skatam
 *
 */
public class UserNotLoggedinException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public UserNotLoggedinException() {
		super();
	}

	public UserNotLoggedinException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotLoggedinException(String message) {
		super(message);
	}

	public UserNotLoggedinException(Throwable cause) {
		super(cause);
	}
	
}
