/**
 * 
 */
package org.sivalabs.jscheduler;

/**
 * @author Siva
 *
 */
public class JobSchedulingException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public JobSchedulingException()
	{
	}

	public JobSchedulingException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public JobSchedulingException(String message)
	{
		super(message);
	}

	public JobSchedulingException(Throwable cause)
	{
		super(cause);
	}

}
